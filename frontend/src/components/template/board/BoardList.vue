<script setup lang="ts">
import { getBoard } from '@/api/board'
import { ref, type Ref, onMounted } from 'vue'
import type { BoardResponseType } from '@/types/board'
import BoardHeader from '@/components/molecules/board/BoardHeader.vue'
import BoardSearchBox from '@/components/organisms/board/BoardSearchBox.vue'
import BoardTableHeader from '@/components/molecules/board/BoardTableHeader.vue'
import BoardElement from '@/components/molecules/board/BoardElement.vue'
import MobileBoardElement from '@/components/molecules/board/MobileBoardElement.vue'
import LineAtom from '@/components/atoms/LineAtom.vue'

const boards: Ref<BoardResponseType[]> = ref([])
getBoard(
    {},
    (response) => {
        const data = response.data
        if (data.status === 'OK') {
            console.log(data.result)
        }
    },
    (error) => console.error(error)
)

onMounted(() => {
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
                boardNo: 3,
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
                boardNo: 3,
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
                boardNo: 3,
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
                boardNo: 3,
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
                boardNo: 3,
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
                boardNo: 3,
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
                boardNo: 3,
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
                boardNo: 3,
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
})

const current = ref(1)
</script>

<template>
    <div class="bg-A805RealWhite flex flex-col flex-1 max-md:w-full items-center">
        <BoardHeader
            class="w-full min-w-[768px] max-w-[1080px] max-md:min-w-0 max-md:hidden py-[20px] ps-[10px]"
        />
        <LineAtom class="mb-[30px]" />
        <div class="flex flex-col w-full md:min-w-[768px] max-w-[1080px] max-md:min-w-0">
            <BoardSearchBox class="max-md:px-[20px] mb-[30px]" />

            <!-- pc -->
            <table class="table-auto text-center max-md:hidden">
                <BoardTableHeader class="max-md:hidden" />
                <tbody>
                    <BoardElement v-for="board in boards" :key="board.boardNo" :board="board" />
                </tbody>
            </table>

            <!-- mobile -->
            <template v-for="(board, i) in boards" :key="board.boardNo">
                <MobileBoardElement :board="board" :class="i == 0 ? 'border-t' : ''" />
            </template>
        </div>
        <a-pagination class="my-[30px]" v-model:current="current" :total="50" show-less-items />
    </div>
</template>

<style scoped>
:deep(.ant-pagination-item-active) {
    border-color: #ac87c5;
}
:deep(.ant-pagination-item-active) a {
    color: #ac87c5;
}

:deep(.ant-pagination-item-active:hover) {
    border-color: #756ab6;
}
:deep(.ant-pagination-item-active:hover) a {
    color: #756ab6;
}
</style>
