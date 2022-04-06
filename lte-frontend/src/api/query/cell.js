import request from '@/utils/request'

export default {

  listCells(page, limit, cellQuery) {
    return request({
      url: `/business-query/cell/condition/${page}/${limit}`,
      method: 'post',
      data: cellQuery
    })
  },

  listSectors() {
    return request({
      url: '/business-query/cell/all-sector',
      method: 'get'
    })
  },

  listEnodebs() {
    return request({
      url: '/business-query/cell/all-enodeb',
      method: 'get'
    })
  }

}
