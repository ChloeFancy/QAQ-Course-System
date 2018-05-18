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
        operationType:'null', //操作框显示什么类型的按钮
        searchType:'semesterSearch'
      }
    },
    mounted(){
      this.getData();
    },
    methods:{
      getData(openTerm){
        let query = {};
        query.sid = this.$store.state.user.sid;
        query.openTerm = openTerm||this.$store.state.time.openTerm;
        // http://localhost:8080/Myscore/findScoreForStudent?sid=15123010&openTerm=2018-2019%20%E6%98%A5%E5%AD%A3
        let url = httpUtil.generateURL('Myscore','findScoreForStudent',query);
        httpUtil.getData(this,url).then((response)=>{
          this.tableData = response.body.data.map((cur)=>{
            var score = {};
            score['cName'] = cur['cName'];
            score['cid'] = cur['cid'];
            score['usualResults'] = cur['usualResults'];
            score['examResults'] = cur['examResults'];
            score['totalResults'] = cur['totalResults'];
            return score;
          });

          if(this.tableData.length>0){
            this.queryData = httpUtil.initQuery(this.tableData[0]);
          }
        }).catch((e)=>{
          console.log(e);
        });
      },
      search(queryData){
        console.log('3:',queryData);

        this.getData(queryData);
      },
      deleteCourse(row){
        if(row['usualResults']){
          this.$message({
            type: 'fail',
            message: `退课失败!`
          });
          return;
        }
        let myscore = {};
        myscore.sid = this.$store.state.user.sid;
        myscore.openTerm = this.$store.state.time.openTerm;
        myscore.tid = row['tid'];
        myscore.cid = row['cid'];
        let url = httpUtil.generateURL('Myscore','delete');
        httpUtil.postData(this,url,myscore).then((response) => {
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
            message: `保存失败!`
          });
        });
      }
    }
  }
</script>

<style scoped>

</style>
