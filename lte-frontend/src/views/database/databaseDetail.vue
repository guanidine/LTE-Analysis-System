<template>
  <div v-if="hasPerm('data.export')" class="app-container">
    <span style="font-size:Extra large">数据库管理</span>

    <el-divider content-position="left">数据表信息查询</el-divider>

    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="数据表" style="margin-left: 300px">
        <!-- 操作的数据表 -->
        <el-select
          v-model="tableName"
          clearable
          placeholder="请选择要操作的数据表"
          style="width: 500px"
        >
          <el-option
            v-for="item in tableList"
            :key="item.tablename"
            :value="item.tablename"
          />
        </el-select>
      </el-form-item>
      <el-button
        :disabled="false"
        type="primary"
        icon="el-icon-search"
        @click="getS()"
      >查询
      </el-button>
    </el-form><!--      tableList: ['tbc2i3', 'tbc2inew', 'tbcell', 'tbkpi', 'tbmrodata', 'tbprb', 'tbprbnew'],-->
    <el-table
      :data="listAr"
      border
      fit
      highlight-current-row
    >
<!--      <el-table-column prop="notnull" label="非空" width="380" />-->
      <el-table-column prop="name" label="字段名" width="480" />
      <el-table-column prop="comment" label="注释" width="480" />
      <el-table-column prop="type" label="类型" width="480" />
    </el-table>
    <el-table
      :data="listSize"
      border
      fit
      highlight-current-row
    >
      <el-table-column prop="size" label="数据表大小" width="1520" />
    </el-table>
    <el-table
      :data="listPath"
      border
      fit
      highlight-current-row
    >
      <el-table-column prop="file" label="数据表文件路径" width="1520" />
    </el-table>
    <el-divider content-position="left">数据库信息查询</el-divider>
    <el-form :inline="true" :model="headform"  class="demo-form-inline">
      <el-form-item label="初始化连接数大小" prop="num">
        <el-input v-model="headform.initialSize" placeholder="请输入初始化连接数" clearable />
      </el-form-item>
      <el-form-item label="最小连接数" prop="num">
        <el-input v-model="headform.minIdle" placeholder="请输入初始化连接数" clearable />
      </el-form-item>
      <el-form-item label="最大连接数" prop="num">
        <el-input v-model="headform.maxActice" placeholder="请输入初始化连接数" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateDB()">更新</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="showDB()">查看</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="listDBP"
      border
      fit
      highlight-current-row
    >
      <!--      <el-table-column prop="notnull" label="非空" width="380" />-->
      <el-table-column prop="URL" label="URL" width="280" />
      <el-table-column prop="InitialSize" label="初始化连接数" width="280" />
      <el-table-column prop="MinIdle" label="最小连接数" width="280" />
      <el-table-column prop="MaxActive" label="最大连接数" width="280" />
      <el-table-column prop="PoolingCount" label="池中连接数" width="280" />
      <el-table-column prop="PoolingPeak" label="池中连接数峰值" width="280" />
      <el-table-column prop="ActiveCount" label="活跃连接数" width="280" />
      <el-table-column prop="ActivePeak" label="活跃连接数峰值" width="280" />
      <el-table-column prop="ExecuteCount" label="执行数" width="280" />
    </el-table>
  </div>
</template>

<script>
import { getArchitecture, getSize, getFilePath, updateAPI, showAPI } from '@/api/database/databaseUtil'

export default {
  data() {
    return {
      msg: 'sd',
      tableName: 'tbcell',
      listAr: [],
      listSize: [],
      listPath: [],
      listDBP: [],
      headform: {
        initialSize: 1,
        minIdle: 1,
        maxActice: 5
      },
      tableList: [{ tablename: 'tbc2i3' }, { tablename: 'tbc2inew' }, { tablename: 'tbcell' }, {tablename: 'tbkpi' }, { tablename: 'tbmrodata' }, { tablename: 'tbprb' }, { tablename: 'tbprbnew' }]
    }
  },
  methods: {
    getS() {
      getArchitecture(this.tableName).then((res) => {
        this.listAr = res.data.list
        // console.log(res.data.list)
        getSize(this.tableName).then((res) => {
          this.listSize = [{ size: res.data.size }]
          // console.log([res.data.size])
          getFilePath(this.tableName).then((res) => {
            this.listPath = [{ file: res.data.file }]
          })
        })
      })
    },
    updateDB() {
      updateAPI(this.headform.initialSize, this.headform.minIdle, this.headform.maxActice).then((res) => {
        this.listDBP = [{ URL: res.data.URL, InitialSize: res.data.InitialSize, MinIdle: res.data.MinIdle, MaxActive: res.data.MaxActive, PoolingCount: res.data.PoolingCount, PoolingPeak: res.data.PoolingPeak, ActiveCount: res.data.ActiveCount, ActivePeak: res.data.ActivePeak, ExecuteCount: res.data.ExecuteCount }]
      })
    },
    showDB() {
      showAPI().then((res) => {
        this.listDBP = [{ URL: res.data.URL, InitialSize: res.data.InitialSize, MinIdle: res.data.MinIdle, MaxActive: res.data.MaxActive, PoolingCount: res.data.PoolingCount, PoolingPeak: res.data.PoolingPeak, ActiveCount: res.data.ActiveCount, ActivePeak: res.data.ActivePeak, ExecuteCount: res.data.ExecuteCount }]
      })
    }
  }
}
</script>

<style>

</style>

<!--<template>-->
<!--  <div>-->
<!--    <el-form :inline="true" class="demo-form-inline">-->
<!--      <el-form-item label="数据表名称">-->
<!--        <el-select-->
<!--          v-model="query.tablename"-->
<!--          clearable-->
<!--          placeholder="名称"-->
<!--          style="width: 400px"-->
<!--        >-->
<!--          <el-option-->
<!--            v-for="item in tableList"-->
<!--            :key="item"-->
<!--            :value="item"-->
<!--          />-->
<!--          <el-form-item label="对该表的操作">-->
<!--            <el-select-->
<!--              v-model="query.operator"-->
<!--              clearable-->
<!--              placeholder="操作名称"-->
<!--              style="width: 400px"-->
<!--            >-->
<!--              <el-option-->
<!--                v-for="item in operatorList"-->
<!--                :key="item"-->
<!--                :value="item"-->
<!--              />-->
<!--        </el-select>-->
<!--      </el-form-item>-->

<!--    </el-form>-->
<!--    <div>-->
<!--      数据库表结构9-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import { getArchitecture, getSize, getFilePath } from '@/api/database/databaseUtils'-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      query: {-->
<!--        tableName: 'tbc213',-->
<!--        operator: 'architecture'-->
<!--      },-->
<!--      tableList: ['tbc2i3', 'tbc2inew', 'tbcell', 'tbkpi', 'tbmrodata', 'tbprb', 'tbprbnew'],-->
<!--      operatorList: ['architecture', 'size', 'filePath']-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    getA: function() {-->
<!--      switch (this.operator){-->
<!--        case 'architecture':-->
<!--          getArchitecture(this.tableName).then((res)=>{-->

<!--          })-->
<!--      }-->
<!--          break;-->
<!--        case 'size':-->
<!--          getSize(this.tableName)-->
<!--          break;-->
<!--        case 'filePath':-->
<!--          getFilePath(this.tableName)-->
<!--          break;-->
<!--        default:-->
<!--          console.log('table_get_wrong')-->
<!--      }-->
<!--    }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
