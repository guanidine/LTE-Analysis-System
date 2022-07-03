import request from '@/utils/request'

export default {
  registerMember(formItem) {
    return request({
      url: `/acl/index/register`,
      method: 'post',
      data: formItem
    })
  }

}
