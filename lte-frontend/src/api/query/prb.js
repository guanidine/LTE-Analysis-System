import request from '@/utils/request'

export default {

  listData(prbQuery) {
    return request({
      url: '/biz/prb/',
      method: 'post',
      data: {
        'enodebName': prbQuery.enodebName,
        'field': prbQuery.field,
        'begin': prbQuery.time[0],
        'end': prbQuery.time[1]
      }
    })
  },

  listHourData(prbQuery) {
    return request({
      url: 'biz/prb/hour',
      method: 'post',
      data: {
        'enodebName': prbQuery.enodebName,
        'field': 'noise' + prbQuery.field.match(/[^\d]*(\d+)[^\d]*/)[1],
        'begin': prbQuery.time[0],
        'end': prbQuery.time[1]
      }
    })
  },

  listEnodebs() {
    return request({
      url: '/biz/prb/all-enodeb',
      method: 'get'
    })
  },

  createTbPrbNew() {
    return request({
      url: '/biz/prb/create/tbprbnew',
      method: 'post'
    })
  }

}
