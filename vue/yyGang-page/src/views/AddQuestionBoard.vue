<template>
    <div>
        <div class="header border-bottom mb-4 d-flex justify-content-between pb-3 mt-2">
            <span class="fs-1 fw-bold pb-1">약 질문</span>
        </div>
        <PostForm></PostForm>
    </div>
</template>

<script setup>
import PostForm from '@/components/forms/PostForm.vue';


</script>

<style scoped>
.header{
    width: 90%;
    margin: 0 auto;
}


</style>