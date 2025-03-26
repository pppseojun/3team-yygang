<template>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous"
                    @click.prevent="changePage(pageInfo.currentPage - 1);">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <li v-for="page in generateSequence" :key="page" class="page-item">
                <a class="page-link" :class="{active: pageInfo.currentPage === page}" href="#"
                    @click.prevent="changePage(page);">
                    {{ page }}
                </a>
            </li>

            <li class="page-item">
                <a class="page-link" href="#" aria-label="Next"
                    @click.prevent="changePage(pageInfo.currentPage + 1);">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</template>

<script setup>
import { computed } from 'vue';

    const props = defineProps({
        pageInfo: {
            type: Object,
            required: true
        }
    });

    /*
    전체 페이지의 수

    totalCount = 100, listLimit = 10
    100 / 10 = 10.0		=> 10페이지
    101 / 10 = 10.1		=> 11페이지
    103 / 10 = 10.3		=> 11페이지
    109 / 10 = 10.9		=> 11페이지
    110 / 10 = 11.0		=> 11페이지
    111 / 10 = 11.1		=> 12페이지
    */
    const totalPages = computed(
        () => Math.ceil(props.pageInfo.totalCount / props.pageInfo.listLimit)
    );

    /*
    페이징 된 페이지 중 시작 페이지	
    < 1 2 3 4 5 6 7 8 9 10 >
    < 11 12 13 14 15 16 17 18 19 20 >
    < 21 22 23 24 25 26 27 28 29 30 >
    
    1, 11, 21, 31, .... => (10 * n) + 1 (n >= 0)
    
    1 ~ 10 : n = 0
    11 ~ 20 : n = 1
    21 ~ 30 : n = 2
    31 ~ 40 : n = 3 
    .... 
    n = (currentPage - 1) / pageLimit
    
    (10 * ((currentPage - 1) / pageLimit)) + 1 (n >= 0)
    */
    const startPage = computed(
        () => (props.pageInfo.pageLimit * Math.floor((props.pageInfo.currentPage - 1) / props.pageInfo.pageLimit)) + 1
    );

    // 페이징 된 페이지 중 마지막 페이지
    // 10, 20, 30, 40, .... 
    const endPage = computed(
        () => {
            let end = startPage.value + props.pageInfo.pageLimit - 1;

            return end > totalPages.value ? totalPages.value : end;
        }
    );

    const generateSequence = computed(
        () => {
            const sequence = [];

            for (let i = startPage.value; i <= endPage.value; i++) {
                sequence.push(i);
            }

            return sequence;
        }
    );

    const emit = defineEmits(['change-page']);

    const changePage = (page) => {
        emit('change-page', { page, totalPages: totalPages.value });
    };
</script>

<style>
.page-link {
  color: #000; 
  background-color: #fff;
  border: 1px solid #ccc; 
}

.page-item.active .page-link {
 z-index: 1;
 color: #555;
 font-weight:bold;
 background-color: #f1f1f1;
 border-color: #ccc;
 
}

.page-link:focus, .page-link:hover {
  color: #000;
  background-color: #fafafa; 
  border-color: #ccc;
}
</style>