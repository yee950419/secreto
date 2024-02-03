<script setup lang="ts">
import WideCardTemplate from '@/components/template/WideCardTemplate.vue'
import { createRoom } from '@/api/room'
import type { DataHandler, Handler, WideCardTemplateType } from '@/types/common'
import type { Ref } from 'vue'
import { ref } from 'vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import MobileInvitationCodeBox from '@/components/molecules/main/MobileInvitationCodeBox.vue'
import MainCard from '@/components/organisms/main/MainCard.vue'
import RoomListView from '@/components/organisms/main/RoomListView.vue'
import MyPage from '@/components/organisms/main/MyPage.vue'
import ChangePasswordForm from '@/components/organisms/main/ChangePasswordForm.vue'
import AccountDeleteModalContent1 from '@/components/organisms/modal/AccountDeleteModalContent1.vue'
import AccountDeleteModalContent2 from '@/components/organisms/modal/AccountDeleteModalContent2.vue'
import AccountDeleteModalContent3 from '@/components/organisms/modal/AccountDeleteModalContent3.vue'
import RoomCreateModalContent from '@/components/organisms/modal/RoomCreateModalContent.vue'
import RoomDeleteModalContent from '@/components/organisms/modal/RoomDeleteModalContent.vue'
import RoomEnterModalContent from '@/components/organisms/modal/RoomEnterModalContent.vue'
import type { RoomCreateRequestType } from '@/types/room'
import { useUserStore } from '@/stores/user'
import MobileMiniHeader from '@/components/molecules/main/MobileMiniHeader.vue'
import InputBox from '@/components/molecules/common/InputBox.vue'
import HeaderProfile from '@/components/molecules/common/HeaderProfile.vue'
import ServiceFeature from '@/components/molecules/main/ServiceFeature.vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const userStore = useUserStore()

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
const ModalState = Object.freeze({
    NONE: 'none',
    WITHDRAW: 'withdraw',
    ROOM_CREATE: 'room_create',
    ROOM_ENTER: 'room_enter',
    ROOM_LEAVE: 'room_leave'
})

const buttonLabel: Ref<string> = ref(ButtonLabel.ENTER)
const state: Ref<string> = ref(State.MAIN_AFTER_LOGIN)

