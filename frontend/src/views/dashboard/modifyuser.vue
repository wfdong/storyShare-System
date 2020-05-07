<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <span>Share a story </span>
        <a>
          <i @click="addNewJokes" class="el-icon-edit" style="font-size: 25px;"></i>
        </a>
      </el-col>
    </el-row>
    <el-row type="flex" class="row-bg" justify="center" v-if="currentitem != null && currentitem.jokeTable !== null" :key="currentitem.jokeTable">
        <el-col :span="4">
          <div>
              <span >{{ currentitem.jokeTable.username }}</span>
          </div>
        </el-col>

        <el-col :span="20">
           <div>
                <el-row>
                  <viewer :images="currentitem.jokeTable.filelocation">
                    <img v-for="curimg in currentitem.jokeTable.filelocation" :src="curimg" crossorigin="anonymous"  height="150">
                  </viewer>
                </el-row>
                <el-row>
                  <!--span>{{ currentitem.jokeTable.content }}</span-->
                  <el-input type="textarea" v-model="currentitem.jokeTable.content" :autosize="{ minRows: 3, maxRows: 10}" :disabled="true" :readonly="true">
                  </el-input>
                  <!--Tinymce ref="editor" style="width: 760px;" v-model="currentitem.jokeTable.content" :height="400" /-->
                </el-row>
                <el-row>
                    <a @click="addALike(currentitem.jokeTable)"><img height=25px width=25px src="../../assets/laugh.png">{{ currentitem.jokeTable.liked }}</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a @click="addADisLike(currentitem.jokeTable)"><img height=25px width=25px src="../../assets/cry.png">{{ currentitem.jokeTable.disliked }}</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <el-button type="primary" @click="addAnewComment(currentitem)" size="mini" icon="el-icon-s-comment" round>+new</el-button>
                </el-row>
           </div>
        </el-col>
      </el-row>
      <el-row style="margin-top:-5px">
        <div v-if="currentitem.jokeTable.liked<=10 && currentitem.jokeTable.liked>0">
            <el-rate align="right"
              v-model="currentitem.jokeTable.liked / currentitem.jokeTable.liked"
              disabled
              allow-half
              show-score
              text-color="#ff9900"
              score-template=""> 
            </el-rate>
        </div>
        <div v-if="currentitem.jokeTable.liked>=50">
            <el-rate align="right"
              v-model="currentitem.jokeTable.liked / currentitem.jokeTable.liked * 5"
              disabled
              allow-half
              show-score
              text-color="#ff9900"
              score-template=""> 
            </el-rate>
        </div>
        <div v-if="currentitem.jokeTable.liked>10 && currentitem.jokeTable.liked<50">
            <el-rate align="right"
              v-model="currentitem.jokeTable.liked / 50"
              disabled
              allow-half
              show-score
              text-color="#ff9900"
              score-template=""> 
            </el-rate>
        </div>
        
      </el-row>
    </el-row>
    <el-divider content-position="left"></el-divider>

    <el-row v-for="curcomment in jokeCommentsDisplay" :key="jokeCommentsDisplay">
        <el-row type="flex" class="row-bg" justify="center">
          <el-col :span="4">
              <div>
                <span >{{ curcomment.username }}</span>
            </div>
          </el-col>
          <el-col :span="20">
             <!--div>
                 <el-input type="textarea" v-model="curcomment.comment" :autosize="{ minRows: 2, maxRows: 10}" :disabled="true" :readonly="true"/>
             </div-->
             <el-form>
             <el-form-item label="">
                 <el-input type="textarea" v-model="curcomment.comment" :autosize="{ minRows: 2, maxRows: 10}" :resize="none" :disabled="true" :readonly="true"></el-input>
             </el-form-item>
             </el-form>
          </el-col>
        </el-row>
    </el-row>
    
    <el-pagination style="margin-top:10px" background @size-change="handleSizeChange" @current-change="handleCurrentPage"
          :current-page="listQuery.page" :page-sizes="[5, 10, 20, 30, 50]" :page-size="listQuery.limit"
          layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>
    
  


    <el-dialog title="publish a new story" :visible.sync="dialogFormVisible" width="50%" :modal-append-to-body=false>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label-width="120px" label="picture:" class="postInfo-container-item">
      <el-upload
                  class="upload-demo"
                  :action="uploadUrl"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :on-success="handleUploadSuccessList"
                  :with-credentials=true
                  :file-list="displayFileList"
                  list-type="picture">
                  <el-button size="mini" type="primary">+</el-button>
                  <div slot="tip" class="el-upload__tip">(Only support jpg/png，no more than 500kb)</div>
      </el-upload>
      </el-form-item>
      <el-form-item label-width="120px" label="content:" class="postInfo-container-item">
          <el-input style="width: 100%; " type="textarea" :rows=4 v-model.number="form.content">
          </el-input>
          <!--div style="width: 100%;" v-html="form.content"></div-->
      </el-form-item>
      <el-form-item label="" style="margin-top:30px;">
        <el-button type="primary" @click="onAllocate">submit</el-button>
        <el-button @click="dialogFormVisible = false">cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>


  <el-dialog title="Add a new comment" :visible.sync="dialogCommentsFormVisible" width="50%" :modal-append-to-body=false>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label-width="120px" label="" class="postInfo-container-item">
          <el-input style="width: 100%; " type="textarea" :rows=4 v-model.number="form.newAddedComment">
          </el-input>
          <!--div style="width: 100%;" v-html="form.newAddedComment"></div-->
      </el-form-item>
      <el-form-item label="" style="margin-top:30px;">
        <el-button type="primary" @click="submitNewComment">submit</el-button>
        <el-button @click="dialogCommentsFormVisible = false">cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  </div>
  
