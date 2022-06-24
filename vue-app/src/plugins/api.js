import api from '../api/index';

// https://v3-migration.vuejs.org/breaking-changes/global-api.html#a-new-global-api-createapp
export default {
    install(Vue) {
        Vue.config.globalProperties.$api = api
    }
}