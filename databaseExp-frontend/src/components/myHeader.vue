<template>
  <el-header>
    <div>
      选课系统
    </div>
    <div>
      <el-button type="text" size="small" @click="logout">退出登录</el-button>
    </div>
    <div>
      欢迎，<slot name="username"></slot>
    </div>
  </el-header>
</template>

<script>
  export default {
    name: "myHeader",
    // prop:['username'],
    methods:{
      logout(){
        this.$emit('logout');
      }
    }
  }
</script>

<style scoped>
  div{
    display: inline-block;
  }
  div:not(:first-of-type){
    float: right;
    margin: 0 10px;
  }
</style>
