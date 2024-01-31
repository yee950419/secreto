<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { EditOutlined, CaretUpOutlined } from '@ant-design/icons-vue'
</script>

<template>
    <div class="w-full md:h-[40px] flex justify-between items-center max-md:flex-col">
        <div class="flex gap-[10px] max-md:flex-col max-md:w-full max-md:px-2">
            <ButtonAtom
                custom-class="button-style-4 button-claret text-[18px] w-[95px] font-bold flex justify-center items-center gap-[5px] max-md:w-full"
                ><EditOutlined /><span>글쓰기</span></ButtonAtom
            >
            <ButtonAtom
                custom-class="button-style-4 button-lightGrey text-[18px] w-[70px] font-bold max-md:w-full"
                >수정</ButtonAtom
            >
            <ButtonAtom
                custom-class="button-style-4 button-lightGrey text-[18px] w-[70px] font-bold  max-md:w-full"
                >삭제</ButtonAtom
            >
        </div>
        <div class="flex gap-[10px] max-md:flex-col-reverse max-md:w-full max-md:px-2 max-md:mt-3">
            <ButtonAtom
                custom-class="button-style-4 button-lightGrey text-[18px] w-[80px] font-bold flex justify-center items-center  max-md:w-full"
                ><CaretUpOutlined /><span>TOP</span></ButtonAtom
            >
            <ButtonAtom
                custom-class="button-style-4 button-lightGrey text-[18px] w-[70px] font-bold  max-md:w-full"
                >목록</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
