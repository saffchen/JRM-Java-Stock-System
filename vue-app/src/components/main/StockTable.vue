<template>
  <div class="container-xl">
    <div class="d-flex align-items-center justify-content-end mt-5">
      <span class="me-3">Push to add new store</span>
      <button class="btn btn-primary">Add</button>
    </div>
    <table id="datatable" class="table table-hover align-middle">
      <thead class="bg-light">
      <tr>
        <th v-for="header in headers" v-text="header"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="record in satellites" v-bind:key="record.id">
        <td>
          <p v-text="record.name"></p>
        </td>
        <td v-text="record.description"></td>
        <td v-text="record.count"></td>
        <td>
          <div class="d-flex align-items-center justify-content-around">
            <button type="button" class="btn btn-outline-warning btn-sm me-2 border-0">Edit</button>
            <button
                type="button"
                class="btn btn-outline-danger btn-sm border-0"
                v-on:click="deleteSatellites(record.id)"
            >Remove</button>
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
      headers: ['Name', 'Description', 'Total Products', 'Actions'],
      satellites: []
    }
  },
  methods: {
    getSatellites: function () {
      this.$load(async () => {
        this.satellites = (await this.$api.satellites.getAll()).data
      })
    },
    deleteSatellites: function (id) {
      console.log(id)
      this.$load( async () => {
        await this.$api.satellites.delete(id)
      })
    },
  },
  watch: {
    satellites: function () {
      deep: true,
      this.getSatellites();
    }
  },
  created() {
    this.getSatellites();
  },
}
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
  background-color: #657894 !important;
  border-color: #657894 !important;
  color: #fff !important;
}

.page-link {
  color: #657894 !important;
}
</style>