<template>
  <Header/>
  <Main component-name="Login"/>
</template>


<script>
import Header from "@/components/layout/header/Header";
import Main from "@/components/layout/main/Main";

export default {
  name: 'app',
  components: {
    Header,
    Main
  }
}
</script>

<style>
</style>