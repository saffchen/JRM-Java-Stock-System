<template>
  <div id="status" v-if="showMessage">
    <span class="message" :class="messageClass" v-text="messageText" />
  </div>
  <form @submit="addStock" name="add-stock" method="post">
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
  name: "AddStockForm",
  data() {
    return {
      name: '',
      description: '',
      payload: {},
      showMessage: false,
      messageClass: '',
      messageText: '',
      addResult: {}
    }
  },
  methods: {
    addStock: function (event) {
      if (event.type === 'submit') {
        event.preventDefault();
      }
      if (this.name === '' || this.description === '' ) {
        this.messageClass = 'error';
        this.messageText = 'Please fill in empty fields';
        this.showMessage = true;
        return;
      }
      this.$load(async () => {
        const result = await this.$api.stocks.create(this.payload);
        if (result.status === 200) {
          this.emitResult(result);
          this.messageText = 'New stock successfully saved';
          this.showMessage = true;
        }
      }, this.handleError);
      this.refresh();
    },
    emitResult: function (result) {
      this.addResult = JSON.parse(result.request.response);
      this.$emit('action', this.addResult);
      this.messageClass = 'success';
    },
    handleError: function (error) {
      this.messageClass = 'error';
      this.messageText = error.response.data.message;
      this.showMessage = true;
    },
    refresh: function() {
      this.name = '';
      this.description = '';
      this.messageClass = '';
      this.messageText = '';
      this.showMessage = false;
      document.getElementById('stock-description').removeAttribute("style");
    }
  },
  watch: {
    name() {
      this.payload['name'] = this.name;
      this.showMessage = false;
    },
    description() {
      this.payload['description'] = this.description;
      this.showMessage = false;
    }
  },
  emits: ['action']
}
</script>

<style scoped>
  #status {
    text-align: right;
  }

  .message {
    padding: 5px;
    border-radius: 10px;
  }

  .success {
    background: #095d09;
    color: white;
  }

  .error {
    background: #b21111;
    color: white;
  }

</style>