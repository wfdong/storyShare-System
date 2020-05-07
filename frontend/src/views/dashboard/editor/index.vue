<template>
  <div class="app-container">
    <!--el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      stripe
      highlight-current-row
      style="width: 100%;"
    >
    <el-table-column label="" min-width="15%" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.jokeTable.username }}</span>
            </template>
      </el-table-column>
    </el-table-->

    <el-row>
      <el-col>
        <span>Share a story </span>
        <a>
          <i @click="addNewJokes" class="el-icon-edit" style="font-size: 25px;"></i>
        </a>
      </el-col>
      <el-col align=right>
        <el-select v-model="listQuery.rank" @change="selectGet" default-first-option placeholder="rank by">
          <el-option label="by time" value=0></el-option>
          <el-option label="by hot" value=1></el-option>
        </el-select>
      </el-col>
    </el-row>
    <el-row v-for="curitem in list" :key="list.jokeTable">
    <!--slot v-for="curitem in list" :key="curitem.jokeTable"-->
      <el-row type="flex" class="row-bg" justify="center">
        <el-col :span="4">
          <div>
              <span >{{ curitem.jokeTable.username }}</span>
          </div>
        </el-col>

        <el-col :span="20">
           <div>
                <el-row>
                  <viewer :images="curitem.jokeTable.filelocation">
                    <img v-for="curimg in curitem.jokeTable.filelocation" :src="curimg"  crossorigin="anonymous" height="150">
                  </viewer>
                </el-row>
                <el-row>
                  <!--el-input type="textarea" v-model="curitem.jokeTable.content" :autosize="{ minRows: 3, maxRows: 10}" :disabled="true" :readonly="true">
                  </el-input-->
                  <p >{{curitem.jokeTable.content}}</p>
                  <!--Tinymce ref="editor" style="width: 760px;" v-model="curitem.jokeTable.content" :height="400" /-->
                </el-row>
                <el-row>
                  <el-col :span="2">
                   <div v-if="addLikeFlag==1 && addLikeTid==curitem.jokeTable.tid">
                    <el-badge>
                     <el-button  type="primary" align="top" size="mini" icon="el-icon-loading" circle></el-button>
                    </el-badge>
                   </div>
                   <div v-else>
                    <a @click="addALike(curitem.jokeTable)"><img height=25px width=25px src="../../../assets/laugh.png">{{ curitem.jokeTable.liked }}</a>
                   </div>
                  </el-col>

                  <el-col :span="2">
                    <!--a v-if="addDislikeFlag==0 || (addDislikeFlag==1 && addDisLikeTid!==curitem.jokeTable.tid)" @click="addADisLike(curitem.jokeTable)"><img height=25px width=25px src="../../../assets/cry.png">{{ curitem.jokeTable.disliked }}</a-->
                    <div v-if="addDislikeFlag==1 && addDisLikeTid==curitem.jokeTable.tid">
                      <el-badge  >
                       <el-button  type="primary" align="top" size="mini" icon="el-icon-loading" circle></el-button>
                      </el-badge>
                    </div>
                    <div v-else>
                      <a  @click="addADisLike(curitem.jokeTable)"><img height=25px width=25px src="../../../assets/cry.png">{{ curitem.jokeTable.disliked }}</a>
                    </div>
                  </el-col>

                  <el-col :span="14">
                    <!--i align="right" @click="addNewJokes" class="el-icon-s-comment" style="font-size: 15px;">{{curitem.jokeComments.length}}</i-->
                    <!--i align="right" @click="addNewJokes" class="el-icon-reading" style="font-size: 15px;">details</i-->
                    
                    <el-badge v-if="curitem.jokeComments.length>0" :value="curitem.jokeComments.length" :max="10"  class="item">
                        <!--el-button type="primary" size="mini" icon="el-icon-s-comment"></el-button-->
                        <el-button type="primary" @click="getCommentsPage(curitem)" size="mini" icon="el-icon-s-comment" circle></el-button>
                        <!--i align="right"  class="el-icon-s-comment" style="font-size: 15px;"></i-->
                    </el-badge>
                    <el-badge v-if="curitem.jokeComments.length<=0" >
                     <el-button  type="primary" align="top" @click="getCommentsPage(curitem)" size="mini" icon="el-icon-s-comment" circle></el-button>
                    </el-badge>
                  </el-col>
                  
                  <el-col :span="6" align=right >
                    <span align=right>
                       {{formatDate(curitem.jokeTable.uploadtime)}}&nbsp;
                    </span>
                  </el-col>
                </el-row>
           </div>
        </el-col>
      </el-row>
      <el-row style="margin-top:-5px">
        <div v-if="curitem.jokeTable.liked==0">
            <el-rate align="right"
              v-model="curitem.jokeTable.liked"
              disabled
              allow-half
              show-score
              text-color="#ff9900"
              score-template=""> 
            </el-rate>
        </div>
        <div v-if="curitem.jokeTable.liked<=10 && curitem.jokeTable.liked>0">
            <el-rate align="right"
              v-model="curitem.jokeTable.liked / curitem.jokeTable.liked"
              disabled
              allow-half
              show-score
              text-color="#ff9900"
              score-template=""> 
            </el-rate>
        </div>
        <div v-if="curitem.jokeTable.liked>=50">
            <el-rate align="right"
              v-model="curitem.jokeTable.liked / curitem.jokeTable.liked * 5"
              disabled
              allow-half
              show-score
              text-color="#ff9900"
              score-template=""> 
            </el-rate>
        </div>
        <div v-if="curitem.jokeTable.liked>10 && curitem.jokeTable.liked<50">
            <el-rate align="right"
              v-model="curitem.jokeTable.liked / 50"
              disabled
              allow-half
              show-score
              text-color="#ff9900"
              score-template=""> 
            </el-rate>
        </div>
        
      </el-row>
      <el-divider content-position="left"></el-divider>
    <!--/slot-->
    </el-row>
    
    <el-pagination style="margin-top:10px" background @size-change="handleSizeChange" @current-change="handleCurrentPage"
        :current-page="listQuery.page" :page-sizes="[5, 10, 20, 30, 50]" :page-size="listQuery.limit"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>
  


    <el-dialog title="publish a new story" :visible.sync="dialogFormVisible" width="50%" :modal-append-to-body=false>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label-width="120px" label="picture:" class="postInfo-container-item">
      <el-upload
                  accept="image/jpeg,image/jpg,image/gif,image/png"
                  class="upload-demo"
                  :action="uploadUrl"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :on-success="handleUploadSuccessList"
                  :with-credentials=true
                  :file-list="displayFileList"
                  list-type="picture">
                  <el-button size="mini" type="primary">+</el-button>
                  <div slot="tip" class="el-upload__tip">(Only photos，no more than 500kb)</div>
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

  </div>
  
