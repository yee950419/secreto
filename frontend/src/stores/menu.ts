import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menu', () => {
    const menuSeen = ref(false)

    return { menuSeen }
})
