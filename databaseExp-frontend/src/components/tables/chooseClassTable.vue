<template>
  <div>
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
          <!--<el-button type="text" size="small" @click="chooseClass(scope.row)">选课</el-button>-->
          <!--<el-button type="text" size="small" @click="confirmOperation(scope.row,'delete','删除',deleteRow)">删除</el-button>-->
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
  export default {
    name: "chooseClassTable",
    components: {editForm},
    props:["tableName"],
    data(){
      return{
        tableData:[],
      }
    },
    methods:{
      getData() {
        let that = this;
        this.$http.get(`http://localhost:8080/${this.tableName}/findAll`)
          .then(function (response) {
            that.tableData = response.body.data;
            console.log(that.tableData)
          }).catch(function (res) {
          console.log(res.body.resMsg);
        });
      },
      chooseClass(row){
        let chooseClass = {
          cid:row.cid,
          sid:this.$store.state.user.sid,
          academicYear: this.$store.state.time.academicYear,
          semester: this.$store.state.time.semester
        };
        let url = `http://localhost:8080/score/add`;
        this.$http.post(url,chooseClass,{emulateJSON:true}).then((response)=>{
          console.log(response.body);
          if (response.body.resCode === '1') {
            this.$message({
              type: 'success',
              message: `选课成功!`
            });
            // this.getData();
          }else{
            this.$message({
              type: 'fail',
              message: `选课失败!`
            });
          }
        }).catch(()=>{
          this.$message({
            type: 'fail',
            message: `选课失败!`
          });
        });
        // this.showEditForm = false;
      }
    },
    mounted(){
      this.getData();
      // this.identity = this.$store.state.user.identity;
      // console.log(this.identity)
    },
  }
</script>

<style scoped>

</style>
