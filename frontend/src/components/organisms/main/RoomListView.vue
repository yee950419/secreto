<script setup lang="ts">
import { getRoomList } from '@/api/room'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { ref, watch, type Ref, onMounted } from 'vue'
import type { Handler, DataHandler } from '@/types/common'
import type { RoomInfoType } from '@/types/room'
import RoomCard from '@/components/molecules/main/RoomCard.vue'
import RoomCreateCard from '@/components/molecules/main/RoomCreateCard.vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const emit = defineEmits(['submit-button-handle'])

/**
 * dummy data
 */
const roomInfoList = ref<Array<RoomInfoType>>([])
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
// const filteredRoomList = ref({
//     all: roomInfoList.value,
//     waiting: roomInfoList.value.filter((room: RoomInfoType) => {
//         if (room['roomStatus'] === 'waiting') {
//             return room
//         }
//     }),
//     in_progress: roomInfoList.value.filter((room: RoomInfoType) => {
//         if (room['roomStatus'] === '참여중') {
//             return room
//         }
//     }),
//     terminated: roomInfoList.value.filter((room: RoomInfoType) => {
//         if (room['roomStatus'] === '종료') {
//             return room
//         }
//     }),
//     favorites: roomInfoList.value.filter((room: RoomInfoType) => {
//         if (room['like']) {
//             return room
//         }
//     })
// })
const roomEnterHandler: DataHandler<number> = (roomNo: number) => {
    // alert(roomNo + '번 방 입장 이벤트')
    router.push('/game/' + roomNo)
}
const roomFavoriteHandler: DataHandler<number> = (roomNo: number) => {
    alert(roomNo + '번 방 즐겨찾기 추가 이벤트')
}
const roomFavoriteHandlerTest: DataHandler<RoomInfoType> = (roomInfo: RoomInfoType) => {
    roomInfo.bookmarkYn = !roomInfo.bookmarkYn
}
const roomLeaveHandler: DataHandler<number> = (roomNo: number) => {
    alert(roomNo + '번 방 삭제 이벤트')
}
const roomLeaveHandlerTest: DataHandler<number> = (roomNo: number) => {
    alert(roomNo + '번 방 삭제 이벤트')
    roomInfoList.value = roomInfoList.value.filter((room: RoomInfoType) => {
        if (room['roomNo'] !== roomNo) {
            return room
        }
    })
    console.log(roomInfoList.value)
}
const roomCreateHandler: Handler = () => {
    emit('submit-button-handle', 'roomCreate')
}

// 방 리스트 정보 불러오기
onMounted(() => {
    getRoomList(
        ({ data }) => {
            roomInfoList.value = data.result
            console.log(data.result)
        },
        (error) => {
            console.error('error', error)
        }
    )
})
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
