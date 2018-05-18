<template>
    <div>
      <personal-info :identity="identity">

      </personal-info>
    </div>
</template>

<script>
  // import httpUtil from "../../../myHttp/httpUtil";

  import PersonalInfo from "../../PersonalInfo";

  export default {
    name: "studentInfo",
    data(){
      return{
        identity:"student",
        // info:null,
        // pwd:'',
        // pwd_1:'',
        // oldPwd:'',
      }
    },
    components:{PersonalInfo},
    // methods:{
    //   getData(){
    //     let query = {sid: this.$store.state.user.sid};
    //     let url = httpUtil.generateURL("student","findByQuery",query);
    //     httpUtil.getData(this,url).then((response)=>{
    //       this.info = response.body.data;
    //     }).catch((e)=>{
    //       console.log(e);
    //     });
    //   },
    //   updatePassword(){
    //     if(this.pwd===this.pwd_1){
    //       let form = {
    //         newPassword:this.pwd,
    //         oldPassword:this.oldPwd,
    //         sid:this.$store.state.user.sid
    //       };
    //       let url = httpUtil.generateURL("student","updatePassWord");
    //       httpUtil.postData(this,url,{data:JSON.stringify(form)}).then((response)=>{
    //         if(response.body.resCode==='-1'){
    //           this.$message({
    //             type:"fail",
    //             message: `旧密码错误`
    //           });
    //         }else{
    //           this.$message({
    //             type:"success",
    //             message: `修改密码成功,请重新登录`
    //           });
    //           this.$router.push("login");
    //         }
    //       })
    //     }else{
    //       this.$message({
    //         type:"fail",
    //         message: `两次新密码不一致`
    //       });
    //     }
    //   }
    // }
  }
</script>

<style scoped>

</style>
