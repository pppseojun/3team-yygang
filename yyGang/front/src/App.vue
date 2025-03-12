<template>
  <div id="app">
    <h2>VUE Router TEST </h2>
    <router-link to="/main">main</router-link>
    <br>
    <router-link to="/user/login">login</router-link>
    <br>
    <router-link to="/qboard">qboard</router-link>
    <router-view></router-view>
  </div>
</template>

<script>

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
