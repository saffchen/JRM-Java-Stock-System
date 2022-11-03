<template>
  <div class="d-flex align-items-center justify-content-end">
    <span v-if="isLoggedIn"> | <button @click="logout" class="btn btn-outline-primary">Logout</button></span>
  </div>
</template>

<script>

export default {
  computed : {
    isLoggedIn : function(){ return this.$store.getters.isLoggedIn}
  },
  methods: {
    logout: function () {
      this.$store.dispatch('logout')
          .then(() => {
            this.$router.push('/login')
          })
    }
  },
}
</script>


<style scoped>

</style>