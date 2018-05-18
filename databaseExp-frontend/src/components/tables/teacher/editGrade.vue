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

        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data.map((cur)=>{
            var score = {};
            score['sName'] = cur['sName'];
            score['sid'] = cur['sid'];
            score['usualResults'] = cur['usualResults'];
            score['examResults'] = cur['examResults'];
            score['totalResults'] = cur['totalResults'];
            return score;
          });
        }).catch((e)=>{
          console.log(e);
        });
      },
      addGrade(formData){
        let url = httpUtil.generateURL("Myscore","add");
        httpUtil.postData(this,url,this.makeDataCurrent(formData)).then((response)=>{
          if(response.body.resCode==='1'){
            this.$message({
              type: 'success',
              message: `添加成功!`
            });
            this.updateGradeTable(this.currentCid);
          }else{
            this.$message({
              type: 'fail',
              message: `添加失败!`
            });
          }
        });
      },
      update(formData){
        let url = httpUtil.generateURL("Myscore","update");
        httpUtil.postData(this,url,this.makeDataCurrent(formData)).then((response)=>{
          this.$message({
            type: 'success',
            message: `更新成功!`
          });
          this.updateGradeTable(this.currentCid);
        });
      },
      deleteRow(formData){
        let url = httpUtil.generateURL("Myscore","deleteByTeacher");
        httpUtil.postData(this,url,this.makeDataCurrent(formData))
          .then((response)=>{
            if(response.body.resCode==='1'){
              this.$message({
                type: 'success',
                message: `删除成功!`
              });
              this.updateGradeTable(this.currentCid);
            }else{
              this.$message({
                type: 'fail',
                message: `删除失败!`
              });
            }
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
