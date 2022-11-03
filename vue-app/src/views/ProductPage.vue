<template>
  <Header/>
  <Main :component-name="componentName"/>
  <Footer/>
</template>

<script>

import Header from "@/components/layout/header/Header";
import Main from "@/components/layout/main/Main";
import Footer from "@/components/layout/footer/Footer";
import Modal from "@/components/modal/Modal";

export default {
  name: 'app',
  props: {
    componentName: {
      type: String,
      require: true
      //default: "ProductTable"
    }
  },
  components: {
    Header,
    Main,
    Footer,
    Modal
  }
}
</script>

<style>
</style>