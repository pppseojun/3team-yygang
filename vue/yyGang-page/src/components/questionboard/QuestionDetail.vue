<template>
    <div class="q-box border mb-5">
            <div class="question mt-4">
                <div class="q-title fw-bold fs-2 mb-3">
                    <p>{{ questionData.qboardTitle }}</p>
                </div>
                <div class="fs-5"> 
                    <p>{{ questionData.qboardContent }}</p>
                </div>
            </div> 
            <div class="q-info row">
                <div class="d-flex-column col">
                    <div class="createAt">
                        작성일 :{{ questionData.qboardDate }}
                    </div>
                    <div class="count">
                        조회수 :{{ questionData.viewCount }}
                    </div>
                </div>
                <div class="col text-end">
                    <button class="btn btn-success rounded-pill px-3 me-1" type="button">
                        <RouterLink class="text-decoration-none text-white" :to="{name:'addanswer'}">답글달기</RouterLink>
                    </button>
                </div>
            </div>
        </div>
</template>

<script setup>

import { defineProps } from 'vue';

const props = defineProps({
    questionData: {
        type: Object,
        required: true
    }
});

</script>

<style scoped>
.q-box{
    width: 90%;
    margin: 0 auto;
    height: 500px;
    border-radius: 20px 0px 20px 0px;
    border-color: #BDBDBD;
    box-shadow: 5px 5px 10px 0 lightgray;   
}
.question{
    width: 80%;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    height: 80%;
}

.q-info, .a-info, .a-content, .helpful, .a-title{
    width: 80%;
    margin: 0 auto;
}

.a-box{
    width: 90%;
    margin: 0 auto;
    height: 500px;
    border-radius: 20px 0px 20px 0px;
    border-color: #BDBDBD;
    box-shadow: 5px 5px 10px 0 lightgray;   
}


.a-title-box{
    /* background-color: #B6F9D5; */
    /* background-color: #A8E063; */
    background-color: #E8F5E9;
    height: 100px;
}

.doctor-icon{
    width: 30px;
    height: 30px;
}

.helpful-text{
    margin: auto 0;
}

</style>