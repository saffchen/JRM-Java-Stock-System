import { createApp } from 'vue';

import HomePage from './HomePage';

import ApiPlugin from './plugins/api';
import LoadPlugin from './plugins/load';
import LoginPage from "@/LoginPage";

createApp(HomePage)
    .use(LoginPage)
    .use(ApiPlugin)
    .use(LoadPlugin)
    .mount('#app');
