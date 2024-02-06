<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue';
import { onMounted } from 'vue';
import { getNotificationLists } from '@/api/notification';
import { useRoute } from 'vue-router';
import { ref } from 'vue';
const route = useRoute()
const notificationLists = ref([])

onMounted(() => {
    getNotificationLists(Number(route.params.roomNo), ({ data }) => {
        notificationLists.value = data.result
    }, (error) => {
        console.log(error)
    })
})
</script>

<template>
    <div class="flex flex-1 flex-col bg-A805White">
        <TextAtom custom-class="text-1">알림 리스트</TextAtom>
        <template v-for="notify in notificationLists">
            <div>알람건들</div>
        </template>
    </div>
</template>

<style scoped></style>