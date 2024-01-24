<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import CheckBoxMolecule from '@/components/molecules/CheckBoxMolecule.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'
import type CheckBoxMoleculeVue from '@/components/molecules/CheckBoxMolecule.vue'

type LoginDataType = Ref<{
    email: string
    password: string
}>
type ClickHandler = () => void

const userData: LoginDataType = ref({
    email: '',
    password: ''
})
const emailInputChangeHandler = (data: string) => {
    userData.value.email = data
}
const passwordInputChangeHandler = (data: string) => {
    userData.value.password = data
}
const loginButtonHandler: ClickHandler = () => {
    console.log(JSON.stringify(userData.value))
    alert('Login')
}

const kakaoLoginButtonHandler: ClickHandler = () => {
    alert('Kakao Login')
}
const googleLoginButtonHandler: ClickHandler = () => {
    alert('Google Login')
}
</script>

<template>
    <div
        class="bg-A805RealWhite flex flex-col justify-between items-center h-[500px] w-full max-w-[400px] py-[20px] px-[40px]"
    >
        <TextAtom customClass="text-[48px] font-Iceland">Login</TextAtom>

        <div class="w-full flex flex-col justify-between h-[200px]">
            <InputBox
                label="이메일"
                type="text"
                labelClass="ps-[10px]"
                inputClass="input-box-style-1 line-claret"
                customClass="w-full"
                customId="email"
                placeHolder="example@secreto.com"
                @inputChange="emailInputChangeHandler"
            ></InputBox>
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
            <div class="text-[12px] w-full flex justify-between items-center">
                <CheckBoxMolecule
                    customClass="checkbox-molecule-style-1 text-[14px] accent-[#E0AED0]"
                    customId="remember"
                    >아이디 기억하기</CheckBoxMolecule
                >
                <a href="#" class="text-A805Blue">비밀번호를 잊어버리셨나요?</a>
            </div>
        </div>
        <ButtonAtom
            customClass="button-style-2 w-full button-shadow button-cream"
            @buttonClick="loginButtonHandler"
            >로그인</ButtonAtom
        >
        <div class="flex justify-center w-full h-[60px]">
            <ButtonAtom @buttonClick="googleLoginButtonHandler">
                <img
                    class="w-[40px] h-[40px] mx-[20px]"
                    src="@/assets/images/button/login-google.png"
                />
            </ButtonAtom>
            <ButtonAtom @buttonClick="kakaoLoginButtonHandler">
                <img
                    class="w-[40px] h-[40px] mx-[20px]"
                    src="@/assets/images/button/login-kakao.png"
            /></ButtonAtom>
        </div>
    </div>
</template>

<style scoped></style>
