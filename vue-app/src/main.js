import { createApp } from 'vue';

import App from "@/App";
import router from "@/router";
import store from "@/store/store";

import ApiPlugin from './plugins/api';
import LoadPlugin from './plugins/load';

createApp(App)
    .use(ApiPlugin)
    .use(LoadPlugin)
    .use(router)
    .use(store)
    .mount('#app');
