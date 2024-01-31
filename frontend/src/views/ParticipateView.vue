<script setup lang="ts">
import MissionHeader from '@/components/organisms/MissionHeader.vue'
import PaticipateProfile from '@/components/molecules/PaticipateProfile.vue'
import { onMounted } from 'vue'
import { ref } from 'vue'
import { getUserList } from '@/api/room'

const userList = ref<userType[]>()
interface userType {
    id: number
    nickName: string
    imageUrl: string
}
const getRooms = () => {
    getUserList(
        1, // 방번호
        (response) => {
            console.log(response)
            // userList.value = data.result
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
        <MissionHeader custom-class="flex w-full h-[100px] bg-A805Green" />
        <div class="flex flex-1 items-center justify-center">
            <div class="flex flex-wrap items-center justify-center gap-[45px] m-[80px]">
                <template v-for="user in userList" :key="user.id">
                    <PaticipateProfile :nick-name="user.nickName" :image-url="user.imageUrl" />
                </template>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
