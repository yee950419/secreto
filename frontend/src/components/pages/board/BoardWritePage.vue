<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import CheckBox from '@/components/molecules/common/CheckBox.vue'
import InputBox from '@/components/molecules/common/InputBox.vue'
import SelectBox from '@/components/molecules/common/SelectBox.vue'
import { ref, type Ref } from 'vue'
import type { BoardWriteRequestType } from '@/types/board'

const boardWriteRequest: Ref<BoardWriteRequestType> = ref({
    boardNo: 0,
    roomUserNo: 0,
    title: '',
    content: '',
    boardCategory: '',
    publicYn: true,
    missionCategory: ''
})
const submitButtonHandle = () => {
    alert(boardWriteRequest.value.title)
    alert(boardWriteRequest.value.content)
}
</script>

<template>
    <div
        class="flex flex-col w-full md:min-w-[768px] max-w-[1080px] max-md:min-w-0 items-center px-[20px] gap-[10px]"
    >
        <div class="w-full flex flex-col gap-[10px] my-[20px]">
            <div class="flex flex-col gap-[15px] border border-A805LightGrey p-5 rounded-sm">
                <div class="flex justify-between h-[30px]">
                    <SelectBox
                        class="w-[80%]"
                        :options="[{ label: '미션을 선택해 주세요.', value: 0 }]"
                    />
                    <CheckBox class="w-[20%] gap-[10px] justify-end" custom-id="publicYn"
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
            <div class="w-full h-[400px] mb-16">
                <QuillEditor
                    theme="snow"
                    toolbar="full"
                    v-model:content="boardWriteRequest.content"
                    content-type="html"
                />
            </div>
            <div class="flex justify-end">
                <ButtonAtom
                    custom-class="button-style-4 button-claret text-[18px] font-bold flex justify-center items-center gap-[5px] max-md:w-full"
                    @button-click="submitButtonHandle"
                    >등록</ButtonAtom
                >
            </div>
        </div>
    </div>
</template>
