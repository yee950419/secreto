<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue';
import type { notificationTypes } from '@/types/notify';
import { getNotificationLists, notificationRead } from '@/api/notification';
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
const router = useRouter()
const route = useRoute()
const notificationLists = ref<notificationTypes[]>([]);

const notifyReading = (alarmNo: number) => {
    console.log(alarmNo, Number(route.params.roomNo))
    notificationRead(alarmNo, Number(route.params.roomNo), ({ data }) => {
        console.log(data)
        getNotifyLists()
        router.go(0)
    }, (error) => {
        console.log(error)
    })
}

const getNotifyLists = () => {
    getNotificationLists(Number(route.params.roomNo), ({ data }) => {
        notificationLists.value = data.result
        console.log(data)
    }, (error) => {
        console.log(error)
    })
}

onMounted(() => {
    getNotifyLists()
})
</script>

<template>
    <div class="flex flex-1 flex-col bg-A805White">
        <TextAtom custom-class="text-1">알림 리스트</TextAtom>
        <template v-for="notify in notificationLists" class="gap-[30px]">
            <div v-if="!notify.readYn"
                class="flex md:flex-wrap items-center justify-center h-[60px] md:mx-[30px] max-md:mx-[10px] rounded-md border-black border-solid border-2 mt-[30px] bg-A805RealWhite md:gap-[30px] max-md:gap-[10px] px-[10px]"
                @click="notifyReading(notify.alarmNo)">
                <TextAtom custom-class="text-A805Red">New!</TextAtom>
                <TextAtom>{{ notify.author }}님이 {{ new Date(notify.generatedAt).toLocaleDateString() }} 에 {{ notify.content
                }}를 알렸습니다.</TextAtom>
            </div>
            <div v-else @click="notifyReading(notify.alarmNo)"
                class="flex  md:flex-wrap items-center justify-center h-[60px] md:mx-[30px] max-md:mx-[10px] rounded-md border-black border-solid border-2 mt-[30px] bg-A805Grey text-A805Cream max-md:gap-[10px] md:gap-[30px] px-[10px]">
                <TextAtom>{{ notify.author }}님이 {{ new Date(notify.generatedAt).toLocaleDateString() }} 에 {{ notify.content
                }}를 알렸습니다.</TextAtom>
                <TextAtom>읽기 완료</TextAtom>
            </div>
        </template>
    </div>
</template>

<style scoped></style>