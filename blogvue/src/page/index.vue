<!--  -->
<template>
  <div class="box">
    <!-- <router-link to="/HelloWorld">数据大屏</router-link> -->
    <el-container style="height: 100%">
      <el-aside width="" style="background: #00152a;">
        <div class="title">MU</div>
        <el-menu
          :default-active="defaultactive"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          :collapse="isCollapse"
          background-color="#00152a"
          text-color="#fff"
          @select="select"
        >
          <!-- <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-grid"></i>
              <span slot="title">设备列表</span>
            </template>
            <el-menu-item-group>
              <span slot="title">分组一</span>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <span slot="title">选项4</span>
              <el-menu-item index="1-4-1">选项1</el-menu-item>
            </el-submenu>
          </el-submenu> -->
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">设备日志</span>
          </el-menu-item>
          <!-- <el-menu-item index="2">
            <i class="el-icon-s-grid"></i>
            <span slot="title">设备列表</span>
          </el-menu-item> -->
            <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-grid"></i>
              <span slot="title">设备列表</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="2-1">小匠</el-menu-item>
              <el-menu-item index="2-2">酷宅</el-menu-item>
              <el-menu-item index="2-3">大华</el-menu-item>
              <el-menu-item index="2-4">涂鸦</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-menu-item index="3"  style="display:none">
            <i class="el-icon-tickets"></i>
            <span slot="title">设备信息</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-document"></i>
            <span slot="title">设备订单</span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="el-icon-user"></i>
            <span slot="title">用户列表</span>
          </el-menu-item>
          <el-menu-item index="7">
            <i class="el-icon-finished"></i>
            <span slot="title">数据录入</span>
          </el-menu-item>
          <el-menu-item index="6">
            <i class="el-icon-setting"></i>
            <span slot="title">工具辅助</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <div style="" class="headers">
            <el-button
              :icon="isCollapse ? isbtn2 : isbtn1"
              @click="tarrget"
              class="btns"
            ></el-button>
            <div class="rightimg">
              <P>欢迎：{{ getUser.username }}</P>

              <el-dropdown @command="handleCommand" class="eldown">
                <el-avatar
                  :size="50"
                  :src="getUser.avatar"
                  @error="errorHandler"
                >
                  <img
                    src="https://img2.woyaogexing.com/2020/04/10/2911e52acd544055ab7f434108037632!400x400.webp"
                  />
                </el-avatar>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>退出</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </div>
        </el-header>
        <el-main>
          <div  class="pagecontent">
            <Pagehome  :defaultactive="defaultactive" />
          </div>
        </el-main>
        <!-- <el-footer>Footer</el-footer> -->
      </el-container>
    </el-container>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import Pagehome from "./page";
import { mapGetters } from "vuex";
export default {
  name: "index",
  //import引入的组件需要注入到对象中才能使用
  components: {
    Pagehome,
  },
  data() {
    //这里存放数据
    return {
      isCollapse: false,
      isbtn1: "el-icon-s-fold",
      isbtn2: "el-icon-s-unfold",
      defaultactive: "2-1",
      message: "Hello",
    };
  },
  //监听属性 类似于data概念
  computed: {
    // 计算属性的 getter
    reversedMessage: function () {
      // `this` 指向 vm 实例
      return this.message.split("").reverse().join("");
    },

    ...mapGetters(["getUser"]),
  },
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    },
    tarrget() {
      this.isCollapse = !this.isCollapse;
    },
    select(key, keyPath) {
      console.log(key, keyPath);
      this.defaultactive = key;
      // sessionStorage.setItem("active",key)

    },
    errorHandler() {
      return true;
    },
    handleCommand(command) {
      this.$confirm("退出, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .get("/logout", {
              headers: {
                Authorization: localStorage.getItem("token"),
              },
            })
            .then((res) => {
              if (res.data.code == 200) {
                this.$store.commit("REMOVE_INFO");
                this.$router.push("/");
                // console.log("d", res);
                this.$message({
                  type: "success",
                  message: "退出成功!",
                });
              } else {
                this.$message({
                  type: "error",
                  message: "退出失败!",
                });
              }
            });
        })
        .catch((err) => {
          this.$message({
            type: "error",
            message: err,
          });
        });
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {},
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css类
.box {
  position: absolute;
  width: 100%;
  height: 100%;
}
.btns {
  font-size: 30px;
  background: transparent;
  border: 0;
  color: #333;
}
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
  height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
  // min-width: 65px;

}
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  //   line-height: 160px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-menu-vertical-demo {
  // height: 100%;
  border: 0;
}
.headers {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
}
.rightimg {
  display: flex;
  align-items: center;
  height: 100%;
  justify-content: space-evenly;
}
.eldown {
  height: 100%;
  padding-left: 10px;
  align-items: center;
  justify-content: center;
  display: flex;
}
.title {
  height: 60px;
  background-color: #fff;
  color: #333;
  display: flex;
  justify-content: center;
  align-items: center;
    // width: 300px;
    // height: 200px;
    // position: absolute;
    // left: 50%;
    // margin-left: -150px;
    background-image: -webkit-linear-gradient(left,blue,#66ffff 10%,#cc00ff 20%,#CC00CC 30%, #CCCCFF 40%, #00FFFF 50%,#CCCCFF 60%,#CC00CC 70%,#CC00FF 80%,#66FFFF 90%,blue 100%);
    -webkit-text-fill-color: transparent;/* 将字体设置成透明色 */
    -webkit-background-clip: text;/* 裁剪背景图，使文字作为裁剪区域向外裁剪 */
    -webkit-background-size: 200% 100%;
    -webkit-animation: masked-animation 4s linear infinite;
    font-size: 35px;
}

@keyframes masked-animation {
    0% {
        background-position: 0  0;
    }
    100% {
        background-position: -100%  0;
    }
    }
    .pagecontent{
      width: 100%;
      height: 100%;
      display: flex;
    justify-content: center;
    align-items: center;
    }
</style>
