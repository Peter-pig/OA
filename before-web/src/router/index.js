import Vue from 'vue'
import VueRouter from 'vue-router'
import login from "@/components/login";
import register from "@/components/register";
import Admin from "@/components/Admin";
import normalUser from "@/components/normalUser";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '',
    component: login
  },
  {
    path: '/register',
    name: '',
    component: register
  },
  {
    path: '/Admin',
    name: '',
    component: Admin
  },
  {
    path: '/normalUser',
    name: '',
    component: normalUser
  }
]

const router = new VueRouter({
  routes
})

export default router
