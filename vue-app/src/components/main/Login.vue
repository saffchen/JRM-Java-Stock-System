<template>
  <Header/>
  <main class="main mb-5">
    <StockTable/>
  </main>
  <form action=/login method="post">
    <div class="row" align="center">
      <form class="login" @submit.prevent="login">
        <h1>Sign in</h1>
        <div class="container">
          <div class="row" align="center">
            <div class="col">
              Email
              <div class="col">
                <input required v-model="email" type="email" placeholder="Name"/>
              </div>
            </div>
          </div>
          <div><label></label></div>
          <div class="row" align="center">
            <div class="col">
              Password
              <div class="col">
                <input required v-model="password" type="password" placeholder="Password"/>
              </div>
            </div>
          </div>
          <hr/>
          <button type="submit">Login</button>
        </div>
      </form>
    </div>
  </form>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: ""
    }
  },
  methods: {
    login: function () {
      let email = this.email
      let password = this.password
      this.$store.dispatch('login', {email, password})
          .then(() => this.$router.push('/'))
          .catch(err => console.log(err))
    }
  }
}
</script>

<style>
</style>