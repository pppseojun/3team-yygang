
// Vue Router 설정 코드 - 페이지 간 이동 처리 라이브러리

import { useAuthStore } from '@/stores/auth'
import { createRouter, createWebHistory } from 'vue-router'


const AuthLayout = () => import('@/layout/AuthLayout.vue');
const BaseLayout = () => import('@/layout/BaseLayout.vue');
const Login = () => import('@/views/auth/Login.vue');
const NotFound = () => import('@/views/common/NotFound.vue');
const BoardView = () => import('@/views/BoardView.vue');
const SupplementInfoView = () => import('@/views/SupplementInfoView.vue');
const QuestionBoardView = () => import('@/views/QuestionBoardView.vue');
const MainView = () => import('@/views/MainView.vue');
const MyPage = () => import('@/views/MyPage.vue');
const Cart = () => import('@/views/Cart.vue');
const Order = () => import('@/views/Order.vue');
const Join = () => import('@/views/Join.vue');
const PwdModify = () => import('@/views/PwdModify.vue');
const Withdraw = () => import('@/views/Withdraw.vue');
const RegistProduct = () => import('@/views/RegistProduct.vue');
const NSupplement = () => import('@/views/NSupplement.vue');

// createRouter : Vue Router 설정 함수 
const router = createRouter({
  // 라우터가 사용할 라우팅 모드 지정 (HTML 5 모드)
  history: createWebHistory(import.meta.env.BASE_URL),
  // 요청 경로에 따라 렌더링 할 컴포넌트 배열로 지정
  // 명명된 라우트
  // routes: [
  //   { path: '/', name: 'home', component: Home },
  //   { path: '/departments', name: 'departments', component: Departments },
  //   // 동적 라우트는 일정한 패턴의 URI 경로를 하나의 라우트에 연결하는 방법이다.
  //   { path: '/departments/:no', name: 'departments/no', component: DepartmentDetail },
  //   { path: '/departments/add', name: 'departments/add', component: AddDepartment },
  //   // 404 라우트
  //   { path: '/:paths(.*)*', name: 'notfound', component: NotFound}
  // ],

  // 중첩된 라우트
  // RouterView에 의해서 렌더링된 컴포넌트가 다시 RouterView를 이용해 자식 라우트에 매칭된 컴포넌트를 렌더링한다. 
  routes: [
    {
      path: '/',
      name: 'base',
      component: BaseLayout,
      children: [
        {
          path:'',
          name: 'main',
          component: MainView,
        },
        {
          path:'supplement',
          name: 'supplement',
          component: SupplementInfoView,
        },
        {
          path:'questionboard',
          name: 'questionboard',
          component: QuestionBoardView,
        },
        {
          path:'board',
          name: 'board',
          component: BoardView,
        },
        {
          path: 'user/my-page',
          name: 'myPage',
          component: MyPage
        }, 
        {
          path: 'user/my-page/withdraw',
          name: 'withdraw',
          component: Withdraw
        }, 
        {
          path: 'user/my-page/pass-word',
          name: 'pwdModify',
          component: PwdModify
        }, 
        {
          path: 'user/cart',
          name: 'cart',
          component: Cart
        }, 
        {
          path: 'user/order',
          name: 'order',
          component: Order
        }, 
        {
          path: 'join',
          name: 'join',
          component: Join
        }, 
        {
          path: 'user/order', 
          name: 'order',
          component: Order
        }, 
        {
          path: 'nsupplement', 
          name: 'registProduct',
          component: RegistProduct
        },
        {
          path: 'nsupplement/check', 
          name: 'nsupplement',
          component: NSupplement
        },
      ]
    },
    {
      path: '/user',
      name: 'auth',
      component: AuthLayout,
      children: [
        {
          path: 'login',
          name: 'login',
          component: Login
        },       
        
        // { 
        //   path: 'my-page', 
        //   name: 'my-page', 
        //   component: MyPage 
        // },
      ]
    },
    // 404 라우트 -> 존재하지 않는 페이지 접근 시 NotFound 페이지 표시
    { 
      path: '/:paths(.*)*', 
      name: 'notfound', 
      component: NotFound
    }
  ],
});

// 네비게이션 가드
//  - 라우팅이 일어날 때 프로그래밍 방식으로 네비게이션을 안전하게 보호하는 기능을 수행한다.
//  - 네비게이션 가드는 라우트하는 경로가 바뀔 때 반응한다.
//  - beforeEach(목표 라우트 객체, 현재 라우트 객체, 다른 라우트 리다이렉트트)
router.beforeEach((to, from, next) => {
  // 라우트가 변경되기 전에 실행되는 가드 -> 라우팅 제어 및 조건에 맞지 않는 경로의 이동을 막을 수 있음
  const authStore = useAuthStore(); 

  if(to.name === 'login' && authStore.isLoggedIn){
    // 이미 로그인 했는데 로그인 페이지로 이동하는 경우 
    next({name: 'base'});
  }

  // 로그인 페이지가 아니고, 로그인 상태가 아니면 로그인 페이지로 리다이렉트한다.
  // 로그인하지 않은 상태에서 로그인 페이지가 아닌 다른 페이지로 가는 경우, 로그인페이지로 리다이렉트 되도록
  if(to.name !== 'login' && to.name !== 'main' && to.name !== 'join' && !authStore.isLoggedIn) {
    next({name: 'login'});
  } else {
    next();
  }
});

export default router
