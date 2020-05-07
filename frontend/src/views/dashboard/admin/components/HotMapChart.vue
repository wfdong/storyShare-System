<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>
<script>
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
// require('echarts/extension/bmap/bmap')
import resize from "./mixins/resize";
import "echarts/extension/bmap/bmap";
// import chinaMap from 'echarts/map/json/province/shandong.json'
// echarts.registerMap('china', chinaMap)
export default {
  beforeCreate() {
    console.log("初始化百度地图脚本...");
    const AK = "RU3uFmobbpe6W3qZGjOEXhBRiULYn1ED";
    const BMap_URL =
      "https://api.map.baidu.com/api?v=2.0&ak=" +
      AK +
      "&s=1&callback=onBMapCallback";
    return new Promise((resolve, reject) => {
      // 如果已加载直接返回
      if (typeof BMap !== "undefined") {
        resolve(BMap);
        return true;
      }
      // 百度地图异步加载回调处理
      window.onBMapCallback = function() {
        console.log("百度地图脚本初始化成功...");
        resolve(BMap);
      };
      // 插入script脚本
      let scriptNode = document.createElement("script");
      scriptNode.setAttribute("type", "text/javascript");
      scriptNode.setAttribute("src", BMap_URL);
      document.body.appendChild(scriptNode);
    });
  },
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
    'barData': [Array]
  },
  data() {
    return {
      chart: null,
      data: [
        { name: "任城区", value: 175 },
        { name: "兖州区", value: 177 },
        { name: "微山县", value: 177 },
        { name: "鱼台县", value: 193 },
        { name: "金乡县", value: 194 },
        { name: "嘉祥县", value: 229 },
        { name: "汶上县", value: 273 },
        { name: "泗水县", value: 279 },
        { name: "梁山县", value: 12 }
      ],
      geoCoordMap: {
        任城区: [116.59, 35.40],
        兖州区: [116.78, 35.55],
        微山县: [117.11, 34.80],
        鱼台县: [116.65, 35.00],
        金乡县: [116.31, 35.06],
        嘉祥县: [116.34, 35.40],
        汶上县: [116.48, 35.73],
        泗水县: [117.25, 35.66],
        梁山县: [116.09, 35.80],
      }
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
      this.chart.setOption({
        title: {
          text: "申贷次数分布图",
          left: "center"
        },
        tooltip: {
          trigger: "item"
        },
        bmap: {
          //济宁市政府
          center: [116.58, 35.31],
          zoom: 9,
          roam: true,
          mapStyle: {
            styleJson: [
            //   {
            //     featureType: "water",
            //     elementType: "all",
            //     stylers: {
            //       color: "#d1d1d1"
            //     }
            //   },
            //   {
            //     featureType: "land",
            //     elementType: "all",
            //     stylers: {
            //       color: "#f3f3f3"
            //     }
            //   },
            //   {
            //     featureType: "railway",
            //     elementType: "all",
            //     stylers: {
            //       visibility: "off"
            //     }
            //   },
              {
                featureType: "highway",
                elementType: "all",
                stylers: {
                  color: "#fdfdfd"
                }
              },
              {
                featureType: "highway",
                elementType: "labels",
                stylers: {
                  visibility: "off"
                }
              },
              {
                featureType: "arterial",
                elementType: "geometry",
                stylers: {
                  color: "#fefefe"
                }
              },
              {
                featureType: "arterial",
                elementType: "geometry.fill",
                stylers: {
                  color: "#fefefe"
                }
              },
              {
                featureType: "poi",
                elementType: "all",
                stylers: {
                  visibility: "off"
                }
              },
              {
                featureType: "green",
                elementType: "all",
                stylers: {
                  visibility: "off"
                }
              },
              {
                featureType: "subway",
                elementType: "all",
                stylers: {
                  visibility: "off"
                }
              },
            //   {
            //     featureType: "manmade",
            //     elementType: "all",
            //     stylers: {
            //       color: "#d1d1d1"
            //     }
            //   },
              {
                featureType: "local",
                elementType: "all",
                stylers: {
                  color: "#d1d1d1"
                }
              },
              {
                featureType: "arterial",
                elementType: "labels",
                stylers: {
                  visibility: "off"
                }
              },
            //   {
            //     featureType: "boundary",
            //     elementType: "all",
            //     stylers: {
            //       color: "#fefefe"
            //     }
            //   },
              {
                featureType: "building",
                elementType: "all",
                stylers: {
                  color: "#d1d1d1"
                }
              },
              {
                featureType: "label",
                elementType: "labels.text.fill",
                stylers: {
                  color: "#999999"
                }
              }
            ]
          }
        },
        series: [
          {
            name: "申贷个数",
            type: "effectScatter",
            coordinateSystem: "bmap",
            data: this.convertData(this.data),
            symbolSize: function(val) {
                //val : 116.09,35.8,12, val[2] 是最后的真实值
            //   console.log("symbol size:" + val)
              //TODO 要动态的求一个综合大小才行
              return Math.max(val[2] / 10, 20);
            },
            showEffectOn: "render",
            rippleEffect: {
              brushType: "stroke"
            },
            hoverAnimation: true,
            label: {
              normal: {
                formatter: "{b}",
                position: "right",
                show: true
              },
              emphasis: {
                show: true
              }
            },
            tooltip: {
                formatter:function(params){
                //自定义模版，获取value的值，默认使用的是经纬度
                return "申贷次数" + ":" + params.data.value[2];
            }
            },
            itemStyle: {
              normal: {
                color: "purple",
                shadowBlur: 10,
                shadowColor: "#333"
              }
            },
            zlevel: 1
          }
        ]
      });
    }
  }
};
</script>
