<template>
    <div class="d-flex align-items-center justify-content-end">
        <span v-if="isLoggedIn"> | <button
            class="btn btn-outline-primary"
            @click="logout"
        >Logout</button></span>
    </div>
</template>

<script>

export default {
    computed : {
        isLoggedIn: function() { return this.$store.getters.isLoggedIn;}
    },
    methods: {
        logout: function() {
            this.$store.dispatch('logout')
                .then(() => {
                    this.$router.push('/login');
                });
        }
    }
};
</script>


<style scoped>

</style>