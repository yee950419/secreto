<script setup lang="ts">
import { ref, type Ref } from 'vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import SelectBox from '@/components/molecules/SelectBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
import CheckBox from '@/components/molecules/CheckBox.vue'
import ProfileInfo from '@/components/molecules/ProfileInfo.vue'
import HeaderProfile from '@/components/molecules/HeaderProfile.vue'
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import DateButton from '@/components/molecules/DateButton.vue'
import ApprovedUserList from '@/components/organisms/ApprovedUserList.vue'
import MissionList from '@/components/organisms/MissionList.vue'
import type { DataHandler, Handler } from '@/types/common'
import type { ProfileInfoType, ProfileInfoCheckBoxType } from '@/types/user'
import UnapprovedUserList from '@/components/organisms/UnapprovedUserList.vue'
const seen = ref(false)
const check = ref(false)
const testhandler = () => {
    seen.value = !seen.value
}
const roomName = ref('당신만의 수호천사 Screto')

const dummyUserList: Ref<ProfileInfoType[]> = ref([
    {
        id: 1,
        nickname: 'test1',
        profileUrl: 'src/assets/images/member/member1.png',
        email: 'test1@test.com'
    },
    {
        id: 2,
        nickname: 'test2',
        profileUrl: 'src/assets/images/member/member2.png',
        email: 'test2@test.com'
    },
    {
        id: 3,
        nickname: 'test3',
        profileUrl: 'src/assets/images/member/member3.png',
        email: 'test3@test.com'
    }
])
const dummyWaitingUserList: Ref<ProfileInfoType[]> = ref([
    {
        id: 4,
        nickname: 'test4',
        profileUrl: 'src/assets/images/member/member4.png',
        email: 'test1@test.com'
    },
    {
        id: 5,
        nickname: 'test5',
        profileUrl: 'src/assets/images/member/member5.png',
        email: 'test2@test.com'
    },
    {
        id: 6,
        nickname: 'test6',
        profileUrl: 'src/assets/images/member/member6.png',
        email: 'test6@test.com'
    }
])
</script>

<template>
    <div class="bg-A805White">
        {{ seen }}
        <ButtonAtom
            @button-click="testhandler"
            custom-class="button-style-1 button-border-violet button-claret"
            >aqwe</ButtonAtom
        >
        <ButtonAtom custom-class="button-style-2" />
        <ModalTemplate v-if="seen" custom-class="modal-template-style-1" @modal-close="testhandler">
            qwerasdf
        </ModalTemplate>

        <SelectBox custom-class="select-box-style-1"></SelectBox>
        <SelectBox custom-class="select-box-style-2"></SelectBox>
        {{ check }}
        <!-- <CheckBox
            custom-class="check-box-style-1"
            custom-id="first-id"
            @check-box-change="checkBoxStateHandler"
            >label</CheckBox
        > -->
        <ButtonInputBox custom-class="button-input-box-style-1"></ButtonInputBox>
        <MissionList></MissionList>
        <ButtonInputBox
            label="방 제목"
            button-class="button-blue text-white line-darkgrey  border-s-0"
            input-class="input-box-style-3 text-center line-darkgrey bg-white"
            v-model="roomName"
            button-label="수정"
        />
        <ProfileInfo name="test" image-url="src/assets/images/member/member1.png"></ProfileInfo>

        <hr />
        <UnapprovedUserList :user-list="dummyWaitingUserList"></UnapprovedUserList>
        <hr />
        <ApprovedUserList :user-list="dummyUserList"></ApprovedUserList>
        <DateButton></DateButton>

        <ModalTemplate :seen="seen" @modal-close="testhandler">asdf</ModalTemplate>
    </div>
</template>
