<script setup lang="ts">
import { ref, type Ref, onMounted, computed, inject } from 'vue'
import type { UserMission, Mission } from '@/types/mission'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import BadgeAtom from '@/components/atoms/BadgeAtom.vue'
import { ReloadOutlined } from '@ant-design/icons-vue'
import type { DataHandler, Handler } from '@/types/common'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import RoomMissionModalContent from '@/components/organisms/modal/RoomMissionModalContent.vue'
import { getUserMission, getRoomMission } from '@/api/mission'
import { type RoomUserInfoType } from '@/types/room'
import { useRoute } from 'vue-router'
import { type Dayjs } from 'dayjs'
import router from '@/router'
import dayjs from 'dayjs'
import type { PropType } from 'vue'
import GameHeader from '@/components/organisms/game/GameHeader.vue'
// import { Card } from 'ant-design-vue'

const props = defineProps({
    userMission: {
        type: Object as () => UserMission[],
        required: true
    }
})
const route = useRoute()
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

const roomMissions = ref<Array<Mission>>([])

const roomMissionsModalOpen = ref<boolean>(false)
const goToMissionCertificationPage: DataHandler<UserMission> = (mission) => {
    console.log(mission)
    router.push({
        name: 'game-board-write',
        query: { boardCategory: 'CERTIFICATE' }
    })
}
const modalOpenHandler: Handler = () => {
    roomMissionsModalOpen.value = true
}
const modalCloseHandler: Handler = () => {
    roomMissionsModalOpen.value = false
}
const getRoomMissionHandler: Handler = () => {
    console.log('getRoomMission')
    getRoomMission(
        roomUserInfo.value.roomNo,
        ({ data }) => {
            console.log(':)', data)
            roomMissions.value = data.result
        },
        (error) => {
            console.log(':(', error)
        }
    )
}
onMounted(async () => {
    getRoomMissionHandler()
})
</script>

<template>
    <div name="header" class="flex flex-col w-full bg-A805RealWhite">
        <GameHeader :user-mission="props.userMission"
            ><ButtonAtom
                class="button-cream text-A805DarkGrey max-md:w-[90px] w-[210px] max-md:h-[20px] button-style-2 transition-none max-md:text-[8pt]"
                @button-click="modalOpenHandler"
                >전체 미션 보기</ButtonAtom
            ></GameHeader
        >
        <hr />
        <div name="content" class="flex max-md:flex-col text-[20pt]">
            <div name="user-mission-list" class="w-full max-md:w-full overflow-auto">
                <!-- 마니또 변경 기능을 위한 v-for 추가 필요 -->
                <div name="multiple-manito" class="md:p-4 flex flex-col-reverse">
                    <div
                        name="person`s missions"
                        v-for="(mission, missionIndex) in userMission"
                        :key="missionIndex"
                        class="flex gap-7 content-center items-start mb-3 p-3 max-md:justify-between"
                    >
                        <div class="flex max-md:flex-col ps-3 items-start">
                            <div class="flex content-center items-center gap-5 me-5">
                                <BadgeAtom
                                    :class="{
                                        'bg-A805Blue': mission.missionType === 'SUDDEN',
                                        'bg-A805Green': mission.missionType === 'REGULAR',
                                        'text-white': true,
                                        'badge-style-mission-type': true
                                    }"
                                    class="max-md:text-[10pt] max-md:h-[20px] rounded-[200px]"
                                    >{{
                                        mission.missionType === 'REGULAR'
                                            ? '정기 미션'
                                            : '돌발 미션'
                                    }}</BadgeAtom
                                >
                                <div class="md:text-[20pt] text-[12pt]">
                                    <p>{{ mission.missionReceivedAt }}</p>
                                </div>
                            </div>
                            <div class="text-[20pt] max-md:text-[14pt]">
                                {{ mission.content }}
                            </div>
                        </div>
                        <div>
                            <ButtonAtom
                                v-if="!mission.missionCertifyYn"
                                class="button-claret button-style-certification max-md:text-[12pt] max-md:w-[90px] max-md:h-[30px]"
                                @button-click="goToMissionCertificationPage(mission)"
                                >인증하기</ButtonAtom
                            >
                            <ButtonAtom
                                v-if="mission.missionCertifyYn"
                                class="button-lightGrey button-style-certification max-md:text-[12pt] max-md:w-[90px] max-md:h-[30px]"
                                @button-click="goToMissionCertificationPage(mission)"
                                >인증완료</ButtonAtom
                            >
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <ModalTemplate
            custom-id="modal"
            custom-class="modal-template-style-1 max-w-[800px]"
            :seen="roomMissionsModalOpen"
            @modal-close="modalCloseHandler"
            ><RoomMissionModalContent :missions="roomMissions"
        /></ModalTemplate>
    </div>
</template>
