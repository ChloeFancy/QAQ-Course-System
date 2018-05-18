<template>
    <div>
      <edit-grade v-if="tableName==='editGrade'"></edit-grade>
      <see-teaching-course v-if="tableName==='seeTeachingCourse'"></see-teaching-course>
      <teacher-info v-if="tableName==='changePwd'"></teacher-info>
    </div>
</template>

<script>
  import editGrade from "./editGrade";
  import seeTeachingCourse from "./seeTeachingCourse";
  import TeacherInfo from "./teacherInfo";
  export default {
    name: "teacherTable",
    props:{
      tableName:{
        type:String
      }
    },
    components:{
      editGrade,
      seeTeachingCourse,
      TeacherInfo
    }
  }
</script>

<style scoped>

</style>
