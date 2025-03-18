<template>
  <div class="main-grid">
    <a href="#"><img id="mainLogo" src="../../../public/yyGangLogo.png" alt="mainLogo" ></a>
    <div class="nav">
      <a href="#">품 정보</a>
      <a href="#">맞춤 제품 찾기</a>
      <a href="#">약 질문</a>
      <a href="#">자유 게시판</a>
    </div>
    <div class="iconSec">
      <a href="#"><img class="search" src="../../../public/icons/seach.png"></a>
      <a href="#"><img class="cart" src="../../../public/icons/cart.png"></a>
      <a href="#"><img class="heart" src="../../../public/icons/heart.png"></a>

    </div>
    
  </div>
</template>

<script>
export default {
  name: 'Header',
  props: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .main-grid{
    display: flex;
    align-items: center;
    justify-content: space-between;
  }


  #mainLogo {
    width: 80px;
    margin: 0px 20px;
  }

  .main-grid > .nav{
    display: inline;
  }

  .main-grid > .nav> a{
    margin: 0px 20px;
    text-decoration: none;
    color: black;
    font-size: 24px;
  }

  .iconSec > a > img {
    width: 35px;
    margin: 10px;
    justify-content: end;
  }



</style>
