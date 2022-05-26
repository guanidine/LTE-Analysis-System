<template>
  <div v-if="hasPerm('user.list')" class="app-container">
    <div>
      <el-button v-if="hasPerm('user.add')" type="primary" size="medium" plain @click="addUser()">添加</el-button>
      <el-button v-if="hasPerm('user.remove')" type="danger" size="medium" plain @click="removeRows()">批量删除</el-button>
    </div>

    <div style="margin: 15px 0;"/>

    <el-table v-loading="listLoading" :data="list" stripe style="width: 100%" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="55"/>

      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="用户名" width="150"/>

      <el-table-column prop="password" label="用户密码"/>

      <!-- 由于民族之光数据库强大的兼容性，移除gmt_create字段 -->
      <!-- <el-table-column prop="gmtCreate" label="创建时间" :formatter="timeFormatter" width="180"/> -->

      <el-table-column label="操作" width="230" align="center">
        <template slot-scope="scope">
          <el-row>
            <el-button
              v-if="hasPerm('user.assign')"
              type="warning"
              size="small"
              icon="el-icon-s-operation"
              circle
              plain
              @click="assignById(scope.row.id)"
            >
            </el-button>
            <el-button
              v-if="hasPerm('user.update')"
              type="primary"
              size="small"
              icon="el-icon-edit"
              circle
              plain
              @click="updateById(scope.row.id)"
            >
            </el-button>
            <el-button
              v-if="hasPerm('user.remove')"
              type="danger"
              size="small"
              icon="el-icon-delete"
              circle
              plain
              @click="removeDataById(scope.row.id)"
            />
          </el-row>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center;"
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      @current-change="fetchData"
      @size-change="changeSize"
    />
  </div>
</template>

<script>
import user from '@/api/acl/user'
import Vue from 'vue'
import { parseTime } from '@/utils'

export default {
  data() {
    return {
      listLoading: true,
      list: null,
      total: 0,
      page: 1,
      limit: 10,
      multipleSelection: []
    }
  },

  created() {
    this.fetchData()
  },

  methods: {
    changeSize(size) {
      this.limit = size
      this.fetchData(1)
    },

    addUser() {
      this.$router.push({ path: '/acl/user/add' })
    },

    fetchData(page = 1) {
      if (Vue.prototype.hasPerm('user.list')) {
        this.page = page

        user.listUsers(this.page, this.limit).then(
          response => {
            this.list = response.data.list
            this.total = response.data.total
            this.listLoading = false
          }
        )
      }
    },

    assignById(id) {
      this.$router.push(`/acl/user/role/${id}`)
    },

    updateById(id) {
      this.$router.push(`/acl/user/update/${id}`)
    },

    removeDataById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return user.removeUserById(id)
      }).then((response) => {
        this.fetchData(this.page)
        if (response.success) {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    handleSelectionChange(selection) {
      this.multipleSelection = selection
    },

    removeRows() {
      if (this.multipleSelection.length === 0) {
        this.$message({
          type: 'warning',
          message: '请选择要删除的记录!'
        })
        return
      }

      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const idList = []
        this.multipleSelection.forEach(item => {
          idList.push(item.id)
        })
        return user.removeUserBatchById(idList)
      }).then((response) => {
        this.fetchData(this.page)
        if (response.success) {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

    timeFormatter(data) {
      return parseTime(new Date(data.gmtCreate))
    }
  }
}
</script>
