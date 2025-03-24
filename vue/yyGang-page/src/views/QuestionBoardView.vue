<template>
    <!-- 게시글 전체 목록 페이지 -->
    <div>
        <div class="border-bottom mb-4 d-flex justify-content-between" id="header">
            <span class="fs-1 fw-bold pb-1">약 질문</span>
            <button class="postbtn btn btn-success rounded-pill px-3" type="button">
                <RouterLink class="text-decoration-none text-white" :to="{name:'addanswer'}">등록하기</RouterLink>
            </button>
        </div>
       <QuestionBoardCard :questionboard="questionboard" :answer="answer" @item-click="itemClick"></QuestionBoardCard>
    </div>
</template>

<script setup>
    import apiClient from '@/api';
    import { onMounted, reactive, ref, watch } from 'vue';
    import { onBeforeRouteUpdate, useRoute, useRouter } from 'vue-router';
    import QuestionBoardCard from '@/components/questionboard/QuestionBoardCard.vue'

    const questionboard = ref([]);
    const answer = ref([]);
    
    const currentRoute = useRoute();
    const router = useRouter();
    const pageInfo = reactive({
        // 값을 정수로 변환하고 실패하면 1을 기본값으로 사용
        currentPage: parseInt(currentRoute.query.page) || 0,
        totalCount: 0, // 전체 데이터 수
        pageLimit: 5, // 페이지네이션에 보이는 페이지의 수
        listLimit: 0 // 한 페이지에 표시될 리스트의 수
    });    

    const fetchQuestionBoard = async (page) => {
    try {
        const response = await apiClient.get(`/qboard?page=${page}&size=10`);
        questionboard.value = response.data;
        pageInfo.totalCount = response.data.totalCount;
    } catch (error) {
        console.error("API 요청 중 오류 발생:", error);
    }};

    // const fetchAnswer = async (id, page) => {
    // try {
    //     const response = await apiClient.get(`/qboard/${id}/answers?page=${page}&size=10`);
    //     answer.value = response.data;
    //     pageInfo.totalCount = response.data.totalCount;
    //     console.log("answer"+response.data);  // 응답 데이터 확인
    //     console.log("answer"+response.data.items);  // 응답 데이터 확인

    // } catch (error) {
    //     console.error("API 요청 중 오류 발생:", error);
    // }
    // };

    const changePage = ({page, totalPages}) => {
        if (page >= 1 && page <= totalPages) {
            router.push({name: 'questionboard', query: {page}});
        }
    };

    onBeforeRouteUpdate((to, form) => {
        pageInfo.currentPage = parseInt(to.query.page) || 1;

        fetchQuestionBoard(pageInfo.currentPage);
    });
    onMounted(() => {
        fetchQuestionBoard(pageInfo.currentPage);
        // fetchAnswer(pageInfo.currentPage, currentRoute.params.id )
    });

    const itemClick = (id) => {
    console.log("클릭한 질문 번호:", id);
    if (!id) {
        console.error("잘못된 ID 값:", id);
        return;
    }
    router.push({ name: 'qboardDetail', params: { id } });
};

</script>

<style scoped>

#header{
    width: 90%;
    margin: 0 auto;
}

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

.postbtn{
    margin: auto 0;
    
}
</style>