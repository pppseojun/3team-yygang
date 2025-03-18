<template>
  <div id="app">
    <Header></Header>
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
  import Header from "@/components/common/Header.vue";

  export default {
    name: 'App',
    components:{
      Header
    }
  }

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
