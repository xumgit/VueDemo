<template>
  <div class="hello">
    <el-dialog title="添加用户信息" :visible.sync="dialogAdd.show">
      <el-form
        :model="formDate"
        ref="formdong"
        label-width="80px"
        :rules="formrules"
        :status-icon=true
        label-position="right"
      >
        <!-- <el-form-item label="日期" prop="date">
          <el-date-picker
            v-model="formDate.date"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item> -->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formDate.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio v-model="formDate.sex" label="male">男</el-radio>
          <el-radio v-model="formDate.sex" label="female">女</el-radio>
        </el-form-item>
        <el-form-item label="省份" prop="province">
          <el-select v-model="formDate.province" placeholder="请选择">
            <el-option
              v-for="item in provinceArr"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
         <el-form-item label="年龄" prop="age">
          <el-select v-model="formDate.age" placeholder="请选择">
            <el-option
              v-for="item in ageArr"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formDate.email"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="formDate.address"></el-input>
        </el-form-item>
        <!-- <el-form-item label="评价" prop="evaluate">
          <el-input v-model="formDate.evaluate"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-input v-model="formDate.state"></el-input>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAdd.show = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormAdd('formdong')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "AddUser",
  props: {
    dialogAdd: Object
  },
  data() {    
    var validateProvince = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请选择省份'));
          } else {
            if (this.formDate.province !== '') {
              this.$refs["formdong"].validateField('province');
            }
            callback();
          }
    };
    var validateAge = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请选择年龄'));
          } else {
            if (this.formDate.province !== '') {
              this.$refs["formdong"].validateField('age');
            }
            callback();
          }
    };
    return {     
      ageArr: this.getAgeValue(),
      provinceArr: this.getProvince(),
      formDate: {
        date: "",
        name: "",
        sex: "male",
        province: "",
        age: "",
        email: "",
        address: "",
        title: "",
        evaluate: "",
        state: ""
      },
      formrules: {
        date: [{ required: true, message: "日期不能为空", trigger: "blur" }],
        name: [{ required: true, message: "用户名不能为空", trigger: "blur" }],
        sex: [{ required: true, message: "请选择性别", trigger: "blur" }],
        province: [{ required: true, validate: validateProvince, trigger: "blur" }],
        age: [{ required: true, validate: validateAge, trigger: "blur" }],
        email: [{ required: true, message: "邮箱不能为空", trigger: "blur" }],
        address: [{ required: true, validate: "地址不能为空", trigger: "blur" }],
      }
    };
  },
  methods: {
    dialogFormAdd(formdong) {
      this.$refs[formdong].validate(valid => {
        if (valid) {
            var paraObj = {
              name: this.formDate.name,
              age: this.formDate.age,
              sex: this.formDate.sex,
              province: this.formDate.province,
              email: this.formDate.email,
              address: this.formDate.address
            };
            this.$axios({
              method: "post",
              url: "/user/insertUser",
              data: paraObj
            })        
            .then(res => {
              if (null != res && '' != res) {
                if ('200' == res.status) {                 
                  this.$message({
                    type: "success",
                    message: "添加信息成功"
                  });
                  this.resetFormData();                 
                } else {
                  this.$message({
                    type: "success",
                    message: "添加信息失败"
                  });
                } 
                this.dialogAdd.show = false;
                this.$emit("update");                             
              }             
            }).catch(error => {
              alert(JSON.stringify(error));
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    getAgeValue() {
        var dataAge = [{value: '', label: '--请选择--'}, {value: '10', label: '10'}, 
                       {value: '20', label: '20'}, {value: '30',label: '30'}];
        return dataAge;
    },
    getProvince() {
      var dataProvince = [{ value: '', label: '--请选择--'}, {value: '安徽', label: '安徽'}, 
                          {value: '上海', label: '上海'}, {value: '北京', label: '北京'}];
      return dataProvince;
    },
    resetFormData() {
      this.formDate = {
        date: "",
        name: "",
        sex: "male",
        province: "",
        age: "",
        email: "",
        address: "",
        title: "",
        evaluate: "",
        state: ""
      };
    },
    initData() {
      //this.$refs["formdong"].clearValidate;
      //this.$refs['formdong'].clearValidate();
    }
  },
  created() {
    this.initData();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
