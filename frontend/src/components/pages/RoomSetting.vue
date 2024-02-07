<script setup lang="ts">
import { computed, ref, type Ref, watch, onMounted, inject, type PropType } from 'vue'
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
import { getUserList, startRoom, endRoom } from '@/api/room'
import { DatePicker, Calendar } from 'ant-design-vue'
import { getRoom } from '@/api/room'
import defaultImage from '@/assets/images/default-avatar.png'
import { changeRoomName } from '@/api/room'
import type { RoomUserInfoType, userType, roomStartType, RoomInfoType } from '@/types/room'
import { useRoute } from 'vue-router'
import router from '@/router'
import ExpectedMissionList from '@/components/organisms/game/ExpectedMissionList.vue'
import UnexpectedMission from '@/components/organisms/game/UnexpectedMission.vue'
import { addUnexpectedMission } from '@/api/mission'

const props = defineProps({
    roomInfo: { type: Object as () => RoomInfoType, required: true }
})
console.log('?S?ADFSADf', props.roomInfo)
// const test: RoomInfoType = props.roomInfo
const route = useRoute()

const emit = defineEmits(['roomNameChanged'])
const missionList = ref<Mission[]>([])
const checkedMissons = computed<{ content: string }[]>(() => {
    return missionList.value
        .filter((mission) => mission.checked)
        .map((mission) => ({
            content: mission.content
        })) as { content: string }[]
})

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
const gamePeriod = ref<[Dayjs, Dayjs]>([
    dayjs(),
    dayjs().add(1, 'day')
    // dayjs(dayjs(), dateTimeFormat),
    // dayjs(dayjs().add(1, 'day'), dateTimeFormat)
])
const unexpectedMissionContent = ref<string>('')
const unexpectedMissionReserved = ref<boolean>(false)
const unexpectedMissionReservationTime = ref<Dayjs>(dayjs())

const { RangePicker } = DatePicker
const { toClipboard } = useClipboard()
const clipboardHandler: Handler = () => {
    toClipboard(roomCode.value)
    console.log(roomCode.value)
    alert('복사했습니다.')
}

const gameStartHandler: Handler = () => {
    startRoom(
        roomUserInfo.value.roomNo,
        {
            period: missionInterval.value,
            commonYn: !isInvidual.value,
            hostParticipantYn: hostInGame.value,
            missionStartAt: gamePeriod.value[0].format(dateTimeFormat).slice(0, 10),
            missionSubmitTime: gamePeriod.value[0].format(dateTimeFormat).slice(11) + ':00',
            roomEndAt: gamePeriod.value[1].format(dateTimeFormat) + ':00',
            missionList: checkedMissons.value
        },
        ({ data }) => {
            console.log(':)', data)
            router.push({
                name: 'game-participate'
            })
        },
        (error) => {
            console.log(':(', error)
        }
    )
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

const addUnexpectedMissionHandler: Handler = () => {
    const testobj = {
        roomNo: roomUserInfo.value.roomNo,
        content: unexpectedMissionContent.value
    }
    console.log('?', testobj)
    addUnexpectedMission(
        testobj,
        ({ data }) => {
            console.log(':)', data)
        },
        (error) => {
            console.error(':(', error)
        }
    )
}

const gameEndHandler: Handler = () => {
    endRoom(
        { roomNo: roomUserInfo.value.roomNo },
        ({ data }) => {
            console.log(':)', data)
        },
        (error) => {
            console.error(':(', error)
        }
    )
}
onMounted(async () => {
    await missionGet()
    await userListGet()
    console.log('?S??f', props.roomInfo)
})
</script>

<template>
    <!-- {{ props.roomInfo }} -->
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
                    <!-- status 연동 필요 -->
                    <!-- <div v-if="test.roomStatus === 'WAIT'" name="before-start"> -->
                    <div v-if="true" name="before-start">
                        <MissionList v-model="missionList"></MissionList>
                        <div name="option-list" class="flex gap-[10%]">
                            <CheckBox v-model="isInvidual">각자 다른 미션 받기</CheckBox>
                            <CheckBox v-model="hostInGame">방장도 게임 참여</CheckBox>
                        </div>
                    </div>
                    <!-- <div v-else-if="test.roomStatus === 'PARTICIPANT'"> -->
                    <div v-else>
                        <UnexpectedMission
                            v-model:content="unexpectedMissionContent"
                            v-model:reserved="unexpectedMissionReserved"
                            v-model:time="unexpectedMissionReservationTime"
                            @add-unexpected-mission="addUnexpectedMissionHandler"
                        ></UnexpectedMission>
                        <!-- 추후 개발 필요 -->
                        <ExpectedMissionList v-if="false"></ExpectedMissionList>
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
                            v-model:value="gamePeriod"
                            :format="dateTimeFormat"
                        />
                    </div>
                    <div name="calendar-div">
                        <Calendar :fullscreen="false" class="h-[40%]"></Calendar>
                    </div>
                    <ButtonAtom
                        aaav-if="test.roomStatus === 'WAIT'"
                        v-if="true"
                        custom-class="button-blue h-[10%] text-A805RealWhite"
                        @button-click="gameStartHandler"
                        >게임 시작하기</ButtonAtom
                    >
                    <ButtonAtom
                        aaav-else-if="test.roomStatus === 'PARTICIPANT'"
                        v-else
                        custom-class="bg-A805Red h-[10%] text-A805RealWhite"
                        @button-click="gameEndHandler"
                        >게임 종료하기</ButtonAtom
                    >
                </div>
                <!-- </div> -->
                <div name="side-part" class="flex flex-col w-[400px] max-md:w-full">
                    <UnapprovedUserList
                        v-model="unapprovedUserList"
                        class="h-[50%] border-b-2"
                        @users-approved="userListGet"
                        @users-denied="userListGet"
                    ></UnapprovedUserList>
                    <hr />
                    <ApprovedUserList v-model="approvedUserList" class="h-[50%]"></ApprovedUserList>
                </div>
            </div>
        </div>
    </div>
</template>
