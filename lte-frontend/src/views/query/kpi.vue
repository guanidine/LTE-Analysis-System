<template>
  <div class="app-container">
    KPI指标信息查询

    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">

      <el-form-item>
        <!-- 小区名称 -->
        <el-select
          v-model="kpiQuery.sectorName"
          filterable
          clearable
          placeholder="小区名称"
          style="width: 280px"
          :popper-append-to-body="false"
        >
          <el-option
            v-for="item in sectorList"
            :key="item"
            :value="item"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-select
          v-model="kpiQuery.field"
          clearable
          placeholder="查询指标"
          style="width: 400px"
        >
          <el-option value="RRC连接建立完成次数 (无)"/>
          <el-option value="RRC连接请求次数（包括重发） (无)"/>
          <el-option value="RRC建立成功率qf (%)"/>
          <el-option value="E-RAB建立成功总次数 (无)"/>
          <el-option value="E-RAB建立尝试总次数 (无)"/>
          <el-option value="E-RAB建立成功率2 (%)"/>
          <el-option value="eNodeB触发的E-RAB异常释放总次数 (无)"/>
          <el-option value="小区切换出E-RAB异常释放总次数 (无)"/>
          <el-option value="E-RAB掉线率 (%)"/>
          <el-option value="无线接通率ay (%)"/>
          <el-option value="eNodeB发起的S1 RESET导致的UE Context释放次数 (无)"/>
          <el-option value="UE Context异常释放次数 (无)"/>
          <el-option value="UE Context建立成功总次数 (无)"/>
          <el-option value="无线掉线率 (%)"/>
          <el-option value="eNodeB内异频切换出成功次数 (无)"/>
          <el-option value="eNodeB内异频切换出尝试次数 (无)"/>
          <el-option value="eNodeB内同频切换出成功次数 (无)"/>
          <el-option value="eNodeB内同频切换出尝试次数 (无)"/>
          <el-option value="eNodeB间异频切换出成功次数 (无)"/>
          <el-option value="eNodeB间异频切换出尝试次数 (无)"/>
          <el-option value="eNodeB间同频切换出成功次数 (无)"/>
          <el-option value="eNodeB间同频切换出尝试次数 (无)"/>
          <el-option value="eNB内切换成功率 (%)"/>
          <el-option value="eNB间切换成功率 (%)"/>
          <el-option value="同频切换成功率zsp (%)"/>
          <el-option value="异频切换成功率zsp (%)"/>
          <el-option value="切换成功率 (%)"/>
          <el-option value="小区PDCP层所接收到的上行数据的总吞吐量 (比特)"/>
          <el-option value="小区PDCP层所发送的下行数据的总吞吐量 (比特)"/>
          <el-option value="RRC重建请求次数 (无)"/>
          <el-option value="RRC连接重建比率 (%)"/>
          <el-option value="通过重建回源小区的eNodeB间同频切换出执行成功次数 (无)"/>
          <el-option value="通过重建回源小区的eNodeB间异频切换出执行成功次数 (无)"/>
          <el-option value="通过重建回源小区的eNodeB内同频切换出执行成功次数 (无)"/>
          <el-option value="通过重建回源小区的eNodeB内异频切换出执行成功次数 (无)"/>
          <el-option value="eNB内切换出成功次数 (次)"/>
          <el-option value="eNB内切换出请求次数 (次)"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-date-picker
          v-model="kpiQuery.time"
          type="datetimerange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="['00:00:00', '00:00:00']"
        />
      </el-form-item>

      <el-button
        :disabled="btnDisabled"
        type="primary"
        icon="el-icon-search"
        @click="showChart()"
      >查询
      </el-button>
    </el-form>

    <div class="chart-container">
      <div id="chart" class="chart" style="height:500px;width:100%"/>
    </div>
  </div>
</template>

<script>
import echarts from 'echarts'
import kpiApi from '@/api/query/kpi'