const buttonClickHandler: Handler = (data) => {
    switch (state.value) {
        case State.MAIN_AFTER_LOGIN:
            modalToggle()
            if (data === 'roomCreate') {
                modalState.value = ModalState.ROOM_CREATE
            } else {
                modalState.value = ModalState.ROOM_ENTER
            }
            break
        case State.CHANGE_PWD:
            break
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

// modal
const modalSeen: Ref<boolean> = ref(false)
const modalState: Ref<string> = ref(ModalState.NONE)

// delete Modal
const deleteModalStep: Ref<number> = ref(0)
const modalToggle: Handler = () => {
    if (deleteModalStep.value > 0) deleteModalStep.value = 0
    modalSeen.value = !modalSeen.value
}
const withdrawSubmitButtonHandle: DataHandler<string> = (password: string) => {
    alert(password)
    modalToggle()
}

// room
const roomCreateHandler: DataHandler<RoomCreateRequestType> = (request: RoomCreateRequestType) => {
    // alert('방 생성 ' + JSON.stringify(request))
    createRoom(
        request,
        ({ data }) => {
            console.log(data)
        },
        (error) => {
            console.error('error', error)
        }
    )
}
const roomLeaveHandler: DataHandler<number> = (roomNo: number) => {
    alert('방 나가기 ' + roomNo)
}
const roomEnterHandler: DataHandler<string> = (nickname: string) => {
    alert('방 입장 ' + nickname)
}

// main
const profileClickHandler = () => {
    state.value = State.MY_PAGE
    buttonLabel.value = ButtonLabel.GAME
}
</script>

<template>
    <div class="bg-A805White h-full w-full flex justify-center items-center">
        <div
            class="card-template-container max-md:w-full max-md:h-full max-md:bg-A805Cream max-md:flex-col"
        >
            <!-- pc -->
            <MainCard
                class="max-md:hidden"
                v-if="state !== State.TEMPLATE"
                @button-click="buttonClickHandler"
                :button-label-ref="buttonLabel"
            >
                <template v-if="state === State.MAIN_AFTER_LOGIN">
                    <HeaderProfile
                        :name="userStore.userInfo.nickname"
                        :image-url="userStore.userInfo.profileUrl"
                        custom-class="cursor-pointer"
                        @click="profileClickHandler" />
                    <InputBox
                        custom-class="input-box-style-2 mt-[10px] bg-A805White w-[200px] h-[50px]"
                        input-class="text-center text-[24px]"
                        place-holder="초대코드 입력"
                /></template>
                <ServiceFeature v-if="state !== State.MAIN_AFTER_LOGIN" />
            </MainCard>

            <!-- mobile -->
            <div class="w-full md:hidden">
                <MobileMiniHeader class="my-2" @profile-click-handle="profileClickHandler" />
                <MobileInvitationCodeBox
                    v-show="state === State.MAIN_AFTER_LOGIN"
                    @submit-button-handle="buttonClickHandler"
                />
            </div>

            <RoomListView
                class="max-md:max-w-full max-md:max-h-full max-md:h-full max-md:w-full"
                v-if="state === State.MAIN_AFTER_LOGIN"
                @submit-button-handle="buttonClickHandler"
            />
            <MyPage
                class="max-md:h-full max-md:w-full"
                v-if="state === State.MY_PAGE"
                @password-change-handle="() => (state = State.CHANGE_PWD)"
                @withdrawal-handle="
                    () => {
                        modalSeen = true
                        modalState = ModalState.WITHDRAW
                    }
                "
                @close-button-handle="buttonClickHandler"
            />
            <ChangePasswordForm
                class="max-md:max-w-full max-md:max-h-full max-md:h-full max-md:w-full"
                v-if="state === State.CHANGE_PWD"
                @prev-button-handle="() => (state = State.MY_PAGE)"
                @close-button-handle="() => (state = State.MY_PAGE)"
            />
            <WideCardTemplate
                v-if="state === State.TEMPLATE"
                :title="template.title"
                :content-messages="template.contentMessages"
                :button-label="template.buttonLabel"
                @button-click="template.buttonClickHandler"
                @close-button-handle="template.buttonClickHandler"
            />
        </div>
    </div>

    <!-- 회원 탈퇴 모달창 -->
    <ModalTemplate
        custom-id="modal"
        custom-class="modal-template-style-1 w-[350px]"
        :seen="modalSeen"
        v-if="modalSeen"
        @modal-close="modalToggle"
    >
        <AccountDeleteModalContent1
            v-if="modalState === ModalState.WITHDRAW && deleteModalStep === 0"
            @yes-button-handle="() => ++deleteModalStep"
            @no-button-handle="modalToggle"
        />
        <AccountDeleteModalContent2
            v-if="modalState === ModalState.WITHDRAW && deleteModalStep === 1"
            @yes-button-handle="() => ++deleteModalStep"
            @no-button-handle="modalToggle"
        />
        <AccountDeleteModalContent3
            v-if="modalState === ModalState.WITHDRAW && deleteModalStep === 2"
            @submit-button-handle="withdrawSubmitButtonHandle"
        />
        <RoomCreateModalContent
            v-if="modalState === ModalState.ROOM_CREATE"
            @yes-button-handle="roomCreateHandler"
            @no-button-handle="modalToggle"
        />
        <RoomDeleteModalContent
            v-if="modalState === ModalState.ROOM_LEAVE"
            @yes-button-handle="roomLeaveHandler"
            @no-button-handle="modalToggle"
        />
        <RoomEnterModalContent
            v-if="modalState === ModalState.ROOM_ENTER"
            @yes-button-handle="roomEnterHandler"
            @no-button-handle="modalToggle"
        />
    </ModalTemplate>
</template>
