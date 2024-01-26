<script setup lang="ts">
import FindPasswordForm from '@/components/organisms/FindPasswordForm.vue'
import JoinForm from '@/components/organisms/JoinForm.vue'
import LoginForm from '@/components/organisms/LoginForm.vue'
import MainCard from '@/components/organisms/MainCard.vue'
import WideCardTemplate from '@/components/template/WideCardTemplate.vue'
import type { DataHandler, Handler, WideCardTemplateType } from '@/types/common'
import type { LoginRequestType, PasswordFindMailRequest } from '@/types/user'
import type { Ref } from 'vue'
import { ref } from 'vue'

const ButtonLabel = Object.freeze({
    START: 'start',
    JOIN: 'join',
    LOGIN: 'login',
    GAME: 'game',
    ENTER: 'enter'
})
const State = Object.freeze({
    MAIN: 'main',
    LOGIN: 'login',
    JOIN: 'join',
    PASSWORD: 'find_password',
    TEMPLATE: 'template'
})

const buttonLabel: Ref<string> = ref(ButtonLabel.START)
const state: Ref<string> = ref(State.MAIN)

const buttonClickHandler: Handler = () => {
    switch (state.value) {
        case State.MAIN:
            state.value = State.LOGIN
            buttonLabel.value = ButtonLabel.JOIN
            break
        case State.LOGIN:
            state.value = State.JOIN
            buttonLabel.value = ButtonLabel.LOGIN
            break
        case State.JOIN:
        case State.PASSWORD:
            state.value = State.LOGIN
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
    alert('login')
    console.log(JSON.stringify(loginRequest))
}
const googleLoginHandler: Handler = () => {
    alert('google login')
}
const kakaoLoginHandler: Handler = () => {
    alert('kakao login')
}
const findPasswordHandler: Handler = () => {
    state.value = State.PASSWORD
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
        state.value = State.LOGIN
        buttonLabel.value = ButtonLabel.JOIN
    }
    state.value = State.TEMPLATE
}
const findPasswordPrevPageHandler: Handler = () => {
    state.value = State.LOGIN
    buttonLabel.value = ButtonLabel.JOIN
}
</script>

<template>
    <div class="bg-A805White h-full w-full flex justify-center items-center">
        <div class="card-template-container">
            <MainCard
                v-show="state !== State.TEMPLATE"
                @button-click="buttonClickHandler"
                :button-label-ref="buttonLabel"
            />
            <LoginForm
                v-show="state === State.LOGIN"
                @login-handle="loginHandler"
                @google-login-handle="googleLoginHandler"
                @kakao-login-handle="kakaoLoginHandler"
                @find-password-handle="findPasswordHandler"
            />
            <JoinForm v-show="state === State.JOIN" @join-submit-handle="joinHandler" />
            <FindPasswordForm
                v-show="state === State.PASSWORD"
                @emailSubmitHandle="findPasswordEmailSubmitHandler"
                @prev-page-handle="findPasswordPrevPageHandler"
            />
            <WideCardTemplate
                v-show="state === State.TEMPLATE"
                :title="template.title"
                :content-messages="template.contentMessages"
                :button-label="template.buttonLabel"
                @button-click="template.buttonClickHandler"
            />
        </div>
    </div>

    <ModalTemplate
        custom-id="modal"
        custom-class="modal-template-style-1 w-[350px]"
        :seen="deleteModal.seen"
        @modal-close="deleteModalToggle"
    >
        <DeleteModalContent1
            v-show="deleteModal.seen && deleteModal.step === 0"
            @yes-button-handle="() => ++deleteModal.step"
            @no-button-handle="deleteModalToggle"
        />
        <DeleteModalContent2
            v-show="deleteModal.seen && deleteModal.step === 1"
            @yes-button-handle="() => ++deleteModal.step"
            @no-button-handle="deleteModalToggle"
        />
        <DeleteModalContent3
            v-show="deleteModal.seen && deleteModal.step === 2"
            @submit-button-handle="withdrawSubmitButtonHandle"
        />
    </ModalTemplate>
</template>
