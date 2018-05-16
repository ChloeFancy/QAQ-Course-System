<template>
    <div>
      <el-form size="mini" label-width="100px" id="center" :inline="true">
        <el-form-item v-for="(value,key) in queryData" :key="key" :label="key">
          <el-input v-model="queryData[key]"></el-input>
        </el-form-item>
        <br>
        <el-button type="info" @click="search">搜索</el-button>
      </el-form>
    </div>
</template>

<script>
  export default {
    name: "commonSearch",
    props:{
      'queryData':{
        type:Object
      }
    },
    methods:{
      search(){
        this.$emit('search',this.queryData);
      }
    }
  }
</script>

<style scoped>

</style>
