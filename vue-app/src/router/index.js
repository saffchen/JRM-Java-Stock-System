import { createRouter, createWebHistory } from 'vue-router';

import HomePage from "@/views/HomePage";
import StockPage from "@/views/StockPage";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomePage
    },
    {
        path: '/stocks',
        name: 'Stocks',
        component: StockPage
    }
];

const router = createRouter({
   history: createWebHistory(),
   routes
});

export default router;
