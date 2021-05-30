<template>
  <div class="info">
    <h1>秒杀界面</h1>
    <el-row>
      <el-col :span="20" :push="2">
        <div class="table">
          <el-table :data="searchUserinfo(keyUser)" border style="width: 100%">
            <el-table-column label="序号" align="center" style="width: 10%">
              <template scope="scope">
                <span>{{scope.$index + (currentPage - 1) * pageSize + 1}}</span>
              </template>
            </el-table-column>
            <el-table-column label="姓名" align="center" style="width: 30%">
              <template slot-scope="scope">
                <span>name_{{ scope.row.user_id }}</span>
              </template>
            </el-table-column>
            <el-table-column label="状态" align="center" style="width: 30%">
              <template slot-scope="scope">
                <span>{{ scope.row.state == '1' ? "成功" : "失败" }}</span>
              </template>
            </el-table-column>
            <el-table-column label="时间" align="center" style="width: 30%">
              <template slot-scope="scope">
                <span>{{ scope.row.create_time }}</span>
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
  </div>
</template>

<script>
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
        user_id: "",
        state: "1",
        create_time: ""
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
      this.$axios({
        method: "post",
        url: "/seckill/getSuccessKilledData",
        params: {
          current: this.currentPage,
          rowCount: this.pageSize
        }
      })
        .then(response => {
          if (null != response && "" != response) {
            //var data = [];
            //var rows = response.rows;
            //res.count = rows.length;
            /*for (var i = 0; i < rows.length; i++) {
              var obj = {};              
              obj["name"] = rows[i].name;
              obj["age"] = rows[i].age;
              obj["sex"] = rows[i].sex;
              obj["province"] = rows[i].province;
              obj["email"] = rows[i].email;
              obj["address"] = rows[i].address;
              obj["id"] = rows[i].id;               
              data.push(obj);
            }*/
            //alert(JSON.stringify(data));
            this.tableData = response.rows;
            this.totalNum = response.total;
          }
        })
        .catch(error => {
          alert(JSON.stringify(error));
        });

      //});
    },
    searchUserinfo(keyUser) {
      return this.tableData.filter(user => {
        if (user.user_id.includes(keyUser) || user.state.includes(keyUser) || user.create_time.includes(keyUser)) {
          return user;
        }
      });
    }
  },
  created() {
    this.getUserInfo();
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
