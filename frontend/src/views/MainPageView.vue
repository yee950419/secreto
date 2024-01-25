<script setup lang="ts">
import MainCard from '@/components/organisms/MainCard.vue'
import LoginForm from '@/components/organisms/LoginForm.vue'
import JoinForm from '@/components/organisms/JoinForm.vue'
import MyPage from '@/components/organisms/MyPage.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ChangePasswordForm from '@/components/organisms/ChangePasswordForm.vue'
import { useUserStore } from '@/stores/user'
import FindPasswordForm from '@/components/organisms/FindPasswordForm.vue'
import WideCardTemplate from '@/components/template/WideCardTemplate.vue'
import type { Handler } from '@/types/common'
const userStore = useUserStore()
const { getUserInfo } = userStore

const eventHandler = async (email: string) => {
    console.log('eventHandler')
    await getUserInfo(email)
}
const buttonEvent: Handler = () => {
    alert('event!!')
}
</script>

<template>
    <div class="bg-A805White">
        <WideCardTemplate
            title="Verify Please"
            :content-messages="[
                '사용자 이메일로 비밀번호 변경 링크를 전송했어요.',
                '이메일을 통해 비밀번호 변경을 완료해주세요.'
            ]"
            button-label="메인화면 이동"
            @button-click="buttonEvent"
        ></WideCardTemplate>
        <br />
        <MainCard></MainCard>
        <br />
        <LoginForm></LoginForm>
        <br />
        <JoinForm></JoinForm>
        <br />
        <MyPage></MyPage>
        <br />
        <ChangePasswordForm></ChangePasswordForm>
        <br />
        <FindPasswordForm></FindPasswordForm>
        <br />
        <div class="flex flex-col gap-[20px]">
            <RouterLink to="/game"
                ><ButtonAtom customClass="button-style-1 button-border-violet button-violet "
                    >방 이동</ButtonAtom
                ></RouterLink
            >
            <ButtonAtom
                @click="eventHandler('kjsw12@naver.com')"
                customClass="button-style-1 button-border-violet button-violet "
                >API 호출</ButtonAtom
            >
        </div>
    </div>
</template>
