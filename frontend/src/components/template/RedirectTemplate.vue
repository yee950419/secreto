<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()

type TokenType = {
    accessToken: string
    refreshToken: string
}

// 쿼리 스트링 파싱 함수
const parseQueryString = (queryString: string): TokenType => {
    const tokenPair: TokenType = {
        accessToken: '',
        refreshToken: ''
    }
    const query = new URLSearchParams(queryString)
    for (const [key, value] of query) {
        if (key === 'accessToken') tokenPair.accessToken = value
        if (key === 'refreshToken') tokenPair.refreshToken = value
    }
    return tokenPair
}

// 현재 라우트의 쿼리 스트링 파싱
const tokens: TokenType = parseQueryString(route.fullPath.split('?')[1] || '')

// 유저 아이디와 이름 표시
const accessToken = tokens.accessToken || ''
const refreshToken = tokens.refreshToken || ''
</script>

<template>
    <div>유저 아이디: {{ accessToken }}, 유저 이름: {{ refreshToken }}</div>
</template>

<style scoped></style>
