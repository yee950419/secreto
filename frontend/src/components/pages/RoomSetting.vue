<script setup lang="ts">
import { computed, ref, type Ref, watch, onMounted, onUnmounted, inject, type PropType } from 'vue'
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
import { Calendar as VCalendar, DatePicker as VDatePicker } from 'v-calendar'
import IconArrowRight from 'v-calendar'
import 'v-calendar/style.css'

// const props = defineProps({
//     roomInfo: { type: Object as () => RoomInfoType, required: true }
// })
const roomInfo = ref<RoomInfoType>({
    entryCode: '',
    commonYn: true,
    hostParticipateYn: '',
    hostRoomUserNo: 0,
    missionSubmitTime: '',
    missionStartAt: '',
    roomEndAt: '',
    roomName: '',
    roomNo: 0,
    roomStartAt: '',
    roomStartYn: false,
    roomStatus: '',
    userInfo: {
        nickname: '',
        profileUrl: '',
        roomUserNo: 0
    }
})
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

watch(roomUserInfo, (newValue) => {
    roomName.value = newValue.roomName
})
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
const roomCode: Ref<string> = inject('roomCode', ref('testCode'))
const dateTimeFormat = 'YYYY-MM-DD HH:mm'
const gamePeriod = ref<[Dayjs, Dayjs]>([
    dayjs(),
    dayjs().add(1, 'day')
    // dayjs(dayjs(), dateTimeFormat),
    // dayjs(dayjs().add(1, 'day'), dateTimeFormat)
])
const range = ref<{ start: string; end: string }>({
    start: dayjs().format(dateTimeFormat),
    end: dayjs().add(1, 'day').format(dateTimeFormat)
})
const crange = computed(() => {
    return [
        dayjs(range.value.start).format(dateTimeFormat),
        dayjs(range.value.end).format(dateTimeFormat)
    ]
})
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
    console.log('?', {
        period: missionInterval.value,
        commonYn: !isInvidual.value,
        hostParticipantYn: hostInGame.value,
        missionStartAt: crange.value[0].slice(0, 10),
        missionSubmitTime: crange.value[0].slice(11) + ':00',
        roomEndAt: crange.value[1] + ':00',
        missionList: checkedMissons.value
    })
    startRoom(
        roomUserInfo.value.roomNo,
        {
            period: missionInterval.value,
            commonYn: !isInvidual.value,
            hostParticipantYn: hostInGame.value,
            missionStartAt: crange.value[0].slice(0, 10),
            missionSubmitTime: crange.value[0].slice(11) + ':00',
            roomEndAt: crange.value[1] + ':00',
            missionList: checkedMissons.value
        },
        // {
        //     period: missionInterval.value,
        //     commonYn: !isInvidual.value,
        //     hostParticipantYn: hostInGame.value,
        //     missionStartAt: gamePeriod.value[0].format(dateTimeFormat).slice(0, 10),
        //     missionSubmitTime: gamePeriod.value[0].format(dateTimeFormat).slice(11) + ':00',
        //     roomEndAt: gamePeriod.value[1].format(dateTimeFormat) + ':00',
        //     missionList: checkedMissons.value
        // },
        ({ data }) => {
            console.log(':)', data)
            router.push({
                name: 'game-participate'
            })
        },
        (error) => {
            console.log(':(', error.response.data.message)
            alert(error.response.data.message)
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
        roomUserInfo.value.roomNo,
        ({ data }) => {
            roomInfo.value = data.result
            roomName.value = data.result.roomName
            console.log('roomInfo', roomInfo.value)
        },
        (error) => {
            console.log(':(', error)
        }
    )
}

