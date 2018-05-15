<template>
  <div>
    <el-button type="text" size="small" @click="deleteClass">退课</el-button>
  </div>
</template>

<script>
  export default {
    name: "deleteClass",
    methods:{
      deleteClass(){
        this.$emit('deleteClass');
      }
    }
  }
</script>

<style scoped>

</style>
