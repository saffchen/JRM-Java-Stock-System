<template>
  <Header/>
  <Main content="Login"/>
  <Footer/>
</template>


<script>
import Header from "@/components/header/Header";
import Main from "@/components/main/Main";

export default {
  name: 'app',
  components: {
    Header,
    Main
  }
}
</script>

<style>
</style>