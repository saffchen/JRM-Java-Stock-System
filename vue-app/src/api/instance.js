// https://www.npmjs.com/package/vue-axios
// https://v2.vuejs.org/v2/cookbook/using-axios-to-consume-apis.html?redirect=true
// https://youtu.be/cJfEJ4mXBEg
// https://vueuse.org/integrations/useAxios/#usage
// https://www.codemag.com/Article/2103071/The-Complete-Guide-to-Vue-3-Plug-ins-Part-2

import axios from 'axios';

const userInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/',
    withCredentials: false,
    headers: {
        Accept: 'application/json'
    }
});

const adminInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/admin/',
    withCredentials: false
});

const authInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/auth/',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
});

export default {
    user: userInstance,
    admin: adminInstance,
    auth: authInstance
};