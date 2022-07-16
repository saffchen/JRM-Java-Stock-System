<template>
    <div class="container-xl">
          <div class="d-flex align-items-center justify-content-end mt-5">
            <span class="me-3">Push to add new stock</span>
            <button class="btn btn-primary">Add</button>
          </div>
            <table id="datatable" class="table table-hover align-middle">
                <thead class="bg-light">
                  <tr>
                    <th v-for="header in headers" v-text="header"></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="record in stocks">
                    <td>
                      <div class="d-flex align-items-center">
                        <div>
                          <p class="fw-bold mb-1 text-nowrap" v-text="record.name"></p>
                        </div>
                      </div>
                    </td>
                    <td>
                      <div class="d-flex align-items-center">
                        <div>
                          <p class="fw-bold mb-1 text-nowrap" v-text="record.description"></p>
                        </div>
                      </div>
                    </td>
                    <td v-text="record.count"></td>
                  </tr>
                </tbody>
              </table>
        </div>
</template>

<script>
export default {
    data() {
      return {
        headers: ['Name', 'Description', 'Count'],
        stocks: []
      }
    },
    methods: {
      getStocks: function() {
        this.$load(async () => {
          this.stocks = (await this.$api.stocks.getAll()).data
        })
      },
      applyTable: function() {
        $("#datatable").DataTable();
      }
    },
    created() {
      this.getStocks();
    },
    updated() {
      this.applyTable();
    }
}
</script>

<style>

    .dataTables_wrapper .row:first-child {
        padding: 20px 0;
    }

    .page-item.active .page-link {
        background-color: #657894!important;
        border-color: #657894!important;
        color: #fff!important;
    }

    .page-link {
        color: #657894!important;
    }
</style>