<template>
  <div>
    <el-form size="mini" label-width="100px" id="center" :inline="true">
      <el-form-item v-for="(value,key) in queryFormData" :key="key" :label="key">
        <el-input v-model="queryFormData[key]"></el-input>
      </el-form-item>
      <br>
      <el-button type="info" @click="search">搜索</el-button>
    </el-form>
    <el-button id="addNew" v-if="identity==='administrator'" type="primary" size="small" @click="addNew">新增</el-button>
    <el-table
      :data="tableData"
      :header-cell-style="{'text-align':'center','height':'50px'}"
      class="table-class"
      >
      <el-table-column
        v-for="(value,key) in tableData[0]"
        :prop="key"
        :label="key"
        :key="key"
        >
      </el-table-column>
      <el-table-column
        label="操作"
        width="100">
        <template slot-scope="scope">
          <administration-oper
            v-if="identity==='administrator'"
           @edit="edit(scope.row)"
           @delete="confirmOperation(scope.row,'delete','删除',deleteRow)"
          >
          </administration-oper>
          <choose-course
            v-else-if="identity==='student'&&operation==='findAll'&&tableName==='teaching'"
            @chooseCourses="confirmOperation(scope.row,'add','选课',chooseCourses)"
          >
          </choose-course>
          <delete-course
            @deleteClass="confirmOperation(scope.row,'delete','退课',deleteRow)"
            v-else-if="identity==='student'&&operation==='findSomeCourse'">
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
  import vuex from 'vuex'
  import { mapMutations } from 'vuex'
  import editForm from "./editForm";
  import administrationOper from "./operations/administratorOper";
  import chooseCourse from "./operations/chooseCourse";
  import deleteCourse from "./operations/deleteCourse";
  export default {
    name: "testTable",
    components: {editForm,administrationOper,chooseCourse,deleteCourse},
    props:{
      "tableName":{type:String},
      "operation":{type:String},
      "query":{},
    },
    data(){
      return{
        tableData:[],
        formData:{},
        showEditForm:false,
        editFormStatus:"update",
        obj:{
          key:1
        },
        identity:'',
        queryFormData:{}
      }
    },
    watch:{
      tableName(){
        this.getData();
      }
    },
    methods: {
      //生成url
      generateURL(tableName, operation, query) {
        let url = `http://localhost:8080/${tableName}/${operation}`;
        if (query) {
          url += (Object.keys(query).reduce((prev, cur) => {
            if(query[cur]!=='')
              prev += cur + "=" + query[cur] + '&';
            return prev;
          }, "?"));
          url = url.slice(0, url.length - 1);
        }
        return url;
      },
      //获取数据，渲染表格
      getData() {
        let that = this;
        let query;
        if (this.tableName === 'score' && this.operation.indexOf('findSome')===0) {
          //退课
          query = {};
          query.sid = this.$store.state.user.sid;
          query.semester = this.$store.state.time.semester;
          query.academicYear = this.$store.state.time.academicYear;
        }
        let url = this.generateURL(this.tableName, this.operation, query);
        console.log(url);
        this.$http.get(url)
          .then(function (response) {
            that.tableData = response.body.data;
            this.initQuery();
          }).catch(function (res) {
          console.log(res.body.resMsg);
        });
      },
      search(){

      },
      //确认是否完成选择的操作
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
      //确认删除后的实际操作
      deleteRow(row, oper, hint) {
        let id = Object.keys(row)[0];
        let val = row[id];
        let url = `http://localhost:8080/${this.tableName}/delete`;
        this.$http.post(url, row, {emulateJSON: true})
          .then(function (response) {
            if (response.body.resCode === '1') {
              this.$message({
                type: 'success',
                message: `${hint}成功!`
              });
              this.getData();
            } else {
              this.$message({
                type: 'fail',
                message: `${hint}失败!`
              });
            }
          }).catch(function (res) {
          console.log(res.body.resMsg);
        });
      },
      //点击编辑/新建，弹出编辑框
      edit(row) {
        this.editFormStatus = "update";
        this.showEditForm = true;
        this.formData = row;
      },
      //学生选课
      chooseCourses(row, oper, hint) {
        //添加到成绩表里，但是没有成绩，只有学年，学期，学号，课号
        this.$emit('chooseCourses',row);
      },
      //确认是否保存新建/编辑的结果
      save(formData) {
        this.confirmOperation(formData, this.editFormStatus, '保存', this.saveRow);
      },
      //编辑一行后确认保存后的实际操作
      saveRow(row, oper, hint) {
        console.log(oper);
        let url = `http://localhost:8080/${this.tableName}/${oper}`;
        this.$http.post(url, row, {emulateJSON: true}).then((response) => {
          console.log(response.body);
          if (response.body.resCode === '1') {
            this.$message({
              type: 'success',
              message: `${hint}成功!`
            });
            this.getData();
          } else {
            this.$message({
              type: 'fail',
              message: `${hint}失败!`
            });
          }
        }).catch(() => {
          this.$message({
            type: 'fail',
            message: `${hint}失败!`
          });
        });
        this.showEditForm = false;
      },
      cancel() {
        this.showEditForm = false;
      },
      addNew() {
        let keys = Object.keys(this.tableData[0]);
        this.formData = keys.reduce((prev, cur) => {
          prev[cur] = '';
          return prev;
        }, {});
        this.showEditForm = true;
        this.editFormStatus = "add";
      },
      initQuery(){
        //初始化查询表单对象
        console.log(this.tableData);
        this.queryFormData = Object.keys(this.tableData[0]).reduce((prev,cur)=>{
          if(cur.indexOf("Password")==-1)
            prev[cur] = "";
          return prev;
        },{});
      },
      search(){
        let url = this.generateURL(this.tableName,"findByQuery",this.queryFormData);
        this.$http.get(url).then((response)=>{
          this.tableData = response.body.data;
        }).catch((response)=>{

        })
      }
    },
    mounted(){
      this.getData();
      this.identity = this.$store.state.user.identity;
    },
    watch:{
      operation(newval){
        this.getData();
      }
    }
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
