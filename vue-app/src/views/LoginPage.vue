<template>
    <AppHeader />
    <AppMain component-name="Login" />
</template>


<script>
import AppHeader from '@/components/layout/header/Header';
import AppMain from '@/components/layout/main/Main';

export default {
    name: 'App',
    components: {
        AppHeader,
        AppMain
    }
};
</script>

<style>
</style>