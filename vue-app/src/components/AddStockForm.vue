<template>
  <form @submit="addStock" name="add-stock" method="post">
    <fieldset class="mb-3">
      <label for="stock-name" class="form-label">Name</label>
      <input type="text" class="form-control" id="stock-name" v-model="name" required/>
    </fieldset>
    <fieldset class="mb-3">
      <label for="stock-description" class="form-label">Description</label>
      <textarea id="stock-description" class="form-control" v-model="description" required/>
    </fieldset>
  </form>
</template>

<script>
export default {
  name: "AddStockForm",
  data() {
    return {
      name: '',
      description: '',
      payload: {}
    }
  },
  methods: {
    addStock: function (event) {
      event.preventDefault();
      this.$load(async () => {
        await this.$api.stocks.update(this.payload);
      })
    }
  },
  watch: {
    name() {
      this.payload['name'] = this.name;
    },
    description() {
      this.payload['description'] = this.description;
    }
  }
}
</script>

<style>
</style>