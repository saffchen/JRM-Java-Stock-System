<template>
    <main class="main mb-5">
      <component :is="component"/>
    </main>
</template>

<script>
import ProductTable from "@/components/main/ProductTable";
import StockTable from "@/components/main/StockTable";

export default {
  components: {
    ProductTable,
    StockTable
  },
  data() {
    return {
      component: this.content
    }
  },
  props: {
    content: String
  },
}

</script>

<style>

</style>