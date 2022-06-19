<template>
    <Header/>
    <Main/>
    <Footer/>
</template>


<script>
  import 'bootstrap/dist/js/bootstrap.js'

  import Header from "@/components/header/Header";
  import Main from "@/components/main/Main"
  import Footer from "@/components/footer/Footer";

    function date() {
        const current = new Date();
        const day =  format(current.getDate());
        const month = format(current.getMonth() + 1);
        const year = current.getFullYear();
        return day + '.' + month + '.' + year;
    }

    function format(value) {
        if (value < 10) {
            value = '0' + value;
        }
        return value;
    }


  export default {
      name: 'app',
      components: {
          Header,
          Footer,
          Main
      },
      mounted() {
        window.addEventListener("load", () => {
          $('#datatable').DataTable();
          document.getElementById('date').innerHTML = date();
        });
      }
  }
</script>

<style>
  @import 'bootstrap/dist/css/bootstrap.min.css';
  @import 'https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css';
</style>