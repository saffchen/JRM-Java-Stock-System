<template>
    <button
        class="btn btn-outline-primary"
        @click="logout"
    >
        Logout
    </button>
</template>

<script>

export default {
    methods: {
        logout: function() {
            this.$store.dispatch('user/logout')
                .then(() => {
                    this.$router.push({
                        name: 'Login'
                    });
                });
        }
    }
};
</script>


<style scoped>

</style>