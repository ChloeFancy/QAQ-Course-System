<template>
  <div>
    <my-table
      :my-table-data="tableData"
      :query-data="queryData"
      :operationType="operationType"
      :showAddNewBtn="showAddNewBtn"
      :searchType="searchType"
      @changeGradeCourse="updateGradeTable"
    >
    </my-table>
  </div>
</template>

<script>
  import httpUtil from "../../../myHttp/httpUtil";
  import MyTable from "../MyTable";
  export default {
    name: "editGrade",
    components:{MyTable},
    data(){
      return{
        tableData: [], //传给myTable的表格数据
        queryData: {}, //查询的数据对象
        showAddNewBtn: true, //是否显示新增按钮
        operationType:'admin', //操作框显示什么类型的按钮
        searchType:'gradeCourseSearch'
      }
    },
    methods:{
      updateGradeTable(){
        let query = {
          tid:this.$store.state.user.tid,
          openTerm:this.$store.state.time.openTerm
        };
        let url = httpUtil.generateURL('score','findByQuery',query);
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data;
        }).catch((e)=>{
          console.log(e);
        });
      }
    }
  }
</script>

<style scoped>

</style>
