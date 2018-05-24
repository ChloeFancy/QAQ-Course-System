<template>
  <div>
    <!--只有在当前学期的课程才可以修改成绩-->
    <el-form :inline="true">
      <el-select
        v-model="courseName"
        placeholder="请选择课程"
        @change="changeGradeCourse"
      >
        <el-option
          v-for="(item,index) in courses"
          :key="index"
          :label="item.cName"
          :value="JSON.stringify(item)"
        >
        </el-option>
      </el-select>
    </el-form>
  </div>
</template>

<script>
  import httpUtil from "../../../myHttp/httpUtil";

  export default {
    name: "gradeCourseSearch",
    data(){
      return{
        courses:[],//老师在当前开学年学期开的的课
        courseName:''
      }
    },
    methods:{
      getData(){
        let query = {
          tid:this.$store.state.user.tid,
          openTerm:this.$store.state.time.academicYear+" "+this.$store.state.time.semester,
        };
        let url = httpUtil.generateURL("teaching","findTeachingCourseName",query);
        httpUtil.getData(this,url).then((response)=>{
          // console.log(response.body.data);
          this.courses = response.body.data.map((item)=>{
            return Object.keys(item).reduce((prev,cur)=>{
              prev[cur] = item[cur];
              return prev;
            },{});
          });
        }).catch((e)=>{
          console.log(e);
        });
      },
      changeGradeCourse(courseObject){
        // console.log(cid);
        this.$emit('search',JSON.parse(courseObject));
      }
    },
    mounted(){
      this.getData();
    }
  }
</script>

<style scoped>

</style>
