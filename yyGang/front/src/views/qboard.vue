<template>
  <div>
    <h1>약 질문 게시판</h1>
    <p>게시판 목록이 여기에 표시됩니다.</p>
    <table style="border: 1px solid">
      <thead>
        <tr>
          <th>게시글 ID</th>
          <th>제목</th>
          <th>생성일</th>
          <th>사용자 ID</th>
        </tr>
      </thead>
      <tbody>
      <tr v-for="(row,no) in list" :key="no">
        <td>{{row.qboardId}}</td>
        <td>{{row.qboardTitle}}</td>
        <td>{{row.qboardDate}}</td>
        <td>{{row.userId}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  export default {
    name: "qboardView",
    data(){
      return {
        requestBody: {},
        list: {}
      }
    },
    mounted() {
      this.fnGetList()
    },
    methods:{
      fnGetList(){
        this.requestBody={
          page: this.page,
          size: this.size
        }

        this.$axios.get(this.$serverUrl+"/qboard",{
          params:this.requestBody,
          headers:{}
        }).then((res)=>{
          this.list =res.data
        }).catch((err)=>{
          if (err.message.indexOf('NetWork Error')>-1){
            alert("에바야");
          }
        })
      }
    }
  };
</script>

<style>

</style>
