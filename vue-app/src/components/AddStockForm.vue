<template>
  <form @submit="addStock" @submitFromModal="addStock" ref="addStockForm" name="add-stock" method="post">
    <fieldset class="mb-3">
      <label for="stock-name" class="form-label">Name</label>
      <input type="text" class="form-control" id="stock-name" required/>
    </fieldset>
    <fieldset class="mb-3">
      <label for="stock-description" class="form-label">Description</label>
      <textarea id="stock-description" class="form-control" required/>
    </fieldset>
  </form>
</template>

<script>
export default {
  name: "AddStockForm",
  methods: {
    addStock: function (event) {
      event.preventDefault();
      console.log(event);
      console.log('fetching http://localhost:8080/api/satellites');
    }
  }
}
</script>

<style>
</style>