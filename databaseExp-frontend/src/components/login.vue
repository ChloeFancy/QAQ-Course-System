<template>
    <div id="login">
      <h2>登录</h2>
      <el-form label-width="80px">
        <!--<h3>login status: {{msg}}</h3>-->
        <el-form-item label="账号" :required="true">
          <el-input v-model="idNumber" placeholder="请输入学号/工号"></el-input>
        </el-form-item>
        <el-form-item label="密码" :required="true">
          <el-input v-model="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <div>
          <el-radio-group v-model="identity">
            <el-radio label="student">学生</el-radio>
            <el-radio label="teacher">教师</el-radio>
            <el-radio label="administrator">管理员</el-radio>
          </el-radio-group>
        </div>
        <div>
          <el-button type="info" v-on:click="login">
            登录
          </el-button>
        </div>
      </el-form>
    </div>
</template>

<script>
  export default {
    name: "login",
    data(){
      return{
        idNumber:0,
        password:0,
        msg:'未登录',
        identity:'student',
      }
    },
    methods:{
      login(){
        let that = this;
        let url = `http://localhost:8080/${this.identity}/findOne?`;
        switch (this.identity){
          case"student":url+=`sid=${this.idNumber}&sPassword=${this.password}`;break;
          case"teacher":url+=`tid=${this.idNumber}&tPassword=${this.password}`;break;
          case"administrator":url+=`aid=${this.idNumber}&aPassword=${this.password}`;break;
        }
        this.$http.get(url)
          .then(function(response){
            that.msg = response.body.resMsg;
            if(response.body.resCode==='1'){
              //成功
              console.log(response.body)
              this.$message({
                type: 'success',
                message: `登录成功!`
              });
              that.$store.state.user = response.body.data;
              that.$store.state.user.identity = this.identity;
              that.$router.push({path:'/logedin'});
            }else{
              //失败
              this.$message({
                type: 'fail',
                message: `登录失败!`
              });
            }
          })
          // .catch(function(res){
          //   alert('fail')
          //   // that.msg = res.body.resMsg;
          // });
      }
    }
  }
</script>

<style scoped lang="less">
  div#login{
    width: 30%;
    height: 40%;
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    margin: auto;
    /*margin,重要！否则不能绝对居中*/
    & div{
      margin: 10px auto;
    }
  }
</style>
