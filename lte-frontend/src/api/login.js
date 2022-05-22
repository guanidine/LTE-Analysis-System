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
  return asyncRoutes
  // return request({
  //   url: '/acl/index/asyncRoutes',
  //   method: 'get'
  // })
}

export const asyncRoutes = [
  {
    path: '/query',
    name: '业务查询',
    meta: { title: '业务查询', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'cell',
    name: '小区配置信息',
    meta: { title: '小区配置信息', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'kpi',
    name: 'KPI指标信息查询',
    meta: { title: 'KPI指标信息查询', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'prb',
    name: 'PRB干扰数据查询',
    meta: { title: 'PRB干扰数据查询', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: '/manage',
    name: '管理面板',
    meta: { title: '管理面板', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'data',
    name: '数据管理',
    meta: { title: '数据管理', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: '/acl',
    name: '访问权限',
    meta: { title: '访问权限', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'user/list',
    name: '用户管理',
    meta: { title: '用户管理', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'user/add',
    name: '用户添加',
    meta: { title: '用户添加', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'user/update/:id',
    name: '用户修改',
    meta: { title: '用户修改', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'user/role/:id',
    name: '用户职务',
    meta: { title: '用户职务', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'role/list',
    name: '职务管理',
    meta: { title: '职务管理', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'role/add',
    name: '职务添加',
    meta: { title: '职务添加', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'role/update/:id',
    name: '职务修改',
    meta: { title: '职务修改', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'role/distribution/:id',
    name: '职务权限',
    meta: { title: '职务权限', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  },
  {
    path: 'permission/list',
    name: '权限管理',
    meta: { title: '权限管理', roles: ['administrator', 'developer', 'maintainer', 'visitor'] }
  }
]

export function getMenu() {
  return request({
    url: '/acl/index/menu',
    method: 'get'
  })
}
