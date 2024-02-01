<script setup lang="ts">
import HeaderProfile from '@/components/molecules/common/HeaderProfile.vue'
import HeaderLogo from '@/components/molecules/common/HeaderLogo.vue'
const emit = defineEmits(['profileClickHandle'])
</script>

<template>
    <div class="px-5 flex items-center w-full justify-between">
        <HeaderLogo />
        <HeaderProfile
            name="sdf"
            :image-url="null"
            custom-class="cursor-pointer"
            @click="() => emit('profileClickHandle')"
        />
    </div>
</template>

<style scoped></style>
