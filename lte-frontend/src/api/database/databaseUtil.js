import request from '@/utils/request'

export function getArchitecture(table) {
  return request({
    url: `/biz/database/${table}`,
    method: 'get'
  })
}

export function getSize(table) {
  return request({
    url: `biz/database/${table}/size`,
    method: 'get'
  })
}

export function getFilePath(table) {
  return request({
    url: `biz/database/${table}/file`,
    method: 'get'
  })
}

export function updateAPI(init, min, max) {
  return request({
    url: `/biz/druid/update`,
    method: 'post',
    params: {
      initialSize: Number(init),
      minIdle: Number(min),
      maxArtive: Number(max)
    }
  })
}

export function showAPI() {
  return request({
    url: `/biz/druid`,
    method: 'get'
  })
}
// export function analyzeTbC2i(x) {
//   return request({
//     url: `/biz/c2inew/create`,
//     method: 'post',
//     params: {
//       x: x
//     }
//   })
// }
