<!--https://stackoverflow.com/questions/53406350/vue-import-vue-components-dynamically
https://medium.com/@pratikpatel_60309/dynamic-importing-component-templates-with-vue-js-78d2167db1e7-->


<template>
  <main class="main mb-5">
    <router-view></router-view>
<!--    <component :is="componentName"></component>-->
  </main>
</template>

<script>
import { defineAsyncComponent } from 'vue'

export default {
  name: "DynamicComponent",
  props: {
    componentName: {
      type: String,
      /*required: true*/
      default: 'ProductTable'
    }
  },
  computed: {
    componentName() {
      let name = "StockTable"
      return defineAsyncComponent(() => import(`./${name}`)) 
    }
  },
}
</script>

<style>
</style>