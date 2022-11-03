<template>
  <div class="container-xl pt-3">
    <div class="d-flex align-items-center justify-content-between">
      <div class="store-chooser">
        <label class="form-label" for="stores">Choose a store:</label>
        <select name="stores" id="stores" @change="switchStore" class="mt-3 mb-4 form-control" value="7">
          <option v-for="store in stores" :key="store.id" :value="store.id" v-text="store.name" :selected="store.id === store_id"></option>
        </select>
      </div>
      <div>
        <span class="me-3">Push to add new product</span>
        <button class="btn btn-primary">Add</button>
      </div>
    </div>
    
    <table id="datatable" class="table table-hover align-middle">
      <thead class="bg-light">
      <tr>
        <th v-for="header in headers" v-text="header"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="record in products">
        <td>
          <div class="d-flex align-items-center">
            <div>
              <p class="fw-bold mb-1 text-nowrap" v-text="record.name"></p>
              <p class="text-muted mb-0 d-none d-lg-inline" v-text="record.description"></p>
            </div>
          </div>
        </td>
        <td v-text="record.price"></td>
        <td>
          <div class="d-flex flex-column align-items-start">
            <span class="badge bg-info rounded-pill d-inline" v-for="tag in record.tags" v-text="tag"></span>
          </div>
        </td>
        <td v-text="record.category"></td>
        <td v-text="record.count"></td>
        <td v-text="record.storeName"></td>
        <td>
          <div class="d-flex align-items-center justify-content-around">
            <button type="button" class="btn btn-outline-warning btn-sm me-2 border-0">Edit</button>
            <button type="button" class="btn btn-outline-danger btn-sm border-0">Remove</button>
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
      headers: ['Product', 'Price $', 'Tags', 'Category', 'Quantity', 'Store', 'Actions'],
      stores: [],
      products: [],
      store_id: 1,
    }
  },
  methods: {
    getProducts: function () {
      this.$load(async () => {
        this.products = (await this.$api.products.getAll(this.store_id)).data;
      })
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
      this.table = $("#datatable").DataTable();
    }
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
    },
  },
  created() {
    this.getStores();
    this.getProducts();
  },
  updated() {
    this.applyTable();
  }
}
</script>

<style>

.form-label {
  display: inline-block;
  margin-right: 20px
}

.form-control {
  display: inline-block;
  width: 200px;
}

.badge {
  margin-bottom: 2px;
}

div > .badge:last-child {
  margin-bottom: 0;
}

.dataTables_wrapper .row:first-child {
  padding: 20px 0;
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