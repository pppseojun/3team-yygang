<template>
  <div>
    <h1>로그인 페이지</h1>
    <p>로그인 폼을 여기에 추가하세요.</p>
  </div>
  <div>
    <form @submit.prevent="fnLogin">
      <input v-model="userId" type="text" placeholder="아이디"> <br>
      <input v-model="password" type="password" placeholder="패스워드"> <br>
      <button v-on:click="requestAPI(userId, password);">확인</button>
      <input type="reset" value="초기화">
    </form>
  </div>
</template>

<script>
export default {
  name: "loginView"
};
</script>

<style>

</style>