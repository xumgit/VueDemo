<template>
  <div id="app">
    <el-button type="primary" @click="primaryClick()">主要按钮</el-button>
    <el-button type="success" @click="successClick(this)">成功按钮</el-button>
    <el-button type="info">信息按钮</el-button>
    <el-button type="warning">警告按钮</el-button>
    <el-button type="danger">危险按钮</el-button>
    <el-form
      :rules="rules"
      class="login-container"
      label-position="left"
      label-width="0px"
      v-loading="loading"
    >
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="account">
        <el-input
          type="text"
          v-model="loginForm.username"
          auto-complete="off"
          placeholder="账号"
        ></el-input>
      </el-form-item>
      <el-form-item prop="checkPass">
        <el-input
          type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-checkbox
        class="login_remember"
        v-model="checked"
        label-position="left"
        >记住密码</el-checkbox
      >
      <el-form-item style="width: 100%">
        <el-button
          type="primary"
          @click.native.prevent="submitClick"
          style="width: 100%"
          >登录</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      articles: [],
      selItems: [],
      loading: false,
      currentPage: 1,
      totalCount: -1,
      pageSize: 6,
      keywords: "",
      dustbinData: [],
      rules: {
        account: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        checkPass: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      checked: true,
      loginForm: {
        username: "sang",
        password: "123"
      }
    };
  },
  methods: {
    primaryClick() {
      console.log("primaryClick:" + this.currentPage);
    },
    successClick(obj) {
      console.log(JSON.stringify(obj));
    },
    submitClick: function() {
      console.log(JSON.stringify(this.loginForm));
    }
  }
};
</script>
