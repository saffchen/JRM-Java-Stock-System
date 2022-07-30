<template>
    <Header/>
    <Main content="StockTable"/>
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
          Main,
          Footer
      }
  }
</script>

<style>
</style>