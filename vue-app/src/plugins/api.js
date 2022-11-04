import api from '../api';
import store from '../store';

// https://v3-migration.vuejs.org/breaking-changes/global-api.html#a-new-global-api-createapp
export default {
    install(Vue) {
        Vue.config.globalProperties.$api = api;
        Vue.config.globalProperties.$store = store;

    }
};