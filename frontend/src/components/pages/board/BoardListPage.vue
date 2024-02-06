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
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { EditOutlined } from '@ant-design/icons-vue'
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
        query: { postNo: boardNo }
    })
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
    <div class="flex flex-col w-full md:min-w-[568px] max-w-[1200px] max-md:min-w-0 items-center">
        <!-- pc -->
        <table class="table-auto text-center max-md:hidden w-full min-h-[300px]">
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
        <div class="min-h-[50px]">
            <template v-for="(board, i) in boards" :key="board.boardNo">
                <MobileBoardElement
                    :board="board"
                    :class="i == 0 ? 'border-t' : ''"
                    @click="() => boardDetail(board.boardNo)"
                />
            </template>
        </div>
        <Pagination
            class="my-[30px]"
            v-model:current="current"
            :total="total"
            :page-size="boardRequest.size"
            show-less-items
            :showSizeChanger="false"
            @change="(page, pageSize) => (boardRequest.page = page - 1)"
        />
        <div class="flex w-full justify-between px-4 items-center max-md:flex-col mb-5">
            <div></div>
            <BoardSearchBox />
            <ButtonAtom
                custom-class="button-style-4 button-claret text-[18px] w-[95px] font-bold flex justify-center items-center gap-[5px] max-md:w-full h-[35px] max-md:mt-7 max-md:rounded-none"
                @button-click="() => router.push({ name: 'game-board-write' })"
                ><EditOutlined /><span>글쓰기</span></ButtonAtom
            >
        </div>
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
