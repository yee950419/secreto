<script setup lang="ts">
import MenuItem from '@/components/molecules/common/MenuItem.vue'
import type { RoomUserInfoType, RoomInfoType } from '@/types/room'
import type { notificationTypes } from '@/types/notify'
import { SettingOutlined } from '@ant-design/icons-vue'
import { ref, inject, watch, type Ref, onMounted } from 'vue'
import { useMenuStore } from '@/stores/menu'
import { useRouter } from 'vue-router'

const prop = defineProps({
    roomName: {
        type: String as () => String,
        default: '방 제목'
    },
    roomInfo: {
        type: Object as () => RoomInfoType | undefined
    },
    navStatus: {
        type: Number,
        required: true
    }
})

const menuStore = useMenuStore()
const { handleClick } = menuStore

const emit = defineEmits(['make-room'])

const notificationLists: Ref<notificationTypes[]> = inject('notifyLists') as Ref<
    notificationTypes[]
>
const roomUserInfo = inject<RoomUserInfoType>('roomUserInfo')
const showSubMenu = ref<string[]>([])
const activeMenu = ref(-1)
const router = useRouter()
const unReadMessage = ref(0)

const makeRoom = (roomName: string) => {
    emit('make-room', {
        name: roomName,
        imageUrl: ''
    })
}

const calculateUnReadMessage = () => {
    unReadMessage.value = 0
    notificationLists.value.forEach((notification) => {
        if (!notification.readYn) {
            unReadMessage.value++
        }
    })
}

watch(
    notificationLists,
    () => {
        console.log(notificationLists.value)
        calculateUnReadMessage()
    },
    { deep: true }
)

watch(
    () => prop.roomInfo,
    () => {
        console.log(prop.roomInfo)
    }
)

watch(
    () => prop.navStatus,
    () => {
        console.log('navbar?', prop.navStatus)
        activeMenu.value = prop.navStatus
    }
)

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

onMounted(() => {
    calculateUnReadMessage()
})
</script>

<template>
    <div
        class="flex flex-col h-full bg-A805White md:min-w-[230px] max-md:w-full md:border-solid md:border-2 md:border-A805Cream md:overflow-y-auto"
    >
        <MenuItem
            v-if="roomUserInfo?.roomUserNo === roomInfo?.hostRoomUserNo"
            custom-class="menu-item"
            :active="activeMenu === 0"
            @menu-click="
                handleMenuClick(0), handleClick(), router.push({ name: 'game-roomsettings' })
            "
        >
            <div class="flex w-full items-center gap-[20px]">
                방 설정
                <SettingOutlined />
            </div>
        </MenuItem>
        <MenuItem custom-class="menu-item">{{ roomName }}</MenuItem>
        <MenuItem
            custom-class="menu-item"
            :active="activeMenu === 1"
            @menu-click="
                handleMenuClick(1), handleClick(), router.push({ name: 'game-notification' })
            "
            >알림 {{ unReadMessage }} 건
            <span v-if="unReadMessage > 0" class="ml-[20px] text-A805Red">새로운 알림!</span>
        </MenuItem>
        <MenuItem v-if="roomInfo?.roomStartYn === true" custom-class="menu-item" :active="activeMenu === 3" @menu-click="
            handleClick(), router.push({ name: 'game-participate' }), handleMenuClick(3)
            ">참여 인원</MenuItem>
        <MenuItem v-if="roomInfo?.roomStartYn === true" custom-class="menu-item" :active="activeMenu === 4"
            @menu-click="handleMenuClick(4), router.push({ name: 'game-mission' }), handleClick()">내 미션</MenuItem>
        <MenuItem v-if="roomInfo?.roomStartYn === true" custom-class="menu-item" :active="activeMenu === 5"
            @menu-click="handleMenuClickAndToggleSubMenu(5, 'chat')">채팅</MenuItem>
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
        <MenuItem v-if="roomInfo?.roomStartYn === true" custom-class="menu-item" :active="activeMenu === 6"
            @menu-click="handleMenuClickAndToggleSubMenu(6, 'board')">게시판</MenuItem>
        <div v-if="showSubMenu.includes('board')">
            <MenuItem
                custom-class="sub-menu-item"
                @menu-click="
                    router.push({ name: 'game-board-list', query: { boardCategory: 'NOTICE' } }),
                        handleClick()
                "
                >공지 게시판</MenuItem
            >
            <MenuItem
                custom-class="sub-menu-item"
                @menu-click="
                    router.push({
                        name: 'game-board-list',
                        query: { boardCategory: 'CERTIFICATE' }
                    }),
                        handleClick()
                "
                >인증 게시판</MenuItem
            >
            <MenuItem
                custom-class="sub-menu-item"
                @menu-click="
                    router.push({ name: 'game-board-list', query: { boardCategory: 'BOAST' } }),
                        handleClick()
                "
                >자랑 게시판</MenuItem
            >
        </div>
        <MenuItem
            v-if="roomInfo?.roomStatus === 'END'"
            custom-class="menu-item"
            :active="activeMenu === 7"
            @menu-click="handleMenuClick(7), handleClick(), router.push({ name: 'game-statistic' })"
            >게임 통계</MenuItem
        >
        <MenuItem
            v-if="roomInfo?.roomStatus === 'END'"
            custom-class="menu-item"
            :active="activeMenu === 8"
            @menu-click="handleMenuClick(8), handleClick(), router.push({ name: 'game-timeline' })"
            >나의 히스토리</MenuItem
        >
        <MenuItem
            v-if="roomInfo?.roomStatus === 'END'"
            custom-class="menu-item"
            :active="activeMenu === 9"
            @menu-click="handleMenuClick(9), handleClick(), router.push({ name: 'game-wordcloud' })"
            >후기</MenuItem
        >
    </div>
</template>

<style scoped></style>
