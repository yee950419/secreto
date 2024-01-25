<script setup lang="ts">
import { ref } from 'vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import SelectBox from '@/components/molecules/SelectBox.vue'
import ButtonInputBox from '@/components/molecules/ButtonInputBox.vue'
const seen = ref(false)
const testhandler = () => {
    seen.value = !seen.value
}
</script>

<template>
    <div class="bg-A805White">
        {{ seen }}
        <ButtonAtom
            @button-click="testhandler"
            custom-class="button-style-1 button-border-violet button-claret"
            >aqwe</ButtonAtom
        >
        <ButtonAtom custom-class="button-style-2" />
        <ModalTemplate v-if="seen" custom-class="modal-template-style-1" @modal-close="testhandler">
            qwer
        </ModalTemplate>
        <SelectBox custom-class="select-box-style-1"></SelectBox>
        <ButtonInputBox custom-class="button-input-box-style-1"></ButtonInputBox>
    </div>
</template>
