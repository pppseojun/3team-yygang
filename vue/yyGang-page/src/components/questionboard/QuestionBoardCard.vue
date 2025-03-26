<template>
 <div>
        <div  v-for="(question, index) in questionboard" :key="question.qboardId" class="border mb-3" id="qcard">
            <div id="qcard-content" class="mt-3 mb-4" @click.stop="emit('item-click', question.qboardId);">
                <div id="qcard-question" class="mb-3">
                        <div class="question-item">
                            <div id="qcard-question-title" class="fs-2 d-flex">
                                <span id="q" class="fw-bold">Q. &nbsp</span>
                                <p class="">{{question.qboardTitle}}</p>
                            </div>
                            <div id="qcard-question-content">
                                <p>{{ question.qboardContent }}</p>
                            </div>
                        </div>

                    <div v-for="(answer, index) in question.answers" :key="index" id="qcard-question-answer" class="d-flex">
                        <img src="https://yygang-bucket.s3.ap-northeast-2.amazonaws.com/doctor.png" alt="의사아이콘" id="answer-icon" class="me-2">
                        <!-- <p id="qcard-question-answer-content">아 그럴때는 돼지감자를 우려 드시는게 최곱니다......</p> -->
                        <p id="qcard-question-answer-content">{{answer.answerContent}}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>


<script setup>

    import { onMounted, ref } from 'vue';
    import apiClient from '@/api';

    const props = defineProps({
        questionboard: {
            type : Array,
            required: true,
        },
    });

    const answers = ref([]); 

    const fetchAnswers = async (qboardId) => {
    try {
        const response = await apiClient.get(`/qboard/${qboardId}/answers?page=0&size=10`);
        console.log(response.data.qboardContent)

        console.log(response.data);
        return response.data.items;
        } catch (error) {
            console.error(`답변 불러오기 실패 (qboardId: ${qboardId})`, error);
        }
    };

    const emit = defineEmits(['item-click']);


    onMounted(() => {
    props.questionboard.forEach(async (question) => {
        const fetchedAnswers = await fetchAnswers(question.qboardId);
        question.answers = fetchedAnswers || []; 
    });
});

</script>

<style scoped>


#qcard{
    width: 80%;
    margin: 0 auto;
    border-radius: 20px 0px 20px 0px;
    border-color: #BDBDBD;
    box-shadow: 5px 5px 10px 0 lightgray;   
}

#qcard-content{
    width: 90%;
    margin: 0 auto;
}

#q{
    color: #6AB456;
}

#qcard-question-answer{
    height: 30px;
}

#answer-icon{
    width: 30px;
    border: 1px solid;
    border-radius: 50%;
    
}
#qcard-question-answer-content{
    margin: auto 0;
}

</style>