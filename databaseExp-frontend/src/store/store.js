import vue from "vue"
import vuex from "vuex"
vue.use(vuex);
export default new vuex.Store({
  state:{
    time:{
      academicYear:'2017-2018',
      semester:'冬季',
      openTerm:'2017-2018 冬季'
    },
    user:{},
    menuTitles:{
      administrator:{
        '数据管理':[
            {tableName:'teacher',chi:'教师',oper:'findAll'},
            {tableName:'student',chi:'学生',oper:'findAll'},
            {tableName:'course',chi:'课程',oper:'findAll'},
            {tableName:'teaching',chi:'开课',oper:'findAll'}
          ],
        // '数据查询':[
        //     {tableName:'teacher',chi:'查询教师',oper:'findone'},
        //     {tableName:'student',chi:'查询学生',oper:'findone'}
        //   ]
      },
      student:{
        '个人信息':[{tableName:'student',chi:'我的信息',oper:'findOne'}],
        '课程管理':[
          {tableName:'seeAllCourse',chi:'所有课程',oper:'findAll'},
          {tableName:'chooseCourse',chi:'选课',oper:'findAll'},
          {tableName:'seeChosenCourse',chi:'查看已选课程',oper:'findSomeCourse'},
          {tableName:'seeScore',chi:'查看成绩',oper:'findSomeScore'},
          // {tableName:'score',chi:'',oper:'find'},
        ]
        // '课程管理':[
        //     {tableName:'course',chi:'所有课程',oper:'findAll'},
        //     {tableName:'teaching',chi:'选课',oper:'findAll'},
        //     {tableName:'score',chi:'查看已选课程',oper:'findSomeCourse'},
        //     {tableName:'score',chi:'查看成绩',oper:'findSomeScore'},
        //     // {tableName:'score',chi:'',oper:'find'},
        // ]
      },
      teacher:{
        '课程管理':[
          {tableName:'seeTeachingCourse',chi:'本学期所有课程',oper:'findQuery'},
          {tableName:'editGrade',chi:'编辑成绩',oper:'findQuery'},
        ]
      }
    }
  },
  mutations:{
    // generateURL(tableName,operation,query){
    //   let url = `http://localhost:8080/${tableName}/${operation}`;
    //   if(query){
    //     url += (Object.keys(query).reduce((prev,cur)=>{
    //       prev+=cur+"="+query[cur];
    //       return prev;
    //     },"?"));
    //   }
    //   return url;
    // },
  },
  getters:{

  }
});
