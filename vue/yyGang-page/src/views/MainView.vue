<template>
    <div>
        <main>
            <img src="https://yygang-bucket.s3.ap-northeast-2.amazonaws.com/mainImg1.png" alt="mainImg" class="main-img-1 container">
            <MainCard></MainCard>
            <MainProductCard ></MainProductCard>
        </main>
    </div>
</template>

<script setup>

import MainCard from '@/components/main/MainCard.vue';
import MainProduct from '@/components/main/MainProduct.vue';
import MainProductCard from '@/components/main/MainProductCard.vue';


</script>

<style scoped>



.main-img-1{
    padding: 0px;
    top: 0px;
}


</style>