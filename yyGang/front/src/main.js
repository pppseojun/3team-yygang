import { createApp } from 'vue'
import App from './App.vue'

import router from './router';
import axios from "axios";


const app = createApp(App);

// axios 설정 코드
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$serverUrl = '//localhost:8080';

app.use(router);
app.mount('#app');
