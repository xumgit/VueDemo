<template>
  <div class="hello">
    <el-dialog title="编辑用户信息" :visible.sync="dialogEdit.show">
      <el-form
        :model="form"
        ref="formEdit"
        label-width="100px"
        :rules="formrules"
      >
        <!-- <el-form-item label="日期" prop="date">
          <el-date-picker
            v-model="form.date"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item> -->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <!-- <el-input v-model="form.age"></el-input> -->
          <el-select v-model="form.age" placeholder="请选择">
            <el-option
              v-for="item in ageArr"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio v-model="form.sex" label="male">男</el-radio>
          <el-radio v-model="form.sex" label="female">女</el-radio>
          <!-- <el-input v-model="form.sex"></el-input> -->
        </el-form-item>
        <el-form-item label="省份" prop="province">
          <!-- <el-input v-model="form.province"></el-input> -->
          <el-select v-model="form.province" placeholder="请选择">
            <el-option
              v-for="item in provinceArr"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address"></el-input>
        </el-form-item>
        <!-- <el-form-item label="评价" prop="evaluate">
          <el-input v-model="form.evaluate"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-input v-model="form.state"></el-input>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogEdit.show = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormEdit('formEdit')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "EditUser",
  props: {
    dialogEdit: Object,
    form: Object
  },
  data() {
    return {
      ageArr: this.getAgeValue(),
      provinceArr: this.getProvince(),
      formrules: {
        name: [{ required: true, message: "用户名不能为空", trigger: "blur" }],
        age: [{ required: true, message: "年龄不能为空", trigger: "blur" }],
        sex: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        province: [{ required: true, message: "省份不能为空", trigger: "blur" }],
        email: [{ required: true, message: "邮箱不能为空", trigger: "blur" }],
        address: [{ required: true, message: "地址不能为空", trigger: "blur" }]
      }
    };
  },
  methods: {
    dialogFormEdit(formEdit) {
      this.$refs[formEdit].validate(valid => {
        if (valid) {
          //console.log("form:" + JSON.stringify(this.form));
          var paraObj = {
            // id: this.form.id,
            // tvname: this.form.name,
            // cloneMode: 'Upgrade'
             id: this.form.id,
             name: this.form.name,
             age: this.form.age,
             sex: this.form.sex,
             province: this.form.province,
             email: this.form.email,
             address: this.form.address            
          };         
          this.$axios({
                method: 'post',
                headers: {
                    'Content-Type': 'application/json' 
                },
                url: '/user/updateUser',
                data: paraObj
            })
            .then(res => {
                if (null != res && '' != res) {
                    if ('200' == res.status) {
                        this.$message({
                          type: "success",
                          message: "编辑信息成功"
                        });
                    } else {
                      this.$message({
                        type: "error",
                        message: "编辑信息失败"
                      });
                    }
                }
                
                console.log(res);
                this.dialogEdit.show = false;
                this.$emit("updateEdit");
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
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
