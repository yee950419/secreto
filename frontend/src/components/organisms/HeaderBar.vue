<script setup lang="ts">
import HeaderLogo from '@/components/molecules/HeaderLogo.vue'
import HeaderProfile from '@/components/molecules/HeaderProfile.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { MenuOutlined } from '@ant-design/icons-vue'
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useUserStore } from '@/stores/user'
import { storeToRefs } from 'pinia'

const userStore = useUserStore()
const { userInfo } = storeToRefs(userStore)

const windowWidth = ref(window.innerWidth)
const isMdOrLarger = computed(() => windowWidth.value >= 768) // 예시에서는 md의 화면 크기가 768px이라고 가정

const handleResize = () => {
    windowWidth.value = window.innerWidth
}

const clickHandler = () => {
    console.log('clickHandler', userInfo.value)
}

onMounted(() => {
    handleResize()
    window.addEventListener('resize', handleResize)
})

onUnmounted(() => {
    window.removeEventListener('resize', handleResize)
})
</script>

<template>
    <div
        class="flex md:h-[140px] max-md:h-[100px] bg-A805Cream w-full md:justify-between items-center"
    >
        <MenuOutlined v-if="!isMdOrLarger" style="font-size: 24px" class="ml-[40px]" />
        <HeaderLogo class="md:ml-[20px] max-md:mx-auto" />
        <TextAtom class="text-1" v-if="isMdOrLarger">방제목</TextAtom>
        <HeaderProfile
            @click="clickHandler"
            v-if="isMdOrLarger"
            :imageUrl="userInfo.profileUrl"
            :name="userInfo.userName"
            class="mr-[40px]"
        />
    </div>
</template>

<style scoped></style>
