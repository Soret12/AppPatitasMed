import { createRouter, createWebHistory } from 'vue-router'

import InicioView from '../views/InicioView.vue'
import RegistroUsuarioView from '../views/RegistroUsuarioView.vue'
import DashboardView from '../views/DashboardView.vue'
import MascotasView from '../views/MascotasView.vue'
import CitasMedicasView from '../views/CitasMedicasView.vue'

const routes = [
  {
    path:'/',
    component: InicioView
  },
  {
    path:'/registro',
    component: RegistroUsuarioView
  },
  {
    path:'/dashboard',
    component: DashboardView
  },
  {
    path:'/mascotas',
    component: MascotasView
  },
  {
    path:'/citas',
    component: CitasMedicasView
  }
  ]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router