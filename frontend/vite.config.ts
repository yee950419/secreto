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
    server: {
        port: 8080,
        host: '0.0.0.0'
    },
    resolve: {
        alias: {
            '@': path.resolve(__dirname, './src')
        }
    },
    build: {
        rollupOptions: {
            external: ['socket.io-client', 'ant-design-vue', 'vue-clipboard3', 'dayjs']
        }
    }
})
