import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/ucenter/member/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/ucenter/member/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/ucenter/member/logout',
    method: 'post'
  })
}
