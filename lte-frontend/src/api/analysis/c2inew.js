import request from '@/utils/request'

export function getTotal() {
  return request({
    url: `/biz/c2inew/total`,
    method: 'get'
  })
}

export function getList(page, limit) {
  return request({
    url: `/biz/c2inew/list`,
    method: 'get',
    params: {
      page: page,
      limit: limit
    }
  })
}
export function analyzeTbC2i(x) {
  return request({
    url: `/biz/c2inew/create`,
    method: 'post',
    params: {
      x: x
    }
  })
}
