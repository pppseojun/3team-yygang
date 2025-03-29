<template>
    <div class="product-card border d-flex flex-column ">
        <div class="product-card text-center position-relative h-100">
            <img src="../../../public/영양제1.avif" class="product-img mt-5 w-50 h-50 align-self-center">
            <!-- 좋아요 버튼 -->
            <button class="icon-btn position-absolute top-0 end-0 m-3">♥</button>
            <!-- 상품명 -->
            <h6 class="mb-1 mt-3">라이프 익스텐션</h6>
            <!-- 가격 -->
            <p class="text-muted mb-2">15,000 원</p>
            <!-- 장바구니 버튼 -->
            <button class="icon-btn">🛒</button>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>

/* 제품 카드 */
.product-card{
    height: 350px;
    width: 250px;
    border-radius: 20px 0px 20px 0px;
    box-shadow: 5px 5px 10px 0 lightgray;
    /* align-items: center */
}
.icon-btn {
  background: none;
  border: none;
  color: #aaa;
}

.icon-btn:hover {
  color: #000;
}

/* .product-img{

} */

</style>