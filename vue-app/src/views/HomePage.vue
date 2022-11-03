<template>
    <Header/>
    <Main component-name="ProductTable"/>
    <Footer/>
</template>


<script>
  import Header from "@/components/layout/header/Header";
  import Main from "@/components/layout/main/Main";
  import Footer from "@/components/layout/footer/Footer";

  export default {
      name: 'app',
      props: {
        componentName: {
          type: String,
          require: true
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
