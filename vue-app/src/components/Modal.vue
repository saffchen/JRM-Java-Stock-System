<template>
    <div class="modal fade" :id="id"  tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">{{ label }}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"/>
          </div>
          <div class="modal-body">
            <slot name="modal-body"/>
          </div>
          <div class="modal-footer">
            <button @click="$emit(btnEvent)" type="button" class="btn btn-primary">{{ btnValue }}</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: "Modal",
  props: {
    id: String,
    label: String,
    btnValue: String,
    btnEvent: String
  }
}
</script>

<style scoped>

</style>