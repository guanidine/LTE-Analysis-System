<template>
  <div class="app-container">
    <el-form ref="role" :model="role" :rules="validateRules" label-width="120px">
      <el-form-item label="职务名称" prop="name">
        <el-input v-model="role.name"/>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="role.remark"/>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import roleApi from '@/api/acl/role'
import Vue from 'vue'

const defaultForm = {
  name: '',
  remark: ''
}

export default {
  data() {
    return {
      role: defaultForm,
      saveBtnDisabled: true,
      validateRules: {
        name: [{ required: true, trigger: 'blur', message: '职务名不能为空' }]
      }
    }
  },

  watch: {
    $route(to, from) {
      this.init()
    }
  },

  created() {
    this.init()
  },

  methods: {
    init() {
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id
        this.saveBtnDisabled = !Vue.prototype.hasPerm('role.update')
        this.fetchDataById(id)
      } else {
        this.saveBtnDisabled = !Vue.prototype.hasPerm('role.add')
        this.role = { ...defaultForm }
      }
    },

    saveOrUpdate() {
      this.$refs.role.validate(valid => {
        if (valid) {
          this.saveBtnDisabled = true
          if (!this.role.id) {
            this.saveData()
          } else {
            this.updateData()
          }
        }
      })
    },

    saveData() {
      roleApi.saveRole(this.role).then(response => {
        if (response.success) {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.$router.push({ path: '/acl/role/list' })
        }
      }).catch(error => {
        this.saveBtnDisabled = !Vue.prototype.hasPerm('role.add')
        console.log(error)
      })
    },

    updateData() {
      roleApi.updateRole(this.role).then(response => {
        if (response.success) {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.$router.push({ path: '/acl/role/list' })
        }
      }).catch(error => {
        this.saveBtnDisabled = !Vue.prototype.hasPerm('role.update')
        console.log(error)
      })
    },

    fetchDataById(id) {
      roleApi.getRoleById(id).then(response => {
        this.role = response.data.item
      })
    }

  }
}
</script>
