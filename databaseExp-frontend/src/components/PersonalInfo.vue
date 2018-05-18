<template>
    <div>
      <el-form>
        <el-form-item label="旧密码">
          <el-input v-model="oldPwd" type="password"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="pwd" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码">
          <el-input v-model="pwd_1" type="password"></el-input>
        </el-form-item>
        <el-button @click="updatePassword">确认修改</el-button>
      </el-form>
    </div>
</template>

<script>
  import httpUtil from "../myHttp/httpUtil";

  export default {
    name: "studentInfo",
    data(){
      return{
        pwd:'',
        pwd_1:'',
        oldPwd:'',
      }
    },
    props:{
      identity:{
        //student/teacher
        type:String,
      }
    },
    methods:{
      updatePassword(){
        var idName = this.identity[0]+'id';
        if(this.pwd===this.pwd_1){
          let form = {
            newPassword:this.pwd,
            oldPassword:this.oldPwd
          };
          form[idName] = this.$store.state.user[idName];
          let url = httpUtil.generateURL(this.identity,"updatePassWord");
          httpUtil.postData(this,url,{data:JSON.stringify(form)}).then((response)=>{
            if(response.body.resCode==='-1'){
              this.$message({
                type:"fail",
                message: `旧密码错误`
              });
            }else{
              this.$message({
                type:"success",
                message: `修改密码成功,请重新登录`
              });
              this.$router.replace("/login");
            }
          })
        }else{
          this.$message({
            type:"fail",
            message: `两次新密码不一致`
          });
        }
      }
    }
  }
</script>

<style scoped>

</style>
