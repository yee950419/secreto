<script setup lang="ts">
import type { DataHandler, Handler } from '@/types/common'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import DeleteModalContent1 from '@/components/organisms/DeleteModalContent1.vue'
import DeleteModalContent2 from '@/components/organisms/DeleteModalContent2.vue'
import DeleteModalContent3 from '@/components/organisms/DeleteModalContent3.vue'
import { ref, type Ref } from 'vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'

// template
const deleteModal: Ref<{ seen: boolean; step: number }> = ref({
    seen: false,
    step: 0
})

const deleteModalToggle: Handler = () => {
    deleteModal.value.step = 0
    deleteModal.value.seen = !deleteModal.value.seen
}
const withdrawSubmitButtonHandle: DataHandler<string> = (password: string) => {
    alert(password)
    deleteModalToggle()
}
</script>

<template>
    <div class="bg-A805White h-full flex justify-center items-center">
        <ButtonAtom custom-class="button-style-2 button-claret" @button-click="deleteModalToggle"
            >회원 탈퇴</ButtonAtom
        >
    </div>

    <ModalTemplate
        custom-id="modal"
        custom-class="modal-template-style-1 w-[350px]"
        :seen="deleteModal.seen"
        @modal-close="deleteModalToggle"
    >
        <DeleteModalContent1
            v-show="deleteModal.seen && deleteModal.step === 0"
            @yes-button-handle="() => ++deleteModal.step"
            @no-button-handle="deleteModalToggle"
        />
        <DeleteModalContent2
            v-show="deleteModal.seen && deleteModal.step === 1"
            @yes-button-handle="() => ++deleteModal.step"
            @no-button-handle="deleteModalToggle"
        />
        <DeleteModalContent3
            v-show="deleteModal.seen && deleteModal.step === 2"
            @submit-button-handle="withdrawSubmitButtonHandle"
        />
    </ModalTemplate>
</template>
