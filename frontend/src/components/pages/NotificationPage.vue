<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue';
import type { notificationTypes } from '@/types/notify';
import { notificationRead } from '@/api/notification';
import { inject, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const emit = defineEmits(['refreshNotify'])

const router = useRouter()
const route = useRoute()
const notificationLists = inject('notifyLists') as Ref<notificationTypes[]>

const notifyReading = (alarmNo: number) => {
    notificationRead(alarmNo, Number(route.params.roomNo), ({ data }) => {
        emit('refreshNotify')
    }, (error) => {
        console.log(error)
    })
}

</script>

<template>
    <div class="flex flex-1 flex-col bg-A805White">
        <TextAtom custom-class="text-1">알림 리스트</TextAtom>
        <template v-for="notify in notificationLists.slice().reverse()" class="gap-[30px]">
            <div v-if="!notify.readYn"
                class="flex md:flex-wrap items-center justify-center h-[60px] md:mx-[30px] max-md:mx-[10px] rounded-md border-black border-solid border-2 mt-[30px] bg-A805RealWhite md:gap-[30px] max-md:gap-[10px] px-[10px]"
                @click="notifyReading(notify.alarmNo)">
                <TextAtom custom-class="text-A805Red">New!</TextAtom>
                <TextAtom>{{ notify.content }}</TextAtom>
            </div>
            <div v-else @click="notifyReading(notify.alarmNo)"
                class="flex  md:flex-wrap items-center justify-center h-[60px] md:mx-[30px] max-md:mx-[10px] rounded-md border-black border-solid border-2 mt-[30px] bg-A805Grey text-A805Cream max-md:gap-[10px] md:gap-[30px] px-[10px]">
                <TextAtom>{{ notify.content }}</TextAtom>
                <TextAtom>읽기 완료</TextAtom>
            </div>
        </template>
    </div>
</template>

<style scoped></style>