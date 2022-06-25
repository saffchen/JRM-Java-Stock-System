import { createApp } from 'vue';

import HomePage from './HomePage';

import ApiPlugin from './plugins/api';
import LoadPlugin from './plugins/load';

createApp(HomePage)
    .use(ApiPlugin)
    .use(LoadPlugin)
    .mount('#app');
