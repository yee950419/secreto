<script setup lang="ts">
import StatisticCard from '@/components/molecules/game/StatisticCard.vue'
import StatisticLargeCard from '@/components/molecules/game/StatisticLargeCard.vue'
import ManitoResultElement from '@/components/molecules/game/ManitoResultElement.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { inject, onMounted, ref, type Ref } from 'vue'
import { getPredictResult, getSummary } from '@/api/history'
import { convertStringToRegistrationDateTime } from '@/utils/date'
import type { RoomInfoType } from '@/types/room'
import router from '@/router'
// import { ref, watch } from 'vue'
// import { useElementSize } from '@vueuse/core'

// const leftContainer = ref<HTMLDivElement | null>(null)
// const rightContainer = ref<HTMLDivElement | null>(null)
// const { width, height } = useElementSize(leftContainer)

const roomNo: Ref<number> = inject('roomNo', ref(-1))
const roomInfo = inject('roomInfo') as Ref<RoomInfoType>
type MatchingResultType = {
    maniti: { nickname: string; profileUrl: string }
    manito: { nickname: string; profileUrl: string }
    predictResult: boolean
}
type SummaryContentType = {
    header: string
    type: string
    summary: {
        amount: number
        contents: string
        dateTime: string
        id: number
        nickname: string
        imageUrl: string
        title: string
    }
}
const matchingResults: Ref<MatchingResultType[]> = ref([])
const summaryBoardLikes: Ref<SummaryContentType | null> = ref(null)
const summaryBoardViews: Ref<SummaryContentType | null> = ref(null)
const summaryUserBest: Ref<SummaryContentType | null> = ref(null)
const summaryUserFast: Ref<SummaryContentType | null> = ref(null)
const summaryUserCert: Ref<SummaryContentType | null> = ref(null)
const summaryUserBoast: Ref<SummaryContentType | null> = ref(null)
onMounted(() => {
    getPredictResult(
        roomNo.value,
        (response) => {
            const data = response.data
            if (data.status === 'OK') {
                console.log('predict result', data.result.predict_history)
                matchingResults.value = data.result.predict_history
            }
        },
        (error) => {
            console.error(error)
            alert(error.response.data.message)
        }
    )
    getSummary(
        roomNo.value,
        (response) => {
            const data = response.data
            if (data.status === 'OK') {
                console.log('summary result', data.result.summaryResult)
                data.result.summaryResult.forEach((summary: SummaryContentType) => {
                    switch (summary.type) {
                        case 'BOARD_LIKES':
                            summaryBoardLikes.value = summary
                            break
                        case 'BOARD_VIEWS':
                            summaryBoardViews.value = summary
                            break
                        case 'USER_BEST':
                            summaryUserBest.value = summary
                            break
                        case 'USER_CERT':
                            summaryUserCert.value = summary
                            break
                        case 'USER_FAST':
                            summaryUserFast.value = summary
                            break
                        case 'USER_BOAST':
                            summaryUserBoast.value = summary
                            break
                        default:
                            break
                    }
                })
            }
        },
        (error) => {
            console.error(error)
            alert(error.response.data.message)
        }
    )
})

const dateDiffWithRoomEndAt = (date: string): number => {
    let diff = new Date(roomInfo.value.roomEndAt).getTime() - new Date(date).getTime()
    if (diff <= 0) {
        return 0
    }
    return Math.ceil(diff / (1000 * 60 * 60 * 24))
}
const contentConstruct1 = (content: string): string => {
    const splited: string[] = content.split(',')
    return '게시글 수 ' + splited[7] + '개 / 댓글 수 : ' + splited[6] + '개'
}
const contentConstruct2 = (content: string): string => {
    const splited: string[] = content.split(',')
    return '좋아요 수 ' + splited[4] + '개 / 조회수 : ' + splited[5] + '개'
}
</script>

