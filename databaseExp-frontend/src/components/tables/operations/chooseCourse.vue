<template>
    <div>
      <el-button type="text" size="small" @click="chooseClass">选课</el-button>
    </div>
</template>

<script>
  export default {
    name: "class",
    methods:{
      chooseClass(){
        this.$emit('chooseCourses');
      }
    }
  }
</script>

<style scoped>

</style>
