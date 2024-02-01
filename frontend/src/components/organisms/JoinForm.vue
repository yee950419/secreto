<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ref, type Ref } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import type { JoinRequestType } from '@/types/user'
import { signup } from '@/api/user'
import { useUserStore, ViewState } from '@/stores/user'
import { storeToRefs } from 'pinia'

const userStore = useUserStore()
const { viewState } = storeToRefs(userStore)

const passwordConfirm: Ref<String> = ref('')
const verificationCode: Ref<String> = ref('')
const userData: Ref<JoinRequestType> = ref({
    email: '',
    password: '',
    nickname: ''
})
const emailVerificationButtonHandler: DataHandler<string> = (data: string) => {
    alert(`send to : ${data}`)
}
const verificationCodeButtonHandler: DataHandler<string> = (data: string) => {
    alert(`verification code : ${data}`)
}
const joinButtonHandler: Handler = () => {
    if (userData.value.password !== passwordConfirm.value) {
        alert('incorrect password confirm.')
        return
    }
    signup(
        userData.value,
        (response) => {
            console.log(response.data.message)
            viewState.value = ViewState.LOGIN
        },
        (error) => {
            alert(error.response.data.message)
        }
    )
}
</script>

<template>
    <div class="card-container scroll-container">
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
                v-model="userData.email"
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
                v-model="verificationCode"
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
                v-model="userData.password"
            ></InputBox>
            <InputBox
                label="비밀번호 확인"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="passwordConfirm"
                place-holder="비밀번호 확인을 입력해주세요"
                v-model="passwordConfirm"
            ></InputBox>
            <InputBox
                label="닉네임"
                type="text"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="nickname"
                place-holder="닉네임을 입력해주세요"
                v-model="userData.nickname"
            ></InputBox>
            <ButtonAtom
                custom-class="button-style-2 w-full button-shadow button-cream mt-[15px]"
                @button-click="joinButtonHandler"
                >회원가입</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
