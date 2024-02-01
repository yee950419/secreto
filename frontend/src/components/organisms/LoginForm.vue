<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import CheckBox from '@/components/molecules/CheckBox.vue'
import { ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'
import type { LoginRequestType } from '@/types/user'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
const { userLogin } = userStore

const emit = defineEmits([
    'afterLoginHandle',
    'kakaoLoginHandle',
    'googleLoginHandle',
    'findPasswordHandle'
])
const loginRequest: Ref<LoginRequestType> = ref({
    email: '',
    password: ''
})
const loginButtonHandler: Handler = () => {
    userLogin(loginRequest.value)
}
const kakaoLoginButtonHandler: Handler = () => {
    emit('kakaoLoginHandle')
}
const googleLoginButtonHandler: Handler = () => {
    emit('googleLoginHandle')
}
const findPasswordButtonHandler: Handler = () => {
    emit('findPasswordHandle')
}
</script>

<template>
    <div class="card-container">
        <TextAtom custom-class="text-[48px] font-Iceland">Login</TextAtom>

        <div class="w-full flex flex-col justify-between h-[200px]">
            <InputBox
                label="이메일"
                type="text"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full"
                custom-id="email"
                place-holder="example@secreto.com"
                v-model="loginRequest.email"
            ></InputBox>
            <InputBox
                label="비밀번호"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full"
                custom-id="password"
                place-holder="비밀번호를 입력해주세요"
                v-model="loginRequest.password"
            ></InputBox>
            <div class="text-[12px] w-full flex justify-between items-center">
                <CheckBox
                    custom-class="checkbox-molecule-style-1 text-[14px] accent-[#E0AED0]"
                    custom-id="remember"
                    >아이디 기억하기</CheckBox
                >
                <ButtonAtom custom-class="text-A805Blue" @button-click="findPasswordButtonHandler"
                    >비밀번호를 잊어버리셨나요?</ButtonAtom
                >
            </div>
        </div>
        <ButtonAtom
            custom-class="button-style-2 w-full button-shadow button-cream mt-[20px] mb-[10px]"
            @button-click="loginButtonHandler"
            >로그인</ButtonAtom
        >
        <div class="flex justify-center w-full h-[60px]">
            <ButtonAtom @button-click="googleLoginButtonHandler">
                <img
                    class="w-[40px] h-[40px] mx-[20px]"
                    src="@/assets/images/button/login-google.png"
                />
            </ButtonAtom>
            <ButtonAtom @button-click="kakaoLoginButtonHandler">
                <img
                    class="w-[40px] h-[40px] mx-[20px]"
                    src="@/assets/images/button/login-kakao.png"
            /></ButtonAtom>
        </div>
    </div>
</template>

<style scoped></style>
