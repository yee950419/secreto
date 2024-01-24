<script setup lang="ts">
import MainCard from '../components/organisms/MainCard.vue'
import LoginForm from '../components/organisms/LoginForm.vue'
import JoinForm from '@/components/organisms/JoinForm.vue'

import { getRoom } from '@/api/room'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'

const RoomTest = async () => {
    getRoom(
        1,
        (response) => console.log('getRoom', response.data),
        (error) => console.log('getRoom error', error)
    )
}
</script>

<template>
    <div class="bg-A805White">
        <MainCard></MainCard>
        <br />
        <LoginForm></LoginForm>
        <br />
        <JoinForm></JoinForm>
        
        <ButtonAtom @click="RoomTest">방 테스트</ButtonAtom>


    </div>
</template>
