import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', component: () => import('@/views/DomovView.vue') },
    { path: '/products', component: () => import('@/views/ProduktView.vue') },
    { path: '/contact', component: () => import('@/views/KontaktView.vue') },
    { path: '/Login', component: () => import('@/views/LoginView.vue') },
  ]
});

export default router