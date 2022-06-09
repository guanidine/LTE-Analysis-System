<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">

      <el-form-item label="数据库名称">
        <!-- 基站名称 -->
        <el-select
          v-model="query.databaseName"
          filterable
          clearable
          placeholder="数据库名称"
          style="width: 250px"
          :popper-append-to-body="false"
        >
          <el-option
            v-for="item in databaseList"
            :key="item"
            :value="item"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="数据表名称">
        <el-select
          v-model="query.tablename"
          clearable
          placeholder="数据表名称"
          style="width: 400px"
        >
          <el-option
            v-for="item in tableList"
            :key="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      query: {
        databaseName: 'database',
        tablename: 'table'
      },
      databaseList: ['database1', 'database2'],
      tableList: ['table1', 'table2']
    }
  }
}
</script>

<style scoped>

</style>
