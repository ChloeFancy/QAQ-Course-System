<template>
    <div>
      <admin-info v-if="tableName==='changePwd'"></admin-info>
      <my-table
        v-else
        :myTableData="tableData"
        :query-data="queryData"
        :operationType="operationType"
        :showAddNewBtn="showAddNewBtn"
        :tableName="tableName"
        :searchType="searchType"
        @search="search"
        @update="save"
        @add="addNew"
        @delete="deleteRow"
      >
      </my-table>
    </div>
</template>

<script>
  import httpUtil from "../../../myHttp/httpUtil";
  import MyTable from "../MyTable";
  import administratorOper from "../operations/administratorOper";
  import adminInfo from "./adminInfo";
  export default {
    name: "adminTable",
    components: {MyTable,administratorOper,adminInfo},
    props:{
      tableName:{
        type:String
      }
    },
    data(){
      return{
        tableData:[], //传给myTable的表格数据
        queryData:{}, //查询的数据对象
        showAddNewBtn:true, //是否显示新增按钮
        operationType:'admin', //操作框显示什么类型的按钮\
        searchType:'commonSearch'
      }
    },
    methods:{
      getData(){
        let url = httpUtil.generateURL(this.tableName, 'findAll');
        console.log(url);
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data;
          if(this.tableData.length>0){
            this.queryData = httpUtil.initQuery(this.tableData[0]);
          }
        }).catch((e)=>{
          console.log(e);
        });
      },
      // search(queryData){
      //   let url = httpUtil.generateURL(this.tableName,"findByQuery",queryData);
      //   httpUtil.getData(this,url).then((response)=>{
      //     this.tableData = response.body.data;
      //   }).catch((e)=>{
      //     console.log(e);
      //   });
      // },
      search(queryData){
        httpUtil.search(this,this.tableName,queryData).then((response)=>{
          this.tableData = response.body.data;
        }).catch((e)=>{
          console.log(e);
        });
      },
      save(row){
        let url = httpUtil.generateURL(this.tableName,"update");
        httpUtil.postData(this,url,row).then((response) => {
          if (response.body.resCode === '1') {
            this.$message({
              type: 'success',
              message: `保存成功!`
            });
            this.getData();
          } else {
            this.$message({
              type: 'fail',
              message: `保存失败!`
            });
          }
        }).catch(() => {
          this.$message({
            type: 'fail',
            message: `保存失败!`
          });
        });
      },
      deleteRow(row){
        let url = httpUtil.generateURL(this.tableName,'delete');
        this.$http.post(url, row, {emulateJSON: true})
          .then(function (response) {
            if (response.body.resCode === '1') {
              this.$message({
                type: 'success',
                message: `删除成功!`
              });
              this.getData();
            } else {
              this.$message({
                type: 'fail',
                message: `$删除失败!`
              });
            }
          }).catch(function (res) {
          console.log(res.body.resMsg);
        });
      },
      addNew(row){
        let url = httpUtil.generateURL(this.tableName,"add");
        httpUtil.postData(this,url,row).then((response) => {
          if (response.body.resCode === '1') {
            this.$message({
              type: 'success',
              message: `保存成功!`
            });
            this.getData();
          } else {
            this.$message({
              type: 'fail',
              message: `保存失败!`
            });
          }
        }).catch(() => {
          this.$message({
            type: 'fail',
            message: `保存失败!`
          });
        });
      }
    },
    mounted(){
      this.getData();
    },
    watch:{
      tableName(){
        this.getData();
      }
    }
  }
</script>

<style scoped>

</style>
