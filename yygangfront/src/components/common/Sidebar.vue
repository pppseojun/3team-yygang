<template>
<div class="flex-shrink-0 p-3" style="width: 280px;">
    <div class="row">
        <div class="d-flex align-items-center pb-3 mb-3 link-body-emphasis border-bottom">
            <span class="fs-5 fw-semibold flex-grow-1">YYAGNG</span>

            <button @click="$emit('toggle-sidebar')" class="btn"><i class="bi bi-x"></i></button>
        </div>
    </div>
    <ul class="list-unstyled ps-0">

      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">
          홈
        </button>
        <div class="collapse" id="home-collapse" style="">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴1</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴2</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴3</a></li>
          </ul>
        </div>
      </li>

      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
          제품 정보
        </button>
        <div class="collapse" id="dashboard-collapse" style="">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴1</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴2</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴3</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴4</a></li>
          </ul>
        </div>
      </li>

      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          추천 영양제 찾기
        </button>
        <div class="collapse" id="orders-collapse" style="">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴1</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴2</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴3</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴4</a></li>
          </ul>
        </div>
      </li>

      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          약 질문하기
        </button>
        <div class="collapse" id="orders-collapse" style="">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴1</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴2</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴3</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴4</a></li>
          </ul>
        </div>
      </li>

      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          자유 게시판
        </button>
        <div class="collapse" id="orders-collapse" style="">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴1</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴2</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴3</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메뉴4</a></li>
          </ul>
        </div>
      </li>

      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
          내 계정
        </button>
        <div class="collapse" id="account-collapse" style="">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">New...</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Profile</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Settings</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Sign out</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
defineProps({
  isSidebarOpen: Boolean
});

defineEmits(['toggle-sidebar']);
</script>

<style scoped>
.sidebar {
  background: white;
  position: fixed;
  height: 100%;
  left: 0;
  top: 0;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}
</style>