<template>
    <div
        :id="id"
        class="modal fade"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabindex="-1"
        aria-labelledby="modalLabel"
        aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5
                        id="modalLabel"
                        class="modal-title"
                    >
                        {{ label }}
                    </h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                        @click="closeModal"
                    />
                </div>
                <div
                    v-if="activated"
                    class="modal-body"
                >
                    <keep-alive>
                        <component
                            :is="comp"
                            ref="stockForm"
                        />
                    </keep-alive>
                </div>
                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-primary"
                        @click="handleClick"
                    >
                        {{ btnValue }}
                    </button>
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                        @click="closeModal"
                    >
                        Close
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { defineAsyncComponent } from 'vue';

export default {
    name: 'AppModal',
    props: {
        id: String,
        label: String,
        btnValue: String,
        btnEvent: String,
        componentName: {
            type: String,
            required: true
        },
        activated: Boolean
    },
    emits: [
        'processStock',
        'deactivate'
    ],
    data() {
        return {
            addResult: {}
        };
    },
    computed: {
        comp() {
            let name = this.componentName;
            console.log(name);
            return defineAsyncComponent(() => import(`../form/${name}.vue`));
        }
    },
    methods: {
        handleClick: function(event) {
            this.$refs.stockForm[this.btnEvent](event);
        },
        closeModal: function() {
            this.$refs.stockForm.refresh();
            this.$emit('deactivate');
        },
        processStock: function (addResult) {
            if (addResult) {
                this.addResult = addResult;
                this.$emit('processStock', this.addResult);
            } else {
                this.$emit('processStock');
            }
        }
    }
};
</script>

<style scoped>
</style>