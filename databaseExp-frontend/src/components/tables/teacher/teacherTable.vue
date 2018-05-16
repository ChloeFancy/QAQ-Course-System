<template>
    <div>
      <edit-grade v-if="tableName==='editGrade'"></edit-grade>
      <see-teaching-course v-if="tableName==='seeTeachingCourse'"></see-teaching-course>
    </div>
</template>

<script>
  import editGrade from "./editGrade";
  import seeTeachingCourse from "./seeTeachingCourse";
  export default {
    name: "teacherTable",
    props:{
      tableName:{
        type:String
      }
    },
    components:{
      editGrade,
      seeTeachingCourse
    }
  }
</script>

<style scoped>

</style>
