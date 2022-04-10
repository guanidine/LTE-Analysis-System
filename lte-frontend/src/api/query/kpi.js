import request from '@/utils/request'

export default {

  listData(kpiQuery) {
    return request({
      url: '/business-query/kpi/',
      method: 'post',
      data: {
        'sectorName': kpiQuery.sectorName,
        'field': kpiQuery.field,
        'begin': kpiQuery.time[0],
        'end': kpiQuery.time[1]
      }
    })
  },

  listSectors() {
    return request({
      url: '/business-query/kpi/all-sector',
      method: 'get'
    })
  }

}