</template>

<script>
import { addAJoke,addALike,addADisLike,submitMyNewComment} from '@/api/jokes'
import { dynamicList,getSpecificItem} from '@/api/jokesInternet'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import store from '../../store'
import Tinymce from "@/components/Tinymce";
import MDinput from "@/components/MDinput";
import Vue from "vue";
import Viewer from "v-viewer";
import "viewerjs/dist/viewer.css";
Vue.use(Viewer);

const calendarTypeOptions = [
  { key: 'CN', display_name: 'China' },
  { key: 'US', display_name: 'USA' },
  { key: 'JP', display_name: 'Japan' },
  { key: 'EU', display_name: 'Eurozone' }
]

// arr to obj, such as { CN : "China", US : "USA" }
const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: "ComplexTable",
  components: { Tinymce,MDinput,Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type]
    },
    getStep(step) {
      return getStepName(step)
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      currentitem:null,
      tid:null,
      total: 0,
      listLoading: true,
      uploadUrl:process.env.VUE_APP_BASE_API + "/rest/v1/file/preview",
      displayFileList:[],
      jokeCommentsDisplay:[],
      listQuery: {
        page: 1,
        limit: 10,
        rank: null, //0,
      },
      regionList:[],
      regionInsMap:{},
      bankSelectList:[],
      importanceOptions: [1, 2, 3],
      calendarTypeOptions,
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      dialogFormVisible: false,
      dialogCommentsFormVisible : false,
      dialogStatus: '',
      showSummary:true,
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      summary : {
        // shendaiTotal:1234,
        // fangdaiTotal:2345
      },
      uploadFileList_local: [],
      form: {
        tid:null,
        username:null,
        content:null, 
        liked:null,
        disliked:null,
        commentsnum: null,
        filelocation: "",
        newAddedComment:null,
      },
      rules: {
        content: [{ required: true, message: 'content is required', trigger: 'change' }]
      },
      dialogFormVisible: false,
      dialogPvVisible: false,
      pvData: [],
      downloadLoading: false
    }
  },
  created() {
      this.tid = this.$route.query && this.$route.query.detailrecord;
      //this.total = this.currentitem.jokeComments.length
      this.getList()
  },
  activated(){
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      this.form.tid = this.tid;
      getSpecificItem(this.form).then(response => {
          console.log("response:" + JSON.stringify(response.data));
          this.currentitem = response.data.data;
          var imgs = [];
              if(this.currentitem != null && this.currentitem.userFiles != null){
                for (var j=0; j<this.currentitem.userFiles.length;j++){
                   imgs.push(process.env.VUE_APP_BASE_API +
                        "/image/" + this.currentitem.userFiles[j].filePath);
                }
              }
          this.currentitem.jokeTable.filelocation=imgs;
          this.total = this.currentitem.jokeComments.length;
          this.getCurrentPageComments();
          this.listLoading = false
      }).catch(error => {
        this.listLoading = false
        // reject(error)
        
      })
    },
    getCurrentPageComments(){
        this.jokeCommentsDisplay = [];
        var page = this.listQuery.page;
        var pageSize = this.listQuery.limit;
        for(var i=(page-1)*pageSize;i<page*pageSize && i<this.currentitem.jokeComments.length;i++){
            this.jokeCommentsDisplay.push(this.currentitem.jokeComments[i]);
        }
    },
    addALike(currentjokeTable){
      if(this.$store.getters.token === undefined){
        this.$router.push({ path: '/login' });
      }else{
        this.form.tid = currentjokeTable.tid;
        this.form.username = currentjokeTable.username;
        this.form.liked = currentjokeTable.liked + 1;
        addALike(this.form).then(response => {
          console.log("response:" + JSON.stringify(response.data));
          var responseliked = response.data.data
          currentjokeTable.liked = responseliked;
          this.currentitem.jokeTable.liked = responseliked;
          this.listLoading = false
          }
        ).catch(error => {
          this.listLoading = false
       });
      }
    },
    addADisLike(currentjokeTable){
      if(this.$store.getters.token === undefined){
        this.$router.push({ path: '/login' });
      }else{
        this.form.tid = currentjokeTable.tid;
        this.form.username = currentjokeTable.username;
        this.form.disliked = currentjokeTable.disliked + 1;
        addADisLike(this.form).then(response => {
          console.log("response:" + JSON.stringify(response.data));
          var responseDisliked = response.data.data
          currentjokeTable.disliked = responseDisliked;
          this.currentitem.jokeTable.disliked = responseDisliked;
          this.listLoading = false
          }
        ).catch(error => {
          this.listLoading = false
       });
      }
    },
    addAnewComment(currentitem){
      if(this.$store.getters.token === undefined){
        this.$router.push({ path: '/login' });
      }else{
        this.dialogCommentsFormVisible = true;
        this.form.tid = currentitem.jokeTable.tid;
      }
    },
    submitNewComment(){
        var page = this;
        if(this.form.newAddedComment != null){
            submitMyNewComment(this.form).then(response => {
                this.currentitem = response.data.data;
                this.total = this.currentitem.jokeComments.length;
                this.$notify({
                  title: 'success',
                  message: 'success',
                  type: 'success',
                  duration: 2000
                });
            console.log("response:" + JSON.stringify(response.data.data));
            this.form.newAddedComment = null;
            page.getList();
        }).catch(error => {
        });
        }
        this.dialogCommentsFormVisible = false;
    },
    selectGet(){
        this.getList();
    },
    getCommentsPage(item){
        this.$router.push({ path: '/dashboard/editor/detailsPage', query:{detailrecord: item} })
    },
    onAllocate() {
      var page = this;

      if(this.uploadFileList_local != null){
        for(var i=0 ; i<this.uploadFileList_local.length;i++){
          if(i==0){
            this.form.filelocation = this.form.filelocation + this.uploadFileList_local[i].filePath;
          }else{
            this.form.filelocation = this.form.filelocation + '|' + this.uploadFileList_local[i].filePath;
          }
        }
      }
      //if(this.form.uploadFileList != ""){
      //  this.form.uploadFileList = this.form.uploadFileList + ']';
      //}
      
      var value1 = this.form.content;
      var value2 = this.form.filelocation;
      console.log("we got content: " + value1);
      console.log("we got uploadFileList: " + value2);

      value1 = value1 + "";
      if (value1 != null && value1.length>0 && value1.length <= 2000) {
        addAJoke(this.form).then(response => {
            this.$notify({
                  title: 'success',
                  message: 'success',
                  type: 'success',
                  duration: 2000
                });
            console.log("response:" + JSON.stringify(response.data.data));
            page.getList();
        }).catch(error => {
        });
        this.dialogFormVisible = false
        //clear data....
           // this.form.tid=null;
            //this.form.content=null;
            //this.form.filelocation="";
            //this.uploadFileList_local=[];
            //page.getList();
            //location.reload();
      } else {
            console.log("invalid input");
            this.$message({
            message: 'input error!',
            type: 'error'
          })
      }
    },
    addNewJokes(){
      if(this.$store.getters.token === undefined){
        this.$router.push({ path: '/login' });
      }else{
        this.dialogFormVisible = true;
      }
    },
    handlePreview(file) {
        console.log("preview:" + file);
      },
    handleUploadSuccessList(data, file) {
        console.log("successList:" + JSON.stringify(data))
        //console.log(JSON.stringify(this.form.uploadFileList))
        console.log(JSON.stringify(this.uploadFileList_local))
        var obj = {};
        obj.filePath = data.data.content;
        this.uploadFileList_local.push(obj);
        //this.form.uploadFileList.push(obj);
    },
      handleRemove(file, fileList) {
        console.log("file:" + JSON.stringify(file), fileList);
        console.log("fileList:" + JSON.stringify(fileList))
        //this.form.uploadFileList = []
        this.uploadFileList_local = []
        var i = 0
        for(i = 0; i < fileList.length; i++) {
          if(fileList[i].response == null) {
            continue;
          }
          var obj = {};
          obj.filePath = fileList[i].response.data.content
          this.uploadFileList_local.push(obj);
        }
      },
    handleSizeChange(val) {
      this.listQuery.limit = val;
      //this.getList()
      this.getCurrentPageComments();
    },
    handleCurrentPage(val) {
      this.listQuery.page = val;
      //this.getList()
      this.getCurrentPageComments();
    },
    handleFilter() {
      this.listQuery.page = 1
      //this.getList()
    },
    handleModifyStatus(row, status) {
      // let routeData = this.$router.resolve({
      //     name: "/standingBookDetailAudit",
      //     // query: {
      //     //   bar: marbleUrl
      //     // },
      //   });
      //   window.open("/standingBookDetailAudit", '_blank');
      //this.$router.push({ path: '/standingBookAudit/standingBookDetailAudit', query:{tid: row.tid, tt:"23"} })
      if(row.recordType==0){
          this.$router.push({ path: '/statistics/viewSignalDetail', query:{tid: row.tid, tt:"24"} })
      }
      if(row.recordType==1){
          this.$router.push({ path: '/statistics/viewSignalDetailCompany', query:{tid: row.tid, tt:"24"} })
      }
      if(row.recordType==2){
          this.$router.push({ path: '/statistics/viewSignalDetailSmallCompay', query:{tid: row.tid, tt:"24"} })
      }
      // this.$message({
      //   message: '操作成功',
      //   type: 'success'
      // })
      // row.status = status
    },
  }
}
</script>

<style lang="scss" scoped>
.el-row {
    margin-top: 20px;
    margin-bottom: 1px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
  }
  .el-main {
    background-color: #E9EEF3;
    color: #333;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

  </style>