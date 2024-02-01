<script setup lang="ts">
import MenuItem from '@/components/molecules/MenuItem.vue'
import { SettingOutlined } from '@ant-design/icons-vue'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useMenuStore } from '@/stores/menu'
const menuStore = useMenuStore()
const { handleClick } = menuStore

defineProps({
    roomName: {
        type: String as () => String,
        default: '방 제목'
    }
})

const showSubMenu = ref<string[]>([])
const activeMenu = ref(-1)
const router = useRouter()
const emit = defineEmits(['make-room'])

const makeRoom = (roomName: string) => {
    emit('make-room', {
        name: roomName,
        imageUrl: 'src/assets/images/character.png'
    })
}

const toggleSubMenu = (menu: string) => {
    const index = showSubMenu.value.indexOf(menu)
    if (index === -1) {
        showSubMenu.value.push(menu)
    } else {
        showSubMenu.value.splice(index, 1)
    }
}

const handleMenuClick = (index: number) => {
    // 현재 활성화된 메뉴의 인덱스 업데이트
    activeMenu.value = index
}

const handleMenuClickAndToggleSubMenu = (index: number, menu: string) => {
    handleMenuClick(index)
    toggleSubMenu(menu)
}
</script>

<template>
    <div
        class="flex flex-col h-full bg-A805White md:w-[230px] max-md:w-full md:border-solid md:border-2 md:border-A805Cream md:overflow-y-auto"
    >
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 0"
            @menu-click="handleMenuClick(0)"
            ><div class="flex w-full items-center gap-[20px]">방 설정 <SettingOutlined /></div
        ></MenuItem>
        <MenuItem custom-class="menu-item">{{ roomName }}</MenuItem>
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 1"
            @menu-click="handleMenuClick(1)"
            >알림 OO건</MenuItem
        >
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 2"
            @menu-click="handleMenuClick(2), router.push('/main')"
            >메인 화면</MenuItem
        >
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 3"
            @menu-click="handleClick(), router.push('/game/participate'), handleMenuClick(3)"
            >참여 인원</MenuItem
        >
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 4"
            @menu-click="handleMenuClick(4), router.push('/game/mission')"
            >내 미션</MenuItem
        >
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 5"
            @menu-click="handleMenuClickAndToggleSubMenu(5, 'chat')"
            >채팅</MenuItem
        >
        <div v-if="showSubMenu.includes('chat')">
            <MenuItem custom-class="sub-menu-item" @menu-click="makeRoom('마니또'), handleClick()"
                >마니또와의 채팅</MenuItem
            >
            <MenuItem custom-class="sub-menu-item" @menu-click="makeRoom('마니띠'), handleClick()"
                >마니띠와의 채팅</MenuItem
            >
            <MenuItem
                custom-class="sub-menu-item"
                @menu-click="makeRoom('단체 채팅'), handleClick()"
                >단체 채팅</MenuItem
            >
        </div>
        <!-- 다른 세부 메뉴들도 추가 가능 -->
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 6"
            @menu-click="handleMenuClickAndToggleSubMenu(6, 'board')"
            >게시판</MenuItem
        >
        <div v-if="showSubMenu.includes('board')">
            <MenuItem
                custom-class="sub-menu-item"
                @menu-click="router.push('/game/board'), handleClick()"
                >공지 게시판</MenuItem
            >
            <MenuItem
                custom-class="sub-menu-item"
                @menu-click="router.push('/game/board'), handleClick()"
                >인증 게시판</MenuItem
            >
            <MenuItem
                custom-class="sub-menu-item"
                @menu-click="router.push('/game/board'), handleClick()"
                >자랑 게시판</MenuItem
            >
        </div>
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 7"
            @menu-click="handleMenuClick(7)"
            >게임 통계</MenuItem
        >
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 8"
            @menu-click="handleMenuClick(8)"
            >나의 히스토리</MenuItem
        >
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 9"
            @menu-click="handleMenuClick(9)"
            >후기</MenuItem
        >
    </div>
</template>

<style scoped></style>
