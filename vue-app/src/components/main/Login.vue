<template>
  <Header/>
  <main class="main mb-5">
    <StockTable/>
  </main>
  <form action=/login method="post">
    <div class="container">
      <div class="row" align="center">
        <div class="col">
          User Name:
          <div class="col">
            <input type="text" name="username"/>
          </div>
        </div>
      </div>
          <div>
            <label></label>
          </div>
          <div class="row" align="center">
            <div class="col">
              Password:
              <div class="col">
                <input type="password" name="password"/>
              </div>
              <div>
              </div>
                <label></label>
              </div>
              <div align="center">
                <input type="submit" value="Sign In"/>
              </div>
            </div>
          </div>
  </form>
</template>


<script>
</script>

<style>
</style>