const changeRoomNameHandler: Handler = () => {
    console.log('???')
    changeRoomName(
        { roomName: roomName.value, roomNo: roomUserInfo.value.roomNo },
        ({ data }) => {
            if (data.status === 'OK') {
                console.log(':)', data)
                console.log(roomName.value)
                emit('roomNameChanged', roomName.value)
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
    if (testobj.content.length > 0) {
        addUnexpectedMission(
            testobj,
            ({ data }) => {
                console.log(':)', data)
            },
            (error) => {
                console.error(':(', error)
            }
        )
    } else {
        alert('미션 내용을 입력해주세요.')
    }
}

const gameEndHandler: Handler = () => {
    endRoom(
        { roomNo: roomUserInfo.value.roomNo },
        ({ data }) => {
            console.log(':)', data)
            router.push({
                name: 'game-statistic'
            })
        },
        (error) => {
            console.error(':(', error)
        }
    )
}

const refreshUserList = setInterval(() => {
    userListGet()
}, 5000)

onMounted(async () => {
    await roomInfoGet()
    await missionGet()
    await userListGet()
})

onUnmounted(() => {
    clearInterval(refreshUserList)
})
</script>

<template>
    <div class="bg-A805RealWhite flex flex-1 max-w-[1920px] flex-col">
        <div class="flex justify-center max-lg:flex-col gap-3 m-[3%]">
            <div
                name="main"
                class="flex max-2xl:flex-col px-3 gap-3 w-full lg:w-[130%] 2xl:w-[200%]"
            >
                <div name="main-1" class="flex flex-col w-full px-3 gap-3">
                    <ButtonInputBox
                        label="방 제목"
                        label-class="text-[1.5rem]"
                        class="w-full"
                        button-class="button-blue text-white line-darkgrey  border-s-0"
                        input-class="w-full rounded-s-[100px] text-center line-darkgrey bg-white"
                        v-model="roomName"
                        button-label="수정"
                        @button-click="changeRoomNameHandler"
                    />
                    <!-- status 연동 필요 -->
                    <!-- <div v-if="test.roomStatus === 'WAIT'" name="before-start"> -->
                    <div
                        v-if="roomInfo.roomStartYn === false || roomInfo.roomStatus === 'END'"
                        name="before-start"
                    >
                        <MissionList v-model="missionList"></MissionList>
                        <div name="option-list" class="flex gap-[10%] pt-4 px-3">
                            <CheckBox v-model="isInvidual" class="gap-3"
                                >각자 다른 미션 받기</CheckBox
                            >
                            <CheckBox v-model="hostInGame" class="gap-3">방장도 게임 참여</CheckBox>
                        </div>
                    </div>
                    <!-- <div v-else-if="test.roomStatus === 'PARTICIPANT'"> -->
                    <div v-else class="flex flex-col gap-3 mt-5">
                        <UnexpectedMission
                            v-model:content="unexpectedMissionContent"
                            v-model:reserved="unexpectedMissionReserved"
                            v-model:time="unexpectedMissionReservationTime"
                            @add-unexpected-mission="addUnexpectedMissionHandler"
                        ></UnexpectedMission>
                        <!-- 추후 개발 필요 -->
                        <!-- <ExpectedMissionList></ExpectedMissionList> -->
                    </div>
                </div>
                <div name="main-2" class="flex flex-col w-full px-3 gap-3">
                    <div name="main-2-1" class="flex max-md:flex-col gap-3 md:justify-between">
                        <DateButton
                            class="w-full max-w-[300px]"
                            custon-class="w-full"
                            label-class="text-[1.5rem]"
                            button-class="w-[50%] button-style-7 button-blue text-white"
                            input-class="w-full min-w-[10px] input-box-style-4"
                            type="number"
                            label="미션 주기"
                            v-model="missionInterval"
                        >
                        </DateButton>

                        <ButtonInputBox
                            :readonly="true"
                            label="초대 코드"
                            button-label="복사"
                            v-model="roomInfo.entryCode"
                            label-class="text-[1.5rem]"
                            custom-class="w-full max-w-[300px]"
                            input-class="w-[70%] h-[45px] text-center text-[1rem]"
                            button-class="w-[30%] text-[11pt] min-w-15 button-blue button-style-7 text-white text-[20pt]"
                            @button-click="clipboardHandler"
                        ></ButtonInputBox>
                    </div>

                    <label for="range" class="text-[1.5rem]">마니또 기간</label>
                    <div name="calendar-div w-full" id="range">
                        <!-- <Calendar :fullscreen="false" class="h-[40%]"></Calendar> -->
                        <VDatePicker
                            v-model.range="range"
                            mode="dateTime"
                            class="!w-full !text-18pt"
                            :rules="{
                                minutes: 0,
                                seconds: 0
                            }"
                            :time-accuracy="1"
                        ></VDatePicker>
                    </div>
                </div>
            </div>
            <div name="side-part" class="flex flex-col w-full lg:w-[70%] px-3">
                <UnapprovedUserList
                    v-model="unapprovedUserList"
                    class="h-[50%] border-b-2"
                    @users-approved="userListGet"
                    @users-denied="userListGet"
                ></UnapprovedUserList>
                <hr />
                <ApprovedUserList
                    v-model="approvedUserList"
                    class="h-[50%]"
                    @test="userListGet"
                ></ApprovedUserList>
            </div>
        </div>
        <ButtonAtom
            v-if="roomInfo.roomStartYn === false || roomInfo.roomStatus === 'END'"
            custom-class="button-blue h-[10%] min-h-[50px] text-A805RealWhite"
            @button-click="gameStartHandler"
            ><p class="md:text-[3rem]">게임 시작하기</p>
        </ButtonAtom>
        <ButtonAtom
            v-else
            custom-class="bg-A805Red h-[10%] text-A805RealWhite"
            @button-click="gameEndHandler"
            ><p class="md:text-[3rem]">게임 종료하기</p>
        </ButtonAtom>
    </div>
</template>
