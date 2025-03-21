<template>
    <form @submit.prevent="submitClick">
        <div class="justify-content-center d-flex border-bottom mb-5" >   
            <img src="https://yygang-bucket.s3.ap-northeast-2.amazonaws.com/mainLogo.png"   alt="mainLogo" width="200px">
        </div>
        <h1 class="h3 mb-3 fw-normal text-center">로그인</h1>
        <div class="row">
            <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" id="floatingInput" placeholder="E-mail"
                v-model.trim="formData.username" required>
                <label for="floatingInput">E-mail</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password"
                v-model.trim="formData.password" required>
                <label for="floatingPassword">Password</label>
            </div>
            <div class="mb-5">
                <input type="checkbox" class="btn-check" id="save-login-btn" autocomplete="off">
                <label id="saveId" class="btn btn-sm btn-outline-dark me-1" for="save-login-btn"><i class="bi bi-check fs-7 "></i></label>
                자동 로그인
            
                <input type="checkbox" class="btn-check" id="save-id-btn" autocomplete="off">
                <label id="saveId" class="btn btn-sm btn-outline-dark me-1 ms-2" for="save-id-btn"><i class="bi bi-check fs-7 "></i></label>
                아이디 저장
            </div>
        </div>
        <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
    </form>
</template>

<script setup>
    import {reactive, toRaw} from 'vue';

// 사용자 정보 저장
const formData = reactive({
    username : '',
    password : ''
})

const emit = defineEmits(['form-submit']);

const submitClick = () => {
    emit('form-submit',toRaw(formData));
}


</script>

<style scoped>
label{
    left: auto;
}   

#saveId{
    border-radius: 20px;

}

</style>