<template>
  <div>
    <my-table
      :my-table-data="tableData"
      :query-data="queryData"
      :operationType="operationType"
      :showAddNewBtn="showAddNewBtn"
      :searchType="searchType"

      @search="search"
      @chooseCourses="chooseCourses"
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
        tableData: [], //要显示的数据，传给myTable的表格数据
        queryData: {}, //查询的数据对象
        showAddNewBtn: false, //是否显示新增按钮
        operationType:'chooseCourse', //操作框显示什么类型的按钮
        searchType:'commonSearch',
      }
    },
    mounted(){
      this.getData();
    },
    methods:{
      getData(){
        let query = {openTerm:this.$store.state.time.openTerm};
        let url = httpUtil.generateURL('teaching','findAllDetail',query);
        httpUtil.getData(this,url).then((response)=>{

          this.tableData = response.body.data.map((cur)=>{
              var c = {};
              c["cid"] = cur["cid"];
              c["cName"] = cur["cName"];
              c["tName"] = cur["tName"];
              c["tid"] = cur["tid"];
              c["ttime"] = cur["ttime"];
              return c;
          });
          if(this.tableData.length>0){
            this.queryData = httpUtil.initQuery(this.tableData[0]);
          }
        })
      },
      search(queryData){
        let query = Object.assign({openTerm:this.$store.state.time.openTerm},queryData);
        var data = JSON.stringify(query);
        console.log(data);
        let url = httpUtil.generateURL("teaching","findAllDetailByQuery");
        httpUtil.postData(this,url,{data:data}).then((response)=>{
          this.tableData = response.body.data.map((cur)=>{
            var c = {};
            c["cid"] = cur["cid"];
            c["cName"] = cur["cName"];
            c["tName"] = cur["tName"];
            c["tid"] = cur["tid"];
            c["ttime"] = cur["ttime"];
            return c;
          });
        }).catch((e)=>{
            console.log(e);
        });
        // console.log(queryData);
        // httpUtil.search(this,'teaching',queryData).then((response)=>{
        //   this.tableData = response.body.data;
        // }).catch((e)=>{
        //   console.log(e);
        // });
      },
      chooseCourses(row){
        console.log(row);
        let url = httpUtil.generateURL('Myscore','add');

        console.log(url);
        let tempScore = {};
        let {time, user} = this.$store.state;
        tempScore.openTerm = time.openTerm;
        tempScore.sid = user.sid;
        tempScore.cid = row.cid;
        tempScore.tid = row.tid;


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
