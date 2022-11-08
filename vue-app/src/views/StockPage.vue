<template>
    <AppHeader />
    <AppMain :component-name='componentName' />
    <AppFooter />
</template>

<script>
import AppHeader from '@/components/layout/header/Header';
import AppMain from '@/components/layout/main/Main';
import AppFooter from '@/components/layout/footer/Footer';

export default {
    name: 'App',
    components: {
        AppHeader,
        AppMain,
        AppFooter
    },
    props: {
        componentName: {
            type: String,
            require: true,
            default: 'StockTable'
        }
    }
};
</script>

<style>
</style>