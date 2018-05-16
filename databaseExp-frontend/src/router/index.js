import Vue from 'vue'
import Router from 'vue-router'
import testTable from "@/components/tables/testTable"
import login from "@/components/login"
import logedinPage from "@/components/logedinPage"
import adminTable from "@/components/tables/administrator/adminTable";
import studentTable from "@/components/tables/student/studentTable";
import teacherTable from "@/components/tables/teacher/teacherTable";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/logedin',
      name: 'logedin',
      component: logedinPage,
      children:[
        //在logedinPage中的<router-view>更新
        {
          //由于管理员工作类似，因此通过prop作为选项来渲染表格
          path: '/administrator/table/:tableName',
          name: 'administratorTable',
          component: adminTable,
          props: true,
        },
        // {
        //   path: '/student/table/:tableName/:operation',
        //   name: 'studentTable',
        //   component: testTable,
        //   props: (route)=>{
        //     return route.params;
        //   },
        // }
        {
          path: '/student/table/:tableName',
          name: 'studentTable',
          component: studentTable,
          props: true
        },
        {
          path: '/teacher/table/:tableName',
          name: 'teacherTable',
          component: teacherTable,
          props: true
        }
      ]
    },
  ]
})
