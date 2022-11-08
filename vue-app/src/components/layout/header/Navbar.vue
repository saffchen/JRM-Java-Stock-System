<template>
    <nav class='navbar navbar-expand-lg navbar-light bg-light'>
        <div class='container'>
            <DropdownMenu />
            <div class='navbar-brand brand text-center'>
                JRM Stock System Project
            </div>
            <div
                v-if='$store.getters.isLoggedIn'
                class='d-flex align-items-center justify-content-end'
            >
                <span class='me-3'>{{ $store.getters.userName }}</span>
                <LogoutBtn />
            </div>
            <div
                v-else
                class='d-flex align-items-center justify-content-end'
            >
                <LoginBtn />
            </div>
        </div>
    </nav>
    <!-- <div>{{ $store.state.UserStore }}</div> -->
</template>

<script>
import DropdownMenu from './DropdownMenu';
import LoginBtn from '@/components/button/LoginBtn';
import LogoutBtn from '@/components/button/LogoutBtn';

export default {
    name: 'AppNavbar',
    components: {
        DropdownMenu, LoginBtn, LogoutBtn
    }
};

</script>

<style scoped>

.navbar-brand {
    margin: 0;
}

.container div {
    flex-grow: 1;
    flex-basis: calc(100% / 3);
}

</style>
