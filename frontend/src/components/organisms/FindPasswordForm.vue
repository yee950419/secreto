<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ref } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import type { PasswordFindMailRequest } from '@/types/user'

const passwordFindMailRequest: PasswordFindMailRequest = ref({
    email: ''
})
const emailInputChangeHandler: DataHandler<string> = (data: string) => {
    passwordFindMailRequest.value.email = data
}
const findPasswordButtonHandler: Handler = () => {
    alert('find password :' + passwordFindMailRequest.value.email)
}
const myPageButtonHandler: Handler = () => {
    alert('go to my page')
}
</script>

<template>
    <div
        class="bg-A805RealWhite flex flex-col justify-between items-center h-[500px] w-full max-w-[400px] py-[20px] px-[40px]"
    >
        <div class="flex flex-col items-center">
            <TextAtom custom-class="text-[44px] font-Iceland">Find Password</TextAtom>
            <TextAtom custom-class="text-[14px]">기존에 사용하던 이메일을 입력해주세요.</TextAtom>
        </div>
        <InputBox
            label="이메일"
            type="text"
            label-class="ps-[10px]"
            input-class="input-box-style-1 line-claret"
            custom-class="w-full"
            custom-id="email"
            place-holder="example@secreto.com"
            @input-change="emailInputChangeHandler"
        ></InputBox>
        <div class="w-full mb-[20px]">
            <ButtonAtom
                custom-class="button-style-2 w-full button-shadow button-cream my-[15px]"
                @button-click="findPasswordButtonHandler"
                >비밀번호 찾기</ButtonAtom
            >
            <div class="flex justify-around w-full">
                <ButtonAtom
                    custom-class="text-[14px] text-A805DarkGrey"
                    @button-click="myPageButtonHandler"
                    >뒤로가기</ButtonAtom
                >
            </div>
        </div>
    </div>
</template>

<style scoped></style>
