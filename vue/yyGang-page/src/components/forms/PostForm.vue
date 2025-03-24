<template>
    <div>
        <div class="input-form   border-bottom pb-5">
            <div class="input-group mb-3 row">
                <span class="input-title input-group-text col-2" id="basic-addon1"> 제목</span>
                <input type="text" class="form-control col-10" placeholder="제목을 입력하세요" aria-label="Username" aria-describedby="basic-addon1">
            </div>
            <div class="input-group row">
                <span class="input-content input-group-text col-2 ">내용</span>
                <textarea class="form-control col-10" aria-label="With textarea" placeholder="내용을 입력하세요"></textarea>
            </div>
        </div>

        <div class="btn-section d-flex mt-5 mb-5">
            <button class="submit-btn btn btn-outline-secondary fs-5" type="button">등록하기</button>
            <button class="cancel-btn btn btn-outline-secondary fs-5" type="button" @click=" this.$router.go(-1)">취소하기</button>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>

.input-form{
    width: 90%;
    margin: 0 auto;
}

.input-title{
    min-height: 55px;
    justify-content: center;
}
.input-content{
    min-height: 300px;
    align-items: flex-start;
    justify-content: center;
}

.btn-section{
    width: 90%;
    margin: 0 auto;
    justify-content: space-evenly;
    
}

.submit-btn, .cancel-btn{
    width: 25%;
    border-radius: 20px 0px 20px 0px;
    border-color: #BDBDBD;
    box-shadow: 5px 5px 10px 0 lightgray;   
}

.submit-btn{
    background-color: #A0D683;
}
</style>