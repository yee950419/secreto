<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import CheckBox from '@/components/molecules/common/CheckBox.vue'
import InputBox from '@/components/molecules/common/InputBox.vue'
import SelectBox from '@/components/molecules/common/SelectBox.vue'
import { computed, ref, type Ref } from 'vue'
import { BoardCategory, type BoardWriteRequestType } from '@/types/board'
import { createPost } from '@/api/board'
import { useRoute } from 'vue-router'
import router from '@/router'

const route = useRoute()
const roomNo: Ref<number> = ref(Number(route.params.roomNo))
const boardCategory = computed(() => {
    return String(route.query.boardCategory)
})
const boardWriteRequest: Ref<BoardWriteRequestType> = ref({
    title: '',
    content: '',
    boardCategory: boardCategory.value,
    imageUrl: null,
    missionCategory: null,
    publicYn: false
})
const missionCategory: Ref<string | null> = ref(null)
const publicYn: Ref<boolean> = ref(false)
const submitButtonHandle = () => {
    boardWriteRequest.value.boardCategory = boardCategory.value
    if (boardCategory.value !== BoardCategory.CERTIFICATE) {
        boardWriteRequest.value.missionCategory = null
        boardWriteRequest.value.publicYn = true
    }
    createPost(
        roomNo.value,
        boardWriteRequest.value,
        (response) => {
            if (response.data.status === 'OK') {
                router.push({
                    name: 'game-board-detail',
                    query: { boardCategory: boardCategory.value, boardNo: response.data.result }
                })
            }
        },
        (error) => {
            alert(error.response.data.message)
            console.error(error)
        }
    )
}
</script>

<template>
    <div class="w-full flex justify-center">
        <div
            class="flex flex-col w-full md:min-w-[568px] max-w-[1400px] max-md:min-w-0 items-center px-[20px] gap-[10px]"
        >
            <div class="w-full flex flex-col gap-[10px] my-[20px]">
                <div class="flex flex-col gap-[15px] border border-A805LightGrey p-5 rounded-sm">
                    <div
                        class="flex justify-between md:h-[30px] max-md:flex-col-reverse max-md:gap-3"
                        v-if="boardCategory === BoardCategory.CERTIFICATE"
                    >
                        <SelectBox
                            class="w-[65%] max-md:w-full max-md:h-[30px]"
                            button-class="!bg-A805Claret"
                            v-model="missionCategory"
                            :options="[{ label: '미션을 선택해 주세요.', value: -1 }]"
                        />
                        <CheckBox
                            class="gap-[10px] md:justify-end max-md:justify-center accent-[#E0AED0]"
                            custom-id="publicYn"
                            v-model="publicYn"
                            >인증 글 공개 여부</CheckBox
                        >
                    </div>
                    <InputBox
                        type="text"
                        label-class="ps-[10px]"
                        input-class="input-box-style-2 line-lightGrey bg-A805White text-A805Black"
                        custom-class="w-full"
                        custom-id="email"
                        place-holder="제목을 입력해 주세요."
                        v-model="boardWriteRequest.title"
                    ></InputBox>
                </div>
                <div class="w-full h-[500px] mb-16">
                    <QuillEditor
                        theme="snow"
                        toolbar="full"
                        v-model:content="boardWriteRequest.content"
                        content-type="html"
                    />
                </div>
                <div class="flex justify-end max-md:mt-14">
                    <ButtonAtom
                        custom-class="button-style-4 button-claret text-[18px] font-bold flex justify-center items-center gap-[5px] max-md:w-full"
                        @button-click="submitButtonHandle"
                        >등록</ButtonAtom
                    >
                </div>
            </div>
        </div>
    </div>
</template>
