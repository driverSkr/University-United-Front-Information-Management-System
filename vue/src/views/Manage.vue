<template>
    <el-container style="min-height: 100vh">
      <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246);box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
        <Aside :is-collapse="isCollapse" :logo-text-show="logoTextShow"/>
      </el-aside>

      <el-container>
        <el-header style="border-bottom: 1px solid #cccccc">
          <Header :collapse="collapse" :collapseBtnClass="collapseBtnClass"/>
        </el-header>

        <el-main>
          <!--表示当前页面的子路由会<router-view/>在里面展示-->
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
</template>

<script>

//import request from "@/utils/request";
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Home',
  components: {
    Aside,
    Header
  },
  data(){
    return{
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
    }
  },
  methods: {
    // 点击收缩按钮触发
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    }
  }
}
</script>

