package com.beyond3.yyGang.nsupplement.controller;
import com.beyond3.yyGang.nsupplement.dto.NSupplementModifyDto;
import com.beyond3.yyGang.nsupplement.service.NSupplementService;
import com.beyond3.yyGang.nsupplement.dto.NSupplementRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/nsupplement")
@RequiredArgsConstructor
public class NSupplementController {

    private final NSupplementService nSupplementService;

    @PostMapping
    @Operation(summary = "상품 등록", description = "SELLER 만 상품 등록이 가능하다.")
    public ResponseEntity<String> register(
            Principal principal,
            @RequestBody NSupplementRegisterDto nSupplementRegisterDto) {

        String email = principal.getName();
        nSupplementService.registerProduct(email, nSupplementRegisterDto);

        return ResponseEntity.ok("상품 등록이 완료되었습니다.");
    }

    @GetMapping("/info")
    @Operation(summary = "상품 확인", description = "판매자만 상품 조회 가능 / 본인이 등록한 상품만 조회 가능")
    // Seller 입장에서 등록한 상품들 확인
    public ResponseEntity<List<NSupplementRegisterDto>> info(
            Principal principal
    ) {
        String email = principal.getName();
        List<NSupplementRegisterDto> allNSupplements = nSupplementService.getAllNSupplements(email);

        return ResponseEntity.ok(allNSupplements);
    }

    @DeleteMapping("/{nSupplementId}")
    @Operation(summary = "상품 삭제", description = "해당 상품을 등록한 판매자만 삭제 가능")
    public ResponseEntity<String> delete(
            Principal principal,
            @PathVariable("nSupplementId") Long nSupplementId
    ){
        String email = principal.getName();
        nSupplementService.deleteProduct(email, nSupplementId);

        return ResponseEntity.ok("상품 삭제가 완료되었습니다.");
    }

    @PostMapping("/{nSupplementId}")
    @Operation(summary = "상품 수정", description = "해당 상품을 등록한 판매자만 수정 가능")
    public ResponseEntity<String> modify(
            Principal principal,
            @PathVariable("nSupplementId") Long nSupplementId,
            @RequestBody NSupplementModifyDto nSupplementModifyDto
    ){
        String email = principal.getName();
        nSupplementService.modifyProduct(email, nSupplementId,nSupplementModifyDto);

        return ResponseEntity.ok("상품 삭제가 완료되었습니다.");
    }
}
