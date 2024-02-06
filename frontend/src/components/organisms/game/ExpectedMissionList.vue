<script setup lang="ts">
import CheckBox from '@/components/molecules/common/CheckBox.vue'
import InputBox from '@/components/molecules/common/InputBox.vue'
import { ref } from 'vue'
import type { Handler } from '@/types/common'
import type { Mission } from '@/types/mission'
import { PlusSquareOutlined } from '@ant-design/icons-vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { DatePicker, Calendar } from 'ant-design-vue'
import ExpectedMission from '@/components/molecules/game/ExpectedMission.vue'

const myMisssionName = ref('')
const myMissionChecked = ref(true)
const allMissionChecked = ref(false)
const missionInputVisibility = ref(false)
// 데이터 바뀌면 v-model로 바꿀 예정
const missionList = ref([
    {
        id: 1,
        name: 'test1',
        checked: true
    },
    {
        id: 2,
        name: 'test2',
        checked: false
    },
    {
        id: 3,
        name: 'test3',
        checked: false
    }
])
const addInputBox: Handler = () => {
    if (!missionInputVisibility.value) {
        missionInputVisibility.value = true
    }
}
const addMission: Handler = () => {
    missionList.value.push({
        id: missionList.value.length + 1,
        name: myMisssionName.value,
        checked: myMissionChecked.value
    })
    myMisssionName.value = ''
}
const allChangeHandler: Handler = () => {
    missionList.value.forEach((mission: Mission) => {
        mission.checked = allMissionChecked.value
    })
}
</script>

<template>
    <div class="flex flex-1 flex-col gap-3 overflow-hidden">
        <h1 class="text-2xl">예약 리스트</h1>
        <div
            name="list-container"
            class="flex flex-1 flex-col-reverse border-2 max-h-[300px] overflow-y-auto"
        >
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ1</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ2</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ3</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ4</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ5</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ6</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ7</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ8</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ8</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ8</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ8</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ8</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ8</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ8</ExpectedMission>
            <ExpectedMission>ㅁㄴㅇㄻㄴㅇㄹ8</ExpectedMission>
        </div>
    </div>
</template>

<style scoped></style>
