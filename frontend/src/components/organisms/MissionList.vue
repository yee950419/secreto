<script setup lang="ts">
import CheckBox from '@/components/molecules/CheckBox.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import { ref } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import { PlusSquareOutlined } from '@ant-design/icons-vue'

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
const datata = ref('')
const ic: DataHandler<string> = (data: string) => {
    datata.value = data
    dummyList.value.push({
        id: dummyList.value.length + 1,
        name: data,
        checked: myMissionChecked.value
    })
    console.log('Add')
}
</script>

<template>
    <div class="bg-A805RealWhite border w-full border-A805DarkGrey">
        <!-- <CheckBox custom-class="checkbox-molecule-style-1">전체 선택</CheckBox> -->
        {{ dummyList }}
        {{ myMissionChecked }}
        <hr class="border-A805DarkGrey" />
        <CheckBox
            v-for="mission in dummyList"
            :key="mission.id"
            custom-class="checkbox-molecule-style-1"
            :custom-id="mission.id"
            v-model="mission.checked"
            >{{ mission.name }} {{ mission.id }}</CheckBox
        >
        <div class="flex flex-col"></div>
        <CheckBox
            custom-class="checkbox-molecule-style-1"
            custom-id="new-mission-checkbox"
            v-model="myMissionChecked"
        >
            <InputBox
                custom-class="input-box-style-1 px-0"
                custom-id="new-mission"
                @input-enter="ic"
            />
        </CheckBox>
        <div class="flex items-center" custom-class="checkbox-molecule-style-1" @click="() => {}">
            <PlusSquareOutlined class="my-[5px] mx-[8px]" />
            추가 하기
        </div>
        <InputBox custom-class="input-box-style-2"></InputBox>
    </div>
</template>

<style scoped></style>
