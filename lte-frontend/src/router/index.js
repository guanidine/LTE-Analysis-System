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
  },

  {
    path: '/query',
    component: Layout,
    redirect: '/query/dashboard',
    name: '业务查询',
    meta: { title: '业务查询', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: 'Dashboard', icon: 'dashboard' }
      },
      {
        path: 'cell',
        name: '小区配置信息',
        component: () => import('@/views/query/cell'),
        meta: { title: '小区配置信息', icon: 'table' }
      },
      {
        path: 'kpi',
        name: 'KPI指标信息查询',
        component: () => import('@/views/query/kpi'),
        meta: { title: 'KPI指标信息查询', icon: 'dashboard' }
      },
      {
        path: 'prb',
        name: 'PRB干扰数据查询',
        component: () => import('@/views/query/prb'),
        meta: { title: 'PRB干扰数据查询', icon: 'dashboard' }
      }
    ]
  },

  {
    path: '/manage',
    component: Layout,
    redirect: '/manage/dashboard',
    name: '管理面板',
    meta: { title: '管理面板', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'import',
        name: '数据管理',
        component: () => import('@/views/manage'),
        meta: { title: '数据管理', icon: 'table' }
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

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  mode: 'hash',
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
