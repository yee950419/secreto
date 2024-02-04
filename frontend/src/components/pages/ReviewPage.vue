<script setup lang="ts">
import VueWordCloud from 'vuewordcloud'
</script>

<template>
    <div class="flex flex-1 justify-center">
        <div
            class="flex flex-1 flex-col md:max-w-[1000px] max-md:w-full items-center justify-center bg-red-300 md:m-[20px] max-md:mx-[10px]"
        >
            <vue-word-cloud
                :words="[
                    ['romance', 19],
                    ['horror', 3],
                    ['fantasy', 7],
                    ['adventure', 3]
                ]"
                :color="
                    ([, weight]: [string, number]) =>
                        weight > 10 ? 'DeepPink' : weight > 5 ? 'RoyalBlue' : 'Indigo'
                "
                font-family="Roboto"
            />
            <div>여기는 입력창</div>
        </div>
    </div>
</template>

<style scoped></style>
