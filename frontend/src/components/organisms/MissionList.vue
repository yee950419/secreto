<script setup lang="ts">
import CheckBox from '@/components/molecules/CheckBox.vue'
import { ref } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import { CheckOutlined, PlusSquareOutlined } from '@ant-design/icons-vue'

const myMisssionName = ref('')
const myMissionChecked = ref(true)
const dummyList = ref([
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

const addMission: Handler = () => {
    dummyList.value.push({
        id: dummyList.value.length + 1,
        name: myMisssionName.value,
        checked: myMissionChecked.value
    })
    myMisssionName.value = ''
    myMissionChecked.value = true
}
</script>

<template>
    <div class="bg-A805RealWhite border w-full border-A805DarkGrey">
        <!-- <CheckBox custom-class="checkbox-molecule-style-1">전체 선택</CheckBox> -->
        {{ dummyList }}
        <hr class="border-A805DarkGrey" />
        <CheckBox
            v-for="mission in dummyList"
            :key="mission.id"
            custom-class="checkbox-molecule-style-1"
            :custom-id="mission.id"
            v-model="mission.checked"
            >{{ mission.name }}</CheckBox
        >
        <CheckBox
            custom-class="checkbox-molecule-style-1"
            :custom-id="123"
            v-model="myMissionChecked"
            ><input type="text" :value="myMisssionName" class="w-10"
        /></CheckBox>
        <CheckBox custom-class="checkbox-molecule-style-1" :custom-id="123">aaa</CheckBox>
        <div class="flex items-center" custom-class="checkbox-molecule-style-1" @click="addMission">
            <PlusSquareOutlined class="my-[5px] mx-[8px]" />
            추가 하기
        </div>
    </div>
</template>

<style scoped></style>
