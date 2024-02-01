<script setup lang="ts">
import CheckBox from '@/components/molecules/CheckBox.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import { ref, type Ref } from 'vue'
import type { DataHandler, Handler } from '@/types/common'
import type { ProfileInfoType, ProfileInfoCheckBoxType } from '@/types/user'
import ProfileInfo from '@/components/molecules/ProfileInfo.vue'
import { CheckOutlined, MinusCircleOutlined } from '@ant-design/icons-vue'

const props = defineProps({
    userList: {
        type: Array<ProfileInfoType>,
        required: true
    }
})
const emit = defineEmits(['usersApproved', 'usersDenied'])

const { userList } = props
const userCheckList: Ref<Array<ProfileInfoCheckBoxType>> = ref(
    userList.map((item) => ({
        ...item,
        checked: true
    }))
)
const allChangeHandler: Handler = () => {
    userCheckList.value.forEach((user: ProfileInfoCheckBoxType) => {
        user.checked = allUserChecked.value
    })
}
const usersApproveHandler: Handler = () => {
    const users = userCheckList.value.filter((user) => user.checked)
    emit('usersApproved', users)
}
const usersDeniedHandler: Handler = () => {
    const users = userCheckList.value.filter((user) => user.checked)
    emit('usersDenied', users)
}
const allUserChecked: Ref<boolean> = ref(true)
</script>

<template>
    <div>
        <div class="flex flex-col bg-A805RealWhite overflow-hidden border-b-2 border-A805DarkGrey">
            <div>
                <p class="text-[32px] px-6 py-2">대기 유저</p>
            </div>
            <div
                class="flex items-center justify-between px-4 py-2 bg-A805RealWhite border-b-2 border-A805DarkGrey"
            >
                <CheckBox
                    v-model="allUserChecked"
                    custom-class="checkbox-molecule-style-1 gap-3"
                    @change="allChangeHandler"
                    >전체 선택</CheckBox
                >
                <div class="flex justify-between gap-3">
                    <p class="text-A805Blue cursor-pointer" @click="usersApproveHandler">
                        선택 승인
                    </p>
                    <p class="cursor-pointer" @click="usersDeniedHandler">선택 거절</p>
                </div>
            </div>
            <div class="overflow-y-auto">
                <div v-for="user in userCheckList" :key="user.id" class="px-4 py-2">
                    <div class="flex items-center justify-between">
                        <CheckBox
                            :custom-id="user.id"
                            v-model="user.checked"
                            custom-class="checkbox-molecule-style-1 gap-3"
                        >
                            <ProfileInfo
                                :name="user.nickname"
                                :image-url="user.profileUrl"
                            ></ProfileInfo>
                        </CheckBox>
                        <div class="flex gap-5 text-[32px]">
                            <CheckOutlined
                                class="text-A805Green"
                                @click="$emit('usersApproved', [user])"
                            />
                            <MinusCircleOutlined
                                class="text-A805Red"
                                @click="$emit('usersDenied', [user])"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
