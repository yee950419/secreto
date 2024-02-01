<script setup lang="ts">
import MissionHeader from '@/components/organisms/game/MissionHeader.vue'
import PaticipateProfile from '@/components/molecules/game/PaticipateProfile.vue'
import { onMounted } from 'vue'
import { ref } from 'vue'
import { getUserList } from '@/api/room'

const userList = ref<userType[]>()
interface userType {
    id: number
    nickname: string
    profile_url: string
    userNo: number
}
const getRooms = () => {
    getUserList(
        1, // 방번호
        ({ data }) => {
            // console.log(response)
            userList.value = data.result
        },
        (error) => {
            console.error('error', error)
        }
    )
}

onMounted(() => getRooms())
</script>

<template>
    <div class="flex flex-1 flex-col bg-A805White">
        <MissionHeader custom-class="flex w-full min-h-[100px] p-[20px] shadow-lg" />
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
