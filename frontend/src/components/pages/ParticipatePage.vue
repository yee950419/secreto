<script setup lang="ts">
import MissionHeader from '@/components/organisms/game/MissionHeader.vue'
import PaticipateProfile from '@/components/molecules/game/PaticipateProfile.vue'
import { onMounted } from 'vue'
import { ref, type Ref } from 'vue'
import { getUserList } from '@/api/room'
import { inject, watch } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()
const userList = ref<userType[]>()
interface userType {
    id: number
    nickname: string
    profile_url: string
    userNo: number
}

const getRooms = () => {
    getUserList(
        Number(route.params.roomNo), // 방번호
        ({ data }) => {
            // console.log(response)
            console.log(data)
            userList.value = data.result
            return
        },
        (error) => {
            console.error('error', error)
        }
    )
}

const roomUserNo: Ref<number> = inject('roomUserNo', ref(0))

onMounted(async () => {
    getRooms()
})
</script>

<template>
    <div class="flex flex-1 flex-col bg-A805White">
        <MissionHeader custom-class="flex w-full min-h-[100px] p-[20px] shadow-lg" />
        당신의 방 번호는 {{ roomUserNo }}입니다.
        <div class="flex flex-1 items-center justify-center">
            <div class="flex flex-wrap items-center justify-center gap-[45px] m-[80px]">
                <template v-for="user in userList" :key="user.userNo">
                    <PaticipateProfile :nick-name="user.nickname" :image-url="''" />
                </template>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
