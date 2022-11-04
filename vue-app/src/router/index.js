/*https://www.vuemastery.com/blog/vue-router-a-tutorial-for-vue-3/*/
/*https://vueschool.io/articles/vuejs-tutorials/how-to-use-vue-router-a-complete-tutorial/*/
/*https://youtu.be/rqB7jRunukw*/

import { createRouter, createWebHistory } from 'vue-router';

import HomePage from "@/views/HomePage";
import LoginPage from "@/views/LoginPage";
import ProductPage from "@/views/ProductPage";
import StockPage from "@/views/StockPage";
import ReportPage from "@/views/ReportPage";
import ImportPage from "@/views/ImportPage";
import ExportPage from "@/views/ExportPage";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomePage
    },
    {
        path: '/login',
        name: 'Login',
        component: LoginPage
    },
    {
        path: '/products',
        name: 'ProductTable',
        component: ProductPage,
        props: router => ({ componentName: router.name})
    },
    {
        path: '/stocks',
        name: 'StockTable',
        component: StockPage,
        props: router => ({ componentName: router.name})
    },
    {
        path: '/report',
        name: 'Report',
        component: ReportPage
    },
    {
        path: '/import',
        name: 'Import',
        component: ImportPage
    },
    {
        path: '/export',
        name: 'Export',
        component: ExportPage
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
})

router.beforeEach((to, from, next) => {
    // const publicPages = ['/login'];
    // const authRequired = !publicPages.includes(to.path);
    // const loggedIn = localStorage.getItem('user');

    // if (authRequired && !loggedIn) {
    //     return next('/login');
    // }
    next();
})

export default router;