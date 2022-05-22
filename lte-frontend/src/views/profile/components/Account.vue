<template>
  <el-form ref="updateForm" :model="updateForm" :rules="updateRules">
    <el-form-item label="新昵称" prop="name">
      <el-input v-model="updateForm.name" type="text"/>
    </el-form-item>
    <el-form-item label="新密码" prop="password">
      <el-input v-model="updateForm.password" type="password"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">Update</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import updateApi from '@/api/login/update'

export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          originName: '',
          name: '',
          avatar: ''
        }
      }
    }
  },
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码不少于6位'))
      } else {
        callback()
      }
    }
    const validateNickname = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('用户名为必填项'))
      } else {
        callback()
      }
    }
    return {
      updateForm: {
        originName: this.user.name,
        name: this.user.name,
        password: ''
      },
      updateRules: {
        name: [{ required: true, trigger: 'blur', validator: validateNickname }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      }
    }
  },
  methods: {
    submit() {
      this.$refs.updateForm.validate(valid => {
        if (valid) {
          updateApi.updateMember(this.updateForm)
            .then(response => {
              this.$message({
                type: 'success',
                message: '用户信息修改成功'
              })
            })
        }
      })
    }
  }
}
</script>
