<template>
  <div class="about">
    <el-tabs type="border-card" >
      <el-tab-pane v-for="(x,index) in tableData" :label="3+index+'日'" >
        <el-table
            ref="singleTable"
            :data="x"
            v-loading="tableloading"
            highlight-current-row
            border
            style="width: 85%">

          <el-table-column
              property="startdatecn"
              label="时间"
              width="250">
          </el-table-column>
          <el-table-column
              property="itemcodename"
              label="大项"
              width="200">
          </el-table-column>
          <el-table-column
              property="title"
              label="比赛"
              width="450">
          </el-table-column>
          <el-table-column
              property="venuename"
              label="场馆"
              width="300">
          </el-table-column>
          <el-table-column
              label="数据"
              width="200">
            数据详情
          </el-table-column>
        </el-table>
      </el-tab-pane>

    </el-tabs>
  </div>
</template>
<script>

 import axios from "_axios@0.26.1@axios";

export default {
  mounted:function () {
    this.getData();
    console.log("mouted");
  },
  data(){
    return {
      tableData: [],
      currentRow: null,
      tableloading: false
    }
  },
  methods:{
    async getData() {
      this.tableloading = true;
      // console.log(targetName.paneName);
      var that=this;
      for(var i=0;i<18;i++){
        var date=3+i;
        console.log(date);

        await axios.get('http://101.132.99.44:8080/search?date=2022/02/'+date+'&item=&site&page=1&size=100')
            .then(function(response){
              if(response.data!=null){
                that.tableData[i]=response.data.data.list;
              }
              else console.log("数据为空");
            })
            .catch(function(err){console.log("获取失败"+i)})
      }
      if(that.tableData[15]==null){
        console.log("错误");
      } else console.log("成功");
      this.tableloading = false;
    },

  },


}
</script>
<style>
.el-table{
  margin-left: 150px;
}
</style>