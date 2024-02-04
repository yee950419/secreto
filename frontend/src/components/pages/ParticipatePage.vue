<script setup lang="ts">
import MissionHeader from '@/components/organisms/game/MissionHeader.vue'
import PaticipateProfile from '@/components/molecules/game/PaticipateProfile.vue'
import { onMounted, watch } from 'vue'
import { ref, type Ref } from 'vue'
import { getUserList } from '@/api/room'
import { inject } from 'vue'
import { useRoute } from 'vue-router'
import type { userType, RoomUserInfoType } from '@/types/room'
const route = useRoute()
const userList = ref<userType[]>()

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

onMounted(() => {
    getRooms()
})
</script>

<template>
    <div class="flex flex-1 flex-col bg-A805White">
        <MissionHeader custom-class="flex w-full min-h-[100px] p-[20px] shadow-lg" />
        <div class="flex flex-1 items-center justify-center">
            <div class="flex flex-wrap items-center justify-center gap-[45px] m-[80px]">
                <template v-for="user in userList" :key="user.userNo">
                    <PaticipateProfile
                        :nick-name="user.nickname"
                        :image-url="''"
                        v-if="user.roomUserNo !== roomUserInfo.roomUserNo"
                    />
                    <PaticipateProfile
                        :nick-name="user.nickname + ' (나)'"
                        :image-url="''"
                        class="border-black border-2 border-solid"
                        v-else
                    />
                </template>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
