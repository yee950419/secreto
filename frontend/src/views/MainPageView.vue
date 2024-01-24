<script setup lang="ts">
import MainCard from '../components/organisms/MainCard.vue'
import LoginForm from '../components/organisms/LoginForm.vue'
import JoinForm from '@/components/organisms/JoinForm.vue'
import MyPage from '@/components/organisms/MyPage.vue'
import ChangePasswordForm from '@/components/organisms/ChangePasswordForm.vue'
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
    </div>
</template>
