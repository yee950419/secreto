<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'
</script>

<template>
    <div
        class="bg-A805RealWhite flex flex-col justify-between items-center h-[500px] w-full max-w-[400px] py-[20px] px-[40px]"
    >
        <TextAtom custom-class="text-[44px] font-Iceland">Change Password</TextAtom>
        <TextAtom custom-class="text-[14px]">새로 사용할 비밀번호를 입력해 주세요.</TextAtom>
        <div class="w-full mb-[20px]">
            <InputBox
                label="현재 비밀번호"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="password"
                place-holder="햔제 비밀번호를 입력해주세요"
                @input-change="passwordInputChangeHandler"
            ></InputBox>
            <InputBox
                label="새로운 비밀번호 확인"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="newPassword"
                place-holder="비밀번호 확인을 입력해주세요"
                @input-change="newPasswordInputChangeHandler"
            ></InputBox>
            <InputBox
                label="새로운 비밀번호 확인"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[30px]"
                custom-id="newPasswordConfirm"
                place-holder="비밀번호 확인을 입력해주세요"
                @input-change="newPasswordConfirmInputChangeHandler"
            ></InputBox>
            <ButtonAtom
                custom-class="button-style-2 w-full button-shadow button-cream mt-[15px] mb-[30px]"
                @button-click="changePasswordButtonHandler"
                >비밀번호 변경</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
