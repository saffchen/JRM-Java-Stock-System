// https://www.npmjs.com/package/vue-axios
// https://v2.vuejs.org/v2/cookbook/using-axios-to-consume-apis.html?redirect=true
// https://youtu.be/cJfEJ4mXBEg
// https://vueuse.org/integrations/useAxios/#usage
// https://www.codemag.com/Article/2103071/The-Complete-Guide-to-Vue-3-Plug-ins-Part-2

import axios from 'axios';

const guestInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/',
    withCredentials: false,
    headers: {
        accept: 'application/json'
    }
});

const adminInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/admin/',
    withCredentials: false,
    headers: {
        accept: 'application/json',
        authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyX2RldGFpbHMiLCJyb2xlIjoiUk9MRV9BRE1JTiIsImlzcyI6InN0b2NrIiwiZXhwIjoxNjY3NTg4ODA3LCJpYXQiOjE2Njc1ODUyMDcsInVzZXJuYW1lIjoidGVzdEFkbWluQGVtYWlsLnJ1In0.vdjcMT7VALWLxBml8kLYkMIzYgjHxdARdZAyqZQpVLoGkORAP-h5KCzzvM2stfVeafqSXgE_jiduj_MQ-fbHuQ'
    }
});

export default {
    guest: guestInstance,
    admin: adminInstance
};