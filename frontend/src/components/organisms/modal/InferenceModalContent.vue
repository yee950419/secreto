<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import InputBox from '@/components/molecules/common/InputBox.vue'
import type InputBoxVue from '@/components/molecules/common/InputBox.vue'
import PaticipateProfile from '@/components/molecules/game/PaticipateProfile.vue'
import type { DataHandler, Handler } from '@/types/common'
import { ref } from 'vue'
// 유저 정보 props
defineProps(['userInfo'])

const memo = ref<string>('')
const submitHandler: Handler = () => {
    console.log(memo.value)
}
const inferenceChanged: DataHandler<'O' | 'X' | '?'> = (guess) => {
    inference.value = guess
}
// 추리 내용 (API에 맞춰 변경 예정)
const inference = ref<'O' | 'X' | '?'>('O')
</script>

<template>
    <div class="flex flex-col justify-center items-center w-full gap-2 overflow-y-auto">
        <div
            name="user=profile-container"
            class="flex content-center gap-3 max-md:flex-col items-center"
        >
            <PaticipateProfile custom-class="max-md:w-"></PaticipateProfile>
            <div name="inference-container" class="flex flex-col justify-between md:h-[200px]">
                <div class="md:w-[380px]">
                    <span class="text-A805Blue text-[16pt]"> {{ userInfo.roomNickname }}</span> 님을
                    마니또로 예측하시겠습니까?
                </div>
                <div name="buttons-container" class="flex justify-between">
                    <ButtonAtom
                        class="button-style-4 w-[100px] button-blue"
                        :class="{ 'bg-A805Blue': inference === 'O' }"
                        @button-click="inferenceChanged('O')"
                        >O</ButtonAtom
                    >
                    <ButtonAtom
                        class="button-style-4 w-[100px] button-claret"
                        :class="{ 'bg-A805Violet': inference === 'X' }"
                        @button-click="inferenceChanged('X')"
                        >X</ButtonAtom
                    >
                    <ButtonAtom
                        class="button-style-4 w-[100px] button-claret"
                        :class="{ 'bg-A805Violet': inference === '?' }"
                        @button-click="inferenceChanged('?')"
                        >?</ButtonAtom
                    >
                </div>
            </div>
        </div>
        <div name="memo-container" class="w-full">
            <textarea
                v-model="memo"
                class="w-full md:h-[200px] rounded-[10px] p-5 focus:outline-none"
                placeholder="마니또로 예측한 이유를 작성해주세요."
            ></textarea>
        </div>
        <div name="submit-button-container" class="flex justify-end w-full">
            <ButtonAtom class="button-style-4 button-claret" @button-click="submitHandler"
                >전송</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
