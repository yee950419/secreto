<script setup lang="ts">
import { ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'
import SelectBox from '@/components/molecules/SelectBox.vue'
import InputBox from '@/components/molecules/InputBox.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'

const searchKeyword: Ref<String> = ref('')
const searchButtonHandler: Handler = () => {
    alert(searchKeyword.value)
}
</script>

<template>
    <div
        class="flex justify-end gap-[10px] w-full max-md:flex-col max-md:px-[20px] mb-[20px] md:h-[35px]"
    >
        <SelectBox
            custom-class="w-[120px] max-md:w-full h-[35px]"
            button-class="!bg-A805Claret !text-A805Khaki text-[20px]"
            :options="[
                { label: '글 제목', value: 'title' },
                { label: '글 내용', value: 'content' },
                { label: '작성자명', value: 'writer' }
            ]"
        />
        <InputBox
            custom-class="input-box-style-2 line-khaki text-A805Black w-[390px] max-md:w-full md:h-full "
            v-model="searchKeyword"
        />
        <ButtonAtom
            custom-class="button-style-4 button-claret border-khaki text-A805Khaki w-[80px] max-md:w-full text-[18px] md:h-full "
            @click="searchButtonHandler"
            >검색</ButtonAtom
        >
    </div>
</template>

<style scoped></style>
