<template>
  <Header/>
  <main class="main mb-5">
    <StockTable/>
  </main>
  <form action=/login method="post">
  <div><label> User Name : <input type="text" name="username"/> </label></div>
  <div><label> Password: <input type="password" name="password"/> </label></div>
  <div><input type="submit" value="Sign In"/></div>
    </form>
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
@import 'https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css';
</style>