<template>
  <div class="app-container">
    <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAllChange">全选</el-checkbox>
    <div style="margin: 15px 0;"></div>
    <el-checkbox-group v-model="checkedRoles" @change="handleCheckedCitiesChange">
      <el-checkbox v-for="city in roles" :key="city.id" :label="city.id">{{ city.name }}</el-checkbox>
    </el-checkbox-group>
    <el-button :disabled="saveBtnDisabled" type="primary" @click="update">保存</el-button>
  </div>
</template>

<script>

import userApi from '@/api/acl/user'
import Vue from 'vue'

export default {
  data() {
    return {
      checkAll: false,
      checkedRoles: [],
      roles: [],
      isIndeterminate: true,
      userId: '',
      saveBtnDisabled: true
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      if (this.$route.params && this.$route.params.id) {
        this.userId = this.$route.params.id
        this.getById(this.userId)
        this.saveBtnDisabled = !Vue.prototype.hasPerm('user.assign')
      }
    },
    getById(userId) {
      userApi.listRolesByUserId(userId).then(response => {
        const jsonObj = response.data.assignRoles
        this.checkedRoles = this.getJsonToList(jsonObj, 'id')
        this.roles = response.data.allRolesList
      })
    },
    getJsonToList(json, key) {
      const list = JSON.parse(JSON.stringify(json))
      const strText = []
      for (let i = 0; i < list.length; i++) {
        strText.push(list[i][key])
      }
      return strText
    },
    handleCheckAllChange(val) {
      this.checkedRoles = val ? this.roles : []
      this.isIndeterminate = false
    },
    handleCheckedCitiesChange(value) {
      const checkedCount = value.length
      this.checkAll = checkedCount === this.roles.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.roles.length
    },
    update() {
      this.saveBtnDisabled = true // 防止表单重复提交
      const ids = []
      this.checkedRoles.forEach(id => {
        ids.push(id)
      })
      userApi.assignRolesByUserId(this.userId, ids).then(response => {
        if (response.success) {
          this.$message({
            type: 'success',
            message: '保存成功'
          })
          this.$router.push({ path: '/acl/user/list' })
        }
      }).catch(error => {
        this.saveBtnDisabled = !Vue.prototype.hasPerm('user.assign')
        console.log(error)
      })
    }
  }
}
</script>
