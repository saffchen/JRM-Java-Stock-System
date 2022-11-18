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

adminInstance.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    config.headers['Accept'] = 'application/json';
    return config;
}, error => {
    console.log(error);
    Promise.reject(error);
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