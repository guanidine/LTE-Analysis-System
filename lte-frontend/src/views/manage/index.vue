<template>
  <div class="app-container">
    超简朴的数据导入：上传/下载请勿同时进行，OOM警告。。。

    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <!-- 操作的数据表 -->
        <el-select
          v-model="tableName"
          filterable
          placeholder="请选择要操作的数据表"
          @change="onChange"
        >
          <el-option
            v-for="item in tableList"
            :key="item.tablename"
            :value="item.tablename"
          />
        </el-select>
      </el-form-item>
    </el-form>

    <el-upload
      ref="upload"
      :auto-upload="false"
      action="#"
      drag
      :http-request="uploadHttpRequest"
      :on-change="onFileChange"
      :on-remove="()=>changeFileStatus(false)"
      type="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    >
      <i class="el-icon-upload"/>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div slot="tip" class="el-upload__tip">只能上传xlsx格式文件</div>
    </el-upload>

    <div slot="footer" class="dialog-footer">
      <el-link :href="tableName+'模板.xlsx'" :disabled="downloadBtnDisabled">
        <el-button :disabled="downloadBtnDisabled">
          <i class="el-icon-download"/>
          点击下载模版
        </el-button>
      </el-link>
      <el-button type="primary" :disabled="uploadBtnDisabled" @click="submitUpload">
        <i class="el-icon-upload el-icon--right"></i>
        {{ fileUploadBtnText }}
      </el-button>
    </div>

    <el-progress v-show="showProgress" :percentage="processLength" :stroke-width="2"/>

    超简朴的数据导出

    <div slot="footer" class="dialog-footer">
      <el-button type="primary" :disabled="downloadBtnDisabled" @click="downloadHttpRequest">
        <i class="el-icon-download el-icon--right"></i>
        导出
      </el-button>
    </div>

  </div>
</template>
<script>
import axios from 'axios'
import dataApi from '@/api/manage/data'

export default {
  data() {
    return {
      BASE_API: process.env.VUE_APP_BASE_API,
      fileUploadBtnText: '上传到服务器',
      errFile: null,
      dialogOfUpload: false,
      processLength: 0,
      showProgress: false,
      hasFile: false,
      uploadBtnDisabled: true,

      tableList: {
        tablename: ''
      },
      tableName: '',
      downloadBtnDisabled: true
    }
  },
  created() {
    this.listTables()
  },
  methods: {
    // ============================================= 上传 =============================================
    submitUpload() {
      this.fileUploadBtnText = '正在上传'
      this.uploadBtnDisabled = true
      this.$refs.upload.submit()
      this.handleChange()
    },
    fileUploadSuccess(error) {
      if (error === '0') {
        this.$message({
          target: 'success',
          message: '数据上传成功'
        })
      } else if (error === '-1') {
        this.$message({
          target: 'error',
          message: '解析Excel出错，请传入正确格式的Excel'
        })
      } else if (error > 0) {
        this.$message({
          target: 'success',
          message: `${error}行数据导入出错，下载错误日志查看原因！`
        })
      } else {
        this.$message({
          target: 'error',
          message: '呐，出错了...'
        })
      }
      this.processLength = 100
      this.uploadBtnDisabled = false
      this.fileUploadBtnText = '上传至服务器'
      // this.$router.push({ path: '/query/dashboard' })
    },
    fileUploadError() {
      this.$message({
        target: 'error',
        message: '数据上传失败'
      })
    },
    // FIXME: 震惊！这进度条居然是假货（doge）
    handleChange() {
      this.processLength = 0
      this.showProgress = true
      const interval = setInterval(() => {
        if (this.processLength >= 99) {
          clearInterval(interval)
          return
        }
        this.processLength++
      }, 50)
    },
    changeFileStatus(status) {
      this.hasFile = status
      this.uploadBtnDisabled = !this.hasFile || this.tableName === ''
    },
    // 自定义上传方法
    uploadHttpRequest(param) {
      const formData = new FormData()
      formData.append('file', param.file)
      const table = this.tableName.substring(2).toLowerCase()
      const url = `${this.BASE_API}/business-query/${table}/upload`
      axios.post(url, formData, {
        responseType: 'arraybuffer'
      }).then(response => {
        if (response.headers['error'] > '0') {
          dataApi.download(response, `${this.tableName}-error`)
        }
        this.fileUploadSuccess(response.headers['error'])
      }).catch(err => {
        console.log(`Error----->${err}`)
        param.onError() // 上传失败的文件会从文件列表中删除
        this.fileUploadError()
      })
    },
    // ============================================= 下载 =============================================
    downloadHttpRequest() {
      const table = this.tableName.substring(2).toLowerCase()
      const url = `${this.BASE_API}/business-query/${table}/download`
      axios.get(url, {
        responseType: 'arraybuffer'
      }).then(response => {
        dataApi.download(response, `${this.tableName}`)
      })
    },
    // ============================================= 选择操作数据表 =============================================
    listTables() {
      dataApi.listTables()
        .then(response => {
          this.tableList = response.data.list
        })
    },
    onChange() {
      this.uploadBtnDisabled = !this.hasFile || this.tableName === ''
      this.downloadBtnDisabled = this.tableName === ''
    },
    onFileChange(file, fileList) {
      this.changeFileStatus(true)
      if (fileList.length > 1) {
        fileList.splice(0, 1)
      }
    }
  }
}
</script>
