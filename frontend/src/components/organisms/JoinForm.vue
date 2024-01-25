<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ref } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import type { JoinRequestType } from '@/types/user'

const passwordConfirm = ref()
const verificationCode = ref()
const userData: JoinRequestType = ref({
    email: '',
    password: '',
    nickname: ''
})
const emailInputChangeHandler: DataHandler<string> = (data: string) => {
    userData.value.email = data
}
const passwordInputChangeHandler: DataHandler<string> = (data: string) => {
    userData.value.password = data
}
const passwordConfirmInputChangeHandler: DataHandler<string> = (data: string) => {
    passwordConfirm.value = data
}
const verificationCodeInputChangeHandler: DataHandler<string> = (data: string) => {
    verificationCode.value = data
}
const nicknameInputChangeHandler: DataHandler<string> = (data: string) => {
    userData.value.nickname = data
}
const emailVerificationButtonHandler: DataHandler<string> = (data: string) => {
    alert(`send to : ${data}`)
}
const verificationCodeButtonHandler: DataHandler<string> = (data: string) => {
    alert(`verification code : ${data}`)
}
const joinButtonHandler: Handler = () => {
    if (userData.value.password !== passwordConfirm.value) {
        alert('incorrect password confirm.')
    }
    console.log(JSON.stringify(userData.value))
}
</script>

<template>
    <div
        class="bg-A805RealWhite flex flex-col justify-between items-center h-[500px] w-full max-w-[400px] py-[20px] px-[40px] scroll-container"
    >
        <TextAtom custom-class="text-[48px] font-Iceland">Join</TextAtom>
        <div class="w-full mb-[20px]">
            <ButtonInputBox
                label="이메일"
                type="email"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="email"
                place-holder="example@secreto.com"
                @input-change="emailInputChangeHandler"
                button-label="인증"
                @button-click="emailVerificationButtonHandler"
            ></ButtonInputBox>
            <ButtonInputBox
                label="인증코드 입력"
                type="text"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="verificationCode"
                place-holder="인증코드를 입력해주세요"
                @input-change="verificationCodeInputChangeHandler"
                button-label="확인"
                @button-click="verificationCodeButtonHandler"
            ></ButtonInputBox>
            <InputBox
                label="비밀번호"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="password"
                place-holder="비밀번호를 입력해주세요"
                @input-change="passwordInputChangeHandler"
            ></InputBox>
            <InputBox
                label="비밀번호 확인"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="passwordConfirm"
                place-holder="비밀번호 확인을 입력해주세요"
                @input-change="passwordConfirmInputChangeHandler"
            ></InputBox>
            <InputBox
                label="닉네임"
                type="text"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="nickname"
                place-holder="닉네임을 입력해주세요"
                @input-change="nicknameInputChangeHandler"
            ></InputBox>
            <ButtonAtom
                custom-class="button-style-2 w-full button-shadow button-cream mt-[15px] mb-[30px]"
                @button-click="joinButtonHandler"
                >회원가입</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
