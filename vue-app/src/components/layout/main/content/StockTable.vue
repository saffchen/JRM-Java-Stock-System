<template>
    <div class="container-xl pt-3">
        <div
            v-if="$store.getters['user/isAdmin']"
            class="d-flex align-items-center justify-content-end py-3"
        >
            <span class="me-3">Push to add new store</span>
            <button
                class="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#add-stock"
                @click="activateAdding"
            >
                Add
            </button>
        </div>
        <table
            id="datatable"
            class="table table-hover align-middle"
        >
            <thead class="bg-light">
                <tr>
                    <th
                        v-for="header, id in headers"
                        :key="id"
                        :class="id > 1 && 'text-center'"
                        v-text="header"
                    />
                </tr>
            </thead>
            <tbody v-if="$store.getters['stock/getStatus'] !== ''">
                <tr
                    v-for="record in stocks"
                    :key="record.id"
                >
                    <td v-text="record.name" />
                    <td
                        class="text-left"
                        v-text="record.description"
                    />
                    <td
                        class="text-center"
                        v-text="record.count"
                    />
                    <td v-if="$store.getters['user/isAdmin']">
                        <div class="d-flex align-items-center justify-content-around">
                            <button
                                type="button"
                                class="btn btn-outline-warning btn-sm me-2 border-0"
                                data-bs-toggle="modal"
                                data-bs-target="#update-stock"
                                @click="activateEditing(record)"
                            >
                                Edit
                            </button>
                            <button
                                type="button"
                                class="btn btn-outline-danger btn-sm border-0"
                                data-bs-toggle="modal"
                                data-bs-target="#confirm"
                                @mouseover="setStockToDelete(record)"
                            >
                                Remove
                            </button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div
        v-if="deleting"
        id="confirm"
        class="modal fade"
        tabindex="-1"
        role="dialog"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        aria-labelledby="confirmLabel"
        aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5
                        id="confirmLabel"
                        class="modal-title"
                    >
                        Please confirm removing a stock
                    </h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                    />
                </div>
                <div class="modal-body">
                    {{ `${stockToDelete.name}, products: ${stockToDelete.count}` }}
                </div>
                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-primary"
                        data-bs-dismiss="modal"
                        @click="deleteStock(stockToDelete.id)"
                    >
                        Confirm
                    </button>
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                    >
                        Close
                    </button>
                </div>
            </div>
        </div>
    </div>
    <Modal
        v-if="$store.getters['user/isAdmin']"
        id="add-stock"
        component-name="AddStockForm"
        label="Adding new stock"
        btn-value="Save"
        btn-event="addStock"
        :activated="adding"
        @deactivate="deactivateAdding"
    />
    <Modal
        v-if="$store.getters['user/isAdmin']"
        id="update-stock"
        component-name="UpdateStockForm"
        label="Updating new stock"
        btn-value="Update"
        btn-event="updateStock"
        :activated="editing"
        @deactivate="deactivateEditing"
    />
</template>

<script>
import Modal from '@/components/modal/Modal';

export default {
    components: {
        Modal
    },
    data() {
        return {
            table: null,
            headers: this.getHeaders(),
            stocks: [],
            stockToDelete: {},
            adding: false,
            editing: false,
            deleting: false
        };
    },
    watch: {
        stocks: {
            handler: function () {
                if (this.table) {
                    this.table.destroy();
                }
            },
            deep: true
        }
    },
    created() {
        this.$store.dispatch('stock/fetchAll');
    },
    updated() {
        this.stocks = this.$store.getters['stock/getAll'];
        // eslint-disable-next-line no-undef
        this.table = $('#datatable').DataTable();
    },
    methods: {
        getHeaders: function () {
            const headers = ['Name', 'Description', 'Total Products'];
            if (this.$store.getters['user/isAdmin']) {
                headers.push('Actions');
            }
            return headers;
        },
        activateAdding: function () {
            this.adding = true;
        },  
        deactivateAdding: function () {
            this.adding = false;
        },
        activateEditing: function (object) {
            this.$store.commit('stock/ACTIVATE_EDITING', object);
            this.editing = true;
        },
        deactivateEditing: function () {
            this.$store.commit('stock/DEACTIVATE_EDITING');
            this.editing = false;
        },
        setStockToDelete: function(stock) {
            this.stockToDelete = stock;
            this.deleting = true;
        },
        deleteStock: function (id) {
            this.deleting = false;
            this.$load(async () => {
                await this.$store.dispatch('stock/delete', id);
                this.stockToDelete = {};
            });
        }
    }
};
</script>

<style>

input[type="search"] {
  display: inline-block;
  margin-left: 10px;
  width: 200px;
}

th:hover {
  cursor: pointer;
}

tr:hover {
  cursor: default;
}

select:hover {
  cursor: pointer;
} 

.form-select {
  width: 70px;
  margin: 0 5px;
}

.badge {
  margin-bottom: 2px;
}

div > .badge:last-child {
  margin-bottom: 0;
}

.dataTables_wrapper .row:first-child {
  align-items: center;
  padding: 20px 0;
}

.dataTables_length label {
  display: flex;
  align-items: center;
}

.dataTables_filter {
  display: flex;
  justify-content: flex-end;
}

.dataTables_paginate {
  display: flex;
  justify-content: flex-end;
}

.page-item.active .page-link {
  background-color: #657894 !important;
  border-color: #657894 !important;
  color: #fff !important;
}

.page-link {
  color: #657894 !important;
}
</style>