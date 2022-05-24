import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/acl/login',
    method: 'post',
    data: {
      name: username,
      password: password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/acl/index/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/acl/index/logout',
    method: 'post'
  })
}

export function getRoutes() {
  return request({
    url: '/acl/index/routes',
    method: 'get'
  })
}

export function getAsyncRoutes() {
  return request({
    url: '/acl/index/asyncRoutes',
    method: 'get'
  })
}
