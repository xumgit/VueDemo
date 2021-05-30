// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router/index.js";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import './utils/filters.js';
import service from './utils/request.js';
import './mock/mock.js';

Vue.config.productionTip = false;
Vue.use(ElementUI);

//自定义创建一个axios
Vue.prototype.$axios = service;

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  components: { App },
  template: "<App/>"
});
