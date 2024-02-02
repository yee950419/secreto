<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { ref, watch, type Ref } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import type { RoomInfoTypeTest } from '@/types/room'
import RoomCard from '@/components/molecules/main/RoomCard.vue'
import RoomCreateCard from '@/components/molecules/main/RoomCreateCard.vue'
import { useRouter } from 'vue-router'
const router = useRouter()
/**
 * dummy data
 */
const roomInfoList = ref<Array<RoomInfoTypeTest>>([
    {
        roomNo: 1,
        title: 'SSAFY 10기 1반',
        nickname: '고구마',
        peopleNumber: 60,
        like: true,
        roomStartAt: '2024/01/15 00:00:00',
        roomEndAt: '2024/01/30 00:00:00',
        roomStatus: '참여중'
    },
    {
        roomNo: 2,
        title: 'SSAFY 10기 2반',
        nickname: '고구마',
        peopleNumber: 60,
        like: false,
        roomStartAt: '2024/01/15 00:00:00',
        roomEndAt: '2024/01/25 00:00:00',
        roomStatus: '종료'
    },
    {
        roomNo: 3,
        title: 'SSAFY 10기 3반',
        nickname: '고구마',
        peopleNumber: 60,
        like: false,
        roomStartAt: '2024/01/14 00:00:00',
        roomEndAt: '2024/01/25 00:00:00',
        roomStatus: '참여중'
    },
    {
        roomNo: 4,
        title: 'SSAFY 10기 4반',
        nickname: '고구마',
        peopleNumber: 60,
        like: false,
        roomStartAt: '2024/01/14 00:00:00',
        roomEndAt: '2024/01/25 00:00:00',
        roomStatus: '참여중'
    },
    {
        roomNo: 5,
        title: 'SSAFY 10기 5반',
        nickname: '고구마',
        peopleNumber: 60,
        like: true,
        roomStartAt: '2024/01/15 00:00:00',
        roomEndAt: '2024/03/15 00:00:00',
        roomStatus: '종료'
    },
    {
        roomNo: 6,
        title: 'SSAFY 10기 6반',
        nickname: '고구마',
        peopleNumber: 60,
        like: true,
        roomStartAt: '2024/01/15 00:00:00',
        roomEndAt: '2024/03/15 00:00:00',
        roomStatus: '종료'
    },
    {
        roomNo: 7,
        title: 'SSAFY 10기 7반',
        nickname: '고구마',
        peopleNumber: 60,
        like: false,
        roomStartAt: '2024/01/10 00:00:00',
        roomEndAt: '2024/01/15 00:00:00',
        roomStatus: '종료'
    },
    {
        roomNo: 8,
        title: 'SSAFY 10기 8반',
        nickname: '고구마',
        peopleNumber: 60,
        like: true,
        roomStartAt: '2024/01/15 00:00:00',
        roomEndAt: '2024/03/15 00:00:00',
        roomStatus: '종료'
    },
    {
        roomNo: 9,
        title: '방 이름이 아주 길다면 어떻게 될까요',
        nickname: '닉네임도 길수도 있겠지요',
        peopleNumber: 5,
        like: false,
        roomStartAt: '2024/01/15 00:00:00',
        roomEndAt: '2024/03/15 00:00:00',
        roomStatus: '종료'
    }
])
const SelectState = {
    ALL: 'all',
    WAITING: 'waiting',
    IN_PROGRESS: 'in_progress',
    TERMINATED: 'terminated',
    FAVORITES: 'favorites'
}
const selectState: Ref<string> = ref(SelectState.ALL)
watch(selectState, () => {
    alert('방 불러오기 이벤트 발생!!!')
})
const filteredRoomList = ref({
    all: roomInfoList.value,
    waiting: roomInfoList.value.filter((room: RoomInfoTypeTest) => {
        if (room['roomStatus'] === 'waiting') {
            return room
        }
    }),
    in_progress: roomInfoList.value.filter((room: RoomInfoTypeTest) => {
        if (room['roomStatus'] === '참여중') {
            return room
        }
    }),
    terminated: roomInfoList.value.filter((room: RoomInfoTypeTest) => {
        if (room['roomStatus'] === '종료') {
            return room
        }
    }),
    favorites: roomInfoList.value.filter((room: RoomInfoTypeTest) => {
        if (room['like']) {
            return room
        }
    })
})
const roomEnterHandler: DataHandler<number> = (roomNo: number) => {
    // alert(roomNo + '번 방 입장 이벤트')
    router.push('/game/' + 1)
}
const roomFavoriteHandler: DataHandler<number> = (roomNo: number) => {
    alert(roomNo + '번 방 즐겨찾기 추가 이벤트')
}
const roomFavoriteHandlerTest: DataHandler<RoomInfoTypeTest> = (roomInfo: RoomInfoTypeTest) => {
    roomInfo.like = !roomInfo.like
}
const roomLeaveHandler: DataHandler<number> = (roomNo: number) => {
    alert(roomNo + '번 방 삭제 이벤트')
}
const roomLeaveHandlerTest: DataHandler<number> = (roomNo: number) => {
    alert(roomNo + '번 방 삭제 이벤트')
    roomInfoList.value = roomInfoList.value.filter((room: RoomInfoTypeTest) => {
        if (room['roomNo'] !== roomNo) {
            return room
        }
    })
    console.log(roomInfoList.value)
}
const roomCreateHandler: Handler = () => {
    alert('새로운 방 만들기 이벤트')
}
</script>

<template>
    <div class="card-container scroll-container justify-start">
        <div
            class="w-full flex gap-[30px] px-[10px] pb-[10px] text-A805DarkGrey border-b-[1px] border-A805Black"
        >
            <ButtonAtom
                :custom-class="selectState === SelectState.ALL ? 'text-A805Black font-bold' : ''"
                @click="() => (selectState = SelectState.ALL)"
                >전체</ButtonAtom
            >
            <ButtonAtom
                :custom-class="
                    selectState === SelectState.WAITING ? 'text-A805Black font-bold' : ''
                "
                @click="() => (selectState = SelectState.WAITING)"
                >대기중</ButtonAtom
            >
            <ButtonAtom
                :custom-class="
                    selectState === SelectState.IN_PROGRESS ? 'text-A805Black font-bold' : ''
                "
                @click="() => (selectState = SelectState.IN_PROGRESS)"
                >참여중</ButtonAtom
            >
            <ButtonAtom
                :custom-class="
                    selectState === SelectState.TERMINATED ? 'text-A805Black font-bold' : ''
                "
                @click="() => (selectState = SelectState.TERMINATED)"
                >종료</ButtonAtom
            >
            <ButtonAtom
                :custom-class="
                    selectState === SelectState.FAVORITES ? 'text-A805Black font-bold' : ''
                "
                @click="() => (selectState = SelectState.FAVORITES)"
                >즐겨찾기</ButtonAtom
            >
        </div>
        <div
            class="w-full flex justify-around items-start flex-wrap gap-x-[10px] gap-y-[30px] my-[20px]"
        >
            <RoomCard
                v-for="roomInfo in roomInfoList"
                :key="roomInfo.roomNo"
                :room-info="roomInfo"
                @click="() => roomEnterHandler(roomInfo.roomNo)"
                @favorite-handle="() => roomFavoriteHandlerTest(roomInfo)"
                @delete-handle="() => roomLeaveHandlerTest(roomInfo.roomNo)"
            />
            <RoomCreateCard @click="roomCreateHandler" />
        </div>
    </div>
</template>

<style scoped></style>