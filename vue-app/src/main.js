import { createApp } from 'vue';

import App from "@/App";
import router from "@/router";

import ApiPlugin from './plugins/api';
import LoadPlugin from './plugins/load';
import router from './router';

createApp(App)
    .use(ApiPlugin)
    .use(LoadPlugin)
    .use(router)
    .mount('#app');
