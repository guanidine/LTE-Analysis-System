<template>
  <div v-if="hasPerm('cell.list')" class="app-container">
    <!-- 查询表单 -->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <!-- 小区ID -->
        <el-input v-model="cellQuery.sectorId" placeholder="小区ID" />
      </el-form-item>
      <el-form-item>
        <!-- 小区名称 -->
        <el-select
          v-model="cellQuery.sectorName"
          filterable
          clearable
          placeholder="小区名称"
          :filter-method="filterMethod"
          @change="onChange($event,'sectorName')"
          @blur="onBlur($event,'sectorName')"
          @clear="onClear('sectorName')"
        >
          <el-option
            v-for="item in renderSectorList"
            :key="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
      <!-- 基站标识 -->
      <el-form-item>
        <el-select
          v-model="cellQuery.enodebid"
          filterable
          clearable
          placeholder="基站标识"
          @change="onChange($event,'enodebid')"
          @blur="onBlur($event,'enodebid')"
          @clear="onClear('enodebid')"
        >
          <el-option
            v-for="item in renderEnodebList"
            :key="item.enodebid"
            :value="item.enodebid"
          />
        </el-select>
      </el-form-item>
      <!-- 基站名称 -->
      <el-form-item>
        <el-select
          v-model="cellQuery.enodebName"
          filterable
          clearable
          placeholder="基站名称"
          @change="onChange($event,'enodebName')"
          @blur="onBlur($event,'enodebName')"
          @clear="onClear('enodebName')"
        >
          <el-option
            v-for="item in renderEnodebList"
            :key="item.enodebName"
            :value="item.enodebName"
          />
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="listCells()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
      <Download :names="[ '查询结果下载' ]" :data="[list]" />
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
    >

      <el-table-column
        fixed
        label="序号"
        width="70"
        align="center"
      >
        <template v-slot="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="city" label="城市/地区名称" width="110" />
      <el-table-column prop="sectorId" label="小区ID" width="100" />
      <el-table-column prop="sectorName" label="小区名称" width="150" />
      <el-table-column prop="enodebid" label="小区所属基站标识" width="80" />
      <el-table-column prop="enodebName" label="基站名称" width="150" />
      <el-table-column prop="earfcn" label="小区配置的频点编号" width="100" />
      <el-table-column prop="pci" label="物理小区标识" width="80" />
      <el-table-column prop="pss" label="主同步信号标识" width="80" />
      <el-table-column prop="sss" label="辅同步信号标识" width="80" />
      <el-table-column prop="tac" label="跟踪区编码" width="80" />
      <el-table-column prop="vendor" label="供应商" width="70" />
      <el-table-column prop="longitude" label="经度" width="90" />
      <el-table-column prop="latitude" label="纬度" width="90" />
      <el-table-column prop="style" label="基站类型" width="80" />
      <el-table-column prop="azimuth" label="小区天线方位角" width="80" />
      <el-table-column prop="height" label="小区天线高度" width="80" />
      <el-table-column prop="electtilt" label="小区天线电下倾角" width="80" />
      <el-table-column prop="mechtilt" label="小区天线机械下倾角" width="80" />
      <el-table-column prop="totletilt" label="总下倾角" width="80" />
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="listCells"
    />

  </div>
</template>

<script>

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 *  O\ = /O
 * ___/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * .............................................
 *            佛曰：bug泛滥，我已瘫痪！
 */

import cellApi from '@/api/query/cell'
import Vue from 'vue'
import Download from '@/components/Download'

export default {
  components: {
    Download
  },
  data() {
    return {
      list: [],
      page: 1,
      limit: 20,
      total: 0,
      cellQuery: {
        sectorId: '',
        sectorName: '',
        enodebid: '',
        enodebName: ''
      },
      sectorList: [],
      renderSectorList: [],
      enodebList: {
        enodebid: '',
        enodebName: ''
      },
      renderEnodebList: {
        enodebid: '',
        enodebName: ''
      }
    }
  },
  created() {
    this.listCells()
    this.listSectors()
    this.listEnodebs()
  },
  methods: {
    listCells(page = 1) {
      if (Vue.prototype.hasPerm('cell.list')) {
        this.page = page
        cellApi.listCells(this.page, this.limit, this.cellQuery)
          .then(response => {
            this.list = response.data.list
            console.log(response)
            this.total = response.data.total
          })
      }
    },
    resetData() {
      this.cellQuery = {}
      this.listCells()
      this.listSectors()
      this.listEnodebs()
    },
    listSectors() {
      if (Vue.prototype.hasPerm('cell.list')) {
        cellApi.listSectors()
          .then(response => {
            this.sectorList = response.data.list
            this.renderSectorList = this.sectorList.slice(0, 100)
          })
      }
    },
    listEnodebs() {
      if (Vue.prototype.hasPerm('cell.list')) {
        cellApi.listEnodebs()
          .then(response => {
            this.enodebList = response.data.list
            this.renderEnodebList = this.enodebList.slice(0, 100)
          })
      }
    },
    filterMethod(val, object = 'sectorName') {
      if (object === 'sectorName') {
        this.renderSectorList = this.sectorList.filter((item) => {
          if (!val || item.toUpperCase().includes(val.toUpperCase())) {
            return true
          }
        }).slice(0, 100)
      } else if (object === 'enodebName') {
        this.renderEnodebList = this.enodebList.filter((item) => {
          if ((!val || item.enodebName.toUpperCase().includes(val.toUpperCase())) &&
            (!this.cellQuery.enodebid || item.enodebid.toUpperCase().includes(this.cellQuery.enodebid.toUpperCase()))) {
            return true
          }
        }).slice(0, 100)
      } else if (object === 'enodebid') {
        this.renderEnodebList = this.enodebList.filter((item) => {
          if ((!val || item.enodebid.toUpperCase().includes(val.toUpperCase())) &&
            (!this.cellQuery.enodebName || item.enodebName.toUpperCase().includes(this.cellQuery.enodebName.toUpperCase()))) {
            return true
          }
        }).slice(0, 100)
      }
    },
    onBlur(val, object = 'sectorName') {
      if (val.target !== '') {
        if (object === 'sectorName') {
          this.cellQuery.sectorName = val.target.value
          this.filterMethod(val.target.value, 'sectorName')
        } else if (object === 'enodebName') {
          this.cellQuery.enodebName = val.target.value
        } else if (object === 'enodebid') {
          this.cellQuery.enodebid = val.target.value
        }
      }
    },
    onClear(object = 'sectorName') {
      if (object === 'sectorName') {
        this.cellQuery.sectorName = ''
        this.filterMethod('', 'sectorName')
      } else if (object === 'enodebName') {
        this.cellQuery.enodebName = ''
        this.cellQuery.enodebid = ''
        this.filterMethod('', 'enodebName')
      } else if (object === 'enodebid') {
        this.cellQuery.enodebName = ''
        this.cellQuery.enodebid = ''
        this.filterMethod('', 'enodebid')
      }
    },
    onChange(val, object = 'sectorName') {
      if (object === 'sectorName') {
        this.cellQuery.sectorName = val
        this.filterMethod(val, 'sectorName')
      } else if (object === 'enodebName') {
        this.cellQuery.enodebName = val
        this.filterMethod(val, 'enodebName')
      } else if (object === 'enodebid') {
        this.cellQuery.enodebid = val
        this.filterMethod(val, 'enodebid')
      }
    }
  }
}
</script>
