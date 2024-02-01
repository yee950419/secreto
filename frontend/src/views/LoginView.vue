<script setup lang="ts">
import FindPasswordForm from '@/components/organisms/FindPasswordForm.vue'
import JoinForm from '@/components/organisms/JoinForm.vue'
import LoginForm from '@/components/organisms/LoginForm.vue'
import MainCard from '@/components/organisms/MainCard.vue'
import WideCardTemplate from '@/components/template/WideCardTemplate.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import type { DataHandler, Handler, WideCardTemplateType } from '@/types/common'
import type { LoginRequestType, PasswordFindMailRequest } from '@/types/user'
import type { Ref } from 'vue'
import { ref } from 'vue'
import { ViewState, useUserStore } from '@/stores/user'
import { storeToRefs } from 'pinia'

const userStore = useUserStore()
const { viewState } = storeToRefs(userStore)
const ButtonLabel = Object.freeze({
    START: 'start',
    JOIN: 'join',
    LOGIN: 'login',
    GAME: 'game',
    ENTER: 'enter'
})
const buttonLabel: Ref<string> = ref(ButtonLabel.START)

const buttonClickHandler: Handler = () => {
    switch (viewState.value) {
        case ViewState.MAIN:
            viewState.value = ViewState.LOGIN
            buttonLabel.value = ButtonLabel.JOIN
            break
        case ViewState.LOGIN:
            viewState.value = ViewState.JOIN
            buttonLabel.value = ButtonLabel.LOGIN
            break
        case ViewState.JOIN:
        case ViewState.PASSWORD:
            viewState.value = ViewState.LOGIN
            buttonLabel.value = ButtonLabel.JOIN
            break
    }
}

// template
const template: Ref<WideCardTemplateType> = ref({
    title: '',
    contentMessages: [],
    buttonLabel: '',
    buttonClickHandler: null
})

// login view
const loginHandler: DataHandler<LoginRequestType> = (loginRequest: LoginRequestType) => {
    console.log(JSON.stringify(loginRequest))
}
const googleLoginHandler: Handler = () => {
    alert('google login')
}
const kakaoLoginHandler: Handler = () => {
    alert('kakao login')
}
const findPasswordHandler: Handler = () => {
    viewState.value = ViewState.PASSWORD
    buttonLabel.value = ButtonLabel.LOGIN
}

// join view
const joinHandler: Handler = () => {
    console.log('join handler')
}

// find password view
const findPasswordEmailSubmitHandler: DataHandler<PasswordFindMailRequest> = (
    findRequest: PasswordFindMailRequest
) => {
    alert('send email')
    console.log(JSON.stringify(findRequest))
    template.value.title = 'Verify Please'
    template.value.contentMessages = [
        '사용자 이메일로 비밀번호 변경 링크를 전송했어요.',
        '이메일을 통해 비밀번호 변경을 완료해주세요.'
    ]
    template.value.buttonLabel = '로그인 페이지 이동'
    template.value.buttonClickHandler = () => {
        viewState.value = ViewState.LOGIN
        buttonLabel.value = ButtonLabel.JOIN
    }
    viewState.value = ViewState.TEMPLATE
}
const findPasswordPrevPageHandler: Handler = () => {
    viewState.value = ViewState.LOGIN
    buttonLabel.value = ButtonLabel.JOIN
}
</script>

<template>
    <div class="bg-A805White h-full w-full flex justify-center items-center">
        <div class="card-template-container">
            <MainCard
                v-if="viewState !== ViewState.TEMPLATE"
                @button-click="buttonClickHandler"
                :button-label-ref="buttonLabel"
            >
                <TextAtom custom-class="text-[16px]">
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quasi consequatur iure
                    quos accusantium corrupti velit officiis dignissimos est porro! Tempore aperiam
                    quasi saepe, maiores illum aliquam fugit ut consequuntur aut?
                </TextAtom></MainCard
            >
            <LoginForm
                v-if="viewState === ViewState.LOGIN"
                @login-handle="loginHandler"
                @google-login-handle="googleLoginHandler"
                @kakao-login-handle="kakaoLoginHandler"
                @find-password-handle="findPasswordHandler"
            />
            <JoinForm v-if="viewState === ViewState.JOIN" @join-submit-handle="joinHandler" />
            <FindPasswordForm
                v-if="viewState === ViewState.PASSWORD"
                @emailSubmitHandle="findPasswordEmailSubmitHandler"
                @prev-page-handle="findPasswordPrevPageHandler"
            />
            <WideCardTemplate
                v-if="viewState === ViewState.TEMPLATE"
                :title="template.title"
                :content-messages="template.contentMessages"
                :button-label="template.buttonLabel"
                @button-click="template.buttonClickHandler"
            />
        </div>
    </div>
</template>
