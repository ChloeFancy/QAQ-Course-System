<template>
  <div>
    <my-table
      :my-table-data="tableData"
      :query-data="queryData"
      :operationType="operationType"
      :showAddNewBtn="showAddNewBtn"
      :searchType="searchType"
      @search="updateGradeTable"
      @add="addGrade"
      @update="update"
      @delete="deleteRow"
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
        searchType:'gradeCourseSearch',
        currentCid: null,
      }
    },
    methods:{
      updateGradeTable(cid){
        this.currentCid = cid;
        let query = {
          tid:this.$store.state.user.tid,
          openTerm:this.$store.state.time.openTerm,
          cid:cid
        };
        let url = httpUtil.generateURL('Myscore','findByCourse',query);
        console.log(url);
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data;
        }).catch((e)=>{
          console.log(e);
        });
      },
      addGrade(formData){
        let url = httpUtil.generateURL("Myscore","add");
        httpUtil.postData(this,url,this.makeDataCurrent(formData)).then((response)=>{
          this.updateGradeTable(this.currentCid);
        });
      },
      update(formData){

        let url = httpUtil.generateURL("Myscore","update");
        httpUtil.postData(this,url,this.makeDataCurrent(formData)).then((response)=>{
          this.updateGradeTable(this.currentCid);
        });
      },
      deleteRow(formData){
        let url = httpUtil.generateURL("Myscore","delete");
        httpUtil.postData(this,url,this.makeDataCurrent(formData)).then((response)=>{
          this.updateGradeTable(this.currentCid);
        });
      },
      makeDataCurrent(obj){
        obj.cid = this.currentCid;
        obj.tid = this.$store.state.user.tid;
        obj.openTerm = this.$store.state.time.openTerm;
        return obj;
      }
    }
  }
</script>

<style scoped>

</style>
