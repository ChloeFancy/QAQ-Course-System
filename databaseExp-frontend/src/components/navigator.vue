<template>
  <div class="hello">
    <el-col>
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        active-text-color="#ffd04b"
      >
        <el-submenu v-for="(value,key) in menuTitle" :key="key" :index="key">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>{{key}}</span>
          </template>
          <el-menu-item-group v-for="{tableName,chi,oper,myquery} in value" :key="chi">
            <template slot="title">
              <router-link :to="{ name: identity+'Table',
                params: { tableName: tableName, operation: oper}}"
              >
                {{chi}}
              </router-link>
            </template>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-col>
  </div>
</template>
<script>

export default {
  name: 'navigator',
  // router,
  props:['identity'],
  data(){
    return {
      menuTitle:[],
    }
  },
  mounted(){
    this.menuTitle = this.$store.state.menuTitles[this.identity];
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style scoped>
  .el-menu{
    border:none;
  }
</style>
