<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    },
    'barData': [Object]
    // 'xAxisData': [Array],
    // 'realData': [Array]
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      console.log("axisx:" + this.xAxisData)
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        title:{
          text:this.barData.colName,
          left:"center",
                textStyle: {
                    color: '#3498DB',
                    fontSize: 15
                },
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: this.barData.xAxisData,
          axisTick: {
            alignWithLabel: true
          }
        }],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: [{
          // name: this.barData.colName,
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: this.barData.realData,
          animationDuration,
          color: [ '#2ECC71','#3498DB','#9B59B6','#34495E','#F39C12','#7F8C8D'],
        }]
      })
    }
  },
  watch: {
      barData(newV) {
        this.initChart(newV);
      }
    }
}
</script>
