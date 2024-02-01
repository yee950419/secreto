<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import { ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'
import type { MyPageUserDataType } from '@/types/user'
import CloseButtonAtom from '@/components/atoms/CloseButtonAtom.vue'

const emit = defineEmits(['passwordChangeHandle', 'withdrawalHandle', 'closeButtonHandle'])
const userInfo: Ref<MyPageUserDataType> = ref({
    email: 'test@secreto.com',
    nickname: '테스트유저',
    profileUrl: null
})
const profileImageChangeHandler: Handler = () => {
    alert('profile image change')
}
const modifyButtonHandler: Handler = () => {
    console.log(JSON.stringify(userInfo.value))
    alert('Modify')
}
const profileImageDeleteButtonHandler: Handler = () => {
    alert('profile image delete')
    changePasswordButtonHandler
}
const changePasswordButtonHandler: Handler = () => {
    emit('passwordChangeHandle')
}
const withdrawalButtonHandler: Handler = () => {
    emit('withdrawalHandle')
}
</script>

<template>
    <div class="card-container max-md:justify-center max-md:items-center">
        <CloseButtonAtom class="absolute top-2 right-2" @click="() => emit('closeButtonHandle')" />
        <div class="flex h-full w-full justify-between flex-col items-center max-md:h-[500px]">
            <TextAtom custom-class="text-[48px] font-Iceland">My Page</TextAtom>
            <div class="w-full flex flex-col justify-between h-[270px]">
                <InputBox
                    label="이메일"
                    type="text"
                    label-class="ps-[10px]"
                    input-class="input-box-style-1 line-claret"
                    custom-class="w-full"
                    custom-id="email"
                    v-model="userInfo.email"
                    readonly
                ></InputBox
                ><InputBox
                    label="닉네임"
                    type="text"
                    label-class="ps-[10px]"
                    input-class="input-box-style-1 line-claret"
                    custom-class="w-full"
                    custom-id="email"
                    v-model="userInfo.nickname"
                ></InputBox>
                <div class="w-full flex justify-left ps-[20px] items-center">
                    <AvatarAtom
                        custom-class="my-page-profile h-[100px] cursor-pointer"
                        :image-url="userInfo.profileUrl"
                        @image-click="profileImageChangeHandler"
                    ></AvatarAtom>
                    <div class="flex flex-col text-[12px] text-A805DarkGrey ms-[14px]">
                        <TextAtom>200 X 200</TextAtom>
                        <TextAtom>5MB 이내</TextAtom>
                        <TextAtom>jpg, png, gif 파일</TextAtom>
                        <ButtonAtom
                            custom-class="mt-[10px] font-bold text-left text-A805Blue"
                            @button-click="profileImageDeleteButtonHandler"
                            >삭제</ButtonAtom
                        >
                    </div>
                </div>
            </div>
            <ButtonAtom
                custom-class="button-style-2 w-full button-shadow button-cream"
                @button-click="modifyButtonHandler"
                >수&emsp;정</ButtonAtom
            >
            <div class="flex justify-around w-full">
                <ButtonAtom
                    custom-class="text-[14px] text-A805Blue"
                    @button-click="changePasswordButtonHandler"
                    >비밀번호 변경</ButtonAtom
                >
                <ButtonAtom
                    custom-class="text-[14px] text-A805DarkGrey"
                    @button-click="withdrawalButtonHandler"
                    >회원 탈퇴</ButtonAtom
                >
            </div>
        </div>
    </div>
</template>

<style scoped></style>
