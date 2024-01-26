<script setup lang="ts">
import MainCard from '@/components/organisms/MainCard.vue'
import WideCardTemplate from '@/components/template/WideCardTemplate.vue'
import type { DataHandler, Handler, WideCardTemplateType } from '@/types/common'
import type { LoginRequestType, PasswordFindMailRequest } from '@/types/user'
import type { Ref } from 'vue'
import { ref } from 'vue'
import DeleteModalContent1 from '@/components/organisms/DeleteModalContent1.vue'
import DeleteModalContent2 from '@/components/organisms/DeleteModalContent2.vue'
import DeleteModalContent3 from '@/components/organisms/DeleteModalContent3.vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import RoomListView from '@/components/organisms/RoomListView.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import HeaderProfile from '@/components/molecules/HeaderProfile.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import MyPage from '@/components/organisms/MyPage.vue'
import ChangePasswordForm from '@/components/organisms/ChangePasswordForm.vue'

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
    MAIN_AFTER_LOGIN: 'main_after_login',
    MY_PAGE: 'my_page',
    CHANGE_PWD: 'change_password',
    TEMPLATE: 'template'
})

const buttonLabel: Ref<string> = ref(ButtonLabel.ENTER)
const state: Ref<string> = ref(State.MAIN_AFTER_LOGIN)

const buttonClickHandler: Handler = () => {
    switch (state.value) {
        case State.MAIN_AFTER_LOGIN:
            alert('방 접속 이벤트')
            break
        case State.CHANGE_PWD:
        case State.MY_PAGE:
            state.value = State.MAIN_AFTER_LOGIN
            buttonLabel.value = ButtonLabel.ENTER
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

// delete Modal
const deleteModal: Ref<{ seen: boolean; step: number }> = ref({
    seen: false,
    step: 0
})
const deleteModalToggle: Handler = () => {
    deleteModal.value.step = 0
    deleteModal.value.seen = !deleteModal.value.seen
}
const withdrawSubmitButtonHandle: DataHandler<string> = (password: string) => {
    alert(password)
    deleteModalToggle()
}

// main
const profileClickHandler = () => {
    state.value = State.MY_PAGE
    buttonLabel.value = ButtonLabel.GAME
}
</script>

<template>
    <div class="bg-A805White h-full w-full flex justify-center items-center">
        <div class="card-template-container">
            <MainCard
                v-if="state !== State.TEMPLATE"
                @button-click="buttonClickHandler"
                :button-label-ref="buttonLabel"
            >
                <template v-if="state === State.MAIN_AFTER_LOGIN">
                    <HeaderProfile
                        name="sdf"
                        :image-url="null"
                        custom-class="cursor-pointer"
                        @click="profileClickHandler" />
                    <InputBox
                        custom-class="input-box-style-2 mt-[10px] bg-A805White w-[200px] h-[50px]"
                        input-class="text-center  text-[24px]"
                        place-holder="초대코드 입력"
                /></template>
            </MainCard>
            <RoomListView v-if="state === State.MAIN_AFTER_LOGIN" />
            <MyPage
                v-if="state === State.MY_PAGE"
                @password-change-handle="() => (state = State.CHANGE_PWD)"
                @withdrawal-handle="() => (deleteModal.seen = true)"
            />
            <ChangePasswordForm
                v-if="state === State.CHANGE_PWD"
                @prev-button-handle="() => (state = State.MY_PAGE)"
            />
            <WideCardTemplate
                v-if="state === State.TEMPLATE"
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
            v-if="deleteModal.seen && deleteModal.step === 0"
            @yes-button-handle="() => ++deleteModal.step"
            @no-button-handle="deleteModalToggle"
        />
        <DeleteModalContent2
            v-if="deleteModal.seen && deleteModal.step === 1"
            @yes-button-handle="() => ++deleteModal.step"
            @no-button-handle="deleteModalToggle"
        />
        <DeleteModalContent3
            v-if="deleteModal.seen && deleteModal.step === 2"
            @submit-button-handle="withdrawSubmitButtonHandle"
        />
    </ModalTemplate>
</template>
