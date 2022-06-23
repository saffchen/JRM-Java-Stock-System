import api from '../api/index';

export default {
    install(Vue) {
        Vue.prototype.$api = api
    }
}