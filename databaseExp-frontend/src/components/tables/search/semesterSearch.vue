<template>
    <div>
      <el-form :inline="true">
        <el-select v-model="query.academicYear" placeholder="请选择学年">
          <el-option
            v-for="(item,index) in academicYear"
            :key="index"
            :label="item"
            :value="item.toString().slice(0,item.length-2)"
          >
          </el-option>
        </el-select>

        <el-select v-model="query.semester" placeholder="请选择学期">
          <el-option
            v-for="(item,index) in semester"
            :key="index"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
        <el-button type="info" @click="search">搜索</el-button>
      </el-form>
    </div>
</template>

<script>
  export default {
    name: "semesterSearch",
    data(){
      return{
        query:{
          academicYear:'',
          semester:''
        },
        academicYear:['2015-2016学年','2016-2017学年','2017-2018学年'],
        semester:['秋季学期','冬季学期','春季学期','夏季学期']
      }
    },
    methods:{
      search(){
        console.log('0:',this.query);
        this.$emit('search',this.query);
      }
    }
  }
</script>

<style scoped>

</style>
