<template>
  <div class="app-container">
    小区配置信息查询

    <!-- 查询表单 -->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="cellQuery.sectorId" placeholder="小区ID"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="cellQuery.sectorName" placeholder="小区名称"/>
      </el-form-item>
      <!-- TODO: 筛选条件输入与下拉选择合并 -->
      <el-form-item label="小区名称">
        <el-select
          v-model="cellQuery.sectorName"
          placeholder="请选择"
        >
          <el-option
            v-for="sector in nameList"
            :key="sector.sectorName"
            :label="sector.sectorName"
            :value="sector.sectorName"
          />
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
    >

      <el-table-column
        label="序号"
        width="70"
        align="center"
      >
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="city" label="城市/地区名称" width="80"/>
      <el-table-column prop="sectorId" label="小区ID" width="80"/>
      <el-table-column prop="sectorName" label="小区名称" width="80"/>
      <el-table-column prop="enodebid" label="小区所属基站标识" width="80"/>
      <el-table-column prop="enodebName" label="基站名称" width="80"/>
      <el-table-column prop="earfcn" label="小区配置的频点编号" width="80"/>
      <el-table-column prop="pci" label="物理小区标识" width="80"/>
      <el-table-column prop="pss" label="主同步信号标识" width="80"/>
      <el-table-column prop="sss" label="辅同步信号表示" width="80"/>
      <el-table-column prop="tac" label="跟踪区编码" width="80"/>
      <el-table-column prop="vender" label="vender" width="80"/>
      <el-table-column prop="longitude" label="经度" width="80"/>
      <el-table-column prop="latitude" label="纬度" width="80"/>
      <el-table-column prop="style" label="style" width="80"/>
      <el-table-column prop="azimuth" label="小区天线方位角" width="80"/>
      <el-table-column prop="height" label="小区天线高度" width="80"/>
      <el-table-column prop="electtilt" label="小区天线电下倾角" width="80"/>
      <el-table-column prop="mechtilt" label="小区天线机械下倾角" width="80"/>
      <el-table-column prop="totletilt" label="总下倾角" width="80"/>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />

  </div>
</template>

<script>
import cellApi from '@/api/query/cell'

export default {
  data() {
    return {
      list: null,
      page: 1,
      limit: 20,
      total: 0,
      cellQuery: {},
      nameList: []
    }
  },
  created() {
    this.getList()
    this.getCellNameList()
  },
  methods: {
    getList(page = 1) {
      this.page = page
      cellApi.getCellList(this.page, this.limit, this.cellQuery)
        .then(response => {
          this.list = response.data.list
          this.total = response.data.total
        })
    },
    resetData() {
      this.cellQuery = {}
      this.getList()
    },
    getCellNameList() {
      cellApi.getCellNameList()
        .then(response => {
          this.nameList = response.data.list
        })
    }
  }
}
</script>
