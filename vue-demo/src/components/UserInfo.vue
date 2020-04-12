<template>
  <div class="info">
    <h1>用户信息管理界面</h1>
    <el-row>
      <el-col :span="20" :push="2">
        <div>
          <el-form :inline="true">
            <el-form-item style="float: left" label="查询用户信息:">
              <el-input v-model="keyUser" placeholder="查询所需要的内容......"></el-input>
            </el-form-item>
            <el-form-item style="float: right">
              <el-button
                type="primary"
                size="small"
                icon="el-icon-edit-outline"
                @click="hanldeAdd()"
              >添加</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="table">
          <el-table :data="searchUserinfo(keyUser)" border style="width: 100%">
            <el-table-column label="序号" align="center" width="60">
              <template scope="scope">
                <span>{{scope.$index + (currentPage - 1) * pageSize + 1}}</span>
              </template>
            </el-table-column>
            <!-- <el-table-column label="日期" align="center" width="120">
              <template slot-scope="scope">
                <span>{{ scope.row.date | moment }}</span>
              </template>
            </el-table-column> -->
            <el-table-column label="姓名" align="center" width="120">
              <template slot-scope="scope">
                <span>{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column label="年龄" align="center" width="120">
              <template slot-scope="scope">
                <span>{{ scope.row.age }}</span>
              </template>
            </el-table-column>
            <el-table-column label="性别" align="center" width="120">
              <template slot-scope="scope">
                <span>{{ scope.row.sex }}</span>
              </template>
            </el-table-column>
            <el-table-column label="省份" align="center" width="120">
              <template slot-scope="scope">
                <span>{{ scope.row.province }}</span>
              </template>
            </el-table-column>
            <el-table-column label="邮箱" align="center" width="180">
              <template slot-scope="scope">
                <span>{{ scope.row.email }}</span>
              </template>
            </el-table-column>
            <el-table-column label="地址" align="center" width="180">
              <template slot-scope="scope">
                <span>{{ scope.row.address }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" align="center">
              <template slot-scope="scope">
                <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[3, 5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalNum"
          ></el-pagination>
        </div>
      </el-col>
    </el-row>
    <AddUser :dialogAdd="dialogAdd" @update="getUserInfo"></AddUser>
    <EditUser :dialogEdit="dialogEdit" :form="form" @updateEdit="getUserInfo"></EditUser>
  </div>
</template>

<script>
import AddUser from "./AddUser.vue";
import EditUser from "./EditUser.vue";
export default {
  name: "info",
  data() {
    return {
      tableData: [],
      dialogEdit: {
        show: false
      },
      dialogAdd: {
        show: false
      },
      keyUser: "",
      totalNum: 0,
      // 当前页
      currentPage: 1,
      // 每页多少条
      pageSize: 5,
      form: {
        //编辑信息
        //date: "",
        name: "",
        age: "",
        sex: "male",
        province: "",
        email: "",
        address: ""
      }
    };
  },
  methods: {
    formatterIndex(row, column) {
      console.log(this.pageSize + "|" + this.currentPage + "|" + row.rowIndex);
      //return this.pageSize * (this.currentPage - 1)  + 1 + row.rowIndex;
    },
    // 每页多少条
    handleSizeChange(val) {
      this.pageSize = val;
      this.getUserInfo();
    },
    // 当前页
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getUserInfo();
    },
    getUserInfo() {
      /*this.$axios.get("http://localhost:8080/")
        .then(res => {
        console.log(res);*/
      //var res = {"count": 0, "data": ""};
      // var dataS = [{"date": "20001010", "name": "abc3", "email": "abc@123.com",
      //               "title": "abc123", "evaluate": "evaluate123", "state": "abc3"},
      //              {"date": "20010810", "name": "abc4", "email": "abc@124.com",
      //               "title": "abc124", "evaluate": "evaluate124", "state": "abc4"},
      //              {"date": "20020910", "name": "abc5", "email": "abc@125.com",
      //               "title": "abc125", "evaluate": "evaluate125", "state": "abc5"}];
      this.$axios({
        method: "post",
        url: "/user/getUsers",
        params: {
          current: this.currentPage,
          rowCount: this.pageSize
        }
      })
        .then(response => {
          if (null != response && "" != response) {
            var data = [];
            var rows = response.rows;
            //res.count = rows.length;
            for (var i = 0; i < rows.length; i++) {
              var obj = {};
              // obj["date"] = "2020-04-01";
              // obj["name"] = rows[i].TVName;
              // obj["email"] = "abc@123.com";
              // obj["title"] = rows[i].Type;
              // obj["evaluate"] = rows[i].networkInterfaceIp;
              // obj["state"] = rows[i].Progress;
              // obj["id"] = rows[i].Id;
               
              obj["name"] = rows[i].name;
              obj["age"] = rows[i].age;
              obj["sex"] = rows[i].sex;
              obj["province"] = rows[i].province;
              obj["email"] = rows[i].email;
              obj["address"] = rows[i].address;
              obj["id"] = rows[i].id;               
              data.push(obj);
            }
            //alert(JSON.stringify(data));
            this.tableData = data;
            this.totalNum = response.total;
          }
        })
        .catch(error => {
          alert(JSON.stringify(error));
        });

      //});
    },
    hanldeAdd() {
      //添加
      this.dialogAdd.show = true;
    },
    handleEdit(index, row) {
      console.log("row:" + JSON.stringify(row));
      //编辑
      this.dialogEdit.show = true; //显示弹
      this.form = {
        // date: row.date,
        // name: row.name,
        // email: row.email,
        // title: row.title,
        // evaluate: row.evaluate,
        // state: row.state,
        // id: row.id,
        name: row.name,
        age: row.age,
        sex: row.sex,
        province: row.province,
        email: row.email,
        address: row.address,
        id: row.id
      };
      console.log(row);
    },
    handleDelete(index, row) {
      //alert(JSON.stringify(row));
      //删除用户信息
      this.$confirm('将删除该用户, 是否确定?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteDevices(index, row);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
      
    },
    deleteDevices(index, row) {
        this.$axios({
          method: "post",
          url: "/user/deleteUser",
          params: {
            Id: row.id
          }
        }).then(res => {
          if ('200' == res.status) {
              this.$message({
                type: "success",
                message: "删除信息成功"
              });
              this.getUserInfo(); //删除数据，更新视图
          } else {
              this.$message({
                type: "error",
                message: "删除信息失败"
              });
          }         
        });
    },
    searchUserinfo(keyUser) {
      return this.tableData.filter(user => {
        if (user.name.includes(keyUser) || user.age.includes(keyUser) || user.sex.includes(keyUser)
            || user.province.includes(keyUser) || user.email.includes(keyUser) || user.address.includes(keyUser)) {
          return user;
        }
      });
    }
  },
  created() {
    this.getUserInfo();
  },
  components: {
    AddUser,
    EditUser
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  font-size: 30px;
  color: #333;
  text-align: center;
  margin: 0 auto;
  padding-bottom: 5px;
  border-bottom: 2px solid #409eff;
  width: 300px;
}
</style>
