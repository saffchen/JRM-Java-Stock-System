<template>
    <Header/>

    <Footer/>
</template>


<script>
  import 'bootstrap/dist/js/bootstrap.js'

  import Header from "@/components/header/Header";

  import Footer from "@/components/footer/Footer";

  export default {
      name: 'app',
      components: {
          Header,
          Footer,
          
      }
  }
</script>

<style>
  @import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
  @import 'https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css';
</style>