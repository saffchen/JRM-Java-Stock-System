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
import axios from 'axios';

export default {
    name: 'Footer',
    data() {
      return {
          date: getDate(),
          contributors: []
        }
    },
    mounted() {
      getContributors(this)
    }
}

function getContributors(obj) {
    const axiosInstance = axios.create({
      baseURL: 'http://localhost:8080',
      headers: {
        'Accept': 'application/json'
      }
    });

    return axiosInstance.get('/participants')
                        .then(response => {
                          obj.contributors = response.data;
                        })
                        .catch(error => {
                          console.error(error);
                        });
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