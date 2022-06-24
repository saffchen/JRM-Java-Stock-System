// https://www.npmjs.com/package/vue-axios
// https://v2.vuejs.org/v2/cookbook/using-axios-to-consume-apis.html?redirect=true
// https://youtu.be/cJfEJ4mXBEg
// https://vueuse.org/integrations/useAxios/#usage

import axios from 'axios'
//import VueAxios from 'vue-axios'

const instance = axios.create({
    baseURL: 'http://localhost:3000/api/',
    withCredentials: true,
    headers: {
        accept: 'application/json'
    }
})

export default instance