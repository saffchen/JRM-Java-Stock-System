<template>
    <button
        class="btn btn-outline-primary"
        @click="login"
    >
        Login
    </button>
</template>

<script>

export default {
    methods: {
        login: function () {
            this.$router.push({
                name: 'Login'
            });
        }
    }
};
</script>


<style scoped>

</style>