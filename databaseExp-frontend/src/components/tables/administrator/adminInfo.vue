<template>
    <div>
      <personal-info :identity="identity">

      </personal-info>
    </div>
</template>

<script>
  import PersonalInfo from "../../PersonalInfo";

  export default {
    name: "adminInfo",
    data(){
      return{
        identity:"administrator",
        // info:null,
        // pwd:'',
        // pwd_1:'',
        // oldPwd:'',
      }
    },
    components:{PersonalInfo},
  }
</script>

<style scoped>

</style>
