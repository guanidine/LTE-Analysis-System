import request from '@/utils/request'

export default {

  download(response, fileName) {
    fileName += `-${response.headers['filename']}`
    const binaryData = []
    binaryData.push(response.data)
    const url = window.URL.createObjectURL(new Blob(binaryData))
    const link = document.createElement('a')
    link.download = decodeURIComponent(fileName)
    link.href = url
    link.click()
  },

  listTables() {
    return request({
      url: '/biz/database',
      method: 'get'
    })
  }

}
