<template>
  <div>
    <my-search
      v-if="searchType!==null"
      :search-type="searchType"
      :query-data="queryData"
      @search="doSearch"
    >
    </my-search>

    <el-button
      v-if="showAddNewBtn"
      id="addNew"
      type="primary"
      size="small"
      @click="addNew"
    >
      新增
    </el-button>

    <slot name="additional">

    </slot>

    <el-table
      :data="myTableData"
      :header-cell-style="{'text-align':'center','height':'50px'}"
      class="table-class"
      >
      <el-table-column
        v-for="(value,key) in myTableData[0]"
        :prop="key"
        :label="key"
        :key="key"
        >
      </el-table-column>
      <el-table-column
        v-if="operationType!=='null'"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <!--<slot name="operation"></slot>-->
          <administration-oper
           v-if="operationType==='admin'"
           @edit="edit(scope.row)"
           @delete="confirmOperation(scope.row,'delete','删除',deleteRow)"
          >
          </administration-oper>
          <choose-course
            v-if="operationType==='chooseCourse'"
            @chooseCourses="confirmOperation(scope.row,'add','选课',chooseCourses)"
          >
          </choose-course>
          <delete-course
            @deleteClass="confirmOperation(scope.row,'delete','退课',deleteRow)"
            v-else-if="operationType==='canDeleteCourse'">
          </delete-course>
        </template>
      </el-table-column>
    </el-table>
    <edit-form
      v-if="showEditForm"
      :formData="formData"
      :isEdit="editFormStatus"
      @save="save"
      @cancel="cancel"
    >
    </edit-form>
  </div>
</template>