<template>
    <div class="flex flex-1 flex-col justify-start w-full items-center">
        <div
            ref="leftContainer"
            class="flex w-full h-full md:min-w-[568px] max-w-[1400px] max-md:min-w-0 items-center relative max-md:flex-col"
        >
            <div
                class="h-full md:w-[70%] flex flex-wrap gap-7 gap-x-10 items-start justify-center border-A805DarkGrey md:border-e-2 py-10 px-2 max-md:px-7"
                ref="cardContainer"
            >
                <StatisticLargeCard
                    v-if="summaryBoardLikes"
                    class="cursor-pointer"
                    :title="summaryBoardLikes.header"
                    :image-url="summaryBoardLikes.summary.imageUrl"
                    :content="[
                        summaryBoardLikes.summary.title,
                        convertStringToRegistrationDateTime(summaryBoardLikes.summary.dateTime),
                        '좋아요 ' + summaryBoardLikes.summary.amount
                    ]"
                    @click="
                        router.push({
                            name: 'game-board-detail',
                            query: { boardNo: summaryBoardLikes.summary.id }
                        })
                    "
                />
                <StatisticLargeCard
                    v-if="summaryBoardViews"
                    class="cursor-pointer"
                    :title="summaryBoardViews.header"
                    :image-url="summaryBoardViews.summary.imageUrl"
                    :content="[
                        summaryBoardViews.summary.title,
                        convertStringToRegistrationDateTime(summaryBoardViews.summary.dateTime),
                        '조회수 ' + summaryBoardViews.summary.amount
                    ]"
                    @click="
                        router.push({
                            name: 'game-board-detail',
                            query: { boardNo: summaryBoardViews.summary.id }
                        })
                    "
                />
                <StatisticCard
                    v-if="summaryUserBest"
                    :title="summaryUserBest.header"
                    :image-url="summaryUserBest.summary.imageUrl"
                    :content="[
                        summaryUserBest.summary.nickname,
                        contentConstruct1(summaryUserBest.summary.contents),
                        contentConstruct2(summaryUserBest.summary.contents)
                    ]"
                />
                <StatisticCard
                    v-if="summaryUserFast"
                    :title="summaryUserFast.header"
                    :image-url="summaryUserFast.summary.imageUrl"
                    :content="[
                        summaryUserFast.summary.nickname,
                        '게임 종료 ' +
                            dateDiffWithRoomEndAt(summaryUserFast?.summary.dateTime) +
                            '일전',
                        convertStringToRegistrationDateTime(summaryUserFast.summary.dateTime)
                    ]"
                />
                <StatisticCard
                    v-if="summaryUserCert"
                    :title="summaryUserCert.header"
                    :image-url="summaryUserCert.summary.imageUrl"
                    :content="[
                        summaryUserCert.summary.nickname,
                        '인증글 ' + summaryUserCert.summary.amount + '개',
                        '&nbsp;'
                    ]"
                />
                <StatisticCard
                    v-if="summaryUserBoast"
                    :title="summaryUserBoast.header"
                    :image-url="summaryUserBoast.summary.imageUrl"
                    :content="[
                        summaryUserBoast.summary.nickname,
                        '자랑글 ' + summaryUserBoast.summary.amount + '개',
                        '&nbsp;'
                    ]"
                />
            </div>
            <div
                ref="rightContainer"
                class="h-full md:w-[400px] max-md:w-full md:py-10 px-7 max-md:border-t max-md:py-5 flex flex-col md:scroll-container md:max-h-[100vh]"
            >
                <TextAtom class="md:hidden text-[28px] text-center font-bold mb-2"
                    >마니또 예측 결과</TextAtom
                >
                <ManitoResultElement
                    v-for="matching in matchingResults"
                    :key="matching.maniti + ' ' + matching.manito"
                    :maniti="matching.maniti"
                    :manito="matching.manito"
                    :predict-correct="matching.predictResult"
                />
            </div>
        </div>
    </div>
</template>

<style scoped></style>
