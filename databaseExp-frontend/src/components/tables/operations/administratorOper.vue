<template>
    <div>
      <el-button type="text" size="small" @click="edit">
        编辑
      </el-button>

      <el-button
        type="text"
        size="small"
        @click="emitDelete"
      >
        删除
      </el-button>
    </div>
</template>

<script>
  export default {
    name: "administratorOper",
    props:['row'],
    methods:{
      edit(){
        this.$emit('edit');
      },
      emitDelete(){
        this.$emit('delete');
      }
    }
  }
</script>

<style scoped>

</style>
