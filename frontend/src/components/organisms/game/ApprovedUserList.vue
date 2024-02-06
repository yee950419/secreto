<script setup lang="ts">
import CheckBox from '@/components/molecules/common/CheckBox.vue'
import InputBox from '@/components/molecules/common/InputBox.vue'
import { ref, type Ref, type ModelRef } from 'vue'
import type { Handler } from '@/types/common'
import type { ProfileInfoType } from '@/types/user'
import ProfileInfo from '@/components/molecules/game/ProfileInfo.vue'
import type { userType } from '@/types/room'

defineProps({
    userList: {
        type: Array<ProfileInfoType>
    }
})
const usersApproved: ModelRef<userType[]> = defineModel({ required: true })
</script>

<template>
    <div class="flex flex-col bg-A805RealWhite overflow-hidden">
        <div>
            <p class="text-[32px] px-6 py-5">참여 유저</p>
        </div>
        <div class="overflow-y-auto">
            <div v-for="user in usersApproved" :key="user.roomUserNo" class="px-4 py-2">
                <ProfileInfo :name="user.nickname" :image-url="user.profileUrl"></ProfileInfo>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
