import request from '@/utils/request'

export default {

  listData(prbQuery) {
    return request({
      url: '/business-query/prb/',
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
      url: '/business-query/prb/hour',
      method: 'post',
      data: {
        'enodebName': prbQuery.enodebName,
        'field': prbQuery.field,
        'begin': prbQuery.time[0],
        'end': prbQuery.time[1]
      }
    })
  },

  listEnodebs() {
    return request({
      url: '/business-query/prb/all-enodeb',
      method: 'get'
    })
  }

}
