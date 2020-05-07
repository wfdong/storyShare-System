<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <div class="createPost-main-container">
        <el-row>
          <el-col :span="24">
            <el-form-item style="margin-bottom: 40px;" prop="title">
              <MDinput v-model="postForm.title" :maxlength="100" name="name" required>标题</MDinput>
            </el-form-item>

            <div class="postInfo-container"></div>
          </el-col>
        </el-row>

        <el-form-item prop="content" style="margin-bottom: 30px;">
          <Tinymce ref="editor" v-model="postForm.content" :height="400" />
        </el-form-item>

        <el-button
          v-loading="loading"
          style="margin-left: 10px;"
          type="success"
          @click="submitForm"
        >提交</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import Tinymce from "@/components/Tinymce";
import Upload from "@/components/Upload/SingleImage3";
import MDinput from "@/components/MDinput";
import Sticky from "@/components/Sticky"; // 粘性header组件
import { validURL } from "@/utils/validate";
import { fetchArticle } from "@/api/article";
import { searchUser } from "@/api/remote-search";
import { addArticle, updateArticle, listArticle } from "@/api/smallloan";

const defaultForm = {
  status: "draft",
  title: "", // 文章题目
  content: "", // 文章内容
  id: undefined,
  author: "noone",
  platforms: ["a-platform"],
  comment_disabled: false,
  importance: 0
};

export default {
  name: "ArticleDetail",
  components: { Tinymce, MDinput, Upload, Sticky },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === "") {
        this.$message({
          message: rule.field + "为必传项",
          type: "error"
        });
        callback(new Error(rule.field + "为必传项"));
      } else {
        callback();
      }
    };
    const validateSourceUri = (rule, value, callback) => {
      if (value) {
        if (validURL(value)) {
          callback();
        } else {
          this.$message({
            message: "外链url填写不正确",
            type: "error"
          });
          callback(new Error("外链url填写不正确"));
        }
      } else {
        callback();
      }
    };
    return {
      postForm: Object.assign({}, defaultForm),
      loading: false,
      userListOptions: [],
      rules: {
        image_uri: [{ validator: validateRequire }],
        title: [{ validator: validateRequire }],
        content: [{ validator: validateRequire }],
        source_uri: [{ validator: validateSourceUri, trigger: "blur" }]
      },
      tempRoute: {},
      isEdit: false
    };
  },
  computed: {
    contentShortLength() {
      return this.postForm.content_short.length;
    },
    lang() {
      return this.$store.getters.language;
    },
    displayTime: {
      // set and get is useful when the data
      // returned by the back end api is different from the front end
      // back end return => "2013-06-25 06:59:25"
      // front end need timestamp => 1372114765000
      get() {
        return +new Date(this.postForm.display_time);
      },
      set(val) {
        this.postForm.display_time = new Date(val);
      }
    }
  },
  created() {
    const id = this.$route.query && this.$route.query.id;
    console.log("id:" + id);
    if (id != null) {
      this.fetchData(id);
      this.isEdit = true;
      this.postForm.id = id;
    } else {
      this.postForm = Object.assign({}, defaultForm);
    }

    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route);
  },
  methods: {
    fetchData(id) {
      var query = {};
      query.id = id;
      listArticle(query)
        .then(response => {
          console.log("data:" + JSON.stringify(response.data));
          if (response.data.total == 0) {
            this.$message({
              message: "文章ID错误",
              type: "error"
            });
            this.$router.push({ path: "/articleManage/articleList" });
            return;
          }
          this.postForm = response.data.data[0];
          this.postForm.gmtModified = null
        })
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = this.lang === "zh" ? "编辑文章" : "Edit Article";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.id}`
      });
      this.$store.dispatch("tagsView/updateVisitedView", route);
    },
    setPageTitle() {
      const title = "Edit Article";
      document.title = `${title} - ${this.postForm.id}`;
    },
    submitForm() {
      console.log(this.postForm);
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.isEdit) {
            updateArticle(this.postForm)
              .then(response => {
                console.log("response:" + JSON.stringify(response.data.data));

                this.list = response.data.data;
                this.total = response.data.total;

                this.loading = false;
                this.$notify({
                  title: "成功",
                  message: "更新文章成功",
                  type: "success",
                  duration: 2000
                }).then(
                  this.$router.push({ path: "/articleManage/articleList" })
                );
              })
              .catch(error => {
                this.loading = false;
                // reject(error)
              });
          } else {
            addArticle(this.postForm)
              .then(response => {
                console.log("response:" + JSON.stringify(response.data.data));

                this.list = response.data.data;
                this.total = response.data.total;

                this.loading = false;
                this.$notify({
                  title: "成功",
                  message: "发布文章成功",
                  type: "success",
                  duration: 2000
                }).then(
                  this.$router.push({ path: "/articleManage/articleList" })
                );
              })
              .catch(error => {
                this.loading = false;
                // reject(error)
              });
          }

          this.postForm.status = "published";
          this.loading = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    draftForm() {
      if (
        this.postForm.content.length === 0 ||
        this.postForm.title.length === 0
      ) {
        this.$message({
          message: "请填写必要的标题和内容",
          type: "warning"
        });
        return;
      }
      this.$message({
        message: "保存成功",
        type: "success",
        showClose: true,
        duration: 1000
      });
      this.postForm.status = "draft";
    },
    getRemoteUserList(query) {
      searchUser(query).then(response => {
        if (!response.data.items) return;
        this.userListOptions = response.data.items.map(v => v.name);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";

.createPost-container {
  position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;

      .postInfo-container-item {
        float: left;
      }
    }
  }

  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
}

.article-textarea /deep/ {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}
</style>
