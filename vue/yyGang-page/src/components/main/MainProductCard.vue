<template>
    <div class="car mt-3">
        <div class="carousel__wrapper">
            <Carousel v-bind="config" class="carousel-container">
                <!-- <Slide v-for="image in images" :key="image.id" class="me-2"> -->
                    <Slide v-for="image in images" :key="image.id" class="carousel-slide">
                    <!-- <img :src="image.url" alt="image" /> -->
                     <MainProduct class="mp"></MainProduct>
                    </Slide>
                <template #addons>
                    <Navigation />
                </template>
            </Carousel>
        </div>
    </div>
</template>

<script setup>
import 'vue3-carousel/carousel.css'
import { Carousel, Slide, Navigation } from 'vue3-carousel'
import MainProduct from '@/components/main/MainProduct.vue';

const images = Array.from({ length: 3 }, (_, index) => ({
  id: index + 1,
  url: `https://picsum.photos/seed/${Math.random()}/800/600`,
}))

const config = {
  height: 350,
  itemsToShow: 3,
  gap: 70,
  snapAlign: 'center',
}
</script>

<style>
.car{
    display: flex;
    justify-content: center;
}
.mp{
    margin-left: 250px;
}
.carousel-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}

:root {
  background-color: #242424;
}

.carousel {
  --vc-nav-background: rgba(255, 255, 255, 0.7);
  --vc-nav-border-radius: 100%;
}

img {
  border-radius: 8px;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.carousel__wrapper {
  resize: horizontal;
  /* border: 2px dashed gray; */
  overflow: auto;
  max-width: 90%;
  padding: 2px;
  display: flex;
  justify-content: center;
}



.carousel-slide {   
  max-width: 250px; /* 각 슬라이드 최대 너비 */
}

.carousel__viewport{
    width: 1200px;
}




</style>