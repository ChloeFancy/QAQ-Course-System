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

      <div slot='additional'>
        <el-button
          id="addNew"
          type="primary"
          size="small"
          @click="openRateForm"
        >
          修改成绩比例
        </el-button>

      </div>
    </my-table>

    <el-form v-if="ifShowRateForm" size="mini">
      <el-form-item label="考试成绩/总评成绩">
        <el-input v-model="examRate" min="0" max="1"></el-input>
      </el-form-item>
      <el-button
        type="primary"
        size="small"
        @click="updateRate"
      >
        确认修改
      </el-button>
      <el-button
        type="primary"
        size="small"
        @click="cancel"
      >
        取消修改
      </el-button>
    </el-form>

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
        examRate:'', //成绩比例
        ifShowRateForm: false,
      }
    },
    methods:{
      updateGradeTable({cid,scoreRate}){
        this.examRate = scoreRate||this.examRate;
        this.currentCid = cid||this.currentCid;
        let query = {
          tid:this.$store.state.user.tid,
          openTerm:this.$store.state.time.openTerm,
          cid:this.currentCid
        };
        let url = httpUtil.generateURL('Myscore','findByCourse',query);

        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data.map((item)=>{
            return Object.keys(item).reduce((prev,cur)=>{
              prev[cur] = item[cur];
              return prev;
            },{});
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
      },
      openRateForm(){
        this.ifShowRateForm = true;
      },
      updateRate(){
        let url = httpUtil.generateURL("teaching","updateScoreRate");
        httpUtil.postData(this,url,this.makeDataCurrent({scoreRate:this.examRate}))
          .then(({data:{resCode}})=>{
            if(resCode==='1'){
              this.updateGradeTable({});
              this.ifShowRateForm = false;
              this.$message({
                type: 'success',
                message: `修改成功!`
              });
            }else{
              this.$message({
                type: 'fail',
                message: `修改失败!`
              });
            }
          })
      },
      cancel(){
        this.ifShowRateForm = false;
      }
    }
  }
</script>

<style scoped>
  input{
    width: 20px;
    text-align: center;
  }
  .el-form{
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    border: 1px solid #eee;
    background-color: #fff;
    width: 30%;
    margin: 0 auto;
    padding: 20px;
  }
</style>
