<template>

      <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
          <div class="container justify-content-between">
              <DropdownMenu/>
              <div class="navbar-brand col-6" align="center">
                  JRM Stock System Project
              </div>
              <LoginBtn/>
          </div>
      </nav>

</template>

<script>
import DropdownMenu from "./DropdownMenu.vue";
import LoginBtn from "./LoginBtn.vue";

export default {
  components: {
    DropdownMenu, LoginBtn
  }
}

//https://bootstrap-4.ru/docs/4.0/layout/grid/
//https://getbootstrap.com/docs/5.0/components/navbar/?


</script>

<styles scoped>

</styles>
