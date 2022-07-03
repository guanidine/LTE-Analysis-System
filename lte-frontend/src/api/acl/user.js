import request from '@/utils/request'

const api_name = '/acl/user'

export default {
  listUsers(page, limit) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get'
    })
  },
  getUserById(id) {
    return request({
      url: `${api_name}/get/${id}`,
      method: 'get'
    })
  },
  saveUser(user) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: user
    })
  },
  updateUser(user) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: user
    })
  },
  removeUserById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
    })
  },
  removeUserBatchById(idList) {
    return request({
      url: `${api_name}/batch-remove`,
      method: 'delete',
      data: idList
    })
  },
  listRolesByUserId(userId) {
    return request({
      url: `${api_name}/${userId}`,
      method: 'get'
    })
  },
  assignRolesByUserId(userId, roleId) {
    return request({
      url: `${api_name}/assign/${userId}`,
      method: 'post',
      data: roleId
    })
  }
}