export default {
  data() {
    return {
      kpiQuery: {
        sectorName: '',
        time: '',
        field: ''
      },
      btnDisabled: false,
      chart: null,
      xData: [],
      yData: [],
      sectorList: []
    }
  },
  created() {
    this.listSectors()
  },
  methods: {
    showChart() {
      this.initChartData()
    },

    // 准备图表数据
    initChartData() {
      kpiApi.listData(this.kpiQuery).then(response => {
        this.yData = response.data.list
        this.xData = response.data.date
        this.setChart()
      })
    },

    // 设置图标参数
    setChart() {
      // 基于准备好的dom，初始化echarts实例
      this.chart = echarts.init(document.getElementById('chart'))

      const option = {
        backgroundColor: 'rgba(129,199,212,0.2)',
        legend: {
          top: '20',
          x: 'center',
          textStyle: {
            fontSize: 16,
            color: '#0B346EAA'
          },
          icon:
            'path://M512 881.777778 512 881.777778C716.222629 881.777778 881.777778 716.222629 881.777778 512 881.777778 307.777371 716.222629 142.222222 512 142.222222 307.777373 142.222222 142.222222 307.777371 142.222222 512 142.222222 716.222629 307.777373 881.777778 512 881.777778L512 881.777778ZM512 1024 512 1024C229.230208 1024 0 794.769789 0 512 0 229.230211 229.230208 0 512 0 794.769789 0 1024 229.230211 1024 512 1024 794.769789 794.769789 1024 512 1024L512 1024Z',
          itemWidth: 8, // 设置宽度
          itemHeight: 8, // 设置高度
          itemGap: 12 // 设置间距
        },
        tooltip: {
          show: true,
          trigger: 'item', // axis , item
          backgroundColor: 'rgba(0,98,132,0.8)',
          borderColor: 'rgba(125,185,222,0.8)',
          borderWidth: 1,
          borderRadius: 0,
          formatter: `${this.kpiQuery.field} <br/>{b} : {c}`,
          textStyle: {
            color: '#A5DEE4',
            fontSize: 16,
            align: 'left'
          }
        },
        grid: {
          right: '5%',
          top: '10%',
          left: '5%',
          bottom: '5%',
          containLabel: true
        },
        xAxis: {
          name: '日期',
          nameTextStyle: {
            color: '#0B346EAA'
          },
          type: 'category',
          boundaryGap: true,
          data: this.xData,
          axisLabel: {
            // 坐标轴刻度标签的相关设置
            interval: 0, // 设置为 1，表示『隔一个标签显示一个标签』
            margin: 15,
            textStyle: {
              color: '#0B346EAA',
              fontStyle: 'normal',
              fontSize: 16
            }
          },
          axisTick: {
            // 坐标轴刻度相关设置。
            show: false
          },
          axisLine: {
            // 坐标轴轴线相关设置
            lineStyle: {
              color: 'rgba(88,178,220,0.2)'
            }
          },
          splitLine: {
            // 坐标轴在 grid 区域中的分隔线。
            show: true,
            lineStyle: {
              color: 'rgba(88,178,220,0.2)'
            }
          }
        },
        dataZoom: [
          {
            type: 'slider',
            show: true,
            bottom: '10px',
            borderColor: 'rgba(102,186,183,0.2)',
            backgroundColor: 'transparent',
            dataBackground: {
              lineStyle: {
                color: 'transparent',
                shadowOffsetY: 0
              },
              areaStyle: {
                color: 'transparent',
                shadowOffsetY: 0
              }
            },
            // 拖拽手柄样式 svg 路径
            handleIcon:
              'M512 512m-208 0a6.5 6.5 0 1 0 416 0 6.5 6.5 0 1 0-416 0Z M512 192C335.264 192 192 335.264 192 512c0 176.736 143.264 320 320 320s320-143.264 320-320C832 335.264 688.736 192 512 192zM512 800c-159.072 0-288-128.928-288-288 0-159.072 128.928-288 288-288s288 128.928 288 288C800 671.072 671.072 800 512 800z',
            handleColor: '#78C2C48F',
            height: 6,
            handleSize: 12,
            showDataShadow: false,
            filterMode: 'filter',
            textStyle: {
              color: '#0B346E4F'
            },
            start: 0,
            end: 100
          }
        ],
        title: {
          text: this.kpiQuery.field,
          textStyle: {
            color: '#0B346EAA'
          }
        },
        yAxis: [
          {
            name: '',
            nameTextStyle: {
              color: '#0B346EAA'
            },
            type: 'value',
            axisLabel: {
              textStyle: {
                color: '#0B346EAA',
                fontStyle: 'normal',
                fontSize: 16
              }
            },
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: 'rgba(88,178,220,0.2)'
              }
            }
          }
        ],
        series: [
          {
            name: '柱状图',
            type: 'pictorialBar',
            data: this.yData,
            barWidth: '50%',
            label: {
              normal: {
                show: false
              }
            },
            itemStyle: {
              normal: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 0,
                  y2: 1,
                  colorStops: [
                    {
                      offset: 0,
                      color: 'rgba(48,207,208,0.8)' // 0% 处的颜色
                    },
                    {
                      offset: 1,
                      color: 'rgba(51,8,103,0.2)' // 100% 处的颜色
                    }
                  ],
                  globalCoord: false // 缺省为 false
                } // 渐变颜色
              }
            },
            symbol:
              'path://M12.000,-0.000 C12.000,-0.000 16.074,60.121 22.731,60.121 C26.173,60.121 -3.234,60.121 0.511,60.121 C7.072,60.121 12.000,-0.000 12.000,-0.000 Z'
          },
          {
            name: '折线图',
            type: 'line',
            data: this.yData,
            smooth: true,
            symbol: 'none',
            lineStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: 'rgba(44,216,213,0.3)'
                }, {
                  offset: 0.48,
                  color: 'rgba(107,141,214,1.0)'
                }, {
                  offset: 1,
                  color: 'rgba(142,55,215,0.3)'
                }]),
                shadowColor: '#005CAF',
                shadowBlur: 10
              }
            }
          }
        ]
      }

      this.chart.setOption(option)
    },

    listSectors() {
      kpiApi.listSectors()
        .then(response => {
          this.sectorList = response.data.list
        })
    }

    // TODO: 四个selector未全部选择的时候查询按钮应为disabled，懒着做了
  }
}
</script>
