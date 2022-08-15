import { createApp } from 'vue';

import App from "@/App";

import ApiPlugin from './plugins/api';
import LoadPlugin from './plugins/load';
import router from './router';
import store from './store/store';

createApp(App)
    .use(ApiPlugin)
    .use(LoadPlugin)
    .use(router)
    .use(store)
    .mount('#app');
