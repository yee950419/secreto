<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import type { BoardDetailResponseType } from '@/types/board'
import BoardWriterInformation from '@/components/molecules/board/BoardWriterInformation.vue'

const board: Ref<BoardDetailResponseType> = ref({
    boardNo: 0,
    title: '',
    content: '',
    writer: '',
    writerEmail: '',
    writerProfileUrl: null,
    registerAt: '',
    hit: 0,
    boardCategory: '',
    publicYn: false,
    missionCategory: '',
    likedCount: 0,
    replyCount: 0
})
onMounted(() => {
    const dummyData = {
        boardNo: 1,
        title: 'title1',
        content: 'content1',
        writer: 'writer1',
        writerEmail: 'writer@naver.com',
        writerProfileUrl: null,
        registerAt: '2022-03-10T13:22:09',
        hit: 12,
        boardCategory: 'notice',
        publicYn: true,
        missionCategory: '선물사주기',
        likedCount: 0,
        replyCount: 123
    }
    board.value = dummyData
})
</script>

<template>
    <div class="">
        <BoardWriterInformation
            :writer="board.writer"
            :writer-email="board.writerEmail"
            :writer-profile-url="board.writerProfileUrl"
            :register-at="board.registerAt"
            :hit="board.hit"
            :liked-count="board.likedCount"
            :reply-count="board.replyCount"
        />
    </div>
</template>

<style scoped></style>
