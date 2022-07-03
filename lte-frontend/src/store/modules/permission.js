import { asyncRoutes, constantRoutes } from '@/router'
import { getAsyncRoutes } from '@/api/login'
import { deepClone } from '../../utils/index'

const clientRoutes = deepClone(asyncRoutes)

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * @param clientAsyncRoutes 前端保存动态路由
 * @param serverRouter 后端保存动态路由
 */
function makePermissionRouters(serverRouter, clientAsyncRoutes) {
  clientAsyncRoutes.map(ele => {
    if (!ele.name || (!ele.meta && !ele.meta.roles)) return
    let roles_obj = null
    for (let i = 0; i < serverRouter.length; i++) {
      const element = serverRouter[i]
      if (ele.name === element.name) {
        roles_obj = element
      }
    }
    if (roles_obj !== null) {
      ele.meta.roles = roles_obj.meta.roles
    }

    if (ele.children) {
      makePermissionRouters(serverRouter, ele.children)
    }
  })
  return clientAsyncRoutes
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  async generateRoutes({ commit }, roles) {
    let permissionRouters = await getAsyncRoutes().then(res => {
      const data = res.data.list
      permissionRouters = makePermissionRouters(data, clientRoutes)
      return permissionRouters
    })
    return new Promise(resolve => {
      let accessedRoutes
      if (roles.includes('admin')) {
        accessedRoutes = permissionRouters || []
      } else {
        accessedRoutes = filterAsyncRoutes(permissionRouters, roles)
      }
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
