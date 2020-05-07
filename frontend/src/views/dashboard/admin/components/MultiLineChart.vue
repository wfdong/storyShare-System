<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>
<script>
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
import resize from "./mixins/resize";
export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart"
    },
    width: {
      type: String,
      default: "100%"
    },
    height: {
      type: String,
      default: "450px"
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    // chartData: {
    // type: Object,
    // required: true
    // },
    'barData': [Object]
  },
  data() {
    return {
      chart: null,
    };
  },
  watch: {
      barData(newV) {
        this.initChart(newV);
      }
    },
  mounted() {
    this.$nextTick(() => {
      this.initChart();
    });
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
        console.log("mapData:" +JSON.stringify(this.barData))
      this.chart = echarts.init(this.$el, "macarons");
      // console.log("lineData:" + JSON.stringify(this.lineData))
      this.data = this.barData;
      this.setOptions();
    },
    convertData(data) {
      var res = [];
      for (var i = 0; i < data.length; i++) {
        var geoCoord = this.geoCoordMap[data[i].name];
        if (geoCoord) {
          res.push({
            name: data[i].name,
            value: geoCoord.concat(data[i].value)
          });
        }
      }
      console.log("res:" + JSON.stringify(res))

      return res;
    },
    setOptions() {
      this.chart.setOption({tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['个人申请','合伙经营申请','小微企业申请']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data: this.barData.regionList
            // data : ['周一','周二','周三','周四','周五','周六','周日']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'个人申请',
            type:'bar',
            barWidth : 30,
            stack: '搜索引擎',
            data: this.barData.personList,
            color: [ '#3498DB'],
            // data:[620, 732, 701, 734, 1090, 1130, 1120]
        },
        {
            name:'合伙经营申请',
            type:'bar',
            stack: '搜索引擎',
            data: this.barData.companyList,
            color: [ '#9B59B6','#34495E','#F39C12','#7F8C8D'],
            // data:[120, 132, 101, 134]
        },
        {
            name:'小微企业申请',
            type:'bar',
            stack: '搜索引擎',
            data: this.barData.microList,
            color: [ '#F39C12','#7F8C8D'],
            // data:[60, 72, 71, 74]
        }
    ]});
    }
  }
};
</script>
