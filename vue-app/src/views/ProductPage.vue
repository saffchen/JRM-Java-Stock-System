<template>
  <Header/>
  <Main :component-name="componentName"/>
  <Footer/>
  <Modal id="add-stock" label="Adding new stock" btn-value="Save" btn-event="addStock"/>
</template>

<script>

import Header from "@/components/header/Header";
import Main from "@/components/main/Main";
import Footer from "@/components/footer/Footer";
import Modal from "@/components/Modal";

export default {
  name: 'app',
  props: {
    componentName: {
      type: String,
      require: true,
      default: "ProductTable"
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