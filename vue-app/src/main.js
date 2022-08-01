import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from "@/App";

import ApiPlugin from './plugins/api';
import LoadPlugin from './plugins/load';
import router from './router';

createApp(App)
    .use(createPinia())
    .use(ApiPlugin)
    .use(LoadPlugin)
    .use(router)
    .mount('#app');
