<template>
    <div class="container-xl">
          <div class="d-flex align-items-center justify-content-end mt-5">
            <span class="me-3">Push to add new product</span>
            <button class="btn btn-primary">Add</button>
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
                          <p class="fw-bold mb-1 text-nowrap" v-text="record.title"></p>
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
                    <td v-text="record.satellite"></td>
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
        headers: ['Title', 'Price $', 'Tags', 'Category', 'Count', 'Satellite', 'Actions'],
        products: []
      }
    },
      created() {
          this.$load(async() => {
            this.products = (await this.$api.products.getAll()).data
            console.log(this.products)
          } )
      }
}

    $('#datatable').ready(() => {
      $('#datatable').DataTable()
    });
</script>

<style>

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
        background-color: #657894!important;
        border-color: #657894!important;
        color: #fff!important;
    }

    .page-link {
        color: #657894!important;
    }
</style>