<template>
    <button
        class='btn btn-outline-primary'
        @click='logout'
    >
        Logout
    </button>
</template>

<script>

export default {
    methods: {
        logout: function() {
            this.$store.dispatch('logout', localStorage.getItem('username'))
                .then(() => {
                    this.$router.push('/login');
                });
        }
    }
};
</script>


<style scoped>

</style>