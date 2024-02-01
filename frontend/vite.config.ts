import { fileURLToPath, URL } from 'node:url'
import path from 'path'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

// https://vitejs.dev/config/
export default defineConfig({
    base: './',
    plugins: [
        vue({
            script: {
                defineModel: true,
                propsDestructure: true
            }
        }),
        vueJsx()
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url)),
            'ant-design-vue': path.resolve(__dirname, 'node_modules/ant-design-vue')
        }
    },
    build: {
        rollupOptions: {
            external: ['socket.io-client', 'ant-design-vue', 'vue-clipboard3', 'dayjs']
        }
    }
})
