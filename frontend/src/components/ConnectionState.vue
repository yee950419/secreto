<template>
    <p>State: {{ connected }}</p>
</template>

<script setup lang="ts">
// import { state } from '../../socket'

const connected = () => {
    return state.connected
}
</script>
