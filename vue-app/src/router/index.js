/*https://www.vuemastery.com/blog/vue-router-a-tutorial-for-vue-3/*/
/*https://vueschool.io/articles/vuejs-tutorials/how-to-use-vue-router-a-complete-tutorial/*/
/*https://youtu.be/rqB7jRunukw*/

import { createRouter, createWebHistory } from 'vue-router';

import HomePage from "@/views/HomePage";
import LoginPage from "@/views/LoginPage";
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
        path: '/stocks',
        name: 'Stocks',
        component: StockPage
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
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router;