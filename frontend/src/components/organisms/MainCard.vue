<script setup lang="ts">
import MainLogo from '@/components/molecules/MainLogo.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
</script>

<template>
    <div
        class="bg-A805Cream flex flex-col justify-between items-center h-[500px] w-full max-w-[400px] p-[20px]"
    >
        <MainLogo size="200"></MainLogo>
        <div class="text-[16px]">
            <p>
                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quasi consequatur iure
                quos accusantium corrupti velit officiis dignissimos est porro! Tempore aperiam
                quasi saepe, maiores illum aliquam fugit ut consequuntur aut?
            </p>
        </div>
        <ButtonAtom customClass="button-style-1 button-claret button-shadow m-[20px]"
            >START</ButtonAtom
        >
    </div>
</template>

<style scoped></style>
