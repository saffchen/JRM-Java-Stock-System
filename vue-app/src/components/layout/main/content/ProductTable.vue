<template>
    <div class='container-xl pt-3'>
        <div class='d-flex align-items-center justify-content-between py-3'>
            <div class='d-flex align-items-center store-chooser'>
                <label
                    class='form-label'
                    for='stores'
                >Choose a store:</label>
                <select
                    id='stores'
                    name='stores'
                    class='form-control'
                    value='7'
                    @change='switchStore'
                >
                    <option
                        v-for='store in stores'
                        :key='store.id'
                        :value='store.id'
                        :selected='store.id === store_id'
                        v-text='store.name'
                    />
                </select>
            </div>
            <div>
                <span class='me-3'>Push to add new product</span>
                <button class='btn btn-primary'>
                    Add
                </button>
            </div>
        </div>
    
        <table
            id='datatable'
            class='table table-hover align-middle'
        >
            <thead class='bg-light'>
                <tr>
                    <th
                        v-for='header, id in headers'
                        :key='id'
                        :class="id > 0 && 'text-center'"
                        v-text='header'
                    />
                </tr>
            </thead>
            <tbody>
                <tr
                    v-for='record, id in products'
                    :key='id'
                >
                    <td>
                        <div class='d-flex align-items-center'>
                            <div>
                                <p
                                    class='fw-bold mb-1 text-nowrap'
                                    v-text='record.name'
                                />
                                <p
                                    class='text-muted mb-0 d-none d-lg-inline'
                                    v-text='record.description'
                                />
                            </div>
                        </div>
                    </td>
                    <td
                        class='text-center'
                        v-text='record.price'
                    />
                    <td>
                        <div class='d-flex flex-column align-items-center'>
                            <span
                                v-for='tag, idx in record.tags'
                                :key='idx'
                                class='badge bg-info rounded-pill d-inline'
                                v-text='tag'
                            />
                        </div>
                    </td>
                    <td
                        class='text-center'
                        v-text='record.category'
                    />
                    <td
                        class='text-center'
                        v-text='record.count'
                    />
                    <td>
                        <div class='d-flex align-items-center justify-content-around'>
                            <button
                                type='button'
                                class='btn btn-outline-warning btn-sm me-2 border-0'
                            >
                                Edit
                            </button>
                            <button
                                type='button'
                                class='btn btn-outline-danger btn-sm border-0'
                            >
                                Remove
                            </button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

export default {
    data() {
        return {
            table: null,
            headers: ['Product', 'Price $', 'Tags', 'Category', 'Quantity', ''],
            stores: [],
            products: [],
            store_id: 1
        };
    },
    watch: {
        products: {
            handler: function () {
                if (this.table) {
                    this.table.destroy();
                }
            },
            deep: true
        },
        store_id() {
            this.getProducts();
        }
    },
    created() {
        this.getStores();
        this.getProducts();
    },
    updated() {
        this.applyTable();
    },
    methods: {
        getProducts: function () {
            this.$load(async () => {
                this.products = (await this.$api.products.getAll(this.store_id)).data;
            });
        },
        getStores: function () {
            this.$load(async () => {
                let result = (await this.$api.stocks.getAll()).data
                    .filter((store) => store.count > 0)
                    .sort((a, b) => a.name > b.name);
                this.stores = result;
                this.store_id = result[0].id;
            });
        },
        switchStore: function (event) {
            this.store_id = event.target.value;
        },
        applyTable: function () {
            // eslint-disable-next-line no-undef
            this.table = $('#datatable').DataTable();
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

.form-label {
  display: inline-block;
  min-width: 120px;
  margin: 0;
}

.form-select {
  width: 80px;
  margin: 0 5px;
}

select:hover {
  cursor: pointer;
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