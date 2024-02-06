<script setup lang="ts">
import { computed, ref, type Ref, watch, onMounted, inject } from 'vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ButtonInputBox from '@/components/molecules/common/ButtonInputBox.vue'
import CheckBox from '@/components/molecules/common/CheckBox.vue'
import DateButton from '@/components/molecules/common/DateButton.vue'
import HeaderBar from '@/components/organisms/common/HeaderBar.vue'
import FooterBar from '@/components/organisms/common/FooterBar.vue'
import type { DataHandler, Handler } from '@/types/common'
import type { ProfileInfoType } from '@/types/user'
import ApprovedUserList from '@/components/organisms/game/ApprovedUserList.vue'
import MissionList from '@/components/organisms/game/MissionList.vue'
import UnapprovedUserList from '@/components/organisms/game/UnapprovedUserList.vue'
import useClipboard from 'vue-clipboard3'
import { type Dayjs } from 'dayjs'
import dayjs from 'dayjs'
import type { Mission } from '@/types/mission'
import { getSystemMission } from '@/api/mission'
import { getUserList } from '@/api/room'
import { DatePicker, Calendar } from 'ant-design-vue'
import { getRoom } from '@/api/room'
import defaultImage from '@/assets/images/default-avatar.png'
import { changeRoomName } from '@/api/room'
import type { RoomUserInfoType, userType } from '@/types/room'
import { useRoute } from 'vue-router'
import router from '@/router'
const route = useRoute()

const emit = defineEmits(['roomNameChanged'])
const missionList = ref<Mission[]>()
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
const roomName = ref(roomUserInfo.value.roomName)
const userList = ref<userType[]>([])
const unapprovedUserList = computed(() => {
    return userList.value.filter((user) => user.standbyYn)
})
const approvedUserList = computed(() => {
    return userList.value.filter((user) => !user.standbyYn)
})
const isInvidual = ref<boolean>(false)
const hostInGame = ref<boolean>(false)
const missionInterval = ref<number>(7)
const roomCode = ref('qwe123rt')
const dateTimeFormat = 'YYYY-MM-DD HH:mm'
const gamePeriod = ref([
    dayjs(dayjs(), dateTimeFormat),
    dayjs(dayjs().add(1, 'day'), dateTimeFormat)
])

const { RangePicker } = DatePicker
const { toClipboard } = useClipboard()
const clipboardHandler: Handler = () => {
    toClipboard(roomCode.value)
    console.log(roomCode.value)
    alert('복사했습니다.')
}

const gameStartHandler: Handler = () => {
    console.log('roomName:', roomName.value)
    console.log('isInvidual:', isInvidual.value)
    console.log('hostInGame:', hostInGame.value)
    console.log('missionInterval:', missionInterval.value)
    console.log('roomCode:', roomCode.value)
    console.log('gamePeriod:', gamePeriod.value[0].toString(), gamePeriod.value[1].toString())
}

const missionGet: Handler = () => {
    getSystemMission(
        ({ data }) => {
            data.result.forEach((mission: Mission) => {
                mission['checked'] = true
            })
            missionList.value = data.result
            console.log('system missions', missionList.value)
        },
        () => {
            console.log('2')
        }
    )
}

const userListGet: Handler = () => {
    getUserList(
        roomUserInfo.value.roomNo,
        ({ data }) => {
            data.result.forEach((mission: userType) => {
                mission['checked'] = true
            })
            console.log('userlist', data)
            userList.value = data.result
        },
        (error) => {
            console.log('error', error)
        }
    )
}
const roomInfoGet: Handler = () => {
    getRoom(
        1,
        (res) => {
            console.log('1', res)
        },
        () => {
            console.log('2')
        }
    )
}

const changeRoomNameHandler: Handler = () => {
    changeRoomName(
        { roomName: roomName.value, roomNo: roomUserInfo.value.roomNo },
        ({ data }) => {
            if (data.status === 'OK') {
                // console.log(':)', data)
                emit('roomNameChanged', roomName)
            } else {
                console.log(':(', data)
            }
        },
        (error) => {
            console.error('error!', error)
        }
    )
}

onMounted(async () => {
    await missionGet()
    await userListGet()
})
</script>

<template>
    <div class="flex flex-1">
        <div class="bg-A805RealWhite flex flex-col">
            <div class="flex justify-center max-md:flex-col gap-3 m-[3%]">
                <!-- <div name="main-part" class="flex selection:max-md:flex-col max-md:w-full"> -->
                <div name="main-1" class="flex flex-col w-[500px] px-4 gap-8 max-md:w-full">
                    <ButtonInputBox
                        label="방 제목"
                        button-class="button-blue text-white line-darkgrey  border-s-0"
                        input-class="input-box-style-3 rounded-s-[100px] text-center line-darkgrey bg-white"
                        v-model="roomName"
                        button-label="수정"
                        @button-click="changeRoomNameHandler"
                    />
                    <MissionList v-model="missionList"></MissionList>
                    <div name="option-list" class="flex gap-[10%]">
                        <CheckBox v-model="isInvidual">각자 다른 미션 받기</CheckBox>
                        <CheckBox v-model="hostInGame">방장도 게임 참여</CheckBox>
                    </div>
                </div>
                <div name="main-2" class="flex flex-col w-[500px] px-4 gap-[5%] max-md:w-full">
                    <div name="main-2-1" class="flex justify-between">
                        <DateButton
                            class=""
                            custon-class=""
                            label-class="text-[15pt]"
                            button-class="button-style-7 button-blue text-white"
                            input-class="w-[60px] input-box-style-4"
                            slot-class="w-[50px] text-[12pt]"
                            type="number"
                            label="미션 주기"
                            v-model="missionInterval"
                            >일 마다</DateButton
                        >

                        <ButtonInputBox
                            :readonly="true"
                            label="초대 코드"
                            button-label="복사"
                            v-model="roomCode"
                            label-class="text-[15pt]"
                            custom-class=""
                            input-class="w-[150px] h-[45px] text-center text-[15pt]"
                            button-class="button-blue button-style-7 text-white text-[20pt]"
                            @button-click="clipboardHandler"
                            >qwer1234</ButtonInputBox
                        >
                    </div>
                    <div class="flex flex-col">
                        <label for="range">마니또 기간</label>
                        <RangePicker
                            id="range"
                            showTime
                            :v-model:value="gamePeriod"
                            :format="dateTimeFormat"
                        />
                    </div>
                    <div name="calendar-div">
                        <Calendar :fullscreen="false" class="h-[40%]"></Calendar>
                    </div>
                    <ButtonAtom
                        custom-class="button-blue h-[10%] text-A805RealWhite"
                        @button-click="gameStartHandler"
                        >게임 시작하기</ButtonAtom
                    >
                </div>
                <!-- </div> -->
                <div name="side-part" class="flex flex-col w-[400px] max-md:w-full">
                    <UnapprovedUserList
                        v-model="unapprovedUserList"
                        :user-list="dummyWaitingUserList"
                        class="h-[50%] border-b-2"
                    ></UnapprovedUserList>
                    <hr />
                    <ApprovedUserList
                        v-model="approvedUserList"
                        :user-list="dummyUserList"
                        class="h-[50%]"
                    ></ApprovedUserList>
                </div>
            </div>
        </div>
    </div>
</template>
