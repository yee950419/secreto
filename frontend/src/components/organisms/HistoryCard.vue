<script setup lang="ts">
import { ref } from 'vue'
import TextAtom from '@/components/atoms/TextAtom.vue'

const isActive = ref(false)
type HistoryType = {
    index: number
    title: string
    date: string
    content: string
    imageUrl: string | null
    link: string
    activity: string
}

defineProps({
    HistoryData: {
        type: Array as () => HistoryType[],
        required: true
    }
})

const routerHandler = (link: string) => {
    //TODO: 라우터 이동로직 구현 필요
    console.log(link + '로 이동 하는 로직 추가 필요')
}
</script>

<template>
    <div class="flex flex-col">
        <div>
            <div class="flex flex-col">
                <div class="flex justify-between">
                    <TextAtom>{{ HistoryData[0].date }}</TextAtom
                    ><TextAtom>{{ HistoryData[0].activity }}</TextAtom>
                </div>
                <TextAtom>{{ HistoryData[0].title }}</TextAtom>
            </div>
            <div
                class="w-full my-[10px] cursor-pointer"
                @click="routerHandler(HistoryData[0].link)"
            >
                <img
                    v-if="HistoryData[0].imageUrl"
                    :src="HistoryData[0].imageUrl"
                    class="object-cover rounded-lg w-full h-full max-h-[250px]"
                />
                <TextAtom v-else> {{ HistoryData[0].content }}</TextAtom>
            </div>

            <div v-if="HistoryData.length > 1 && !isActive" class="flex flex-col">
                <hr class="border-solid border-1 border-A805Cream my-[10px]" />
                <TextAtom>외 {{ HistoryData.length - 1 }}건</TextAtom>
                <TextAtom @click="isActive = true" customClass="cursor-pointer"
                    >자세히 보러가기</TextAtom
                >
            </div>
            <div class="flex flex-col" v-else>
                <div v-for="(data, index) in HistoryData.slice(1)" :key="index">
                    <hr class="border-solid border-1 border-A805Cream mt-[10px]" />
                    <div class="flex flex-col">
                        <div class="flex justify-between">
                            <TextAtom>{{ data.date }}</TextAtom
                            ><TextAtom> {{ data.activity }}</TextAtom>
                        </div>
                        <TextAtom>{{ data.title }}</TextAtom>
                    </div>
                    <div class="w-full my-[10px] cursor-pointer" @click="routerHandler(data.link)">
                        <img
                            v-if="data.imageUrl"
                            :src="data.imageUrl"
                            class="object-cover rounded-lg w-full h-full max-h-[250px]"
                        />
                        <TextAtom v-else>{{ data.content }}</TextAtom>
                    </div>
                </div>
                <TextAtom>총 {{ HistoryData.length }}건...</TextAtom>
                <TextAtom @click="isActive = false" custom-class="cursor-pointer">접기</TextAtom>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
