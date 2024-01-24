<script setup lang="ts">
import { ref, computed } from 'vue'
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
import CheckBoxMolecule from './components/molecules/CheckBoxMolecule.vue';
import Modal from './components/templates/Modal.vue'

const clickhandler = (data: string) => {
    console.log('data : ' + data)
}
const seen = ref(false)
const testhandler = () => {
    seen.value = !seen.value
    console.log('Testing...', seen.value)
    return seen.value
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
        <ButtonAtom customClass="button-style-1 button-shadow button-claret" @click="clickhandler"
            >label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style-2 button-shadow button-cream" @click="clickhandler"
            >label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style-3 button-shadow button-white">label</ButtonAtom><br />
        <ButtonAtom customClass="button-style-4 button-shadow button-violet">label</ButtonAtom><br />
        <ButtonAtom customClass="button-style-5 button-claret button-border-violet">label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style-5 button-lightGrey button-border-grey"
            >label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style-5 button-khaki button-border-violet">label</ButtonAtom
        ><br />
        <ButtonAtom customClass="button-style-6 button-blue">label</ButtonAtom><br />
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
        <InputAtom customClass="input-box   -style-1 line-claret" value="Testing..." readonly>content</InputAtom>
        <InputAtom customClass="input-box-style-2 line-khaki" value="Testing...">content</InputAtom>
        <InputAtom customClass="input-box-style-2 line-khaki" customValue="Testing..." id="qwer" readonly>content</InputAtom>

        <input type="checkbox" id="test">
        <label for="test">test</label>
    </div>
    <div>
    <div class="flex">
        <!-- <CheckBoxMolecule customClass="checkbox-molecule-style-1" id="appvueId">content</CheckBoxMolecule> -->
        <CheckBoxMolecule customClass="checkbox-molecule-style-1" customId="appvueId1">It is a very long content and I hate a this case</CheckBoxMolecule>
        <CheckBoxMolecule customClass="checkbox-molecule-style-1" customId="appvueId2">It is a very long content and I hate a this case</CheckBoxMolecule>
        <CheckBoxMolecule customClass="checkbox-molecule-style-1" customId="appvueId3">It is a very long content and I hate a this case</CheckBoxMolecule>
        <CheckBoxMolecule customClass="checkbox-molecule-style-1" customId="appvueId4">It is a very long content and I hate a this case</CheckBoxMolecule>
        <CheckBoxMolecule customClass="checkbox-molecule-style-1" customId="appvueId5">It is a very long content and I hate a this case</CheckBoxMolecule>
        <CheckBoxMolecule customClass="checkbox-molecule-style-1" customIdd="appvueId6">It is a very long content and I hate a this case</CheckBoxMolecule>
        <CheckBoxMolecule customClass="checkbox-molecule-style-1" customId="appvueId7">It is a very long content and I hate a this case</CheckBoxMolecule>
    </div>
    <div>
        <CheckBoxMolecule customClass="checkbox-molecule-style-1" customId="appvueId">It is a very long content and I hate a this case</CheckBoxMolecule>
    </div>
    <div>
        <ButtonAtom @click="testhandler" customClass="button-style-1 button-shadow button-claret">label</ButtonAtom><br />
        <ButtonAtom customClass="button-style-2 button-shadow button-cream" @click.prevent="clickhandler"
            >label</ButtonAtom
        ><br />
        <p>123{{seen}}</p>
        <Modal v-if="seen" @modalClose="testhandler" custom-class="modal-template-style-1">
            content
            <InputAtom customClass="input-box-style-2 line-khaki" value="Testing...">content</InputAtom>
            <ButtonAtom customClass="button-style-5 button-claret button-border-violet">label</ButtonAtom>
            <div style="display: flex; justify-content: space-between; width: 100%;">
                <ButtonAtom style="margin: 0 15%" customClass="button-style-5 button-claret button-border-violet">label</ButtonAtom>
                <ButtonAtom style="margin: 0 15%" customClass="button-style-5 button-claret button-border-violet">label</ButtonAtom>
            </div>
        </Modal>
    </div>
</div>
</template>

<style scoped></style>
