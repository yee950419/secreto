<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ref, type Ref } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import type { JoinRequestType } from '@/types/user'

const emit = defineEmits(['joinSubmitHandle'])
const passwordConfirm: Ref<String> = ref('')
const verificationCode: Ref<String> = ref('')
const userData: JoinRequestType = ref({
    email: '',
    password: '',
    nickname: ''
})
const emailVerificationButtonHandler: DataHandler<string> = (data: string) => {
    alert(`send to : ${data}`)
}
const verificationCodeButtonHandler: DataHandler<string> = (data: string) => {
    alert(`verification code : ${data}`)
}
const joinButtonHandler: Handler = () => {
    if (userData.value.password !== passwordConfirm.value) {
        alert('incorrect password confirm.')
    }
    console.log(JSON.stringify(userData.value))
    emit('joinSubmitHandle')
}
</script>

<template>
    <div class="card-container scroll-container">
        <TextAtom custom-class="text-[48px] font-Iceland">header</TextAtom>
    </div>
</template>

<style scoped></style>
