<template>
    <h3 class='mt-4 text-center'>The application requires authorization</h3>
    <p class="mb-3 text-center">Please verify your authority</p>
    <form
        id='login'
        class='form text-center'
        @submit.prevent='login'
    >
        <div class='mb-3'>
            <label class='form-label'>
                <input
                    v-model='email'
                    required
                    type='email'
                    placeholder='Email'
                    class='form-control'
                >
            </label>
        </div>
        <div class='mb-3'>
            <label class='form-label'>
                <input
                    v-model='password'
                    required
                    type='password'
                    placeholder='Password'
                    class='form-control'
                >
            </label>
        </div>
        <div>
            <button
                class='btn btn-primary text-center'
                form='login'
                type='submit'
            >
                Login
            </button>
        </div>
    </form>
</template>

<script>
export default {
    name: 'AppLogin',
    data() {
        return {
            email: '',
            password: ''
        };
    },
    methods: {
        login: function() {
            this.$store.dispatch('auth', {
                email: this.email,
                password: this.password
            })
            .then(() => {
                return this.$router.push({
                    name: 'Home'
                });
            })
            .catch(err => console.log(err));
        }
    }
};
</script>

<style>
</style>