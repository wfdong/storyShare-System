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
      default: '300px'
    },
    'pieData': [Object],
    'pieTitle':""
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    // this.$nextTick(() => {
    //   this.initChart()
    // })
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

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        title:{
          text:this.pieTitle,
          left:"center",
                textStyle: {
                    color: '#3498DB',
                    fontSize: 13
                },
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: this.pieData.legend
          // data: ['企业', '个人创业', '劳密']
        },
        calculable: true,
        series: [
          {
            name: '申请类型',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '50%'],
            data:this.pieData.data,
            color: [ '#2ECC71','#3498DB','#9B59B6','#34495E','#F39C12','#7F8C8D'],
            // data: [
            //   { value: 320, name: '企业' },
            //   { value: 240, name: '个人创业' },
            //   { value: 149, name: '劳密' }
            // ],
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  },
  watch: {
      pieData(newV) {
        this.initChart(newV);
      }
    }
}
</script>
