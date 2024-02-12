<script setup lang="ts">
import CheckBox from '@/components/molecules/common/CheckBox.vue'
import InputBox from '@/components/molecules/common/InputBox.vue'
import { ref, type Ref, type ModelRef } from 'vue'
import type { Handler } from '@/types/common'
import type { ProfileInfoType } from '@/types/user'
import ProfileInfo from '@/components/molecules/game/ProfileInfo.vue'
import type { userType } from '@/types/room'
import { rollbackRoomUsers } from '@/api/room'

defineProps({
    userList: {
        type: Array<ProfileInfoType>
    }
})
const emit = defineEmits(['test'])

const testHandler: Handler = () => {
    rollbackRoomUsers(
        {
            roomUserNos: [131]
        },
        ({ data }) => {
            console.log('rollbackRoomUser success', data)
            emit('test')
        },
        (error) => {
            console.log('error', error)
        }
    )
}
const usersApproved: ModelRef<userType[]> = defineModel({ required: true })
</script>

<template>
    <button @click="testHandler">RollBack</button>
    <div class="flex flex-col bg-A805RealWhite overflow-hidden">
        <div>
            <p class="text-3 px-6 py-3">참여 유저</p>
        </div>
        <div class="overflow-y-auto">
            <div v-for="user in usersApproved" :key="user.roomUserNo" class="px-4 py-2">
                <ProfileInfo :name="user.nickname" :image-url="user.profileUrl"></ProfileInfo>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
