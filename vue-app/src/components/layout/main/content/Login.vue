<template>
    <form
        id="login"
        class="form text-center"
        @submit.prevent="login"
    >
        <h2 class="my-4">Sign in</h2>
        <div class="mb-3">
            <label class="form-label">
                E-mail
                <input
                    v-model="email"
                    required
                    type="email"
                    placeholder="Email"
                    class="form-control"
                >
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Password
                <input
                    v-model="password"
                    required
                    type="password"
                    placeholder="Password"
                    class="form-control"
                >
            </label>
        </div>
        <div>
            <button
                class="btn btn-primary text-center"
                form="login"
                type="submit"
            >
                Login
            </button>
        </div>
    </form>
</template>

<script>
export default {
    name: "AppLogin",
    data() {
        return {
            email: "",
            password: ""
        };
    },
    methods: {
        login: function() {
            const email = 'testAdmin@email.ru';
            const password = 'testadmin';
            this.$store.dispatch('auth', {
                email,
                password
            })
                .then(() => this.$router.push('/'))
                .catch(err => console.log(err));
        }
    }
};
</script>

<style>
</style>