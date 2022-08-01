<template>
  <Header/>
  <Main :component-name="componentName"/>
  <Footer/>
  <Modal id="add-stock"
         component-name="AddStockForm"
         label="Adding new stock"
         btn-value="Save"
         btn-event="addStock"
  />
  <Modal id="update-stock"
         component-name="UpdateStockForm"
         label="Updating new stock"
         btn-value="Update"
         btn-event="updateStock"
  />
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
      default: "StockTable"
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