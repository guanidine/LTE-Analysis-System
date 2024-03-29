<template>
  <div v-if="hasPerm('prb.list')" class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">

      <el-form-item label="基站名称">
        <!-- 基站名称 -->
        <el-select
          v-model="prbQuery.enodebName"
          filterable
          clearable
          placeholder="基站名称"
          style="width: 250px"
          :popper-append-to-body="false"
        >
          <el-option
            v-for="item in enodebList"
            :key="item"
            :value="item"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="查询指标">
        <el-select
          v-model="prbQuery.field"
          clearable
          placeholder="查询指标"
          style="width: 400px"
        >
          <el-option
            v-for="(n,i) in 100"
            :key="i"
            :value="`第${i}个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)`"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="时间范围">
        <el-date-picker
          v-model="prbQuery.time"
          type="datetimerange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd HH:mm:ss"
          :default-time="['00:00:00', '00:00:00']"
        />
      </el-form-item>
    </el-form>

    <el-row style="width:100%; text-align: center;" justify="center">
      <el-col>
        <el-button
          :disabled="btnDisabled"
          :loading="createLoading"
          type="success"
          icon="el-icon-document"
          @click="create()"
        >生成tbPRBnew
        </el-button>
        <el-button
          :disabled="btnDisabled"
          type="primary"
          icon="el-icon-search"
          @click="showChart()"
        >查询
        </el-button>
        <el-button
          :disabled="btnDisabled"
          type="primary"
          icon="el-icon-search"
          @click="showChart('hour')"
        >按小时查询
        </el-button>
      </el-col>
    </el-row>

    <Download :names="['查询结果','图表']" :data="[ list, chartUrl ]" style="margin-top: 20px; margin-bottom:20px" />

    <div class="chart-container">
      <div id="chart" class="chart" style="height:500px;width:100%" />
    </div>
  </div>
</template>

<script>
import echarts from 'echarts'
import prbApi from '@/api/query/prb'
import Vue from 'vue'
import Download from '@/components/Download'

export default {
  components: {
    Download
  },
  data() {
    return {
      prbQuery: {
        enodebName: 'B马东工地大剧院-HLHF',
        time: ['2020-07-17 00:00:00', '2020-07-18 00:00:00'],
        field: '第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)'
      },
      btnDisabled: false,
      chart: null,
      xData: [],
      yData: [],
      enodebList: [],
      list: [],
      createLoading: false
    }
  },
  computed: {
    chartUrl() {
      if (!this.chart) {
        return ''
      }
      return this.chart.getDataURL({
        type: 'png',
        pixelRatio: 1.5,
        backgroundColor: 'white'
      })
    }
  },
  created() {
    this.listEnodebs()
    this.initChartData()
  },
  methods: {
    showChart(type = 'minute') {
      if (type === 'minute') {
        this.initChartData()
      } else if (type === 'hour') {
        this.initChartHourData()
      }
    },

    // 准备图表数据
    initChartData() {
      prbApi.listData(this.prbQuery).then(response => {
        this.yData = response.data.list
        this.xData = response.data.date
        this.setChart()
        this.list = []
        for (let i = 0; i < this.xData.length; i++) {
          this.list.push({ date: this.xData[i], data: this.yData[i], dataField: this.prbQuery.field })
        }
      })
    },
    initChartHourData() {
      prbApi.listHourData(this.prbQuery).then(response => {
        this.yData = response.data.list
        this.xData = response.data.date
        this.setChart()
        this.list = []
        for (let i = 0; i < this.xData.length; i++) {
          this.list.push({ date: this.xData[i], data: this.yData[i], dataField: this.prbQuery.field })
        }
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
          trigger: 'axis', // axis , item
          backgroundColor: 'rgba(0,98,132,0.8)',
          borderColor: 'rgba(125,185,222,0.8)',
          borderWidth: 1,
          borderRadius: 0,
          formatter: `${this.prbQuery.field} <br/>{b} : {c}`,
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
            interval: 5, // 设置为 1，表示『隔一个标签显示一个标签』
            rotate: 40,
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
            endValue: 10,
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
          text: this.prbQuery.field,
          textStyle: {
            color: '#0B346EAA'
          }
        },
        yAxis: [
          {
            name: '',
            type: 'value',
            inverse: true,
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
            name: `柱状图`,
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
            name: `折线图`,
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

    listEnodebs() {
      if (Vue.prototype.hasPerm('prb.list')) {
        prbApi.listEnodebs()
          .then(response => {
            this.enodebList = response.data.list
          })
      }
    },
    create() {
      this.createLoading = true
      prbApi.createTbPrbNew().then(
        response => {
          this.createLoading = false
          this.$message({ target: 'success', message: '创建成功' })
        }
      )
    }

    // TODO: 四个selector未全部选择的时候查询按钮应为disabled，懒着做了
  }
}
</script>