</template>

<script>
import { addAJoke,addALike,addADisLike} from '@/api/jokes'
import { dynamicList} from '@/api/jokesInternet'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import store from '../../../store'
import Tinymce from "@/components/Tinymce";
import MDinput from "@/components/MDinput";
import Vue from "vue";
import Viewer from "v-viewer";
import "viewerjs/dist/viewer.css";
//import {delCookie} from '@/api/util';
//import Cookies from 'js-cookie'
import moment from 'moment'
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
      total: 0,
      listLoading: true,
      addLikeFlag:0,
      addDislikeFlag:0,
      addLikeTid:0,
      addDisLikeTid:0,
      uploadUrl:process.env.VUE_APP_BASE_API + "/rest/v1/file/preview",
      displayFileList:[],
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
    this.getList()
  },
  activated(){
    this.getList()
  },
  methods: {
    getList() {
      /*
      if(this.$store.getters.token === undefined){
        //delCookie("joke-auth-token");
        //delCookie("JSESSIONID");
        Cookies.remove('joke-auth-token')
        Cookies.remove('JSESSIONID')
      }*/
      this.listLoading = true
      dynamicList(this.listQuery).then(response => {
          console.log("response:" + JSON.stringify(response.data));

        this.list = response.data.data
        this.total = response.data.total
        
        if(this.list != null){
            for(var i=0;i<this.list.length;i++){
              var imgs = [];
              if(this.list[i] != null && this.list[i].userFiles != null){
                for (var j=0; j<this.list[i].userFiles.length;j++){
                   imgs.push(process.env.VUE_APP_BASE_API +
                        "/image/" + this.list[i].userFiles[j].filePath);
                }
              }
              this.list[i].jokeTable.filelocation=imgs;
            }
        }
        this.listLoading = false
      }).catch(error => {
        this.listLoading = false
        // reject(error)
        
      })
    },
    formatDate(value){
      if (value) {
        return moment(String(value)).format('MM/DD/YYYY hh:mm')
      }
    },
    addALike(currentjokeTable){
      if(this.$store.getters.token === undefined){
        this.$router.push({ path: '/login' });
      }else{
        this.addLikeFlag=1;
        this.addLikeTid=currentjokeTable.tid;
        this.form.tid = currentjokeTable.tid;
        this.form.username = currentjokeTable.username;
        this.form.liked = currentjokeTable.liked + 1;
        addALike(this.form).then(response => {
          console.log("response:" + JSON.stringify(response.data));

        var responseliked = response.data.data
        for(var i=0;i<this.list.length;i++){
          if(currentjokeTable.tid == this.list[i].jokeTable.tid){
            this.list[i].jokeTable.liked = responseliked;
          }
        }
        this.addLikeFlag=0;
        this.addLikeTid=0;
        //this.total = response.data.total
        currentjokeTable.liked = responseliked;
        this.listLoading = false
        }
        ).catch(error => {
          this.addLikeFlag=0;
          this.addLikeTid=0;
        this.listLoading = false
      });
      }
        
    },
    addADisLike(currentjokeTable){
      if(this.$store.getters.token === undefined){
        this.$router.push({ path: '/login' });
      }else{
        this.addDislikeFlag=1;
        this.addDisLikeTid=currentjokeTable.tid;
        this.form.tid = currentjokeTable.tid;
        this.form.username = currentjokeTable.username;
        this.form.disliked = currentjokeTable.disliked + 1;
        addADisLike(this.form).then(response => {
          console.log("response:" + JSON.stringify(response.data));

        var responseDisliked = response.data.data
        for(var i=0;i<this.list.length;i++){
          if(currentjokeTable.tid == this.list[i].jokeTable.tid){
            this.list[i].jokeTable.disliked = responseDisliked;
          }
        }
        currentjokeTable.disliked = responseDisliked;
        //this.total = response.data.total
        this.addDislikeFlag=0;
        this.addDisLikeTid=0;
        this.listLoading = false
        }
        ).catch(error => {
          this.addDislikeFlag=0;
          this.addDisLikeTid=0;
        this.listLoading = false
       });
      }
    },
    selectGet(){
        this.getList();
    },
    getCommentsPage(item){
        this.$router.push({ path: '/dashboard/detailsPage', query:{detailrecord: item.jokeTable.tid} })
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
            //clear data....
            this.form.tid=null;
            this.form.username=null;
            this.form.content=null;
            this.form.liked=null;
            this.form.disliked=null;
            this.form.commentsnum= null;
            this.form.filelocation= "";
            this.displayFileList=[];
            this.uploadFileList_local=[];
        }).catch(error => {
        });
        this.dialogFormVisible = false
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
      this.listQuery.limit = val
      this.getList()
    },
    handleCurrentPage(val) {
      this.listQuery.page = val
      this.getList()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
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