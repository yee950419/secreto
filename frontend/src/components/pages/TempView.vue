<script setup lang="ts">
import WideCardTemplate from '@/components/template/WideCardTemplate.vue'
import type { Handler } from '@/types/common'
import type { Ref } from 'vue'
import { ref } from 'vue'

const enterApprove: Ref<boolean> = ref(false)
const prevPageButtonHandler: Handler = () => {
    alert('뒤로가기 이벤트 발생')
}
</script>

<template>
    <div class="bg-A805White h-full w-full flex justify-center items-center">
        <div class="card-template-container">
            <WideCardTemplate
                title="Waiting..."
                :content-messages="
                    enterApprove
                        ? ['입장이 승인되었습니다.', '', '게임이 시작될 때까지 기다려주세요']
                        : ['', '방장의 입장 승인 대기중입니다.', '']
                "
                button-label="창 닫기"
                @button-click="prevPageButtonHandler"
            />
        </div>
    </div>
</template>
