<script setup lang="ts">
import MainCard from '@/components/organisms/MainCard.vue'
import LoginForm from '@/components/organisms/LoginForm.vue'
import JoinForm from '@/components/organisms/JoinForm.vue'
import MyPage from '@/components/organisms/MyPage.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ChangePasswordForm from '@/components/organisms/ChangePasswordForm.vue'
// import { getUser } from '@/api/user'
import { useUserStore } from '@/stores/user'
import FindPasswordForm from '@/components/organisms/FindPasswordForm.vue'
const userStore = useUserStore()
const { getUserInfo } = userStore

const eventHandler = async () => {
    console.log('eventHandler')
    await getUserInfo('kjsw12@naver.com')
}
</script>

<template>
    <div class="bg-A805White">
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
                @click="eventHandler"
                customClass="button-style-1 button-border-violet button-violet "
                >API 호출</ButtonAtom
            >
        </div>
    </div>
</template>
