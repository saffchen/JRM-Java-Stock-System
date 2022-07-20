<template>
    <Header/>
    <Main content="StockTable"/>
    <Footer/>
    <Modal id="add-stock" label="Add new stock" btn-value="Add" btn-event="add-stock">
      <template #modal-body>
        <AddStockForm/>
      </template>
    </Modal>
</template>

<script>
  import Header from "@/components/header/Header";
  import Main from "@/components/main/Main";
  import Footer from "@/components/footer/Footer";
  import Modal from "@/components/Modal";
  import AddStockForm from "@/components/AddStockForm";

  export default {
      name: 'app',
      components: {
          Header,
          Main,
          Footer,
          Modal,
          AddStockForm
      }
  }
</script>

<style>
</style>