<template>
  <Header/>
  <main class="main mb-5">
    <StockTable/>
  </main>
  <form action=/login method="post">
    <div align="center">
      <label> User Name:</label>
      <input type="text" name="username"/>
    </div>
    <div>
      <label></label>
    </div>
    <div align="center">
      <label> Password:</label>
      <input type="password" name="password"/>
    </div>
    <div>
      <label></label>
    </div>
    <div align="center">
      <input type="submit" value="Sign In"/>
    </div>
  </form>
</template>


<script>
</script>

<style>
</style>