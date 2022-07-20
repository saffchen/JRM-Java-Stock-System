<template>
  <form @submit="$emit('add-stock')" method="post">
    <fieldset class="mb-3">
      <label for="stock-name" class="form-label">Name</label>
      <input type="text" class="form-control" id="stock-name"/>
    </fieldset>
    <fieldset class="mb-3">
      <label for="stock-description" class="form-label">Description</label>
      <textarea id="stock-description" class="form-control"/>
    </fieldset>
  </form>
</template>

<script>
export default {
  name: "AddStockForm",
  emits: 'add-stock'
}
</script>

<style>
</style>