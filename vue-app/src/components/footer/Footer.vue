<template>
    <footer class="footer bg-secondary p-1">
        <div class="container">
            <div class="pt-4 pb-3 text-decoration-underline">Project contributors:</div>
            <div class="row mb-3">
                <div class="col-lg-4 col-md-6" v-for="contributor in contributors">
                    <p class="text-nowrap">{{ contributor.email }} - {{ contributor.nickname }}</p>
                </div>
            </div>
            <div class="row text-center">
                <p>{{ date }}</p>
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    data() {
        return {
          date: getDate(),
            
          contributors: [
              {
                  'id': 1,
                  'email': 'denis.savchenko98@yandex.ru',
                  'nickname': 'saffchen'
              },
              {
                  'id': 2,
                  'email': 'freesky15@yandex.ru',
                  'nickname': 'freesky15'
              },
              {
                  'id': 3,
                  'email': 'rusokolov4@gmail.com',
                  'nickname': 'rusokolov1987'
              },
              {
                  'id': 4,
                  'email': 'thiend.weisman@gmail.com',
                  'nickname': 'ReyBove'
              },
              {
                  'id': 5,
                  'email': 'alexan.sergeev@gmail.com',
                  'nickname': 'aalexeen'
              },
              {
                  'id': 6,
                  'email': 'vit.shvaichuk@gmail.com',
                  'nickname': 'fstk1337'
              }
          ]
        }
    }
}

function getDate() {
  const current = new Date();
  const day =  format(current.getDate());
  const month = format(current.getMonth() + 1);
  const year = current.getFullYear();
  return day + '.' + month + '.' + year;
}

function format(value) {
  if (value < 10) {
    value = '0' + value;
  }
  return value;
}
</script>

<style scoped>
    
</style>