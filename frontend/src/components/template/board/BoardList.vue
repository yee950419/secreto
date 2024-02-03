<script setup lang="ts">
import { getBoard } from '@/api/board'
import { watch, ref, type Ref, computed, onMounted } from 'vue'
import type { BoardRequestType, BoardResponseType } from '@/types/board'
import BoardSearchBox from '@/components/organisms/board/BoardSearchBox.vue'
import BoardTableHeader from '@/components/molecules/board/BoardTableHeader.vue'
import BoardElement from '@/components/molecules/board/BoardElement.vue'
import MobileBoardElement from '@/components/molecules/board/MobileBoardElement.vue'
import { Pagination } from 'ant-design-vue'
import { useRoute } from 'vue-router'
import router from '@/router'
const route = useRoute()
const props = defineProps(['roomNo'])
const boards: Ref<BoardResponseType[]> = ref([])
const boardCategory = computed(() => {
    return String(route.query.boardCategory)
})
const boardRequest = ref<BoardRequestType>({
    roomNo: props.roomNo,
    boardCategory: boardCategory.value,
    title: null,
    content: null,
    writer: null,
    page: 0,
    size: 10
})
const current: Ref<number> = ref(1)
const total: Ref<number> = ref(0)

const loadBoardData = () => {
    boardRequest.value.boardCategory = boardCategory.value
    getBoard(
        boardRequest.value,
        (response) => {
            const data = response.data
            if (data.status === 'OK') {
                console.log(data.result)
                const pageable = data.result.pageable
                boards.value = data.result.content
                boardRequest.value.page = pageable.pageNumber
                boardRequest.value.size = pageable.pageSize
                total.value = data.result.totalElements
            }
        },
        (error) => console.error(error)
    )
}

const boardDetail = (boardNo: number): void => {
    router.push({
        name: 'game-board-post',
        query: { postId: boardNo }
    })
    alert(boardNo)
}

onMounted(() => {
    loadBoardData()
})
watch(current, () => {
    loadBoardData()
})
watch(boardCategory, () => {
    boardRequest.value.page = 0
    current.value = 1
    loadBoardData()
})
</script>

<template>
    <div class="flex flex-col w-full md:min-w-[768px] max-w-[1080px] max-md:min-w-0 items-center">
        <BoardSearchBox class="max-md:px-[20px] mb-[30px]" />
        <!-- pc -->
        <table class="table-auto text-center max-md:hidden w-full">
            <BoardTableHeader class="max-md:hidden" />
            <tbody>
                <BoardElement
                    v-for="board in boards"
                    :key="board.boardNo"
                    :board="board"
                    @click="() => boardDetail(board.boardNo)"
                />
            </tbody>
        </table>

        <!-- mobile -->
        <template v-for="(board, i) in boards" :key="board.boardNo">
            <MobileBoardElement :board="board" :class="i == 0 ? 'border-t' : ''" />
        </template>
        <Pagination
            class="my-[30px]"
            v-model:current="current"
            :total="total"
            :page-size="boardRequest.size"
            show-less-items
            :showSizeChanger="false"
            @change="(page, pageSize) => (boardRequest.page = page - 1)"
        />
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
