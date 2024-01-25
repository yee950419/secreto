<script setup lang="ts">
import CheckBox from '@/components/molecules/CheckBox.vue'
import { ref } from 'vue'
import type { DataHandler } from '@/types/common'
import { PlusSquareOutlined } from '@ant-design/icons-vue'
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
const falseValue = ref(true)
const listChecked: DataHandler<boolean> = (data: boolean) => {
    if (data) {
        dummyList.value.forEach((item) => {
            item.checked = true
        })
    } else {
        dummyList.value.forEach((item) => {
            item.checked = false
        })
    }
}
const check = ref(false)
const checkBoxStateHandler: DataHandler<object> = (ob) => {
    ob.checked = !ob.checked
    console.log(ob)
}
</script>

<template>
    <div class="bg-A805RealWhite border w-full border-A805DarkGrey">
        <CheckBox custom-class="checkbox-molecule-style-1">전체 선택</CheckBox>
        {{ dummyList }}
        <hr class="border-A805DarkGrey" />
        <CheckBox
            v-for="mission in dummyList"
            :key="mission.id"
            :value="mission.id"
            custom-class="checkbox-molecule-style-1"
            :checkbox-content="mission"
            @check-box-change="checkBoxStateHandler(mission)"
            >{{ mission.name }} {{ mission.checked }}!</CheckBox
        >
        <div class="flex items-center" custom-class="checkbox-molecule-style-1">
            <PlusSquareOutlined class="my-[5px] mx-[8px]" />
            추가 하기
        </div>
        <input type="checkbox" :checked="falseValue" />
        {{ dummyList }}
    </div>
</template>

<style scoped></style>
