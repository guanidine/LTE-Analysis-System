<template>
  <div class="app-container">
    <el-form ref="user" :model="user" :rules="validateRules" label-width="120px">
      <el-form-item label="用户名" prop="name">
        <el-input v-model="user.name"/>
      </el-form-item>
      <el-form-item label="用户头像" prop="avatar">
        <el-input v-model="user.avatar"/>
      </el-form-item>
      <el-form-item v-if="!user.id" label="用户密码" prop="password">
        <el-input v-model="user.password"/>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import userApi from '@/api/acl/user'
import Vue from 'vue'

const defaultForm = {
  name: '',
  password: '',
  avatar: ''
}

const validatePass = (rule, value, callback) => {
  if (value.length < 6) {
    callback(new Error('密码不能小于6位'))
  } else {
    callback()
  }
}

export default {
  data() {
    return {
      user: defaultForm,
      saveBtnDisabled: true,
      validateRules: {
        name: [{ required: true, trigger: 'blur', message: '用户名必须输入' }],
        password: [{ required: true, trigger: 'blur', validator: validatePass }]
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
        this.saveBtnDisabled = !Vue.prototype.hasPerm('user.update')
        this.fetchDataById(id)
      } else {
        this.saveBtnDisabled = !Vue.prototype.hasPerm('user.add')
        this.user = { ...defaultForm }
      }
    },

    saveOrUpdate() {
      this.$refs.user.validate(valid => {
        if (valid) {
          this.saveBtnDisabled = true
          if (!this.user.id) {
            this.saveData()
          } else {
            this.updateData()
          }
        }
      })
    },

    saveData() {
      userApi.saveUser(this.user).then(response => {
        if (response.success) {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.$router.push({ path: '/acl/user/list' })
        }
      }).catch(error => {
        this.saveBtnDisabled = !Vue.prototype.hasPerm('user.add')
        console.log(error)
      })
    },

    updateData() {
      userApi.updateUser(this.user).then(response => {
        if (response.success) {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.$router.push({ path: '/acl/user/list' })
        }
      }).catch(error => {
        this.saveBtnDisabled = !Vue.prototype.hasPerm('user.update')
        console.log(error)
      })
    },

    fetchDataById(id) {
      userApi.getUserById(id).then(response => {
        this.user = response.data.item
      })
    }

  }
}
</script>
