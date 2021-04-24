<!-- 数据导出 -->
<template>
  <div class="formbox">
    <p>数据导出</p>
    <el-form ref="form" :model="formInline" label-width="80px">
      <el-form-item label="前缀">
        <el-input v-model="formInline.label" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item label="开始值">
        <el-input v-model="formInline.user" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item label="结束值">
        <el-input v-model="formInline.password" style="width: 300px"></el-input>
      </el-form-item>

      <el-form-item label="文件名">
        <el-input v-model="formInline.filename" style="width: 300px">
          <el-select
            slot="append"
            v-model="formInline.sufname"
            style="width: 75px"
          >
            <el-option value=".csv"> .csv </el-option>
            <el-option value=".xls"> .xls </el-option>
            <el-option value=".xlsx"> .xlsx </el-option>
          </el-select>
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  name: "Exportdata",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: [],
  data() {
    //这里存放数据
    return {
      formInline: {
        label: "",
        user: "",
        password: "",
        filename: "数据表",
        sufname: ".csv",
      },
        list: [],
      strdata: "",
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    onSubmit() {
      console.log("submit!",this.formInline);
      this.loopfun()
    },
     loopfun() {
      let v1 = +this.formInline.user;
      let v2 = +this.formInline.password;
      let v3 = this.formInline.label;
      let list = [];

      for (let i = v1; i <= v2; i++) {
        let v4 = v3 + i.toString(16).toLocaleUpperCase();
        // console.log(v4);
        list.push(v4);
      }
      this.list = list;
      this.Arraylist();
    },
    //导出到
    Arraylist() {
      let list = this.list;
      let str = "";
      for (const key in list) {
        str += `${list[key] + "\t\n"}`;
      }
      // console.log("e", str);
      this.strdata = str;
      this.files(str);
    },
    //下载到本地
    files(str) {
      const uri =
        "data:text/csv;charset=utf-8,\ufeff" + encodeURIComponent(this.strdata);
      // 通过创建a标签实现
      const link = document.createElement("a");
      link.href = uri;
      // 对下载的文件命名
      link.download = `${this.formInline.filename}${this.formInline.sufname}`;
      link.click();
       this.$message.success('操作成功！');
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
.formbox {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
