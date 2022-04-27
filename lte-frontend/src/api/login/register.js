import request from '@/utils/request'

export default {
  registerMember(formItem) {
    return request({
      url: `/ucenter/member/register`,
      method: 'post',
      data: formItem
    })
  }

}
