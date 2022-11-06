<template>
    <div
        :id="id"
        class="modal fade"
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
                <div class="modal-body">
                    <keep-alive>
                        <component
                            :is="comp"
                            ref="stockForm"
                            @action="processStock"
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
    name: "AppModal",
    props: {
        id: String,
        label: String,
        btnValue: String,
        btnEvent: String,
        componentName: {
            type: String,
            required: true
        },
        stockObject: {
            type: Object,
            default() {
                return {};
            }
        }
    },
    emits: ['processStock'],
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
    activated() {
        console.log("modal is activated");
    },
    methods: {
        handleClick: function(event) {
            this.$refs.stockForm[this.btnEvent](event);
        },
        closeModal: function() {
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
    }
};
</script>

<style scoped>
</style>