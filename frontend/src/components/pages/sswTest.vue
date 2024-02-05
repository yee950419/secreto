<script setup lang="ts">
import { ref, type Ref } from 'vue'
import type { UserMission, RoomMission } from '@/types/mission'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import BadgeAtom from '@/components/atoms/BadgeAtom.vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import InferenceModalContent from '@/components/organisms/modal/InferenceModalContent.vue'
import { ReloadOutlined } from '@ant-design/icons-vue'
import type { DataHandler, Handler } from '@/types/common'
import type { RoomUserInfoType } from '@/types/room'
// import { Card } from 'ant-design-vue'

const missions = ref<Array<UserMission>>([
    {
        content: 'MBTI 물어보기',
        missionReceivedAt: '2024/01/19',
        missionType: 'individual',
        missionRerollCount: 2,
        missionCertifyYn: false
    },
    {
        content: '자그마한 선물 주기',
        missionReceivedAt: '2024/01/17',
        missionType: 'common',
        missionRerollCount: 2,
        missionCertifyYn: true
    },
    {
        content:
            'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        missionReceivedAt: '2024/01/15',
        missionType: 'individual',
        missionRerollCount: 2,
        missionCertifyYn: true
    }
])

const roomMissions = ref<Array<RoomMission>>([
    {
        content: '미션1'
    },
    {
        content: '미션2'
    },
    {
        content: '미션3'
    }
])

const userInfo = ref<RoomUserInfoType>({
    roomNo: 1,
    roomUserNo: 4,
    roomName: 'Dummy',
    roomNickname: 'Dum Nick Dum Nick',
    profileUrl: ''
})
const modalSeen = ref<boolean>(false)
const rerollHandler: Handler = () => {
    console.log('reroll')
}
const goToMissionCertificationPage: DataHandler<UserMission> = (mission) => {
    console.log(mission)
}
const modalCloseHandler: Handler = () => {
    modalSeen.value = false
}
</script>

<template>
    <div class="flex flex-col w-full bg-A805RealWhite">
        <div
            name="mission-header"
            class="flex justify-between items-center md:px-5 md:py-6 px-3 py-5 w-full md:min-w-[980px] overflow-x-auto"
        >
            <div class="flex items-center gap-5 md:gap-10">
                <div>
                    <h1 class="flex text-[24pt] max-md:text-[12pt]">
                        <p class="me-3">진행 중인 미션:</p>
                        <p>
                            {{ missions[0].content }}
                        </p>
                    </h1>
                </div>

                <ButtonAtom
                    class="relative flex text-[20pt] max-md:text-[10pt] justify-center items-center"
                    @button-click="rerollHandler"
                >
                    <ReloadOutlined class="md:text-[40pt] text-[20pt] absolute"></ReloadOutlined>
                    <p>{{ missions[0].missionRerollCount }}</p>
                </ButtonAtom>
            </div>
            <ButtonAtom
                class="button-cream text-A805DarkGrey max-md:w-[90px] w-[210px] max-md:h-[20px] button-style-2 transition-none max-md:text-[8pt]"
                >전체 미션 보기</ButtonAtom
            >
        </div>
        <hr />
        <div class="flex max-md:flex-col text-[20pt]">
            <div name="user-mission-list" class="w-full max-md:w-full overflow-auto">
                <!-- 마니또 변경 기능을 위한 v-for 추가 필요 -->
                <div class="md:p-4">
                    <div
                        v-for="(mission, missionIndex) in missions"
                        :key="missionIndex"
                        class="flex gap-7 content-center items-start mb-3 p-3 max-md:justify-between"
                    >
                        <div class="flex max-md:flex-col ps-3 items-start">
                            <div class="flex content-center items-center gap-5 me-5">
                                <BadgeAtom
                                    :class="{
                                        'bg-A805Blue': mission.missionType === 'common',
                                        'bg-A805Green': mission.missionType === 'individual',
                                        'text-white': true,
                                        'button-style-mission-type': true
                                    }"
                                    class="max-md:text-[10pt] max-md:h-[20px] rounded-[100px]"
                                    >{{
                                        mission.missionType === 'common' ? '공통 미션' : '개별 미션'
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
        <button
            @click="
                () => {
                    modalSeen = true
                }
            "
        >
            123
        </button>
        <ModalTemplate
            custom-id="modal"
            custom-class="modal-template-style-1 max-md:w-[100vw]"
            :seen="modalSeen"
            @modal-close="modalCloseHandler"
            ><InferenceModalContent :user-info="userInfo"></InferenceModalContent>
        </ModalTemplate>
    </div>
</template>
