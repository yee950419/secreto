<script setup lang="ts">
import StatisticsCard from '@/components/molecules/game/StatisticsCard.vue'
import StatisticsLargeCard from '@/components/molecules/game/StatisticsLargeCard.vue'
import ManitoResultElement from '@/components/molecules/game/ManitoResultElement.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
// import { ref, watch } from 'vue'
// import { useElementSize } from '@vueuse/core'

// const leftContainer = ref<HTMLDivElement | null>(null)
// const rightContainer = ref<HTMLDivElement | null>(null)
// const { width, height } = useElementSize(leftContainer)
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
                <StatisticsLargeCard
                    title="좋아요를 가장 많이 받은 게시글"
                    :content="['게시글 제목1', '2024.01.13. 10:23', '좋아요 32']"
                />
                <StatisticsLargeCard
                    title="조회수가 가장 높은 게시글"
                    :content="['게시글 제목2', '2024.01.13. 10:23', '조회수 34']"
                />

                <StatisticsCard
                    title="최고의 유저"
                    :content="['닉네임', '받은 좋아요 수 13 / 게시글 조회수 30', '&nbsp;']"
                />
                <StatisticsCard
                    title="가장 빨리 마니또를 맞춘 유저"
                    :content="['감자', '13일전', '2024.01.13. 10:23']"
                />

                <StatisticsCard
                    title="가장 많은 자랑글을 작성한 유저"
                    :content="['복숭아', '자랑글 5개', '&nbsp;']"
                />
                <StatisticsCard
                    title="가장 많은 인증글을 작성한 유저"
                    :content="['고구마', '인증글 6개', '&nbsp;']"
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
                    :maniti="{ profileImageUrl: null, nickname: '고구마구마구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과과과과과과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                /><ManitoResultElement
                    :maniti="{ profileImageUrl: null, nickname: '고구마' }"
                    :manito="{ profileImageUrl: null, nickname: '사과' }"
                    :predict-correct="true"
                />
            </div>
        </div>
    </div>
</template>

<style scoped></style>
