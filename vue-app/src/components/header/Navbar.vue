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
import LogoutBtn from "./LogoutBtn";

export default {
  components: {
    DropdownMenu, LoginBtn, LogoutBtn
  }
}

</script>

<styles scoped>

</styles>
