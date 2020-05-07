import request from '@/utils/requestInternetIndex'



export function dynamicList(data) {
    return request({
      url: '/jokes/index',
      method: 'get',
      params:data,
      // data
    })
  }

export function getSpecificItem(data) {
  return request({
    url: '/jokes/getSpecificItem',
    method: 'post',
    params: data
  })
}

