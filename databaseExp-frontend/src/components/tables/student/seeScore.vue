<template>
  <div>
    <my-table
      :my-table-data="tableData"
      :query-data="queryData"
      :operationType="operationType"
      :showAddNewBtn="showAddNewBtn"
      @search="search"
      @delete="deleteCourse"
    >
    </my-table>
  </div>
</template>

<script>
  import httpUtil from "../../../myHttp/httpUtil";
  import MyTable from "../MyTable";
  export default {
    name: "seeChosenCourse",
    components:{MyTable},
    data(){
      return{
        tableData:[], //传给myTable的表格数据
        queryData:{}, //查询的数据对象
        showAddNewBtn:false, //是否显示新增按钮
        operationType:'canDeleteCourse' //操作框显示什么类型的按钮
      }
    },
    mounted(){
      this.getData();
    },
    methods:{
      getData(){
        let query = {};
        query.sid = this.$store.state.user.sid;
        query.semester = this.$store.state.time.semester;
        query.academicYear = this.$store.state.time.academicYear;

        let url = httpUtil.generateURL('score','findSomeScore',query);
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data;
          if(this.tableData.length>0){
            this.queryData = httpUtil.initQuery(this.tableData[0]);
          }
        }).catch((e)=>{
          console.log(e);
        });
      },
      search(queryData){
        queryData.sid = this.$store.state.user.sid;
        queryData.semester = this.$store.state.time.semester;
        queryData.academicYear = this.$store.state.time.academicYear;

        let url = httpUtil.generateURL('score','findSomeScore',queryData);
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data;
          if(this.tableData.length>0){
            this.queryData = httpUtil.initQuery(this.tableData[0]);
          }
        }).catch((e)=>{
          console.log(e);
        });
      },
      deleteCourse(row){
        let url = httpUtil.generateURL('score','delete');
        httpUtil.postData(this,url,row).then((response) => {
          if (response.body.resCode === '1') {
            this.$message({
              type: 'success',
              message: `选课成功!`
            });
            this.getData();
          } else {
            this.$message({
              type: 'fail',
              message: `选课失败!`
            });
          }
        }).catch(() => {
          this.$message({
            type: 'fail',
            message: `保存失败!`
          });
        });
      }
    }
  }
</script>

<style scoped>

</style>
