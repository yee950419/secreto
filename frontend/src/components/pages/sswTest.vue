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
import UnexpectedMission from '@/components/organisms/game/UnexpectedMission.vue'
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
        <div>
            <UnexpectedMission></UnexpectedMission>
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
