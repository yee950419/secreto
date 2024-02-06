<script setup lang="ts">
import WideCardTemplate from '@/components/template/WideCardTemplate.vue'
import type { Handler } from '@/types/common'
import type { Ref } from 'vue'
import { ref, onMounted, onUnmounted } from 'vue'
import {useRouter, useRoute} from 'vue-router'
import { getRoom } from '@/api/room'

const router = useRouter()
const route = useRoute()
const state = ref('')
const enterApprove: Ref<boolean> = ref(false)
const prevPageButtonHandler: Handler = () => {
    router.go(-1)
}





const getStatus = () => {
    console.log('방 정보 호출!')
    getRoom(Number(route.params.roomNo), 
    ({data})=>{
        console.table(data.result)
        state.value = data.result.roomStatus
        if(state.value === 'WAIT')
        {
            return;
        }
        else if(state.value === 'PARTICIPANT' && data.result.roomStartAt === null || data.result.roomStartAt > new Date())
        {
            enterApprove.value = true
        }
        else if(state.value === 'PARTICIPANT' && data.result.roomStartAt < new Date())
        {
            router.push(`/game/${route.params.roomNo}`)
        }
        else 
        {
            alert('오류 발생!')
            router.replace('/main')
        }
    }, 
    (error)=> {
        alert(error.response.data.message)
        router.replace('/main')
    })
}

const setupInterval = () => {
  const intervalId = setInterval(() => {
    getStatus();
  }, 5000);

  onUnmounted(() => {
    clearInterval(intervalId);
  });
};

onMounted(() => {
  getStatus();
  setupInterval();
});


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
                @close-button-handle="prevPageButtonHandler"
                @button-click="prevPageButtonHandler"
            />
        </div>
    </div>
</template>
