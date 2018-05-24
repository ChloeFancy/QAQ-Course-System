<template>
  <el-form label-width="100px" id="center" size="mini">
      <el-form-item
        v-for="(value,key) in formData"
        :key="key"
        :label="key"
        :required="key.indexOf('word')===-1||key.indexOf('Name')===-1||key.indexOf('id')===-1"
      >
        <el-input v-model="formData[key]"></el-input>
      </el-form-item>
    <el-button type="info" @click="save">确认</el-button>
    <el-button @click="cancel">取消</el-button>
  </el-form>
</template>

<script>
  export default {
    name: "editForm",
    data(){
      return{
        nullVal:'',
      }
    },
    props:{
      formData:{
        type:Object,
      },isEdit:{
        type:String,
      }
    },
    methods:{
      save(){
        this.$emit('save',this.formData,'保存');
      },
      cancel(){
        this.$emit('cancel');
      }
    },
    mounted(){

    }
  }
</script>

<style scoped>
  #center{
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    background: #fff;
    border: 1px solid #606163;
    padding: 15px 30px;
    z-index: 100;
  }
</style>
