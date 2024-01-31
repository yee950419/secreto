<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { EditOutlined, CaretUpOutlined } from '@ant-design/icons-vue'
</script>

<template>
    <div class="w-full h-[40px] flex justify-between items-center">
        <div class="flex gap-[10px]">
            <ButtonAtom
                custom-class="button-style-4 button-claret text-[18px] w-[95px] font-bold flex justify-center items-center gap-[5px]"
                ><EditOutlined /><span>글쓰기</span></ButtonAtom
            >
            <ButtonAtom
                custom-class="button-style-4 button-lightGrey text-[18px] w-[70px] font-bold"
                >수정</ButtonAtom
            >
            <ButtonAtom
                custom-class="button-style-4 button-lightGrey text-[18px] w-[70px] font-bold"
                >삭제</ButtonAtom
            >
        </div>
        <div class="flex gap-[10px]">
            <ButtonAtom
                custom-class="button-style-4 button-lightGrey text-[18px] w-[80px] font-bold flex justify-center items-center"
                ><CaretUpOutlined /><span>TOP</span></ButtonAtom
            >
            <ButtonAtom
                custom-class="button-style-4 button-lightGrey text-[18px] w-[70px] font-bold"
                >목록</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
