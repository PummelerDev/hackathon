import { createRouter, createWebHistory } from 'vue-router'
import Main from '@/views/Main.vue'
import NotFound from '@/views/NotFound.vue'

const router = createRouter({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/:catchAll(.*)',
      name: 'NotFound',
      component: NotFound
    }
  ],
  history: createWebHistory()
})

export default router
