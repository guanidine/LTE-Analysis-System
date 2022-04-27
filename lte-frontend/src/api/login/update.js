import request from '@/utils/request'

export default {
  updateMember(formItem) {
    return request({
      url: `/ucenter/member/update`,
      method: 'post',
      data: formItem
    })
  }

}
