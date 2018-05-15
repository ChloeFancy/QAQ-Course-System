<template>
  <div>
    <my-table
      :my-table-data="tableData"
      :query-data="queryData"
      :operationType="operationType"
      :showAddNewBtn="showAddNewBtn"
      @search="search"
      @chooseCourse="chooseCourse"
    >
    </my-table>
  </div>
</template>

<script>
  import httpUtil from "../../../myHttp/httpUtil";
  import MyTable from "../MyTable";
  export default {
    name: "chooseCourse",
    components:{MyTable},
    data(){
      return{
        tableData:[], //传给myTable的表格数据
        queryData:{}, //查询的数据对象
        showAddNewBtn:false, //是否显示新增按钮
        operationType:'chooseCourse' //操作框显示什么类型的按钮
      }
    },
    mounted(){
      this.getData();
    },
    methods:{
      getData(){
        let url = httpUtil.generateURL('teaching','findAll');
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data;
          if(this.tableData.length>0){
            this.queryData = httpUtil.initQuery(this.tableData[0]);
          }
        })
      },
      search(queryData){
        httpUtil.search(this,'teaching',queryData).then((response)=>{
          this.tableData = response.body.data;
        }).catch((e)=>{
          console.log(e);
        });
      },
      chooseCourse(row){
        let url = httpUtil.generateURL('score','add');

        let tempScore = {};
        let {time, user} = this.$store.state;
        tempScore.academicYear = time.academicYear;
        tempScore.semester = time.semester;
        tempScore.sid = user.sid;
        tempScore.cid = row.cid;

        httpUtil.postData(this,url,tempScore)
          .then((response) => {
            if (response.body.resCode === '1') {
              this.$message({
                type: 'success',
                message: `选课成功!`
              });
            } else {
              this.$message({
                type: 'fail',
                message: `选课失败!`
              });
            }
          })
          .catch(() => {
            console.log(
              'error'
            )
          });
      }
    }
  }
</script>

<style scoped>

</style>
