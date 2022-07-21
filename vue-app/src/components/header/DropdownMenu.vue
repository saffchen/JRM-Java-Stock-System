<template>
    <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" data-bs-toggle="dropdown" id="menu">
            <span class="fs-5">Navigate</span>
        </button>
        <ul class="dropdown-menu dropdown-menu-start animate slidein p-0" aria-labelledby="menu">
          <li v-for="entry in Object.entries(menuItems)">
            <router-link class="dropdown-item p-2" :to="{ name: entry[1] }">{{ entry[0] }}</router-link>
          </li>
        </ul>
    </div>
</template>

<script>

export default {
    data() {
      return {
        menuItems: {
          'Home': 'Home',
          'View Stocks': 'Stocks',
          'Generate report': 'Report',
          'Import': 'Import',
          'Export': 'Export'
        }
      }
    }
}
</script>


<style scoped>
    .animate {
    animation-duration: 0.3s;
    -webkit-animation-duration: 0.3s;
    animation-fill-mode: both;
    -webkit-animation-fill-mode: both;
  }

@keyframes slideIn {
    0% {
      transform: translateY(1rem);
      opacity: 0;
    }
  
    100% {
      transform: translateY(0rem);
      opacity: 1;
    }
  
    0% {
      transform: translateY(1rem);
      opacity: 0;
    }
  }
  
  @-webkit-keyframes slideIn {
    0% {
      -webkit-transform: transform;
      -webkit-opacity: 0;
    }
  
    100% {
      -webkit-transform: translateY(0);
      -webkit-opacity: 1;
    }
  
    0% {
      -webkit-transform: translateY(1rem);
      -webkit-opacity: 0;
    }
  }
  
  .slidein {
    -webkit-animation-name: slideIn;
    animation-name: slideIn;
  }
</style>