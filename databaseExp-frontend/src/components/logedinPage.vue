<template>
    <div>
      <my-header @logout="logout">
        <span slot="username">{{username}}</span>
      </my-header>
      <el-container>
        <el-container>
          <!--<router-link to='/home'>home</router-link>-->
          <el-aside width="200px">
            <navigator :identity="this.$store.state.user.identity"></navigator>
          </el-aside>
          <el-main>
            <router-view></router-view>
            <!--<helloworld></helloworld>-->
          </el-main>
        </el-container>
      </el-container>
    </div>
</template>

<script>
  import Navigator from "./navigator";
  import myHeader from "./myHeader";
  import vuex from 'vuex'
  import { mapMutations } from 'vuex'
  export default {
    name: "logedinPage",
    components:{Navigator,myHeader},
    data(){
      return{
        username: '',
        // menuTitle: [],
      }
    },
    mounted(){
      var user = this.$store.state.user;
      this.username = user.aName||user.tName||user.sName;
      // this.menuTitle =  this.$store.state.menuTitles[user.identity];
    },
    methods:{
      generateURL(tableName,operation,query){
        let url = `http://localhost:8080/${tableName}/${operation}`;
        if(query){
          url += (Object.keys(query).reduce((prev,cur)=>{
            prev+=cur+"="+query[cur];
            return prev;
          },"?"));
        }
        return url;
      },
      logout(){
        let url = this.generateURL(this.$store.state.user.identity,'logout');
        this.$http.get(url).then(()=>{
            this.$store.state.user = null;
            this.$message({
              type: 'success',
              message: `已退出登录!`
            });
            this.$router.push('/login');
        });
      }
    },
    watch: {
      '$route' (to, from) {
        console.log(to,from);
      }
    }
  }
</script>

<style scoped>
  .el-container{
    height: 600px;
  }
  .el-header{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100px;
  }
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    position: fixed;
    left: 0;
    top: 60px;
    width:30%;
    height: 540px;
    -webkit-box-shadow: 1px 0 2px gray;
    -moz-box-shadow: 1px 0 2px gray;
    box-shadow: 1px 0 2px gray;
    background-color: #fff;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    /*{*/
    width: calc(100% - 200px);
    float: right;
    position: fixed;
    right:0;
    top:60px;
    bottom:0;
    overflow-y: scroll;
    /*}*/
    /*background-color: #E9EEF3;*/
    /*color: #333;*/
    /*text-align: center;*/
    /*line-height: 160px;*/
  }
</style>
