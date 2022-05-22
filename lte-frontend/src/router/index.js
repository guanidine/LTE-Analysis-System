import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  }
]

export const asyncRoutes = [
  {
    path: '/query',
    component: Layout,
    redirect: '/query/dashboard',
    name: '业务查询',
    meta: { title: '业务查询', icon: 'el-icon-s-help', roles: [] },
    children: [
      {
        path: 'cell',
        name: '小区配置信息',
        component: () => import('@/views/query/cell'),
        meta: { title: '小区配置信息', icon: 'table', roles: [] }
      },
      {
        path: 'kpi',
        name: 'KPI指标信息查询',
        component: () => import('@/views/query/kpi'),
        meta: { title: 'KPI指标信息查询', icon: 'dashboard', roles: [] }
      },
      {
        path: 'prb',
        name: 'PRB干扰数据查询',
        component: () => import('@/views/query/prb'),
        meta: { title: 'PRB干扰数据查询', icon: 'dashboard', roles: [] }
      }
    ]
  },

  {
    path: '/manage',
    component: Layout,
    redirect: '/manage/dashboard',
    name: '管理面板',
    meta: { title: '管理面板', icon: 'el-icon-s-help', roles: [] },
    children: [
      {
        path: 'data',
        name: '数据管理',
        component: () => import('@/views/manage/data'),
        meta: { title: '数据管理', icon: 'table', roles: [] }
      }
    ]
  },

  {
    path: '/acl',
    component: Layout,
    redirect: '/acl/user/list',
    name: '访问权限',
    meta: { title: '访问权限', icon: 'el-icon-s-help', roles: [] },
    children: [
      {
        path: 'user/list',
        name: '用户管理',
        component: () => import('@/views/acl/user/list'),
        meta: { title: '用户管理', icon: 'table', roles: [] }
      },
      {
        path: 'user/add',
        name: '用户添加',
        component: () => import('@/views/acl/user/form'),
        meta: { title: '用户添加', roles: [] },
        hidden: true
      },
      {
        path: 'user/update/:id',
        name: '用户修改',
        component: () => import('@/views/acl/user/form'),
        meta: { title: '用户修改', roles: [] },
        hidden: true
      },
      {
        path: 'user/role/:id',
        name: '用户职务',
        component: () => import('@/views/acl/user/roleForm'),
        meta: { title: '用户职务', roles: [] },
        hidden: true
      },
      {
        path: 'role/list',
        name: '职务管理',
        component: () => import('@/views/acl/role/list'),
        meta: { title: '职务管理', icon: 'table', roles: [] }
      },
      {
        path: 'role/add',
        name: '职务添加',
        component: () => import('@/views/acl/role/form'),
        meta: { title: '职务添加', roles: [] },
        hidden: true
      },
      {
        path: 'role/update/:id',
        name: '职务修改',
        component: () => import('@/views/acl/role/form'),
        meta: { title: '职务修改', roles: [] },
        hidden: true
      },
      {
        path: 'role/distribution/:id',
        name: '职务权限',
        component: () => import('@/views/acl/role/roleForm'),
        meta: { title: '职务权限', roles: [] },
        hidden: true
      },
      {
        path: 'permission/list',
        name: '权限管理',
        component: () => import('@/views/acl/permission/list'),
        meta: { title: '权限管理', icon: 'table', roles: [] }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://gitee.com/pengxiaokay/lte-analysis-system',
        meta: { title: '项目源码', icon: 'link' }
      }
    ]
  },

  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: '个人中心',
        meta: { title: '个人中心', icon: 'user', noCache: true }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
