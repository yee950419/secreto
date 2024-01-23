<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import ButtonAtom from './components/atoms/ButtonAtom.vue'
import InputAtom from './components/atoms/InputAtom.vue';
import CharacterAtom from '@/components/atoms/CharacterAtom.vue'
import LogoAtom from '@/components/atoms/LogoAtom.vue'
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import HeaderLogo from '@/components/molecules/HeaderLogo.vue'
import MainLogo from '@/components/molecules/MainLogo.vue'
import HeaderProfile from '@/components/molecules/HeaderProfile.vue'
import FooterBar from '@/components/organisms/FooterBar.vue'
import HeaderBar from '@/components/organisms/HeaderBar.vue'
const clickhandler = (data: string) => {
    console.log('data : ' + data)
}
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
        <ButtonAtom customClass="button-style1 button-shadow button-claret" @click="clickhandler"
            >label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style2 button-shadow button-cream" @click="clickhandler"
            >label</ButtonAtom
        ><br />
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
        <CharacterAtom />
        <LogoAtom />
        <AvatarAtom />
        <HeaderLogo />
        <MainLogo />
        <HeaderProfile />
        <FooterBar />
        <HeaderBar />
    </div>
    <div>
        <InputAtom customClass="input-box-style-1 line-claret" value="Testing...">content</InputAtom>
        <InputAtom customClass="input-box-style-1 line-claret" value="Testing..." readonly>content</InputAtom>
        <InputAtom customClass="input-box-style-2 line-khaki" value="Testing...">content</InputAtom>
        <InputAtom customClass="input-box-style-2 line-khaki" value="Testing..." readonly>content</InputAtom>
    </div>
</template>

<style scoped></style>
