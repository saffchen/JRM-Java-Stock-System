<template>
    <form
        class="login"
        @submit.prevent="login"
    >
        <h2 class="text-center">
            Sign in
        </h2>
        <div class="container ml-auto mr-auto">
            <div
                class="row"
                align="center"
            >
                <div class="col">
                    Email
                    <div class="col">
                        <input
                            v-model="email"
                            required
                            type="email"
                            placeholder="Name"
                        >
                    </div>
                </div>
            </div>
            <div><label /></div>
            <div
                class="row mb-3"
                align="center"
            >
                <div class="col">
                    Password
                    <div class="col">
                        <input
                            v-model="password"
                            required
                            type="password"
                            placeholder="Password"
                        >
                    </div>
                </div>
            </div>
            <div align="center">
                <button
                    class="btn btn-primary text-center"
                    type="submit"
                >
                    Login
                </button>
            </div>
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
        login: function () {
            let email = this.email;
            let password = this.password;
            this.$store.dispatch('login', {
                email, password 
            })
                .then(() => this.$router.push('/'))
                .catch(err => console.log(err));
        }
    }
};
</script>

<style>
</style>