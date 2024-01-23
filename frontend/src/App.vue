<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import ButtonAtom from './components/atoms/ButtonAtom.vue'
import InputAtom from './components/atoms/InputAtom.vue';
import CheckBoxMorecule from './components/atoms/CheckBoxMorecule.vue';
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
        <!-- <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">content</CheckBoxMorecule> -->
        <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">It is a very long content and I hate a this case</CheckBoxMorecule>

        <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">It is a very long content and I hate a this case</CheckBoxMorecule>
        <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">It is a very long content and I hate a this case</CheckBoxMorecule>
        <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">It is a very long content and I hate a this case</CheckBoxMorecule>
        <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">It is a very long content and I hate a this case</CheckBoxMorecule>
        <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">It is a very long content and I hate a this case</CheckBoxMorecule>
        <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">It is a very long content and I hate a this case</CheckBoxMorecule>
    </div>
    <div>
        <CheckBoxMorecule customClass="checkbox-morecule-style-1" id="appvueId">It is a very long content and I hate a this case</CheckBoxMorecule>

    </div>

</div>
</template>

<style scoped></style>
