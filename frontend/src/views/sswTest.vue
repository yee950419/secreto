<script setup lang="ts">
import { ref } from 'vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import SelectBox from '@/components/molecules/SelectBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
import CheckBox from '@/components/molecules/CheckBox.vue'
import MissionList from '@/components/organisms/MissionList.vue'
import type { Handler } from '@/types/common'
const seen = ref(false)
const check = ref(false)
const testhandler = () => {
    seen.value = !seen.value
}
const checkBoxStateHandler: Handler = () => {
    check.value = !check.value
}
const roomName = ref('당신만의 수호천사 Screto')
</script>

<template>
    <div class="bg-A805White">
        {{ seen }}
        <ButtonAtom
            @button-click="testhandler"
            custom-class="button-style-1 button-border-violet button-claret"
            >aqwe</ButtonAtom
        >
        <ButtonAtom custom-class="button-style-2" />
        <ModalTemplate v-if="seen" custom-class="modal-template-style-1" @modal-close="testhandler">
            qwer
        </ModalTemplate>
        <SelectBox custom-class="select-box-style-1"></SelectBox>
        <SelectBox custom-class="select-box-style-2"></SelectBox>
        {{ check }}
        <!-- <CheckBox
            custom-class="check-box-style-1"
            custom-id="first-id"
            @check-box-change="checkBoxStateHandler"
            >label</CheckBox
        > -->
        <ButtonInputBox custom-class="button-input-box-style-1"></ButtonInputBox>
        <MissionList></MissionList>
        <ButtonInputBox
            label="방 제목"
            button-class="button-blue text-white line-darkgrey  border-s-0"
            input-class="input-box-style-3 text-center line-darkgrey bg-white"
            v-model="roomName"
            button-label="수정"
        />
    </div>
</template>
