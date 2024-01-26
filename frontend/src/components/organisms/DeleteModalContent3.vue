<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import InputBox from '../molecules/InputBox.vue'
import type { DataHandler, Handler } from '@/types/common'
import { ref, type Ref } from 'vue'

const emit = defineEmits(['submitButtonHandle'])
const inputRef: Ref<string> = ref('')
const passwordInputChangeHandler: DataHandler<string> = (password: string) => {
    inputRef.value = password
}
const submitButtonClick: Handler = () => {
    emit('submitButtonHandle', inputRef.value)
}
</script>

<template>
    <div class="flex flex-col justify-center items-center">
        <TextAtom custom-class="text-[48px] font-Iceland text-A805Red">WARN</TextAtom>
        <TextAtom custom-class="text-[24px] my-[10px] text-center"
            >탈퇴하시려면<br />비밀번호를 입력해주세요</TextAtom
        >
        <div class="flex flex-col justify-center items-center">
            <InputBox
                type="password"
                @input-change="passwordInputChangeHandler"
                custom-class="input-box-style-2 line-claret w-[250px] h-[40px] my-[10px]"
            />
            <ButtonAtom
                custom-class="button-style-4 button-white button-shadow w-[250px] mx-[10px] mt-[10px] mb-[20px]"
                @button-click="submitButtonClick"
                >탈퇴하기</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
