import request from '@/utils/request'

export default {
  updateMember(formItem) {
    return request({
      url: `/acl/user/update2`,
      method: 'post',
      data: formItem
    })
  }

}
