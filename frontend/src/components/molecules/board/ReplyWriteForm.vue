<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ref } from 'vue'
const textArea = ref<HTMLElement | null>(null)

const resize = () => {
    if (textArea.value) {
        textArea.value.style.height = 'auto'
        textArea.value.style.height = textArea.value.scrollHeight + 'px'
    }
}
</script>

<template>
    <div class="flex flex-col flex-1 border-2 rounded-lg p-3 text-[16px]">
        <TextAtom custom-class="font-bold"> 유저 닉네임 (이메일) </TextAtom>
        <textarea
            ref="textArea"
            class="min-h-[40px] resize-none focus:outline-none my-2"
            placeholder="댓글을 남겨보세요"
            @input="resize"
        ></textarea>
        <ButtonAtom custom-class="button-white rounded-md font-bold"> 등록 </ButtonAtom>
    </div>
</template>

<style scoped></style>
