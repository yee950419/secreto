<script setup lang="ts">
import BoardList from '@/components/pages/board/BoardListPage.vue'
import BoardDetail from '@/components/pages/board/BoardDetailPage.vue'
import BoardWrite from '@/components/pages/board/BoardWritePage.vue'
import BoardHeader from '@/components/molecules/board/BoardHeader.vue'
import LineAtom from '@/components/atoms/LineAtom.vue'

import { useRouter } from 'vue-router'
const router = useRouter()
</script>

<template>
    <div class="flex flex-1 flex-col justify-start mt-8">
        <!-- <BoardHeader
            class="ms-6"
            @notice-button-handle="
                () => router.push({ name: 'game-board', query: { boardCategory: 'NOTICE' } })
            "
            @certificate-button-handle="
                () => router.push({ name: 'game-board', query: { boardCategory: 'CERTIFICATE' } })
            "
            @boast-button-handle="
                () => router.push({ name: 'game-board', query: { boardCategory: 'BOAST' } })
            "
        ></BoardHeader>
        <LineAtom custom-class="my-4 border-A805DarkGrey" /> -->
        <div class="flex justify-center">
            <BoardList />
            <!-- 
                <BoardDetail /> -->
            <!-- <BoardWrite /> -->
        </div>
    </div>
</template>

<style scoped></style>
