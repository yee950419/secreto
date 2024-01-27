<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import type { Handler } from '@/types/common'
import { ref, type Ref } from 'vue'
import type { RoomCreateRequestType } from '@/types/room'

const emit = defineEmits(['yesButtonHandle', 'noButtonHandle'])
const roomCreateRequest: Ref<RoomCreateRequestType> = ref({
    roomName: '',
    nickname: 'default-name'
})
const yesButtonClick: Handler = () => {
    emit('yesButtonHandle', roomCreateRequest.value)
}
const noButtonClick: Handler = () => {
    emit('noButtonHandle')
}
</script>

<template>
    <div class="flex flex-col justify-center items-center">
        <TextAtom custom-class="text-[48px] font-Iceland text-A805Black">MANITO</TextAtom>
        <div class="flex flex-col justify-center items-center gap-[10px]">
            <InputBox
                label="방 이름"
                type="text"
                label-class="ps-[3px]"
                v-model="roomCreateRequest.roomName"
                input-class="input-box-style-2 bg-A805White line-claret w-[250px] h-[40px]"
                place-holder="방 이름"
            />
            <InputBox
                label="닉네임"
                type="text"
                label-class="ps-[3px]"
                v-model="roomCreateRequest.nickname"
                input-class="input-box-style-2 bg-A805White line-claret w-[250px] h-[40px]"
                place-holder="방에서 사용할 닉네임"
            />
            <div class="flex gap-[20px] mt-[15px] mb-[10px]">
                <ButtonAtom
                    custom-class="button-style-5 button-claret button-shadow w-[100px]"
                    @button-click="yesButtonClick"
                    >방 생성</ButtonAtom
                >
                <ButtonAtom
                    custom-class="button-style-5 button-claret button-shadow w-[100px]"
                    @button-click="noButtonClick"
                    >취소</ButtonAtom
                >
            </div>
        </div>
    </div>
</template>

<style scoped></style>
