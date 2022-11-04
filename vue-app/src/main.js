import {
    createApp 
} from 'vue';

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

import App from "@/components/App.vue";

import ApiPlugin from '@/plugins/api';
import LoadPlugin from '@/plugins/load';
import router from '@/router';
import store from '@/store';

createApp(App)
    .use(ApiPlugin)
    .use(LoadPlugin)
    .use(router)
    .use(store)
    .mount('#app');