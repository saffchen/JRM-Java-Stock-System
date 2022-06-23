import { createApp } from 'vue';

import HomePage from './HomePage';

import ApiPlugin from './plugins/api';

createApp(HomePage).use(ApiPlugin).mount('#app');
