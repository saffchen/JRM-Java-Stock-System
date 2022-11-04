<template>
    <div class="d-flex align-items-center justify-content-end">
        <button
            class="btn btn-outline-primary"
            @click="login"
        >
            Login
        </button>
    </div>
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