<template>
    <div class="modal fade" :id="id"  tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">{{ label }}</h5>
            <button @click="closeModal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"/>
          </div>
          <div class="modal-body">
            <keep-alive>
              <component :is="comp" ref="stockForm" @action="processStock"/>
            </keep-alive>
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
import { defineAsyncComponent } from 'vue';

export default {
  name: "Modal",
  activated() {
    console.log("modal is activated")
  },
  data() {
    return {
      addResult: {}
    }
  },
  methods: {
    handleClick: function(event) {
      this.$refs.stockForm[this.btnEvent](event);
    },
    closeModal: function(event) {
      this.$refs.stockForm.refresh();
    },
    processStock: function (addResult) {
      if (addResult) {
        this.addResult = addResult;
        this.$emit('processStock', this.addResult);
      } else {
        this.$emit('processStock');
      }
    }
  },
  props: {
    id: String,
    label: String,
    btnValue: String,
    btnEvent: String,
    componentName: {
      type: String,
      required: true,
      // default: "AddStockForm"
    },
    stockObject: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  computed: {
    comp() {
      let name = this.componentName
      console.log(name)
      return defineAsyncComponent(() => import(`./${name}`))
    }
  },
  emits: ['processStock']
}
</script>

<style scoped>
</style>