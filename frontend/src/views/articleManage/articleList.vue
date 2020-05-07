<template>
  <div class="app-container">
    <div class="filter-container">
      <el-row>

      </el-row>
        <el-button v-waves style="margin-left:25px" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
       新增政策
        </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      stripe
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
    <el-table-column label="文章标题" min-width="60%" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="发布日期" min-width="20%" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.gmtModified }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" min-width="20%" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="large" type="success" @click="handleModifiy(scope.row)">
            修改
          </el-button>
          <el-button size="large" type="success" @click="handleDelete(scope.row)">
            刪除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination style="margin-top:10px" background @size-change="handleSizeChange" @current-change="handleCurrentPage"
    :current-page="listQuery.page" :page-sizes="[10, 20, 30, 50]" :page-size="listQuery.limit"
    layout="total, sizes, prev, pager, next, jumper" :total="total">
  </el-pagination>
  </div>
</template>

<script>
import { listArticle, deleteArticle } from '@/api/smallloan'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

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
  name: 'ComplexTable',
  components: { Pagination },
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
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      options: [],
      options_display: [],
      listQuery: {
        page: 1,
        limit: 20,
        applyName: undefined,
        title: undefined,
        currentStep: 3,
        applyName: undefined,
        applyPersonId:null,
        adminRegion: undefined,
        queryStartApplyDate:null,
        queryEndApplyDate:null,
      },
      regionList:[],
      regionInsMap:{},
      bankSelectList:[],
      importanceOptions: [1, 2, 3],
      calendarTypeOptions,
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      temp: {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        type: '',
        status: 'published'
      },
      form: {
        tid:null,
        checkPeopleList:null,
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }],
        checkPeopleList: [
          { required: true, message: "该字段不能为空", trigger: "blur" }
        ],
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    regionChange(data) {
        this.bankSelectList = this.regionInsMap[data]
    },
    getList() {
      this.listLoading = true
      listArticle(this.listQuery).then(response => {
          // console.log("response:" + JSON.stringify(response.data.data));

        this.list = response.data.data
        this.total = response.data.total

        this.listLoading = false
      }).catch(error => {
        this.listLoading = false
        // reject(error)
        
      })
    },
    handleModifiy(row) {
      console.log("before id:" + row.id + JSON.stringify(row))
      //{ path: '/investigateinfo/investigateInfoDetail', query:{tid: row.tid} }
      this.$router.push({ path: '/articleManage/viewDetail', query:{id: row.id}})
    },
    handleDelete(row) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            row.gmtModified = null
            deleteArticle(row).then(response => {
                console.log("response:" + JSON.stringify(response.data.data));
                this.$message({
                  message: '删除成功',
                  type: 'success'
                })
                this.getList()
              })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });          
        });
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
      this.$router.push({ path: '/articleManage/viewDetail'})
      // this.getList()
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
          this.temp.author = 'vue-element-admin'
          createArticle(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          updateArticle(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.temp.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.temp)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '更新成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData
        this.dialogPvVisible = true
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
        const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
        const data = this.formatJson(filterVal, this.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
