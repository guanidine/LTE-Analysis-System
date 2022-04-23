import request from '@/utils/request'

export default {

  listCells(page, limit, cellQuery) {
    return request({
      url: `/biz/cell/condition/${page}/${limit}`,
      method: 'post',
      data: cellQuery
    })
  },

  listSectors() {
    return request({
      url: '/biz/cell/all-sector',
      method: 'get'
    })
  },

  listEnodebs() {
    return request({
      url: '/biz/cell/all-enodeb',
      method: 'get'
    })
  }

}
