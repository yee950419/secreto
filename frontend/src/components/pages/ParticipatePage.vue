<script setup lang="ts">
import MissionHeader from '@/components/organisms/game/MissionHeader.vue'
import PaticipateProfile from '@/components/molecules/game/PaticipateProfile.vue'
import type { userType, RoomUserInfoType } from '@/types/room'
import { getUserList } from '@/api/room'
import { ref, onMounted, inject, type Ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const userList = ref<userType[]>()

const roomUserInfo = inject<Ref<RoomUserInfoType>>(
    'roomUserInfo',
    ref({
        roomNo: Number(route.params.roomNo),
        roomUserNo: 0,
        roomName: '',
        roomNickname: '',
        profileUrl: ''
    })
)

const getRooms = () => {
    getUserList(
        Number(route.params.roomNo), // 방번호
        ({ data }) => {
            userList.value = data.result
            console.log(userList.value)
        },
        (error) => {
            console.error('error', error)
        }
    )
}

onMounted(() => {
    getRooms()
})
</script>

<template>
    <div class="flex flex-1 flex-col bg-A805White">
        <MissionHeader custom-class="flex w-full min-h-[100px] p-[20px] shadow-lg" />
        <div class="flex flex-1 items-center justify-center">
            <div class="flex flex-wrap items-center justify-center gap-[50px] md:m-[80px] max-md:m-[60px]">
                <template v-for="user in userList" :key="user.userNo">
                    <PaticipateProfile v-if="user.roomUserNo === roomUserInfo.roomUserNo"
                        :nick-name="user.nickname + ' (나)'" :image-url="user.profileUrl" :title="user.email" :mine="true"
                        class="border-black border-2 border-solid" />

                    <PaticipateProfile :nick-name="user.nickname + ' (마니띠)'" :image-url="user.profileUrl" :maniti="true"
                        :title="user.email" v-else-if="user.usersManito === roomUserInfo.roomUserNo"
                        class="border-A805LightBlue border-2 border-solid" />
                    <PaticipateProfile :nick-name="user.nickname" :image-url="user.profileUrl" :title="user.email" v-else />
                </template>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
