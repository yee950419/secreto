<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import AvatarAtom from '../atoms/AvatarAtom.vue'
import { ref } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import type { MyPageUserDataType } from '@/types/user'

const userInfo: MyPageUserDataType = ref({
    email: 'test@secreto.com',
    nickname: '테스트유저',
    profileUrl: null
})
const nicknameInputChangeHandler: DataHandler<string> = (data: string) => {
    userInfo.value.nickname = data
}
const profileImageChangeHandler: Handler = () => {
    alert('profile image change')
}
const modifyButtonHandler: Handler = () => {
    console.log(JSON.stringify(userInfo.value))
    alert('Modify')
}
const profileImageDeleteButtonHandler: Handler = () => {
    alert('profile image delete')
}
const changePasswordButtonHandler: Handler = () => {
    alert('move change password page')
}
const withdrawalButtonHandler: Handler = () => {
    alert('withrawal modal')
}
</script>

<template>
    <div
        class="bg-A805RealWhite flex flex-col justify-between items-center h-[500px] w-full max-w-[400px] py-[20px] px-[40px]"
    >
        <TextAtom custom-class="text-[48px] font-Iceland">My Page</TextAtom>
        <div class="w-full flex flex-col justify-between h-[270px]">
            <InputBox
                label="이메일"
                type="text"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full"
                custom-id="email"
                :default-value="userInfo.email"
                readonly
            ></InputBox
            ><InputBox
                label="닉네임"
                type="text"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full"
                custom-id="email"
                :default-value="userInfo.nickname"
                @input-change="nicknameInputChangeHandler"
            ></InputBox>
            <div class="w-full flex justify-left ps-[20px] items-center">
                <AvatarAtom
                    custom-class="my-page-profile cursor-pointer"
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
</template>

<style scoped></style>
