<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

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
      default: '350px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    // chartData: {
    //   type: Object,
    //   required: true
    // },
    'lineData': [Object]
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    // lineData(newV) {
    //     this.initChart();
    // }
    lineData: {
      deep: true,
      handler(val) {
        console.log("val:" + JSON.stringify(val))
        if (this.chart) {
          this.chart.dispose()
        }
    
        this.initChart()
        // this.setOptions(val)
      }
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
      this.chart = echarts.init(this.$el, 'macarons')
      console.log("lineData:" + JSON.stringify(this.lineData))
      this.setOptions(this.lineData)
    },
    setOptions({ tmpLineData } = {}) {
      this.chart.setOption({
        xAxis: {
          data: this.lineData.xAxisData,
          // data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月'],
          boundaryGap: false,
          axisTick: {
            show: false
          }
        },
        grid: {
          left: 10,
          right: 10,
          bottom: 20,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          axisTick: {
            show: false
          }
        },
        legend: {
          data: ['申请次数', '申请金额']
        },
        series: [{
          name: '申请次数', itemStyle: {
            normal: {
              color: '#FF005A',
              lineStyle: {
                color: '#FF005A',
                width: 2
              }
            }
          },
          smooth: true,
          type: 'line',
          data: this.lineData.applyCountData,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        },
        {
          name: '申请金额',
          smooth: true,
          type: 'line',
          itemStyle: {
            normal: {
              color: '#3888fa',
              lineStyle: {
                color: '#3888fa',
                width: 2
              },
              areaStyle: {
                color: '#f3f8ff'
              }
            }
          },
          data: this.lineData.applyAmountData,
          animationDuration: 2800,
          animationEasing: 'quadraticOut'
        }
        ]
      })
    }
  }
}
</script>
