<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >

      <div class="title-container">
        <h3 class="title">LTE 网络干扰分析系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="手机号"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-tooltip v-model="capsTooltip" content="大写锁定已开启" placement="right" manual>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password"/>
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="密码"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"/>
          </span>
        </el-form-item>
      </el-tooltip>

      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLogin"
      >登录
      </el-button>

      <div style="position:relative">
        <div class="tips">
          <span>手机号 : 11100001111</span>
        </div>
        <div class="tips">
          <span style="margin-right:18px;">密码 : 111111</span>
        </div>
        <el-button class="register-button" type="primary" @click="showDialog=true">
          没有账号？点击这里注册
        </el-button>
      </div>

      <el-dialog title="注册" :visible.sync="showDialog">
        <!-- TODO: register-container设计 -->
        <div class="register-container">
          <el-form ref="registerForm" :model="registerForm" :rules="registerRules">

            <el-form-item class="input-prepend restyle no-radius" prop="nickname">
              <span class="svg-container">
                <i class="el-icon-user-solid" />
              </span>
              <el-input v-model="registerForm.nickname" type="text" placeholder="用户名"/>
            </el-form-item>

            <el-form-item class="input-prepend restyle no-radius" prop="mobile">
              <span class="svg-container">
                <i class="el-icon-phone" />
              </span>
              <el-input v-model="registerForm.mobile" type="text" placeholder="手机号"/>
            </el-form-item>

            <el-form-item class="input-prepend  restyle no-radius" prop="passwd">
              <span class="svg-container">
                <svg-icon icon-class="password" />
              </span>
              <el-input v-model="registerForm.passwd" type="password" placeholder="密码"/>
            </el-form-item>

            <el-form-item class="input-prepend" prop="passwdValidator">
              <span class="svg-container">
                <svg-icon icon-class="password" />
              </span>
              <el-input v-model="registerForm.passwdValidator" type="password" placeholder="请再输一遍"/>
            </el-form-item>

            <div class="btn">
              <input type="button" class="sign-up-button" value="注册" @click="handleRegister()">
            </div>
          </el-form>
        </div>
      </el-dialog>

    </el-form>
  </div>
</template>

<script>
import registerApi from '@/api/login/register'

export default {
  name: 'Login',
  data() {
    const validateMobile = (rule, value, callback) => {
      if (!(/^1\d{10}$/.test(value))) {
        callback(new Error('手机号格式不正确'))
      } else {
        callback()
      }
    }
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
    const checkPasswd = (rule, value, callback) => {
      if (this.registerForm.passwd !== this.registerForm.passwdValidator) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateMobile }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      registerForm: {
        mobile: '',
        nickname: '',
        passwd: '',
        passwdValidator: ''
      },
      registerRules: {
        mobile: [{ required: true, trigger: 'blur', validator: validateMobile }],
        nickname: [{ required: true, trigger: 'blur', validator: validateNickname }],
        passwd: [{ required: true, trigger: 'blur', validator: validatePassword }],
        passwdValidator: [{ required: true, trigger: 'blur', validator: checkPasswd }]
      },
      loading: false,
      passwordType: 'password',
      capsTooltip: false,
      showDialog: false,
      redirect: undefined

    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  mounted() {
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  methods: {
    // FIXME: 这大写锁定的判定不太对啊，shift+AAA也有提示。。。
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          registerApi.registerMember({
            mobile: this.registerForm.mobile,
            nickname: this.registerForm.nickname,
            passwd: this.registerForm.passwd
          })
            .then(response => {
              this.$message({
                type: 'success',
                message: '注册成功，请等待管理员为您激活账号'
              })
              this.showDialog = false
            })
        }
      })
    }

  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0;
      -webkit-appearance: none;
      border-radius: 0;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0 auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .register-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .register-button {
      display: none;
    }
  }
}
</style>
