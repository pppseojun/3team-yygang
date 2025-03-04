package com.beyond3.yyGang.order.service;

import com.beyond3.yyGang.cart.CartOption;
import com.beyond3.yyGang.cart.repository.CartOptionRepository;
import com.beyond3.yyGang.handler.exception.OrderException;
import com.beyond3.yyGang.handler.message.OrderExceptionMessage;
import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.UserExceptionMessage;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.order.Order;
import com.beyond3.yyGang.order.OrderOption;
import com.beyond3.yyGang.order.OrderStatus;
import com.beyond3.yyGang.order.dto.OrderDto;
import com.beyond3.yyGang.order.dto.OrderOptionDto;
import com.beyond3.yyGang.order.dto.OrderResultDto;
import com.beyond3.yyGang.order.dto.RefundDto;
import com.beyond3.yyGang.order.repository.OrderOptionRepository;
import com.beyond3.yyGang.order.repository.OrderRepository;
import com.beyond3.yyGang.pay.PayStatus;
import com.beyond3.yyGang.pay.Payment;
import com.beyond3.yyGang.pay.PersonalAccount;
import com.beyond3.yyGang.pay.repository.PaymentRepository;
import com.beyond3.yyGang.pay.repository.PersonalAccountRepository;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final NSupplementRepository nSupplementRepository;
    private final OrderRepository orderRepository;
    private final OrderOptionRepository orderOptionRepository;
    private final PersonalAccountRepository personalAccountRepository;
    private final PaymentRepository paymentRepository;
    private final CartOptionRepository cartOptionRepository;

    // 단일 상품 주문 생성
    // orderDto : 주문할 상품 아이디와 수량이 담겨 있음
    @Transactional
    public OrderResultDto orderProduct(String email, List<OrderDto> orderDtos) {

        // 해당 사용자 있는지?
        User user = getUserByEmail(email);

        // 계좌가 있는지? -> 계좌가 존재하지 않으면 해당 상품은 장바구니에 등록 / 계좌 등록 페이지로 넘기도록
        PersonalAccount personalAccount = personalAccountRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new UserException(UserExceptionMessage.ACCOUNT_NOT_EXIST));

        Order order = Order.createOrder(user);  // Order를 먼저 생성, order 상태는 PENDING으로 시작
        log.info("Created order {}", order.getOrderID());    // 생성된 order 확인 -> 상태는 Pending + orderId, CreatedDate 정도 생성 => orderOptionList는 아직임

        Order savedOrder = orderRepository.save(order);

        // 위에서 생성한 Order 객체를 가지고 OrderOption 객체 생성
        List<OrderOption> orderOptions = new ArrayList<>();
        for (OrderDto orderDto : orderDtos) {
            // 주문할 상품 가져오기
            NSupplement product = nSupplementRepository.findByproductId(orderDto.getSupplement_id())
                    .orElseThrow(() -> {
                        orderRepository.delete(savedOrder);
                        return new OrderException(OrderExceptionMessage.PRODUCT_NOT_FOUND);
                    });
            log.info("nSupplement {}", product);

            // 재고 확인
            validateOrder(savedOrder, product, orderDto);
            log.info("validate");

            orderOptions.add(OrderOption.createOrderOption(savedOrder, product, orderDto.getCount()));
        }

        int totalPrice = orderOptions.stream().mapToInt(OrderOption::getPrice).sum();
        if(personalAccount.getBalance() - totalPrice < 0){
            orderRepository.delete(savedOrder);
            throw new OrderException(OrderExceptionMessage.INSUFFICIENT_BALANCE);
        }

        savedOrder.setTotalOrderPrice(totalPrice);  // 이후에 업데이트 해도 더티 체킹으로 값이 업데이트됨

        // 생성 orderOptions을 orderOptionRepository에 저장
        orderOptionRepository.saveAll(orderOptions);

        /* ===== 결제 생성 ===== */

        Payment payment = Payment.builder()
                .order(savedOrder)
                .payMethod("Card")
                .totalPrice(savedOrder.getTotalOrderPrice())
                .build();

        // 재고 감소
        for (OrderOption orderOption : orderOptions) {
            NSupplement nSupplement = orderOption.getNSupplement();
            nSupplement.decreaseStockQuantity(orderOption.getQuantity());
        }

        // 계좌 잔액 감소
        personalAccount.decreaseBalance(totalPrice);


        // 결제 상태를 success로 변경
        payment.setStatus(PayStatus.SUCCESS);
        paymentRepository.save(payment);

        // 주문 상태를 PAID로 변경
        savedOrder.setOrderStatus(OrderStatus.PAID);

        List<OrderOptionDto> orderOptionDtos = orderOptions.stream()
                .map(OrderOptionDto::new)
                .toList();

        // 주문 결과 전송
        return OrderResultDto.builder()
                .orderId(savedOrder.getOrderID())
                .orderOptionDtoList(orderOptionDtos)
                .status(OrderStatus.PAID)
                .totalPrice(savedOrder.getTotalOrderPrice())
                .orderDate(savedOrder.getOrderDate())
                .build();
    }


    @Transactional
    public OrderResultDto orderFromCart(String email, Long cartId) {

        // 해당 사용자 있는지 확인
        User user = getUserByEmail(email);

        // 해당 카트 아이디에 상품이 있는지 확인
        List<CartOption> cartOptions = cartOptionRepository.findByCartId(cartId);
        if(cartOptions.isEmpty()){
            throw new OrderException(OrderExceptionMessage.NO_ITEMS_IN_CART);
        }

        List<OrderDto> orderDtos = cartOptions.stream().map(cartOption ->
                        new OrderDto(cartOption.getNSupplement().getProductId(), cartOption.getQuantity())).toList();

        return orderProduct(email, orderDtos);
    }

    @Transactional
    public OrderResultDto getOrderResult(String email, Long orderId) {

        // 해당 주문이 사용자의 주문이 맞는지 확인
        Order order = verifyOrder(email, orderId);

        // 주문 아이디에 따른 상품 주문 내역이 있는지 확인
        List<OrderOption> orderOptions = orderOptionRepository.findByOrderId(order);

        if(orderOptions.isEmpty()){
            throw new OrderException(OrderExceptionMessage.ITEM_NOT_FOUND_IN_ORDER);
        }

        // 주문 결과 전송
        return OrderResultDto.builder()
                .orderId(order.getOrderID())
                .orderOptionDtoList(
                        orderOptions.stream().map(OrderOptionDto::new).toList())
                .status(order.getOrderStatus())
                .totalPrice(order.getTotalOrderPrice())
                .orderDate(order.getOrderDate())
                .build();
    }

    @Transactional
    public RefundDto cancelOrder(String email, Long orderId) {

        // user 가져오기
        User user = getUserByEmail(email);
        // orderId가 email 사용자의 것이 맞는지 확인
        Order order = verifyOrder(email, orderId);

        // 계좌 찾기
        PersonalAccount personalAccount = personalAccountRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new UserException(UserExceptionMessage.ACCOUNT_NOT_EXIST));
        log.info("personalAccount{}", personalAccount);

        // 결제 정보 찾기
        Payment payment = paymentRepository.findByOrderId(order)
                .orElseThrow(() -> new OrderException(OrderExceptionMessage.CANNOT_FOUND_PAY_INFO));
        log.info("payment{}", payment);

        // order 상태가 IN_TRANSIT, DELIVERED 인 경우 주문 취소 불가
        if(order.getOrderStatus() == OrderStatus.IN_TRANSIT || order.getOrderStatus() == OrderStatus.DELIVERED){
            throw new OrderException(OrderExceptionMessage.CANNOT_CANCEL_ORDER);
        }

        // 주문 취소의 경우 -> 재고 증가 / 잔고 증가 / OrderOption 삭제
        List<OrderOption> orderOptions = orderOptionRepository.findByOrderId(order);
        for (OrderOption orderOption : orderOptions) {
            // 해당 상품을 찾지 못하는 경우 -> 주문 후 상품이 삭제된 경우 -> 그냥 취소 못하게 막자
            NSupplement product = nSupplementRepository.findByproductId(orderOption.getNSupplement().getProductId())
                    .orElseThrow(() -> new OrderException(OrderExceptionMessage.PRODUCT_NOT_FOUND));

            // 재고 증가
            product.increaseStockQuantity(orderOption.getQuantity());
        }
        // payment 상태 -> CANCELLED로 바꾸기
        payment.setStatus(PayStatus.CANCELLED);

        // order 상태 -> CANCELLED로 바꾸기
        order.setOrderStatus(OrderStatus.CANCELLED);

        // 전체 금액 만큼 계좌 잔고 증가
        personalAccount.increaseBalance(order.getTotalOrderPrice());
        // orderOption에서 제거
        orderOptionRepository.deleteAll(orderOptions);

        return new RefundDto(orderId, order.getTotalOrderPrice(), order.getOrderStatus());
    }

    // 주문이 유효한지 확인
    private void validateOrder(Order savedOrder, NSupplement product, OrderDto orderDto) {
        // 재고 부족 시 예외처리
        if (product.getStockQuantity() < orderDto.getCount()) {
            orderRepository.delete(savedOrder);
            throw new OrderException(OrderExceptionMessage.OUT_OF_STOCK);
        }
    }

    public Order verifyOrder(String email, Long orderId){
        // email을 토대로 사용자 받아오기
        User user = getUserByEmail(email);

        // 주문 아이디로 조회한 user랑 email로 얻은 user랑 같은지 확인
        Order byOrder = orderRepository.findByOrderID(orderId)
                .orElseThrow(() -> new OrderException(OrderExceptionMessage.ORDER_NOT_EXIST));

        if(byOrder.getUser().equals(user)){
            return byOrder;
        }
         else {
             throw new OrderException(OrderExceptionMessage.ORDER_NOT_EXIST);
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserExceptionMessage.USER_NOT_FOUND));
    }
}