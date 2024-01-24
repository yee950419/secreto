<script setup lang="ts">
import MainCard from '../components/organisms/MainCard.vue'
import LoginForm from '../components/organisms/LoginForm.vue'
import JoinForm from '@/components/organisms/JoinForm.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue';
</script>

<template>
    <div class="bg-A805White">
        <ButtonAtom @button-click="testhandler" custom-class="button-style-1 button-border-violet button-claret">aqwe</ButtonAtom>
        <ButtonAtom custom-class="button-style-2" />
    </div>
</template>
