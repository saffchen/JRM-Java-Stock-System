<template>
  <Header/>
  <Main :component-name="componentName"/>
  <Footer/>
</template>

<script>
import Header from "@/components/header/Header";
import Main from "@/components/main/Main";
import Footer from "@/components/footer/Footer";

export default {
  name: 'app',
  props: {
    componentName: {
      type: String,
      require: true,
      default: "StockTable"
    }
  },
  components: {
    Header,
    Main,
    Footer
  }
}
</script>

<style>
</style>