<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue'
import PaticipateProfile from '@/components/molecules/game/PaticipateProfile.vue'
defineProps(['userInfo'])
</script>

<template>
    <div class="flex flex-col justify-center items-center w-full">
        <div name="user=profile-container" class="flex">
            <PaticipateProfile></PaticipateProfile>
            <div name="inference-container" class="flex flex-col">
                <div>
                    <p>{{}}</p>
                </div>
                <div name="buttons-container" class="flex"></div>
            </div>
        </div>
        <div name="memo-container"></div>
        <div name="submit-button-container"></div>
    </div>
</template>

<style scoped></style>
