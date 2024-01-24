import { createRouter, createWebHistory } from 'vue-router'
import DomovViewVue from '@/views/DomovView.vue'
import ProduktView from '@/views/ProduktView.vue'
import KontaktView from '@/views/KontaktView.vue'
import LoginView from '@/views/LoginView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', component: DomovViewVue },
    { path: '/products', component: ProduktView },
    { path: '/contact', component: KontaktView },
    { path: '/Login', component: LoginView },
  ]
});
export default router
