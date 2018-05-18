<template>
  <div>
    <my-table
      :my-table-data="tableData"
      :query-data="queryData"
      :operationType="operationType"
      :showAddNewBtn="showAddNewBtn"
      :searchType="searchType"
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
        operationType:'canDeleteCourse', //操作框显示什么类型的按钮
        searchType:'semesterSearch'
      }
    },
    methods:{
      getData(openTerm){
        let query = {};
        query.sid = this.$store.state.user.sid;
        query.openTerm = openTerm||this.$store.state.time.openTerm;
        let url = httpUtil.generateURL('Myscore','findCourseForStudent',query);
        console.log(url);
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data.map((cur)=>{
            var course = {};
            course["cName"]=cur["cName"];
            course["cid"]=cur["cid"];
            course["tName"]=cur["tName"];
            course["tid"]=cur["tid"];
            course["cCredit"]=cur["cCredit"];
            course["cTotalHours"]=cur["cTotalHours"];
            course["ttime"]=cur["ttime"];
            return course;
          });
          if(this.tableData.length>0){
            this.queryData = httpUtil.initQuery(this.tableData[0]);
          }
        }).catch((e)=>{
          console.log(e);
        });
      },
      search(queryData){
        this.getData(queryData);
      },
      deleteCourse(row){
        row.sid = this.$store.state.user.sid;
        row.openTerm = this.$store.state.time.openTerm;
        let url = httpUtil.generateURL('Myscore','deleteByStudent');
        httpUtil.postData(this,url,row).then((response) => {
          if (response.body.resCode === '1') {
            this.$message({
              type: 'success',
              message: `退课成功!`
            });
            this.getData();
          } else {
            this.$message({
              type: 'fail',
              message: `退课失败!`
            });
          }
        }).catch(() => {
          this.$message({
            type: 'fail',
            message: `退课失败!`
          });
        });
      }
    },
    mounted(){
      this.getData();
    },
  }
</script>

<style scoped>

</style>
