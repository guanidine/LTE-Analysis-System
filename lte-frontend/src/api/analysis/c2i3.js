import request from '@/utils/request'

export function getTotal(limit) {
  return request({
    url: `/biz/c2i3/total`,
    method: 'get'
  })
}

export function getList(page, limit) {
  return request({
    url: `/biz/c2i3/list`,
    method: 'get',
    params: {
      page: page,
      limit: limit
    }
  })
}

export function analyzeTbC2i3(x) {
  return request({
    url: `/biz/c2i3/create`,
    method: 'post',
    params: {
      x: x
    }
  })
}
