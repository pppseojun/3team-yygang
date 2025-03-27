import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import BaseLayout from '@/layout/BaseLayout.vue'
import SupplementInfoView from '@/views/SupplementInfoView.vue'
import QuestionBoardView from '@/views/QuestionBoardView.vue'
import BoardView from '@/views/BoardView.vue'
import Login from '@/views/auth/Login.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'base',
      component: BaseLayout,
      children: [
        {
          path:'',
          name: 'main',
          component: MainView,
        },
        {
          path:'supplement',
          name: 'supplement',
          component: SupplementInfoView,
        },
        {
          path:'questionboard',
          name: 'questionboard',
          component: QuestionBoardView,
        },
        {
          path:'board',
          name: 'board',
          component: BoardView,
        },
        {
          path:'login',
          name:'login',
          component: Login,
        },
        
      ]
    },
  ],
})

export default router
