<template>
  <div id="app">
    <!--<login-page></login-page>-->
    <!--<router-link to="/logedin">logedin</router-link>-->
    <router-view>
    </router-view>
    <!--<logedin-page></logedin-page>-->
  </div>
</template>

<script>

import logedinPage from "./components/logedinPage"
import store from "./store/store"
export default {
  name: 'App',
  store,
  components: {logedinPage},
  data(){
    return{
      username:'',
      adminMenu:{
        '数据管理':{'teacher':'教师','student':'学生','class':'课程','kaike':'开课'},
        '数据查询':{'teacher':'查询教师','student':'查询学生'}
      }
    }
  },
  mounted(){
    this.username = "???";
  }
}
</script>

<style>


  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  body{
    margin: 0;
    padding: 0;
    overflow: hidden;
  }
</style>
