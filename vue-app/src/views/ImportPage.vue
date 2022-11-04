<template>
    <AppHeader />
    <AppMain component-name="Secure" />
    <AppFooter />
</template>


<script>
import AppHeader from "@/components/layout/header/Header";
import AppMain from "@/components/layout/main/Main";
import AppFooter from "@/components/layout/footer/Footer";

export default {
    name: 'App',
    components: {
        AppHeader,
        AppMain,
        AppFooter
    }
};
</script>

<style>
</style>