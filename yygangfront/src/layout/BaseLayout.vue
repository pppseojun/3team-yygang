<template>
    <div id="wapper">
        <Header></Header>

        <RouterView></RouterView>

        <Footer></Footer>
    </div>
</template>

<script setup>
import Footer from '@/components/common/Footer.vue';
import Header from '@/components/common/Header.vue';
</script>

<style scoped>
#wapper{
  min-height: 100%;
  position: relative;
}
Footer{
  bottom: 0px;
  height: 162px;
  position : absolute;
}
</style>