<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import ButtonAtom from './components/atoms/ButtonAtom.vue'
import InputBox from './components/molecules/InputBox.vue'
import CharacterAtom from '@/components/atoms/CharacterAtom.vue'
import LogoAtom from '@/components/atoms/LogoAtom.vue'
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import HeaderLogo from '@/components/molecules/HeaderLogo.vue'
import MainLogo from '@/components/molecules/MainLogo.vue'
import HeaderProfile from '@/components/molecules/HeaderProfile.vue'
import FooterBar from '@/components/organisms/FooterBar.vue'
import HeaderBar from '@/components/organisms/HeaderBar.vue'
import CheckBoxMolecule from './components/molecules/CheckBoxMolecule.vue'

const clickhandler = (data: string) => {
    console.log('data : ' + data)
}
</script>

<template>
    <header class="bg-A805Claret">
        <div>
            Secreto Project
            <nav>
                <RouterLink to="/">Home</RouterLink>
                <RouterLink to="/about">About</RouterLink>
                <RouterLink to="/main-page">Main Page</RouterLink>
            </nav>
        </div>
    </header>
    <RouterView />
</template>

<style scoped></style>
