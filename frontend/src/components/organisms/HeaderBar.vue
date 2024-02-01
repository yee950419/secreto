<script setup lang="ts">
import HeaderLogo from '@/components/molecules/HeaderLogo.vue'
import HeaderProfile from '@/components/molecules/HeaderProfile.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { MenuOutlined } from '@ant-design/icons-vue'
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useUserStore } from '@/stores/user'
import { storeToRefs } from 'pinia'
import { useMenuStore } from '@/stores/menu'

const userStore = useUserStore()
const menuStore = useMenuStore()
const { userInfo } = storeToRefs(userStore)
const { menuSeen } = storeToRefs(menuStore)
defineProps({
    roomName: {
        type: String as () => string | null,
        default: '방 제목'
    }
})

const windowWidth = ref(window.innerWidth)
const isMdOrLarger = computed(() => windowWidth.value >= 768) // 예시에서는 md의 화면 크기가 768px이라고 가정

const handleResize = () => {
    windowWidth.value = window.innerWidth
}

const clickHandler = () => {
    console.log('clickHandler', userInfo.value)
}

const menuClick = () => {
    menuSeen.value = !menuSeen.value
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
        class="relative flex md:min-h-[140px] max-md:min-h-[100px] bg-A805Cream w-full md:justify-between items-center shadow-lg"
    >
        <MenuOutlined
            v-if="!isMdOrLarger"
            style="font-size: 24px"
            class="ml-[40px]"
            @click="menuClick"
        />
        <HeaderLogo class="md:ml-[20px] max-md:mx-auto" />
        <TextAtom class="text-2 truncate max-w-[20%]" v-if="isMdOrLarger">{{ roomName }}</TextAtom>
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
