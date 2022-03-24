<template>
  <div class="home">
    <el-container>
      <el-header>奖牌榜</el-header>
      <el-main>
        <el-table
            ref="singleTable"
            :data="tableData"
            v-loading="tableloading"
            highlight-current-row
            border
            @current-change="handleCurrentChange"
            style="width: 40%">
          <el-table-column
              property="rank"
              label="排名"
              width="100">
          </el-table-column>
          <el-table-column
              property="countryname"
              label="国家"
              width="120">
          </el-table-column>
          <el-table-column
              property="gold"
              label="金牌"
              width="120">
          </el-table-column>
          <el-table-column
              property="silver"
              label="银牌"
              width="120">
          </el-table-column>
          <el-table-column
              property="bronze"
              label="铜牌"
              width="120">
          </el-table-column>
          <el-table-column
              property="count"
              label="总数"
              width="120">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  created:function () {
    this.getData();
  },
  data() {

    return {
      tableData: [],
      currentRow: null,
      tableloading: false
    }
  },

  methods: {

    getData() {
      this.tableloading = true;
      var that=this;
      axios.get('http://47.106.185.140:8080/total')
          .then(function(response){
            console.log(response.data.data.list);
            that.tableData=response.data.data.list;
          })
          .catch(function(err){})
      this.tableloading = false;
    }
  },
  setCurrent(row) {
    this.$refs.singleTable.setCurrentRow(row);
  },
  handleCurrentChange(val) {
    this.currentRow = val;
  }
}

</script>
<style>
.el-table{
  margin-left: 150px;
}
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: white;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
