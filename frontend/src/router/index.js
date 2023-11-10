import { createRouter, createWebHistory } from 'vue-router'
import Main from '@/pages/Main.vue'
import NotFound from '@/pages/NotFound.vue'

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
