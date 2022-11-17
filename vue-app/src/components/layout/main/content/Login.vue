<template>
    <h3 class="mt-4 text-center">The application requires authorization</h3>
    <p
        class="mb-3 text-center"
        :class="messageClass"
    >
        {{ message }}
    </p>
    <form
        id="login"
        class="form text-center"
        @submit.prevent="login"
    >
        <div class="mb-3">
            <label class="form-label">
                <input
                    :value="email"
                    required
                    type="email"
                    placeholder="Email"
                    class="form-control"
                    @input="changeEmail"
                >
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                <input
                    :value="password"
                    required
                    type="password"
                    placeholder="Password"
                    class="form-control"
                    @input="changePassword"
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
    name: 'AppLogin',
    data() {
        return {
            email: '',
            password: '',
            message: 'Please verify your authority',
            messageClass: 'text-secondary'
        };
    },
    methods: {
        changeEmail: function(event) {
            this.email = event.target.value;
            this.message = 'Please verify your authority';
            this.messageClass = 'text-secondary';
        },
        changePassword: function(event) {
            this.password = event.target.value;
            this.message = 'Please verify your authority';
            this.messageClass = 'text-secondary';
        },
        login: function() {
            this.$store.dispatch('user/login', {
                email: this.email,
                password: this.password
            })
                .then((response) => {
                    if (response === 'ok') {
                        return this.$router.push({
                            name: 'Home'
                        });
                    }
                    this.email = '';
                    this.password = '';
                    this.message = 'Login error. Please try again';
                    this.messageClass = 'text-danger';
                })
                .catch(error => {
                    console.log(error);
                    this.email = '';
                    this.password = '';
                    this.message = 'Login error. Please try again';
                    this.messageClass = 'text-danger';
                });
        }
    }
};
</script>

<style>
</style>