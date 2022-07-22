import { createApp } from 'vue';

import HomePage from './HomePage';

import ApiPlugin from './plugins/api';
import LoadPlugin from './plugins/load';
import router from './router';

createApp(HomePage)
    .use(ApiPlugin)
    .use(LoadPlugin)
    .use(router)
    .mount('#app');
