/*https://www.vuemastery.com/blog/vue-router-a-tutorial-for-vue-3/*/
/*https://vueschool.io/articles/vuejs-tutorials/how-to-use-vue-router-a-complete-tutorial/*/
/*https://youtu.be/rqB7jRunukw*/

import { createWebHistory, createRouter } from 'vue-router'

import StockTable from '../components/main/StockTable'
import ProductTable from '../components/main/ProductTable'
import Main from '../components/main/Main'

const routes =[
    {
        path: '/StockTable',
        name: 'StockTable',
        component: StockTable
    },
    {
        path: '/ProductTable',
        name: 'ProductTable',
        component: ProductTable
    },
    {
        path: '/',
        name: 'main',
        component: Main
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router;