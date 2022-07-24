<!--https://codepen.io/wslotterback/pen/mmRrVx-->

<template>
  <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" data-bs-toggle="dropdown" id="menu">
      <span class="fs-5">Navigate</span>
    </button>
    <ul class="dropdown-menu dropdown-menu-start animate slidein p-0" aria-labelledby="menu">
      <li v-for="item in menuItems" @click="$router.push({name: item.id })">
        <a class="dropdown-item p-2" href="#">{{ item.label }}</a>
      </li>
    </ul>
  </div>
</template>

<script>

export default {
  data() {
    return {
      menuItems: [
        {
          id: 'Login',
          label: 'Login'
        },
        {
          id: 'ProductTable',
          label: 'View Products'
        },
        {
          id: 'StockTable',
          label: 'View Stocks'
        },
        {
          id: 'Report',
          label: 'Generate report'
        },
        {
          id: 'Import',
          label: 'Import'
        },
        {
          id: 'Export',
          label: 'Export'
        }
      ]
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