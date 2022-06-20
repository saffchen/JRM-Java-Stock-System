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
                  <tr v-for="record in table">
                    <td>
                      <div class="d-flex align-items-center">
                        <div>
                          <p class="fw-bold mb-1 text-nowrap" v-text="record.Title"></p>
                          <p class="text-muted mb-0 d-none d-lg-inline" v-text="record.Description"></p>
                        </div>
                      </div>
                    </td>
                    <td v-text="record.Price"></td>
                    <td>
                      <div class="d-flex flex-column align-items-start">
                        <span class="badge bg-info rounded-pill d-inline" v-for="tag in record.Tags" v-text="tag"></span>
                      </div>
                    </td>
                    <td v-text="record.Category"></td>
                    <td v-text="record.Count"></td>
                    <td v-text="record.Satellite"></td>
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
        table:
            [
              {
                'Id': '1',
                'Title': 'PlayStation 5 Console',
                'Description': 'Experience an all-new generation of incredible PlayStation games.',
                'Price': '1200',
                'Tags': ['Console', 'Gaming', 'Home'],
                'Category': 'PlayStation',
                'Count': '120',
                'Satellite': 'Moscow'
              },
              {
                'Id': '2',
                'Title': 'PlayStation 4 PRO Console',
                'Description': 'Digital Edition console with a vibrant array of new colour options.',
                'Price': '950',
                'Tags': ['Console', 'Gaming', 'Home'],
                'Category': 'PlayStation',
                'Count': '56',
                'Satellite': 'Moscow'
              },
              {
                'Id': '3',
                'Title': 'GoPro HERO 10 Black',
                'Description': 'A professional film studio in the palm of your hand.',
                'Price': '550',
                'Tags': ['Action', 'Video'],
                'Category': 'Camera',
                'Count': '15',
                'Satellite': 'Moscow'
              },
              {
                'Id': '4',
                'Title': 'GoPro MAX',
                'Description': 'A professional film studio in the palm of your hand.',
                'Price': '550',
                'Tags': ['Action', 'Video'],
                'Category': 'Camera',
                'Count': '95',
                'Satellite': 'Moscow'
              },
              {
                'Id': '5',
                'Title': 'Sony A7 |||',
                'Description': 'Professional SLR Camera.',
                'Price': '2900',
                'Tags': ['Photo'],
                'Category': 'Camera',
                'Count': '24',
                'Satellite': 'Moscow'
              },
              {
                'Id': '6',
                'Title': 'Apple Iphone 12 128gb',
                'Description': 'Iphone with large screen.',
                'Price': '699',
                'Tags': ['Phone', 'Camera'],
                'Category': 'Phone',
                'Count': '60',
                'Satellite': 'Moscow'
              },
              {
                'Id': '7',
                'Title': 'Apple Iphone 13 mini 128',
                'Description': 'New iphone mini in a new color scheme.',
                'Price': '699',
                'Tags': ['Phone', 'Camera'],
                'Category': 'Phone',
                'Count': '29',
                'Satellite': 'Moscow'
              },
              {
                'Id': '8',
                'Title': 'Apple iphone 13 Pro Max 128',
                'Description': 'New iphone in a new color scheme and Lidar camera.',
                'Price': '1099',
                'Tags': ['Phone', 'Camera'],
                'Category': 'Phone',
                'Count': '13',
                'Satellite': 'Moscow'
              },
              {
                'Id': '9',
                'Title': 'HUAWEI MateBook 14 KLVL-W56W 16+512GB Space Grey',
                'Description': 'Slim and light laptop.',
                'Price': '1049',
                'Tags': ['Laptop', 'Graphic'],
                'Category': 'Laptop',
                'Count': '38',
                'Satellite': 'Moscow'
              },
              {
                'Id': '10',
                'Title': 'Lenovo Legion 5 15ACH6A',
                'Description': 'Slim and powerful laptop for gaming.',
                'Price': '1949',
                'Tags': ['Laptop', 'Gaming', 'Home'],
                'Category': 'Laptop',
                'Count': '9',
                'Satellite': 'Saint Petersburg'
              },
              {
                'Id': '11',
                'Title': 'GoPro HERO 10 Black',
                'Description': 'A professional film studio in the palm of your hand.',
                'Price': '550',
                'Tags': ['Action', 'Video'],
                'Category': 'Camera',
                'Count': '15',
                'Satellite': 'Saint Petersburg'
              },
              {
                'Id': '12',
                'Title': 'GoPro MAX',
                'Description': 'A professional film studio in the palm of your hand.',
                'Price': '550',
                'Tags': ['Action', 'Video'],
                'Category': 'Camera',
                'Count': '95',
                'Satellite': 'Saint Petersburg'
              }
        ]
      }
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