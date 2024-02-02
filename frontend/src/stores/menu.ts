import { ref, watch } from 'vue'
import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menuStore', () => {
    // 메인메뉴 체크 여부
    const menuSeen = ref(window.innerWidth <= 768)

    // 모바일, PC 체크
    const isMobile = ref(window.innerWidth <= 768)

    // 현재 윈도우의 너비
    const windowWidth = ref(window.innerWidth)

    const handleClick = () => {
        menuSeen.value = !menuSeen.value
    }

    const handleResize = () => {
        windowWidth.value = window.innerWidth
        // pc -> 모바일로 된 경우
        if (!isMobile.value && window.innerWidth <= 768) {
            menuSeen.value = false
            isMobile.value = true
        }
        // 모바일 -> pc
        else if (isMobile.value && window.innerWidth > 768) {
            menuSeen.value = false
            isMobile.value = false
        }
    }

    watch(windowWidth, () => handleResize)

    return { menuSeen, isMobile, windowWidth, handleResize, handleClick }
})
