<template>
  <Header/>
  <Main content="Login"/>
  <Footer/>
</template>


<script>
import Header from "@/components/header/Header";
import Main from "@/components/main/Main";
import Footer from "@/components/footer/Footer";

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