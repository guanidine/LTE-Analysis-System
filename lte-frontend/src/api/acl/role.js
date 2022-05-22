import request from '@/utils/request'

const api_name = '/acl/role'

export default {
  listRoles(page, limit) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get'
    })
  },
  getRoleById(id) {
    return request({
      url: `${api_name}/get/${id}`,
      method: 'get'
    })
  },
  saveRole(role) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: role
    })
  },
  updateRole(role) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: role
    })
  },
  removeRoleById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
    })
  },
  removeRoleBatchById(ids) {
    return request({
      url: `${api_name}/batch-remove`,
      method: 'delete',
      data: ids
    })
  }
}
