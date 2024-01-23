<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'

type LoginDataType = Ref<{
    email: string
    password: string
    nickname: string
}>
const passwordConfirm = ref()
const verificationCode = ref()
const userData: LoginDataType = ref({
    email: '',
    password: '',
    nickname: ''
})
const emailInputChangeHandler = (data: string) => {
    userData.value.email = data
}
const passwordInputChangeHandler = (data: string) => {
    userData.value.password = data
}
const passwordConfirmInputChangeHandler = (data: string) => {
    passwordConfirm.value = data
}
const verificationCodeInputChangeHandler = (data: string) => {
    verificationCode.value = data
}
const nicknameInputChangeHandler = (data: string) => {
    userData.value.nickname = data
}
const joinButtonHandler = () => {
    if (userData.value.password !== passwordConfirm.value) {
        alert('incorrect password confirm.')
    }
    console.log(JSON.stringify(userData.value))
}
</script>

<template>
    <div
        class="bg-A805RealWhite flex flex-col justify-between items-center h-[650px] w-full max-w-[400px] py-[20px] px-[40px]"
    >
        <TextAtom customClass="text-[48px] font-Iceland">Join</TextAtom>
        <div class="w-full flex flex-col justify-between h-full mb-[20px]">
            <ButtonInputBox
                label="이메일"
                type="email"
                labelClass="ps-[10px]"
                inputClass="input-box-style-1 line-claret"
                customClass="w-full"
                customId="email"
                placeHolder="example@secreto.com"
                @inputChange="emailInputChangeHandler"
                buttonLabel="인증"
            ></ButtonInputBox>
            <ButtonInputBox
                label="인증코드 입력"
                type="text"
                labelClass="ps-[10px]"
                inputClass="input-box-style-1 line-claret"
                customClass="w-full"
                customId="verificationCode"
                placeHolder="인증코드를 입력해주세요"
                @inputChange="verificationCodeInputChangeHandler"
                buttonLabel="확인"
            ></ButtonInputBox>
            <InputBox
                label="비밀번호"
                type="password"
                labelClass="ps-[10px]"
                inputClass="input-box-style-1 line-claret"
                customClass="w-full"
                customId="password"
                placeHolder="비밀번호를 입력해주세요"
                @inputChange="passwordInputChangeHandler"
            ></InputBox>
            <InputBox
                label="비밀번호 확인"
                type="password"
                labelClass="ps-[10px]"
                inputClass="input-box-style-1 line-claret"
                customClass="w-full"
                customId="passwordConfirm"
                placeHolder="비밀번호 확인을 입력해주세요"
                @inputChange="passwordConfirmInputChangeHandler"
            ></InputBox>
            <InputBox
                label="닉네임"
                type="text"
                labelClass="ps-[10px]"
                inputClass="input-box-style-1 line-claret"
                customClass="w-full"
                customId="nickname"
                placeHolder="닉네임을 입력해주세요"
                @inputChange="nicknameInputChangeHandler"
            ></InputBox>
            <ButtonAtom
                customClass="button-style-2 w-full button-shadow button-cream mt-[10px]"
                @buttonClick="joinButtonHandler"
                >회원가입</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
