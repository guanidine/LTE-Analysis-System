import request from '@/utils/request'

const api_name = '/acl/permission'

export default {
  listPermissions() {
    return request({
      url: `${api_name}`,
      method: 'get'
    })
  },
  listPermissionsById(roleId) {
    return request({
      url: `${api_name}/${roleId}`,
      method: 'get'
    })
  },
  savePermission(menu) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: menu
    })
  },
  updatePermission(menu) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: menu
    })
  },
  removePermissionsById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
    })
  },
  assignPermissionsById(roleId, permissionIds) {
    return request({
      url: `${api_name}/assign/${roleId}`,
      method: 'post',
      data: permissionIds
    })
  }
}
