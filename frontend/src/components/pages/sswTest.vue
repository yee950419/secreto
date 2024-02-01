<script setup lang="ts">
import { ref, type Ref } from 'vue'
import type { UserMission, RoomMission } from '@/types/mission'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { Card } from 'ant-design-vue/es/index'

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
        content: '칭찬 3개 해주기',
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
</script>

<template>
    <div class="flex flex-col w-full bg-A805RealWhite">
        <div
            name="mission-header"
            class="flex justify-between items-center p-5 w-full md:min-w-[980px] overflow-x-auto"
        >
            <div class="flex items-center gap-5">
                <div>
                    <h1 class="flex text-[24pt] max-md:text-[10pt]">
                        <p class="mx-3">진행 중인 미션:</p>
                        <p>
                            {{ missions[0].content }}
                        </p>
                    </h1>
                </div>

                <div>
                    <p class="text-[20pt]">{{ missions[0].missionRerollCount }}</p>
                </div>
            </div>
            <ButtonAtom
                class="button-cream text-A805DarkGrey max-md:w-[120px] w-[210px] button-style-2 max-md:text-[10pt]"
                >전체 미션 보기</ButtonAtom
            >
        </div>
        <hr />
        <div class="flex max-md:flex-col text-[20pt]">
            <div name="user-mission-list" class="w-full max-md:w-full overflow-auto">
                <!-- 마니또 변경 기능을 위한 v-for 추가 필요 -->
                <Card class="p-4">
                    <div
                        v-for="(mission, missionIndex) in missions"
                        :key="missionIndex"
                        class="flex gap-7 content-center items-center"
                    >
                        <div class="text-[14pt]">
                            {{ mission.missionReceivedAt }} {{ mission.content }}
                            <ButtonAtom
                                :class="{
                                    'bg-A805Blue': mission.missionType === 'common',
                                    'bg-A805Green': mission.missionType === 'individual',
                                    'text-white': true,
                                    'button-style-mission-type': true
                                }"
                                >{{
                                    mission.missionType === 'common' ? '공통 미션' : '개별 미션'
                                }}</ButtonAtom
                            >
                        </div>
                        <ButtonAtom
                            v-if="!mission.missionCertifyYn"
                            class="button-claret button-style-certification"
                            >인증하기</ButtonAtom
                        >
                        <ButtonAtom
                            v-if="mission.missionCertifyYn"
                            class="bg-A805LightGrey button-style-certification"
                            >인증완료</ButtonAtom
                        >
                    </div>
                </Card>
            </div>
        </div>
    </div>
    <!-- <div name="mission-list" class="w-[30%] max-md:w-full min-w-[170px] bg-red">
        <h2>미션 목록</h2>
        <div v-for="(mission, missionIndex) in roomMissions" :key="missionIndex">
            {{ mission.content }}
        </div>
    </div> -->
</template>
