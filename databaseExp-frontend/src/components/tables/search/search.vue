<template>
    <div>
      <common-search
        v-if="searchType==='commonSearch'"
        :queryData="queryData"
        @search="search"
      >
      </common-search>

      <semester-search
        v-if="searchType==='semesterSearch'"
        @search="search"
      >
      </semester-search>

      <grade-course-search
        v-if="searchType==='gradeCourseSearch'"
      >

      </grade-course-search>
    </div>
</template>

<script>
  import commonSearch from "./commonSearch";
  import semesterSearch from "./semesterSearch";
  import gradeCourseSearch from "./gradeCourseSearch";

  export default {
    name: "search",
    components:{
      commonSearch,
      semesterSearch,
      gradeCourseSearch
    },
    props:{
      'searchType':{
        type:String,
      },
      'queryData':{
        type:Object,
      }
    },
    methods:{
      search(queryData){
        console.log('1:',queryData);
        this.$emit('search',queryData);
;      }
    }
  }
</script>

<style scoped>

</style>
