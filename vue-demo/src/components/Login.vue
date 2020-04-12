<template>
	<el-form :rules="rules" ref="loginForm" :model="loginForm"  label-width="70px" 
			 label-position="left" v-loading="loading" class="login-container">
      	<h2 class="login-title">系统登录</h2>
      	<el-form-item label="用户:" prop="username">
        	<el-input type="text" v-model="loginForm.username" placeholder="请输入用户名" auto-complete="off">
          		<el-button slot="prepend" icon="el-icon-user"></el-button>
        	</el-input>
      	</el-form-item>
     	<el-form-item label="密码:" prop="password">
	        <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" auto-complete="off">
	          	<el-button slot="prepend" icon="el-icon-key"></el-button>
	        </el-input>
      	</el-form-item>
      	<el-checkbox class="login-remeber" v-model="checked" label-position="left">记住密码</el-checkbox>
      	<el-form-item style="width: 100%;">
        	<el-button type="primary" style="width: 100%;" icon="el-icon-user-solid" @click="submitClick">登 录</el-button>
  	  	</el-form-item>
    </el-form>
</template>
<script>
import router from "../router/index.js";
export default {
  name: "Login",
  data() {
    return {
    	rules: {
    		username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
    		password: [{required: true, message: '请输入密码', trigger: 'blur'}]
    	},
      	checked: true,
      	loginForm: {
      		username: 'admin',
      		password: '123456'
      	},
      	loading: false
    };
  },
  methods: {
    submitClick() {
    	this.$refs.loginForm.validate(valid => {
    		if (valid) {
    			this.$axios({
    				method: 'post',
            url: '/index/getDevices'
    			}).then(response => {
    				if (null != response && '' != response) {
    					var rows = response.rows;
    					//alert(rows.length + router);
    					if (rows.length > 0) {
    						router.push({path : 'userInfo', query : ''});
    					}
    				}
    			}).catch(error => {
    				alert("error=" + JSON.stringify(error));
    			});
    		}
    	});
    }
  }
};
</script>
<style>
	.login-container {
		border-radius: 15px;
		background-clip: padding-box;
		margin: 160px auto;
		width: 350px;
		padding: 35px 35px 15px 35px;
		background: #fff;
		border: 1px solid #eaeaea;
		box-shadow: 0 0 25px #cac6c6;
	}

	.login-title {
		margin: 0px auto 40px auto;
		text-align: center;
		color: #505458;
	}

	.login-remeber {
		margin: 0px 0px 35px 0px;
		text-align: left;
	}
</style>