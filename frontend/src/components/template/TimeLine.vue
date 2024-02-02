<script setup lang="ts">
import NavBar from '@/components/organisms/common/NavBar.vue'
import { ref, watch, onMounted } from 'vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import HistoryCard from '@/components/organisms/game/HistoryCard.vue'
import PredictCard from '@/components/organisms/game/PredictCard.vue'
import type { HistoryType } from '@/types/history'

//TODO: HistoryData API 연동 필요
const HistoryData = ref<Array<HistoryType>>([
    {
        index: 1,
        activity: '내 활동',
        title: '요건 내 활동이야',
        date: '2024.01.15',
        content:
            '2학기 관통 너무 재밌당 사진이 있으면 사진이 나오고, 사진이 없어서 content가 노출이 되도록 만들거야',
        imageUrl: null,
        link: 'https://www.naver.com'
    },
    {
        index: 2,
        activity: '내 활동',
        title: '요것도 내 활동이지',
        date: '2024.01.15',
        imageUrl: 'https://picsum.photos/200/300',
        content:
            'Lorem ipsum dolor sit amet, quo ei simul congue exerci, ad nec admodum perfecto mnesarchum, vim ea mazim fierent detracto. Ea quis iuvaret expetendis his, te elit voluptua dignissim per, habeo iusto primis ea eam.',
        link: 'https://www.naver.com'
    }
])

const myActivity = ref(true)

const setMyActivity = (value: boolean) => {
    myActivity.value = value
}

//TODO: API 연동 필요
// 내가 마니띠에게 활동한 기록 가져오는 API
const getMyActivity = () => {
    console.log('getMyActivity')
}

// 나의 마니또의 활동 기록 가져오는 API
const getMyManito = () => {
    console.log(`getMyManito's activity`)
}

const getTimeLine = () => {
    if (myActivity.value) {
        getMyActivity()
    } else {
        getMyManito()
    }
}

watch(myActivity, getTimeLine)

onMounted(() => {
    getTimeLine()
})
</script>

<template>
    <div class="flex flex-1 bg-A805Neutral">
        <NavBar />
        <div class="flex flex-1 flex-col">
            <div class="flex flex-col md:ml-[30px] max-md:mx-auto gap-[10px] mt-[30px]">
                <TextAtom custom-class="text-2">마니또 타임라인</TextAtom>
                <div class="flex gap-[30px]">
                    <TextAtom
                        @click="setMyActivity(true)"
                        :custom-class="{
                            'text-4': true,
                            'cursor-pointer': true,
                            'text-A805Black': myActivity,
                            'text-A805Grey': !myActivity
                        }"
                        >나의 기록</TextAtom
                    >
                    <TextAtom
                        @click="setMyActivity(false)"
                        :custom-class="{
                            'text-4': true,
                            'cursor-pointer': true,
                            'text-A805Black': !myActivity,
                            'text-A805Grey': myActivity
                        }"
                        >내 마니또 기록</TextAtom
                    >
                </div>
            </div>
            <div class="flex flex-col mt-[10px]">
                <TextAtom customClass="text-center text-2">TIMELINE</TextAtom>
                <TextAtom customClass="text-center">2024.01.12~2024.01.30일간 진행했던</TextAtom>
                <TextAtom customClass="text-center"> [방 제목] 에서의 마니또 기록이에요</TextAtom>
            </div>

            <div class="timeline relative mx-auto">
                <div class="container">
                    <div class="predict">
                        <PredictCard />
                    </div>
                </div>
                <div class="container right">
                    <div class="content">
                        <HistoryCard :HistoryData="HistoryData" />
                    </div>
                </div>
                <div class="container left">
                    <div class="content">
                        <HistoryCard :HistoryData="HistoryData" />
                    </div>
                </div>
                <div class="container right">
                    <div class="content">
                        <HistoryCard :HistoryData="HistoryData" />
                    </div>
                </div>
                <div class="container left">
                    <div class="content">
                        <HistoryCard :HistoryData="HistoryData" />
                    </div>
                </div>
                <div class="container right">
                    <div class="content">
                        <HistoryCard :HistoryData="HistoryData" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
