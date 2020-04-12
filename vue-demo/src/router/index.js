import Vue from "vue";
import Router from "vue-router";
import HelloWorld from "@/components/HelloWorld";
import Login from "@/components/Login";
import HelloElementUI from "@/components/HelloElementUI";
import ElementUITest from "@/components/ElementUITest";
import UserInfo from "@/components/UserInfo";

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: "/",
      name: "Login",
      component: Login
    },
    {
      path: "/helloElement",
      name: "HelloElementUI",
      component: HelloElementUI,
      hidden: true
    },
    {
      path: "/elementUITest",
      name: "ElementUITest",
      component: ElementUITest,
      hidden: true
    },
    {
      path: "/userInfo",
      name: "UserInfo",
      component: UserInfo,
      hidden: true
    }
  ]
});

export default router;
