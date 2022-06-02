<template>
  <el-input v-model="fileName" placeholder="请输入文件名" class="input-with-select">
    <el-select slot="prepend" v-model="select" placeholder="请选择">
      <el-option
        v-for="(item, index) in names"
        :key="index"
        :label="item"
        :value="index"
      />
    </el-select>
    <el-button slot="append" icon="el-icon-download" type="success" @click="download" />
  </el-input>
</template>

<script>

export default {
  props: {
    names: {
      type: Array,
      required: true
    },
    data: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      fileName: '',
      select: 0
    }
  },
  methods: {
    download() {
      const data = this.data[this.select]
      var url = ''
      if (this.select === 0) {
        const blobData = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' })
        url = window.URL.createObjectURL(blobData)
      } else {
        url = data
      }
      const link = document.createElement('a')
      link.download = decodeURIComponent(this.fileName)
      link.href = url
      link.click()
    }
  }
}
</script>

<style>
  .el-select .el-input {
    width: 150px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>
