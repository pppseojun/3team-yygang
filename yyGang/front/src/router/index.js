import { createRouter, createWebHistory } from 'vue-router';
import MainView from '@/views/Main.vue';
import LoginView from '@/views/login.vue';
import QBoardView from '@/views/qboard.vue';
import HomeView from '@/views/home.vue'

const routes = [
    {path: '/', name:'home', component: HomeView},
    { path: '/main', name: 'main', component: MainView },
    { path: '/user/login', name: 'login', component: LoginView },
    { path: '/qboard', name: 'qboard', component: QBoardView }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;