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
    <div class="flex flex-col gap-3 justify-between bg-A805Red overflow-hidden">
        <InputBox
            label="돌발 미션"
            label-class="text-2xl"
            input-class="input-box-style-3 rounded-[100px] text-center line-darkgrey bg-white w-full min-h-[3rem]"
            custom-class="flex gap-2"
        ></InputBox>
        <div name="mission-options" class="flex max-md:flex-col bg-A805Purple">
            <CheckBox custom-class="flex flex-1 gap-2 px-2">qwerewrqw</CheckBox>
            <DatePicker class="flex flex-1"></DatePicker>
        </div>
        <ButtonAtom
            custom-class="flex flex-1 button-blue text-A805RealWhite items-center justify-center min-h-[3rem] max-h-[4rem] text-2xl"
            >미션 추가</ButtonAtom
        >
    </div>
</template>

<style scoped></style>
