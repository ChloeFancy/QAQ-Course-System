<template>
  <div>
    <my-table
      :my-table-data="tableData"
      :query-data="queryData"
      :operationType="operationType"
      :showAddNewBtn="showAddNewBtn"
      :searchType="searchType"

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
        showAddNewBtn: false, //是否显示新增按钮
        operationType:'null', //操作框显示什么类型的按钮
        searchType: 'semesterSearch' //需要怎样的搜索组件
      }
    },
    methods:{
      getData(){
        let query = {
          tid:this.$store.state.user.tid,
          openTerm:this.$store.state.time.academicYear+" "+this.$store.state.time.semester,
        };
        let url = httpUtil.generateURL("teaching","findTeachingCourseName",query);
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data;//显示老师在该学年该学期开的课
        }).catch((e)=>{
          console.log(e);
        });
      }
    },
    mounted(){
      this.getData();
    }
  }
</script>

<style scoped>

</style>
