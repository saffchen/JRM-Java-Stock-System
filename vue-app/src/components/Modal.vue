<template>
    <div class="modal fade" :id="id"  tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">{{ label }}</h5>
            <button @click="closeModal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"/>
          </div>
          <div class="modal-body">
            <AddStockForm ref="stockForm" @add="addStock"/>
          </div>
          <div class="modal-footer">
            <button @click="handleClick" type="button" class="btn btn-primary">{{ btnValue }}</button>
            <button @click="closeModal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import AddStockForm from "@/components/AddStockForm";

export default {
  name: "Modal",
  components: {
    AddStockForm
  },
  data() {
    return {
      addResult: {}
    }
  },
  methods: {
    handleClick: async function(event) {
      await this.$refs.stockForm[this.btnEvent](event);
    },
    closeModal: function(event) {
      this.$refs.stockForm.refresh();
    },
    addStock: function (addResult) {
      this.addResult = addResult;
      this.$emit('addStock', this.addResult);
    }
  },
  props: {
    id: String,
    label: String,
    btnValue: String,
    btnEvent: String
  },
  emits: ['addStock']
}
</script>

<style scoped>

</style>