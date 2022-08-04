import api from '../api/index';
import store from '../store/store'

// https://v3-migration.vuejs.org/breaking-changes/global-api.html#a-new-global-api-createapp
export default {
    install(Vue) {
        Vue.config.globalProperties.$api = api;
        Vue.config.globalProperties.$store = store;

    }
}