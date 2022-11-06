<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container justify-content-between">
            <DropdownMenu />
            <div class="navbar-brand col-6 text-center">
                JRM Stock System Project
            </div>
            <div v-if="this.$store.getters.isLoggedIn">
                {{ 'admin' }}
            </div>
            <LoginBtn v-else />
        </div>
    </nav>
</template>

<script>
import DropdownMenu from "./DropdownMenu";
import LoginBtn from "@/components/button/LoginBtn";

export default {
    name: "AppNavbar",
    components: {
        DropdownMenu, LoginBtn
    }
};

</script>

<styles scoped>
</styles>
