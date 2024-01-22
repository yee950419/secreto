<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import ButtonAtom from './components/atoms/ButtonAtom.vue'
</script>

<template>
    <header>
        <div class="bg-A805Cream">
            Secreto Project
            <nav>
                <RouterLink to="/">Home</RouterLink>
                <RouterLink to="/about">About</RouterLink>
            </nav>
        </div>
        <RouterView />
    </header>
    <div>
        <ButtonAtom customClass="button-style1 button-shadow button-claret">label</ButtonAtom><br />
        <ButtonAtom customClass="button-style2 button-shadow button-cream">label</ButtonAtom><br />
        <ButtonAtom customClass="button-style3 button-shadow button-white">label</ButtonAtom><br />
        <ButtonAtom customClass="button-style4 button-shadow button-violet">label</ButtonAtom><br />
        <ButtonAtom customClass="button-style5 button-claret button-border-violet">label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style5 button-lightGrey button-border-grey"
            >label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style5 button-khaki button-border-violet">label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style6 button-blue">label</ButtonAtom><br />
    </div>
</template>

<style scoped></style>