<script>
  import editForm from "./editForm";
  import administrationOper from "./operations/administratorOper";
  import chooseCourse from "./operations/chooseCourse";
  import deleteCourse from "./operations/deleteCourse";
  import MySearch from "./search/search";

  export default {
    name: "myTable",
    components: {
      editForm,
      administrationOper,
      chooseCourse,
      deleteCourse,
      MySearch
    },
    props:{
      "showAddNewBtn":{type:Boolean},
      "myTableData":{type:Array},
      "queryData":{type:Object},
      "operationType":{type:String},
      "searchType":{type:String}
      // "tableName":{type:String}
    },
    data(){
      return{
        formData:{}, //用于新增、修改信息保存数据
        showEditForm:false, //是否显示新增修改的表单
        editFormStatus:"update", //表单的显示，新增/修改
        actualTableData:[],
      }
    },
    methods:{
      doSearch(queryData){
        console.log('2:',queryData);

        this.$emit('search',queryData);
      },
      addNew(){
        let keys = Object.keys(this.myTableData[0]);
        this.formData = keys.reduce((prev, cur) => {
          prev[cur] = '';
          return prev;
        }, {});
        this.showEditForm = true;
        this.editFormStatus = "add";
      },
      edit(row) {
        this.editFormStatus = "update";
        this.showEditForm = true;
        this.formData = row;
      },
      cancel() {
        this.showEditForm = false;
      },
      save(formData,hint) {
        this.confirmOperation(formData, this.editFormStatus, hint, this.saveRow);
      },
      //编辑一行后确认保存后的实际操作
      //管理员-更新记录中的信息
      saveRow(row, oper, hint) {
        console.log(oper);
        this.$emit(this.editFormStatus,row);
        this.showEditForm = false;
      },
      //确认删除后的实际操作
      //管理员-删除记录
      //学生-退课
      deleteRow(row) {
        this.$emit('delete',row);
      },
      //学生-选课
      chooseCourses(row, oper, hint) {
        //添加到成绩表里，但是没有成绩，只有学年，学期，学号，课号
        console.log(row);
        this.$emit('chooseCourses',row);
      },
      confirmOperation(row, oper, hint, fn) {
        this.$confirm(`确认${hint}？`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log('before call');
          fn.call(this, row, oper, hint);
          console.log('after call');
        }).catch(() => {
          this.$message({
            type: 'info',
            message: `已取消${hint}`
          });
        })
      },
    },
    mounted(){
      this.actualTableData=this.myTableData;
    }
    // data(){
    //   return{
    //     tableData:[],
    //
    //
    //     formData:{},
    //     showEditForm:false,
    //     editFormStatus:"update",
    //     obj:{
    //       key:1
    //     },
    //     identity:'',
    //     queryFormData:{}
    //   }
    // },
    // watch:{
    //   tableName(){
    //     this.getData();
    //   }
    // },
    // methods: {
    //   //生成url
    //   generateURL(tableName, operation, query) {
    //     let url = `http://localhost:8080/${tableName}/${operation}`;
    //     if (query) {
    //       url += (Object.keys(query).reduce((prev, cur) => {
    //         if(query[cur]!=='')
    //           prev += cur + "=" + query[cur] + '&';
    //         return prev;
    //       }, "?"));
    //       url = url.slice(0, url.length - 1);
    //     }
    //     return url;
    //   },
    //   //获取数据，渲染表格
    //   getData() {
    //     let that = this;
    //     let query;
    //     if (this.tableName === 'score' && this.operation.indexOf('findSome')===0) {
    //       //退课
    //       query = {};
    //       query.sid = this.$store.state.user.sid;
    //       query.semester = this.$store.state.time.semester;
    //       query.academicYear = this.$store.state.time.academicYear;
    //     }
    //     let url = this.generateURL(this.tableName, this.operation, query);
    //     console.log(url);
    //     this.$http.get(url)
    //       .then(function (response) {
    //         that.tableData = response.body.data;
    //         this.initQuery();
    //       }).catch(function (res) {
    //       console.log(res.body.resMsg);
    //     });
    //   },
    //   //确认是否完成选择的操作
    //   confirmOperation(row, oper, hint, fn) {
    //     this.$confirm(`确认${hint}？`, '提示', {
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     }).then(() => {
    //       console.log('before call');
    //       fn.call(this, row, oper, hint);
    //       console.log('after call');
    //     }).catch(() => {
    //       this.$message({
    //         type: 'info',
    //         message: `已取消${hint}`
    //       });
    //     })
    //   },
    //   //确认删除后的实际操作
    //   deleteRow(row, oper, hint) {
    //     let id = Object.keys(row)[0];
    //     let val = row[id];
    //     let url = `http://localhost:8080/${this.tableName}/delete`;
    //     this.$http.post(url, row, {emulateJSON: true})
    //       .then(function (response) {
    //         if (response.body.resCode === '1') {
    //           this.$message({
    //             type: 'success',
    //             message: `${hint}成功!`
    //           });
    //           this.getData();
    //         } else {
    //           this.$message({
    //             type: 'fail',
    //             message: `${hint}失败!`
    //           });
    //         }
    //       }).catch(function (res) {
    //       console.log(res.body.resMsg);
    //     });
    //   },
    //   //点击编辑/新建，弹出编辑框
    //   edit(row) {
    //     this.editFormStatus = "update";
    //     this.showEditForm = true;
    //     this.formData = row;
    //   },
    //   //学生选课
    //   chooseCourses(row, oper, hint) {
    //     //添加到成绩表里，但是没有成绩，只有学年，学期，学号，课号
    //     let url = `http://localhost:8080/score/add`;
    //     let teaching = {};
    //     let {time, user} = this.$store.state;
    //     teaching.academicYear = time.academicYear;
    //     teaching.semester = time.semester;
    //     teaching.sid = user.sid;
    //     teaching.cid = row.cid;
    //     this.$http.post(url, teaching, {emulateJSON: true})
    //       .then((response) => {
    //         if (response.body.resCode === '1') {
    //           this.$message({
    //             type: 'success',
    //             message: `选课成功!`
    //           });
    //         } else {
    //           this.$message({
    //             type: 'fail',
    //             message: `选课失败!`
    //           });
    //         }
    //       })
    //       .catch(() => {
    //         console.log(
    //           'error'
    //         )
    //       });
    //   },
    //   //确认是否保存新建/编辑的结果
    //   save(formData) {
    //     this.confirmOperation(formData, this.editFormStatus, '保存', this.saveRow);
    //   },
    //   //编辑一行后确认保存后的实际操作
    //   saveRow(row, oper, hint) {
    //     console.log(oper);
    //     let url = `http://localhost:8080/${this.tableName}/${oper}`;
    //     this.$http.post(url, row, {emulateJSON: true}).then((response) => {
    //       console.log(response.body);
    //       if (response.body.resCode === '1') {
    //         this.$message({
    //           type: 'success',
    //           message: `${hint}成功!`
    //         });
    //         this.getData();
    //       } else {
    //         this.$message({
    //           type: 'fail',
    //           message: `${hint}失败!`
    //         });
    //       }
    //     }).catch(() => {
    //       this.$message({
    //         type: 'fail',
    //         message: `${hint}失败!`
    //       });
    //     });
    //     this.showEditForm = false;
    //   },
    //   cancel() {
    //     this.showEditForm = false;
    //   },
    //   addNew() {
    //     let keys = Object.keys(this.tableData[0]);
    //     this.formData = keys.reduce((prev, cur) => {
    //       prev[cur] = '';
    //       return prev;
    //     }, {});
    //     this.showEditForm = true;
    //     this.editFormStatus = "add";
    //   },
    //   initQuery(){
    //     //初始化查询表单对象
    //     console.log(this.tableData);
    //     this.queryFormData = Object.keys(this.tableData[0]).reduce((prev,cur)=>{
    //       if(cur.indexOf("Password")==-1)
    //         prev[cur] = "";
    //       return prev;
    //     },{});
    //   },
    //   search(){
    //     let url = this.generateURL(this.tableName,"findByQuery",this.queryFormData);
    //     this.$http.get(url).then((response)=>{
    //       this.tableData = response.body.data;
    //     }).catch((response)=>{
    //
    //     })
    //   }
    // },
    // mounted(){
    //   this.getData();
    //   this.identity = this.$store.state.user.identity;
    // },
    // watch:{
    //   operation(newval){
    //     this.getData();
    //   }
    // }
  }
</script>

<style scoped>
  .table-class{
    width:95%;
    margin:0 auto;
    line-height:50px;
  }

  .el-form{
    width:60%;
    margin: 0 auto;
  }

  .el-button#addNew{
    float: right;
    margin-right: 30px;
  }


</style>
