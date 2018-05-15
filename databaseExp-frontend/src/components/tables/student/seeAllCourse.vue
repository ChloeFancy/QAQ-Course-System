<template>
  <div>
    <my-table
      :my-table-data="tableData"
      :query-data="queryData"
      :operationType="operationType"
      :showAddNewBtn="showAddNewBtn"
      @search="search"
    >
    </my-table>
  </div>
</template>

<script>
  import httpUtil from "../../../myHttp/httpUtil";
  import MyTable from "../MyTable";
  export default {
    name: "seeAllCourse",
    components:{MyTable},
    data(){
      return{
        tableData:[], //传给myTable的表格数据
        queryData:{}, //查询的数据对象
        showAddNewBtn:false, //是否显示新增按钮
        operationType:'null' //操作框显示什么类型的按钮
      }
    },
    mounted(){
      this.getData();
    },
    methods:{
      getData(){
        let url = httpUtil.generateURL('course','findAll');
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data;
          if(this.tableData.length>0){
            this.queryData = httpUtil.initQuery(this.tableData[0]);
          }
        })
      },
      search(queryData){
        httpUtil.search(this,'course',queryData).then((response)=>{
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
