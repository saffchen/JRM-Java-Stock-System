<template>
    <div class="container-xl pt-3">
        <div class="d-flex align-items-center justify-content-end py-3">
            <span class="me-3">Push to add new store</span>
            <button
                class="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#add-stock"
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
            <tbody>
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
                        style="width: 150px;"
                        v-text="record.count"
                    />
                    <td>
                        <div class="d-flex align-items-center justify-content-around">
                            <button
                                type="button"
                                class="btn btn-outline-warning btn-sm me-2 border-0"
                                data-bs-toggle="modal"
                                data-bs-target="#update-stock"
                                @click="passObject(record)"
                            >
                                Edit
                            </button>
                            <button
                                type="button"
                                class="btn btn-outline-danger btn-sm border-0"
                                @click="deleteStock(record.id)"
                            >
                                Remove
                            </button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <Modal
        id="add-stock"
        component-name="AddStockForm"
        label="Adding new stock"
        btn-value="Save"
        btn-event="addStock"
        @process-stock="addStock"
    />
    <Modal
        id="update-stock"
        component-name="UpdateStockForm"
        label="Updating new stock"
        btn-value="Update"
        btn-event="updateStock"
        @process-stock="updateStock"
    />
</template>

<script>
import Modal from "@/components/modal/Modal";

export default {
    components: {
        Modal
    },
    data() {
        return {
            table: null,
            headers: ['Name', 'Description', 'Total Products', ''],
            stocks: []
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
        this.getStocks();
    },
    updated() {
        this.applyTable();
    },
    methods: {
        getStocks: function () {
            this.$load(async () => {
                this.stocks = (await this.$api.stocks.getAll()).data;
            });
        },
        applyTable: function () {
            // eslint-disable-next-line no-undef
            this.table = $("#datatable").DataTable();
        },
        addStock: function (stockObj) {
            this.stocks.push(stockObj);
        },
        passObject: function (object) {
            this.$store.commit('add', object);
            console.log("Store object in StockTable", this.$store.state);
        },
        updateStock: function () {
            this.getStocks();
        },
        deleteStock: function (id) {
            console.log(id);
            this.$load(async () => {
                await this.$api.stocks.delete(id);
                this.getStocks();
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