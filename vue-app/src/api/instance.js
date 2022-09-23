// https://www.npmjs.com/package/vue-axios
// https://v2.vuejs.org/v2/cookbook/using-axios-to-consume-apis.html?redirect=true
// https://youtu.be/cJfEJ4mXBEg
// https://vueuse.org/integrations/useAxios/#usage
// https://www.codemag.com/Article/2103071/The-Complete-Guide-to-Vue-3-Plug-ins-Part-2

import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/',
    withCredentials: false,
    headers: {
        accept: 'application/json'
    },
});

export default instance;