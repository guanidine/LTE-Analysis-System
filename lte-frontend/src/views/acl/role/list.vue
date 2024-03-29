<template>
  <div v-if="hasPerm('role.list')" class="app-container">
    <div>
      <el-button v-if="hasPerm('role.add')" type="primary" size="medium" plain @click="addUser()">添加</el-button>
      <el-button v-if="hasPerm('role.remove')" type="danger" size="medium" plain @click="removeRows()">批量删除</el-button>
    </div>

    <div style="margin: 15px 0;"/>

    <el-table v-loading="listLoading" :data="list" stripe style="width: 100%" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="55"/>

      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="职务名称"/>
      <el-table-column prop="remark" label="备注"/>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-row>
            <el-button
              v-if="hasPerm('role.acl')"
              type="warning"
              size="small"
              icon="el-icon-s-operation"
              circle
              plain
              @click="assignById(scope.row.id)"
            >
            </el-button>
            <el-button
              v-if="hasPerm('role.update')"
              type="primary"
              size="small"
              icon="el-icon-edit"
              circle
              plain
              @click="updateById(scope.row.id)"
            >
            </el-button>
            <el-button
              v-if="hasPerm('role.remove')"
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
import roleApi from '@/api/acl/role'
import Vue from 'vue'

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
      this.$router.push({ path: '/acl/role/add' })
    },

    fetchData(page = 1) {
      if (Vue.prototype.hasPerm('role.list')) {
        this.page = page

        roleApi.listRoles(this.page, this.limit).then(
          response => {
            this.list = response.data.list
            this.total = response.data.total
            this.listLoading = false
          }
        )
      }
    },

    assignById(id) {
      this.$router.push(`/acl/role/distribution/${id}`)
    },

    updateById(id) {
      this.$router.push(`/acl/role/update/${id}`)
    },

    removeDataById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return roleApi.removeRoleById(id)
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
        return roleApi.removeRoleBatchById(idList)
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
    }
  }
}
</script>

