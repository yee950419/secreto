<script setup lang="ts">
//TODO: 마니또 예측 카드 컴포넌트 구현 필요
import TextAtom from '@/components/atoms/TextAtom.vue'
</script>

<template>
    <div>
        <div class="flex flex-col">
            <div class="flex justify-between">
                <TextAtom>2024.01.15</TextAtom><TextAtom>마니또 예측</TextAtom>
            </div>
            <TextAtom>나 -> 너</TextAtom>
            <TextAtom>예측결과 : 실패</TextAtom>
        </div>
        <div class="w-full my-[10px] cursor-pointer">
            <TextAtom> ~~~~~~~~~~~~~~~ 의 사유로 OO님을 마니또로 예측하셨습니다 </TextAtom>
        </div>
    </div>
</template>

<style scoped></style>
