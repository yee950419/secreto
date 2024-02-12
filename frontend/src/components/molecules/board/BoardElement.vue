<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue'
import { BoardCategory } from '@/types/board'
import { convertStringToRegistrationDate } from '@/utils/date'
defineProps(['board'])
</script>

<template>
    <tr class="cursor-pointer border-b h-[40px] hover:bg-A805LightGrey max-md:hidden">
        <td>{{ board.boardNo }}</td>
        <td class="text-left flex justify-left items-center leading-[40px]">
            <TextAtom custom-class="truncate max-w-[400px]">
                <span
                    v-if="board.boardCategory === BoardCategory.CERTIFICATE"
                    class="text-A805DarkGrey"
                >
                    [{{ board.userMission }}]</span
                >
                {{ board.title }}
            </TextAtom>
            <TextAtom custom-class="text-A805Red font-bold ms-1">
                [{{ board.replyCount }}]</TextAtom
            >
        </td>
        <td>{{ board.writer }}</td>
        <td>{{ convertStringToRegistrationDate(board.registerAt) }}</td>
        <td>{{ board.hit }}</td>
        <td>{{ board.likedCount }}</td>
    </tr>
</template>

<style scoped></style>
