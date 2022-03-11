import request from '@/utils/request'

export default {

  getCellList(page, limit, cellQuery) {
    return request({
      url: `/business-query/cell/condition/${page}/${limit}`,
      method: 'post',
      data: cellQuery
    })
  },

  getCellNameList() {
    return request({
      url: '/business-query/cell/all',
      method: 'get'
    })
  }

}
