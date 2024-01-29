<script setup lang="ts">
import { getBoard } from '@/api/board'
import { ref, type Ref } from 'vue'
import type { BoardResponseType } from '@/types/board'
import BoardTableHeader from '@/components/molecules/board/BoardTableHeader.vue'
import BoardElement from '@/components/molecules/board/BoardElement.vue'
import MobileBoardElement from '@/components/molecules/board/MobileBoardElement.vue'

const boards: Ref<BoardResponseType[]> = ref([])
getBoard(
    {},
    (response) => {
        const data = response.data
        if (data.status === 'OK') {
            console.log(data.result)
            const dummyData = {
                content: [
                    {
                        boardNo: 1,
                        title: 'title1asdfasdfasdfsdfasdfsdsdfasdfasdfsdfasdfsdsasdfsd',
                        registerAt: '2024-01-29T13:22:09',
                        hit: 1,
                        boardCategory: 'certification',
                        publicYn: true,
                        missionCategory: 'mission',
                        likedCount: 0,
                        writer: '00님의 마니또',
                        writerEmail: '',
                        writerProfileUrl: '',
                        replyCount: 1
                    },
                    {
                        boardNo: 2,
                        title: 'title1asdfasdfasdfsdfasdfsdsdfasdfasdfsdfasdfsdsasdfsd',
                        registerAt: '2024-01-29T13:22:09',
                        hit: 1,
                        boardCategory: 'certification',
                        publicYn: true,
                        missionCategory: 'mission',
                        likedCount: 0,
                        writer: '00님의 마니또',
                        writerEmail: '',
                        writerProfileUrl: '',
                        replyCount: 1
                    },
                    {
                        boardNo: 2,
                        title: 'title1asdfasdfasdfsdfasdfsdsdfasdfasdfsdfasdfsdsasdfsd',
                        registerAt: '2024-01-29T13:22:09',
                        hit: 1,
                        boardCategory: 'certification',
                        publicYn: true,
                        missionCategory: 'mission',
                        likedCount: 0,
                        writer: '00님의 마니또',
                        writerEmail: '',
                        writerProfileUrl: '',
                        replyCount: 1
                    }
                ],
                pageable: {
                    sort: {
                        sorted: false,
                        unsorted: true,
                        empty: true
                    },
                    offset: 0,
                    pageSize: 10,
                    pageNumber: 0,
                    paged: true,
                    unpaged: false
                },
                totalElements: 18,
                last: false, // 마지막 페이지 여부
                totalPages: 2, // 전체 페이지 갯수 총 18건 데이터 중 10개의 요청이므로 2개 페이지 존재
                size: 10, // 페이지 당 출력 갯수
                number: 0,
                numberOfElements: 10, // 요청 페이지에서 조회 된 데이터의 갯수
                sort: {
                    sorted: false,
                    unsorted: true,
                    empty: true
                },
                first: true, // 첫 페이지 여부
                empty: false
            }
            boards.value = dummyData.content
        }
    },
    (error) => console.error(error)
)
</script>

<template>
    <table class="table-auto text-center min-w-[768px] w-full max-w-[1024px] max-md:min-w-0">
        <BoardTableHeader class="max-md:hidden" />
        <tbody>
            <template v-for="(board, i) in boards" :key="board.boardNo">
                <BoardElement :board="board" class="max-md:hidden" />
                <MobileBoardElement :board="board" :class="i == 0 ? 'border-t' : ''" />
            </template>
        </tbody>
    </table>
</template>

<style scoped></style>
