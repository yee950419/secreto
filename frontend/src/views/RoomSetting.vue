<script setup lang="ts">
import { computed, ref, type Ref, watch } from 'vue'
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
import HeaderBar from '@/components/organisms/HeaderBar.vue'
import FooterBar from '@/components/organisms/FooterBar.vue'
import type { DataHandler, Handler } from '@/types/common'
import type { ProfileInfoType, ProfileInfoCheckBoxType } from '@/types/user'
import UnapprovedUserList from '@/components/organisms/UnapprovedUserList.vue'
import useClipboard from 'vue-clipboard3'
import moment, { type Moment } from 'moment'
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
    <div class="bg-A805RealWhite flex flex-col">
        <HeaderBar />
        <div class="flex w-full bg-A805Green px-">
            <div name="main-part" class="flex w-[75%] bg-A805Claret">
                <div name="main-1" class="flex flex-col w-[50%] px-4 gap-8 bg-A805Red">
                    <ButtonInputBox
                        label="방 제목"
                        button-class="button-blue text-white line-darkgrey  border-s-0"
                        input-class="input-box-style-3 text-center line-darkgrey bg-white"
                        v-model="roomName"
                        button-label="수정"
                    />
                    <MissionList></MissionList>
                    <div name="option-list" class="flex gap-[10%]">
                        <CheckBox>각자 다른 미션 받기</CheckBox>
                        <CheckBox>방장도 게임 참여</CheckBox>
                    </div>
                </div>
                <div name="main-2" class="flex flex-col w-[50%] px-4 gap-[5%] bg-A805Khaki">
                    <div name="main-2-1" class="flex w-full justify-between px-[10%]">
                        <DateButton
                            custon-class="w-[50%] h-[45px]"
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
                            label-class="text-[15pt]"
                            custom-class="w-[50%]"
                            input-class="text-center w-[70%] text-[15pt]"
                            button-class="button-blue button-style-7 text-white w-[40%] text-[20pt]"
                            @button-click="clipboardHandler"
                            >qwer1234</ButtonInputBox
                        >
                    </div>
                    <div class="flex flex-col">
                        <label for="range">마니또 기간</label>
                        <RangePicker
                            id="range"
                            showTime
                            v-model:value="ddd"
                            :format="dateTimeFormat"
                        />
                    </div>
                    <div name="calendar-div">
                        <Calendar :fullscreen="false" class="w-full h-[40%]"></Calendar>
                    </div>
                    <ButtonAtom custom-class="button-blue w-[100%] h-[10%] text-A805RealWhite"
                        >게임 시작하기</ButtonAtom
                    >
                </div>
            </div>
            <div
                name="side-part"
                class="flex flex-col w-[25%] bg-A805Blue border-2 border-A805DarkGrey"
            >
                <UnapprovedUserList
                    :user-list="dummyWaitingUserList"
                    class="h-[50%] border-b-2"
                ></UnapprovedUserList>
                <hr />
                <ApprovedUserList :user-list="dummyUserList" class="h-[50%]"></ApprovedUserList>
            </div>
        </div>
        <div>
            <FooterBar />
        </div>
    </div>
</template>
