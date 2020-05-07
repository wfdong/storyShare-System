<template>
  <div class="dashboard-editor-container">
    <!-- <github-corner class="github-corner" /> -->
    <div class="filter-container">
        <el-date-picker
                          style="width: 180px;"
                          v-model="listQuery.queryStartDate"
                          type="date"
                          placeholder="开始日期"
                          value-format="yyyy-MM-dd"
                          class="filter-item"
                        ></el-date-picker>

        <el-date-picker
                          style="width: 180px;margin-left:5px"
                          v-model="listQuery.queryEndDate"
                          type="date"
                          placeholder="结束日期"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          class="filter-item"
                        ></el-date-picker>

        <el-button style="margin-left:15px" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
          {{ $t('table.search') }}
        </el-button>
      
    </div>
    <panel-group :panelData="panelData" />

    

  </div>
</template>

<script>
import GithubCorner from '@/components/GithubCorner'
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import RaddarChart from './components/RaddarChart'
import PieChart from './components/PieChart'
import BarChart from './components/BarChart'
import TransactionTable from './components/TransactionTable'
import TodoList from './components/TodoList'
import BoxCard from './components/BoxCard'
import { listAmountByRegion,listAmountByRegionAndType } from '@/api/smallloan'
import HotMapChart from './components/HotMapChart'
import MultiLineChart from './components/MultiLineChart'

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'DashboardAdmin',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
    TransactionTable,
    TodoList,
    BoxCard,
    HotMapChart,
    MultiLineChart
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis,
      listQuery: {
        page: 1,
        limit: 20,
        type: undefined,
        currentStep: 2,
        applyName: undefined,
        applyPersonId:null,
        adminRegion: undefined,
        queryStartDate:null,
        queryEndDate:null,
      },
      regionCountData:{},
      regionAmountData:{},
      regionData:['init'],
      panelData:{"xAxisData":[]},
      dayLineChartData:{},
      pieRecordTypeData:{"data":[]},
      regionMapCountData:[],
      regionTypeAmountData:{},
      xueliRecordTypeData:{"data":[]},
      sexRecordTypeData:{"data":[]},
      eduRecordTypeData:{"data":[]},
      ageRecordTypeData:{"data":[]},

    }
  },
  created() {
    // this.regionData.push('2')
    var dateTime=new Date();
    this.listQuery.queryStartDate = new Date(new Date().setMonth(new Date().getMonth()-1));
    this.listQuery.queryEndDate = dateTime
    //this.getList()

  },
  methods: {
    getList() {
      var page = this;
      listAmountByRegion(this.listQuery)
      .then(response => {
        console.log("region:" + JSON.stringify(response.data.data));
        var json = response.data.data
        console.log("str regionData:" + JSON.stringify(json.REGION_LIST.length))

        this.regionCountData = {}
        this.regionCountData.xAxisData = response.data.data.REGION_LIST
        this.regionCountData.realData = response.data.data.REGION_COUNT_LIST
        this.regionCountData.colName = "申请个数"

        var j = 0;
        this.regionMapCountData = []
        for(j = 0; j < this.regionCountData.xAxisData.length; j++) {
          var obj = {};
          obj.name = this.regionCountData.xAxisData[j]
          obj.value = this.regionCountData.realData[j]
          this.regionMapCountData.push(obj)
        }

        this.regionAmountData = {}
        this.regionAmountData.xAxisData = response.data.data.REGION_LIST
        this.regionAmountData.realData = response.data.data.REGION_AMOUNT_LIST
        this.regionAmountData.colName = "申请金额"

        this.panelData = {}
        this.panelData.applyCount = response.data.data.APPLY_COUNT
        this.panelData.loanPlanAmount = response.data.data.LOAN_PLAN_AMOUNT
        this.panelData.bankAmount = response.data.data.BANK_AMOUNT
        this.panelData.jobPersonCount = response.data.data.JOB_PERSION_COUNT

        this.dayLineChartData = {}
        this.dayLineChartData.xAxisData = response.data.data.DAY_KEY_LIST
        this.dayLineChartData.applyCountData = response.data.data.DAY_APPLY_COUNT_LIST
        this.dayLineChartData.applyAmountData = response.data.data.DAY_APPLY_AMOUNT_LIST

        console.log("dayLineChartData:" + JSON.stringify(this.dayLineChartData))

        this.pieRecordTypeData = {}
        var legend = []
        this.pieRecordTypeData.data = response.data.data.RECORD_TYPE_LIST
        var index = 0;
        for(index = 0; index < this.pieRecordTypeData.data.length; index++) {
          legend.push(this.pieRecordTypeData.data[index].name)
        }
        this.pieRecordTypeData.legend = legend

        this.sexRecordTypeData = {}
        let sexLegend = []
        this.sexRecordTypeData.data = response.data.data.SEX_TYPE_LIST
        var index = 0;
        for(index = 0; index < this.sexRecordTypeData.data.length; index++) {
          sexLegend.push(this.sexRecordTypeData.data[index].name)
        }
        this.sexRecordTypeData.legend = sexLegend

        this.eduRecordTypeData = {}
        let eduLegend = []
        this.eduRecordTypeData.data = response.data.data.EDU_TYPE_LIST
        var index = 0;
        for(index = 0; index < this.eduRecordTypeData.data.length; index++) {
          eduLegend.push(this.eduRecordTypeData.data[index].name)
        }
        this.eduRecordTypeData.legend = eduLegend

        this.ageRecordTypeData = {}
        let ageLegend = []
        this.ageRecordTypeData.data = response.data.data.AGE_TYPE_LIST
        var index = 0;
        for(index = 0; index < this.ageRecordTypeData.data.length; index++) {
          ageLegend.push(this.ageRecordTypeData.data[index].name)
        }
        this.ageRecordTypeData.legend = ageLegend

        // this.regionData = response.data.data.REGION_LIST
        // this.regionData = new Array()
        // // this.regionData = response.data.data.REGION_LIST
        // // this.regionData = this.convertStr2Array(json.REGION_LIST)
        // var i = 0;
        // // var arr = [];
        // for(i = 0; i < json.REGION_LIST.length; i++) {
        //   // console.log("type:" + typeof(json.REGION_LIST[i]))

        //   this.regionData.push(json.REGION_LIST[i])
        // }
        console.log("regionCountData:" + JSON.stringify(this.regionCountData))
      })
      .catch(err => {
        console.log(err);
      });

      listAmountByRegionAndType(this.listQuery)
      .then(response => {
        console.log("region2:" + JSON.stringify(response.data.data));
        var json = response.data.data

        this.regionTypeAmountData = {}
        this.regionTypeAmountData.regionList = response.data.data.REGION_LIST
        this.regionTypeAmountData.personList = response.data.data.PERSON_AMOUNT_LIST
        this.regionTypeAmountData.companyList = response.data.data.COMPANY_AMOUNT_LIST
        this.regionTypeAmountData.microList = response.data.data.MICRO_AMOUNT_LIST

        // this.regionCountData.realData = response.data.data.REGION_COUNT_LIST
        // this.regionCountData.colName = "申请个数"

        // var j = 0;
        // this.regionMapCountData = []
        // for(j = 0; j < this.regionCountData.xAxisData.length; j++) {
        //   var obj = {};
        //   obj.name = this.regionCountData.xAxisData[j]
        //   obj.value = this.regionCountData.realData[j]
        //   this.regionMapCountData.push(obj)
        // }
      })
      .catch(err => {
        console.log(err);
      });
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    convertStr2Array(obj) {
      var i = 0;
      var arr = [];
      for(i = 0; i < obj.length; i++) {
        arr.push(obj[i])
      }
      return arr
    },
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
</style>
