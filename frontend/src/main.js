import { createApp } from 'vue'
import '@/style.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import App from '@/App.vue'
import router from "@/router/index.js";

createApp(App).use(router).mount('#app')
