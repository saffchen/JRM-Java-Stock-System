<template>
    <Header/>
    <Main component-name="ProductTable"/>
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
@import 'bootstrap/dist/css/bootstrap.min.css';
@import 'https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css';
</style>
