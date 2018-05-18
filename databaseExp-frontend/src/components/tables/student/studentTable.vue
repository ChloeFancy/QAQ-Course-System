<template>
    <div>
      <choose-course v-if="tableName==='chooseCourse'"></choose-course>
      <see-all-course v-if="tableName==='seeAllCourse'"></see-all-course>
      <see-chosen-course v-if="tableName==='seeChosenCourse'"></see-chosen-course>
      <see-score v-if="tableName==='seeScore'"></see-score>
      <student-info v-if="tableName==='changePwd'"></student-info>
    </div>
</template>

<script>
  import seeAllCourse from "./seeAllCourse";
  import seeChosenCourse from "./seeChosenCourse";
  import seeScore from "./seeScore";
  import chooseCourse from "./chooseCourse";
  import studentInfo from "./studentInfo";
  export default {
    name: "studentTable",
    components:{
      seeAllCourse,
      seeChosenCourse,
      seeScore,
      chooseCourse,
      studentInfo
    },
    props:{
      'tableName':{
        type:String
      }
    }
  }
</script>

<style scoped>

</style>
