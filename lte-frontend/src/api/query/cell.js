import request from '@/utils/request'

export default {

  getCellList(page, limit, cellQuery) {
    return request({
      url: `/business-query/cell/condition/${page}/${limit}`,
      method: 'post',
      data: cellQuery
    })
  },

  getCellSectors() {
    return request({
      url: '/business-query/cell/all-sector',
      method: 'get'
    })
  },

  getCellEnodebs() {
    return request({
      url: '/business-query/cell/all-enodeb',
      method: 'get'
    })
  }

}
