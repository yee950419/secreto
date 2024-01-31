<script setup lang="ts">
import { computed, ref, type Ref, watch } from 'vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import SelectBox from '@/components/molecules/SelectBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
import ProfileInfo from '@/components/molecules/ProfileInfo.vue'
import DateButton from '@/components/molecules/DateButton.vue'
import ApprovedUserList from '@/components/organisms/ApprovedUserList.vue'
import MissionList from '@/components/organisms/MissionList.vue'
import type { DataHandler, Handler } from '@/types/common'
import type { ProfileInfoType, ProfileInfoCheckBoxType } from '@/types/user'
import UnapprovedUserList from '@/components/organisms/UnapprovedUserList.vue'
import useClipboard from 'vue-clipboard3'
import { type Dayjs } from 'dayjs'
import dayjs from 'dayjs'
import { DatePicker, Calendar } from 'ant-design-vue'

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
const period = ref<number>(7)
// const testChange: DataHandler<number> = (newPeriod: number) => {
//     period.value = newPeriod > 1000 ? 1000 : newPeriod < 1 ? 1 : newPeriod
// }

const roomCode = ref('qwe123rt')
const { toClipboard } = useClipboard()
const clipboardHandler: Handler = () => {
    toClipboard(roomCode.value)
    console.log(roomCode.value)
    alert('복사했습니다.')
}

const { RangePicker } = DatePicker
const dateTimeFormat = 'YYYY-MM-DD HH:mm'
const ddd = ref([dayjs(dayjs(), dateTimeFormat), dayjs(dayjs().add(1, 'day'), dateTimeFormat)])
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
        <DateButton
            custon-class=""
            button-class="button-style-7 button-blue text-white"
            input-class="input-box-style-4"
            type="number"
            label="미션 주기"
            v-model="period"
            >일 마다</DateButton
        >

        <ButtonInputBox
            :readonly="true"
            label="초대 코드"
            button-label="복사"
            v-model="roomCode"
            custom-class="w-[200px]"
            input-class="text-center w-[70%]"
            button-class="button-blue button-style-7 text-white w-[30%] text-[20pt]"
            @button-click="clipboardHandler"
            >qwer1234</ButtonInputBox
        >
        <DatePicker custom-class="bg-A805RealWhite" :format="dateTimeFormat"></DatePicker>
        <div>
            <div></div>
            {{ ddd }}
            <RangePicker showTime :v-model:value="ddd" :format="dateTimeFormat" />
        </div>
        <RangePicker disabled />
        <Calendar class="w-[500px]"></Calendar>
        <!-- <calender></calender> -->
        <ModalTemplate :seen="seen" @modal-close="testhandler">asdf</ModalTemplate>
    </div>
</template>
