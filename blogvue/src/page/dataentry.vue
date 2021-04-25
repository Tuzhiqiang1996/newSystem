<template>
  <div class="box">
    <div class="content">
      <div>
        <el-input v-model="orderid" placeholder="手动输入orderid"></el-input>
      </div>
      <div style="padding-top: 25px">
        <el-tooltip
          class="item"
          effect="dark"
          content="tb_device_list_re705"
          placement="top"
        >
          <el-radio v-model="radio" label="1">酷宅云</el-radio>
        </el-tooltip>
        <el-tooltip
          class="item"
          effect="dark"
          content="tb_device_list_re755"
          placement="top"
        >
          <el-radio v-model="radio" label="2">涂鸦云</el-radio>
        </el-tooltip>
        <el-tooltip
          class="item"
          effect="dark"
          content="tb_device_list_xiaojiang"
          placement="top"
        >
          <el-radio v-model="radio" label="3">小匠</el-radio>
        </el-tooltip>
      </div>
      <div style="padding-top: 25px">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="订单号：">
            <el-input
              v-model="formInline.ordernumber"
              placeholder="例：B02-F_WTW-SNL-02_170"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button
              type="primary"
              @click="savelistbtn"
              :disabled="isdisabled"
              >插入</el-button
            >
          </el-form-item>
        </el-form>
      </div>
      <el-upload
        class="upload-demo"
        drag
        ref="upload"
        action="https://jsonplaceholder.typicode.com/posts/"
        multiple
        :auto-upload="false"
        :file-list="fileList"
        :on-change="handleChange"
        :on-success="success"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      </el-upload>
      <div>
        <el-button size="medium" type="primary" @click="btn"
          >获取orderID</el-button
        >
        <el-button size="medium" type="primary" @click="btnorder"
          >插入order表</el-button
        >
        <el-button size="medium" @click="btns">插入devlist表</el-button>
      </div>
    </div>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  name: "",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: [],
  data() {
    //这里存放数据
    return {
      radio: "1",
      fileList: [],
      ee: "",
      name: "",
      info: [],
      filename: "",
      num: "",
      orderid: "",
      datadevlist: [],
      formInline: {
        ordernumber: "",
      },
      isdisabled: true, // 是否查询到
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    onSubmit() {
      if (!this.formInline.ordernumber) {
        this.$message({
          message: "文件名为空或者上传文件！",
          showClose: true,
          type: "error",
        });
        return;
      }
      let name = this.formInline.ordernumber;
      let file1 = name.split(".");
      let file2 = file1[0].split("_");
      let num = file2[2];
      let file3;
      if (file2[1]) {
        file3 = file2[0] + "_" + file2[1];
        if (file2[2]) {
          file3 = file2[0] + "_" + file2[1] + "_" + file2[2];
        }
      }
      console.log("e", file3, num);

      this.getorder(file2[0]);
      this.num = num;
      this.filename = file3;
    },
    savelistbtn() {
      if (this.info.orderNumber) {
        this.info.orderNumber = this.filename;
        this.orderlist();
      } else {
        this.$message({
          message: "请先上传文件！",
          showClose: true,
          type: "error",
        });
      }
    },
    handleChange(file, fileList) {
      //   this.fileList = file.raw;
      this.fileList = fileList.slice(-1);

      this.$message({
        message: file.raw.type,
        showClose: true,
        type: "info",
      });
      this.name = file.name;
      console.log(file.name);
    },
    //解析、查询文件名
    btn() {
      let flie = this.name;
      if (!flie) {
        this.$message({
          message: "请先上传文件！",
          showClose: true,
          type: "error",
        });
        return;
      }
      let file1 = flie.split(".");
      let file2 = file1[0].split("_");
      let num = file2[2];
      let file3 = file2[0] + "_" + file2[1] || "" + "_" + file2[2] || "";

      this.getorder(file2[0]);

      console.log(file2);
      this.filename = file3;
      this.num = num;
    },
    //插入表中事件 order
    btnorder() {
      let flie = this.name;

      if (!flie) {
        this.$message({
          message: "请先上传文件！",
          showClose: true,
          type: "error",
        });
        return;
      }
      this.info.orderNumber = this.filename;
      this.orderlist();
    },
    //获取相同类型额 id order
    getorder(order) {
      let url = "http://localhost:8081/getlistnumber?orderNumber=";
      this.$axios
        .get(url + order)
        .then((res) => {
          if (res.data.code == 200) {
            this.info = res.data.data[0];
            // this.info.orderNumber = this.filename;
            this.info.info = this.num;
            this.info.id = "";
            this.isdisabled = false;
            console.log("dd", res.data.data[0]);

            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
        });
    },

    //插入表中 order
    orderlist() {
      let url = "http://localhost:8081/saveorderfile";

      this.$axios
        .post(url, this.info)
        .then((res) => {
          if (res.data.code == 200) {
            this.orderid = res.data.data.id;
            console.log("dd", this.orderid);
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
        });
    },
    success(response, file, fileList) {
      console.log(response, file, fileList);
      let reader = new FileReader();
      reader.readAsText(file.raw);
      reader.onload = (e) => {
        this.ee = e.target.result;
        console.log(e.target.result);
        this.datadevlist = e.target.result;
        // this.refile(fileList);
        this.arraylist(this.datadevlist);
      };
    },
    //格式化数组
    arraylist(e) {
      let list = e.replace(/([.\n\r]+)/g, ",");
      let lista = list.split(",");
      // console.log("list", lista);

      if (this.radio == 1) {
        this.datalists(lista, this.orderid);
      } else {
        this.datatylist(lista, this.orderid);
      }
    },
    //涂鸦 2 小匠
    datatylist(lista, id) {
      let datalist = {};
      let datalist4 = {};
      let datalist2 = [];
      let datalist3 = [];
      for (let i = 0; i < lista.length; i++) {
        datalist = {
          deviceid: lista[i],
          sn: "",
          addr1: "",
          addr2: "",
          testResult: "",
          testDatetime: "",
          checkDatetime: "",
          createTime: "",
          checkCount: "",
          orderId: this.orderid,
          packages: "",
          packageDatetime: "",
        };
        datalist4 = lista[i];
        datalist2.push(datalist);
        datalist3.push(datalist4);
        // console.log("12", datalist2);  得到的数组
      }
      //1
      this.refile(datalist2);
      //2
      // this.filehas(datalist3);
    },
    //酷宅 1
    datalists(lista, id) {
      let datalist = {};
      let datalist4 = {};
      let datalist2 = [];
      let datalist3 = [];
      for (let i = 0; i < lista.length; i++) {
        datalist = {
          deviceid: lista[i],
          sn: "",
          addr1: " ",
          addr2: " ",
          testResult: "",
          testDatetime: "",
          checkResult: "",
          checkDatetime: "",
          checkCount: "",
          orderId: this.orderid,
        };
        datalist4 = lista[i];
        datalist2.push(datalist);
        datalist3.push(datalist4);
        // console.log("12", datalist2);  得到的数组
      }
      //1
      this.refile(datalist2);
      //2
      // this.filehas(datalist3);
    },
    // 第一方法 执行成功
    refile(e) {
      let url =
        this.radio == 1
          ? "http://localhost:8081/receivefile"
          : this.radio == 2
          ? "http://localhost:8081/tyreceivefile"
          : "http://localhost:8081/xjlist";

      this.$axios
        .post(url, e, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
          if (res.data.code == 200) {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          console.error(err);
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
        });
    },
    //第二套方法 可行
    filehas(e) {
      let url =
        this.radio == 1
          ? "http://localhost:8081/receivefilehas"
          : this.radio == 2
          ? "http://localhost:8081/tyreceivefilehas"
          : "http://localhost:8081/xjreceivefilehas";
      let params = {
        savefiles: e,
        id: this.orderid,
      };

      this.$axios
        .post(url, params, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          if (res.data.code == 200) {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          console.error(err);
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
        });
    },
    btns() {
      let flie = this.name;
      let orderid = this.orderid;

      if (!flie || !orderid) {
        this.$message({
          message: "请先上传文件！或者orderid为空",
          showClose: true,
          type: "error",
        });
        return;
      }

      this.$refs.upload.submit();
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
  display: flex;
  justify-content: center;
  height: 100%;
}
.content {
  justify-content: center;
  align-items: center;
  text-align: center;
  display: flex;
  flex-flow: column;
  >>> .el-form-item {
    margin: 0;
  }
}
.upload-demo {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 50px;
}
</style>
