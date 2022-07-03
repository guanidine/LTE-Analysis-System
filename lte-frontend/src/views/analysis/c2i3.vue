<template>
  <div class="app-container">
    <el-form :inline="true" :model="headform" :rules="rules" class="demo-form-inline" >
      <el-form-item label="重叠覆盖干扰小区三元组分析" prop="num">
        <el-input v-model="headform.num" placeholder="请输入x" clearable>
          <template slot="append">%</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="isLoading" @click="initAnalyze">分析</el-button>
      </el-form-item>
    </el-form>
    <div class="block">
      <div v-if="isLoad === true" class="table">
        <el-table
          max-height="550"
          style="width: 100%;"
          :data="tableData"
          stripe
          fit
          border
        >
          <el-table-column
            prop="cellA"
            label="重叠干扰小区A"
          />
          <el-table-column
            prop="cellB"
            label="重叠干扰小区B"
          />
          <el-table-column
            prop="cellC"
            label="重叠干扰小区C"
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
import { getList, getTotal, analyzeTbC2i3 } from '@/api/analysis/c2i3'

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
            pattern: /^(100|[1-9]?\d(\.\d*)?)$/,
            message: '只能输入百分数'
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
      if (!this.rules.num[1].pattern.test(this.headform.num)) {
        this.$message({ target: 'warning', message: '非法输入' })
        this.headform.num = ''
        return
      }
      this.isLoading = true
      analyzeTbC2i3(this.headform.num / 100).then(() => {
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
