<template>
    <div class="dropdown">
        <button
            id="menu"
            class="btn btn-default dropdown-toggle"
            :class="$store.getters['user/loggedIn'] || 'disabled'"
            type="button"
            data-bs-toggle="dropdown"
        >
            <span class="fs-5">Navigate</span>
        </button>
        <ul
            class="dropdown-menu dropdown-menu-start animate slide-in m-0"
            aria-labelledby="menu"
        >
            <li
                v-for="item, id in menuItems"
                :key="id"
                @click="$router.push({
                    name: item.id 
                })"
            >
                <a
                    class="dropdown-item p-2"
                    href="#"
                >{{ item.label }}</a>
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
                    id: 'ProductTable',
                    label: 'Products'
                },
                {
                    id: 'StockTable',
                    label: 'Stocks'
                },
                {
                    id: 'Report',
                    label: 'Make Report'
                },
                {
                    id: 'Import',
                    label: 'Import Data'
                },
                {
                    id: 'Export',
                    label: 'Export Data'
                }
            ]
        };
    }
};
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
  
  .slide-in {
    -webkit-animation-name: slideIn;
    animation-name: slideIn;
  }
</style>