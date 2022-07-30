<template>
  <form @submit="updateStock" name="update-stock" method="put">
    <fieldset class="mb-3">
      <label for="stock-name" class="form-label">Name</label>
      <input type="text" class="form-control" id="stock-name" v-model="name"/>
    </fieldset>
    <fieldset class="mb-3">
      <label for="stock-description" class="form-label">Description</label>
      <textarea id="stock-description" class="form-control" v-model="description"/>
    </fieldset>
  </form>
</template>

<script>
export default {
  name: "UpdateStockForm",
  data() {
    return {
      name: '',
      description: '',
      payload: {}
    }
  },
  methods: {
    updateStock: function (event) {
      if (event.type === 'submit') {
        event.preventDefault();
      }
      if (this.name === '' || this.description === '' ) {
        console.log('[ERROR]: Please fill in empty fields');
        return;
      }
      this.$load(async () => {
        await this.$api.satellites.update(this.payload);
        console.log('[SUCCESS]: Stock updated')
      });
      this.name = '';
      this.description = '';
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