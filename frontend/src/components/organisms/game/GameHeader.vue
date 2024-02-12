<script setup lang="ts">
import { computed, inject, ref, type Ref } from 'vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { ReloadOutlined } from '@ant-design/icons-vue'
import type { UserMission } from '@/types/mission'
import { rerollMission } from '@/api/mission'
const props = defineProps({
    userMission: {
        type: Object as () => UserMission[],
        required: true
    }
})
const roomNo: Ref<number> = inject('roomNo', ref(0))
const emit = defineEmits(['reroll'])
const userMissionLength = computed<number>(() => {
    return props.userMission.length
})
const lastMissionNo = computed<number>(() => {
    if (userMissionLength.value > 0) {
        return props.userMission[props.userMission.length - 1].userMissionNo
    } else {
        return 0
    }
})

const rerollHandler = () => {
    console.log('reroll')
    rerollMission(
        {
            roomNo: roomNo.value,
            userMissionNo: lastMissionNo.value
        },
        ({ data }) => {
            console.log(':)', data)
        },
        (error) => {
            console.log(':(', error.response.data.message)
            alert(error.response.data.message)
        }
    )
    emit('reroll')
}
</script>

<template>
    <div
        name="mission-header"
        class="flex justify-between items-center md:px-5 md:py-6 px-3 py-5 w-full md:min-w-[590px] overflow-x-auto"
    >
        <div class="flex items-center gap-5 md:gap-10">
            <div name="now-mission">
                <h1 class="flex text-[24pt] max-md:text-[12pt]">
                    <p class="me-3">진행 중인 미션:</p>
                    <p>
                        {{
                            userMissionLength > 0
                                ? userMission[userMissionLength - 1].content
                                : '없음'
                        }}
                    </p>
                </h1>
            </div>

            <ButtonAtom
                v-if="
                    userMissionLength > 0 &&
                    userMission[userMissionLength - 1].missionType === 'REGULAR'
                "
                class="relative flex text-[20pt] max-md:text-[10pt] justify-center items-center"
                @button-click="rerollHandler"
            >
                <ReloadOutlined class="md:text-[40pt] text-[20pt] absolute"></ReloadOutlined>
                <p>{{ userMission[userMissionLength - 1].missionRerollCount }}</p>
            </ButtonAtom>
        </div>
        <slot></slot>
    </div>
</template>

<style scoped></style>
