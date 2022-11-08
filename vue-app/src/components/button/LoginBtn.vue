<template>
    <button
        class='btn btn-outline-primary'
        @click='login'
    >
        Login
    </button>
</template>

<script>

export default {
    methods: {
        login: function () {
            this.$router.push('/login');
        }
    }
};
</script>


<style scoped>

</style>