<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'
import type { PasswordChangeRequest } from '@/types/user'

const emit = defineEmits(['prevButtonHandle'])
const passwordChangeRequest: Ref<PasswordChangeRequest> = ref({
    currentPassword: '',
    newPassword: ''
})
const newPasswordConfirm: Ref<string> = ref('')
const changePasswordButtonHandler: Handler = () => {
    console.log(passwordChangeRequest.value.newPassword, newPasswordConfirm.value)
    if (passwordChangeRequest.value.newPassword !== newPasswordConfirm.value) {
        alert('wrong new password confirm')
        return
    }
    alert('change password' + JSON.stringify(passwordChangeRequest.value))
}
const myPageButtonHandler: Handler = () => {
    emit('prevButtonHandle')
}
</script>

<template>
    <div class="card-container">
        <TextAtom custom-class="text-[44px] font-Iceland">Change Password</TextAtom>
        <TextAtom custom-class="text-[14px]">새로 사용할 비밀번호를 입력해 주세요.</TextAtom>
        <div class="w-full mb-[20px]">
            <InputBox
                label="현재 비밀번호"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[20px]"
                custom-id="password"
                place-holder="현재 비밀번호를 입력해주세요"
                v-model="passwordChangeRequest.currentPassword"
            ></InputBox>
            <InputBox
                label="새 비밀번호"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[20px]"
                custom-id="newPassword"
                place-holder="새 비밀번호를 입력해주세요"
                v-model="passwordChangeRequest.newPassword"
            ></InputBox>
            <InputBox
                label="새로운 비밀번호 확인"
                type="password"
                label-class="ps-[10px]"
                input-class="input-box-style-1 line-claret"
                custom-class="w-full my-[20px]"
                custom-id="newPasswordConfirm"
                place-holder="새 비밀번호를 한번 더 입력해주세요"
                v-model="newPasswordConfirm"
            ></InputBox>
            <ButtonAtom
                custom-class="button-style-2 w-full button-shadow button-cream my-[15px]"
                @button-click="changePasswordButtonHandler"
                >비밀번호 변경</ButtonAtom
            >
            <div class="flex justify-around w-full">
                <ButtonAtom
                    custom-class="text-[14px] text-A805DarkGrey"
                    @button-click="myPageButtonHandler"
                    >뒤로가기</ButtonAtom
                >
            </div>
        </div>
    </div>
</template>

<style scoped></style>
