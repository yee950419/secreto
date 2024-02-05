<script setup lang="ts">
import CheckBox from '@/components/molecules/common/CheckBox.vue'
import InputBox from '@/components/molecules/common/InputBox.vue'
import { ref } from 'vue'
import type { Handler } from '@/types/common'
import type { Mission } from '@/types/mission'
import { PlusSquareOutlined } from '@ant-design/icons-vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { DatePicker, Calendar } from 'ant-design-vue'

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
    <div class="flex flex-col bg-A805Red overflow-hidden w-[600px] h-[800px]">
        <InputBox
            label="돌발 미션"
            input-class="input-box-style-3 rounded-[100px] text-center line-darkgrey bg-white w-full"
        ></InputBox>
        <div name="mission-options" class="flex max-md:flex-col bg-A805Purple">
            <CheckBox custom-class="w-[200px]">qwerewrqw</CheckBox>
            <DatePicker class="w-[200px]"></DatePicker>
        </div>
        <ButtonAtom custom-class="button-blue text-A805RealWhite">미션 추가</ButtonAtom>
    </div>
</template>

<style scoped></style>
