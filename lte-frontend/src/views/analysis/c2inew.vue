<template>
  <div class="app-container">
    <el-form :inline="true" :model="headform" :rules="rules" class="demo-form-inline">
      <el-form-item label="主邻小区间最少干扰测量数据条数">
        <el-input v-model="headform.num" placeholder="请输入最少数据条数" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="isLoading" @click="initAnalyze">分析</el-button>
      </el-form-item>
    </el-form>
    <div class="block">
      <div v-if="isLoad === true" class="table">
        <el-table
          max-height="580"
          style="width: 100%;"
          :data="tableData"
          stripe
          fit
          border
        >
          <el-table-column
            prop="scell"
            label="主小区"
            width="180"
          />
          <el-table-column
            prop="ncell"
            label="邻小区"
            width="180"
          />
          <el-table-column
            prop="c2iMean"
            label="C2I均值"
          />
          <el-table-column
            prop="std"
            label="C2I方差"
          />
          <el-table-column
            prop="prbC2i9"
            label="差值小于9的概率"
          />
          <el-table-column
            prop="prbAbs6"
            label="差值的绝对值小于6的概率"
          />
        </el-table>

        <el-pagination
          :current-page="currentPage"
          :page-sizes="[50, 100, 200]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
      <el-empty v-else description="暂无数据" />
    </div>
  </div>
</template>

<script>
import { getList, getTotal, analyzeTbC2i } from '@/api/analysis/c2inew'

export default {
  data() {
    return {
      // 头部栏表单
      headform: {
        // 最少数量
        num: ''
      },
      // 表格数据总数量
      total: 0,
      // 页面数量
      pageSize: 50,
      // 当前页面
      currentPage: 1,
      // 是否已经载入数据
      isLoad: false,
      // 是否正在加载
      isLoading: false,
      // 表格数据
      tableData: [],
      // 验证规则
      rules: {
        num: [
          { required: true, message: '请输入你的内容', trigger: 'blur' },
          {
            pattern: /^[1-9]\d*$/,
            message: '只能输入正整数'
          }
        ]
      }
    }
  },
  methods: {
    setTable: function() {
      getList(this.currentPage, this.pageSize).then((res) => {
        this.tableData = res.data.data
      })
    },
    handleSizeChange: function(newSize) {
      this.pageSize = newSize
      this.setTable()
    },
    handleCurrentChange: function(newPage) {
      this.currentPage = newPage
      this.setTable()
    },
    initAnalyze: function() {
      this.isLoading = true
      analyzeTbC2i(this.headform.num).then(() => {
        getTotal().then((res) => { this.total = res.data.total })
        this.setTable()
        this.isLoading = false
        this.isLoad = true
      }).catch((err) => {
        console.log(err)
        this.$message({ target: 'error', message: '分析出错！' })
        this.isLoading = false
      }
      )
    }
  }
}
</script>

<style>
.block {
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}
.el-pagination {
  padding: 30px 0;
  text-align: center;
}
</style>
