<template>
  <div class="d-flex align-items-center justify-content-end">
    <div class="me-3">Push to login in the system</div>
    <button @click="login" class="btn btn-outline-primary">Login</button>
  </div>
</template>

<script>

export default {
  methods: {
    login: function () {
      this.$router.push('/login')
    }
  }
}
</script>


<style scoped>
    
</style>