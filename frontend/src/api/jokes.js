import request from '@/utils/request'


/*
export function dynamicList(data) {
    return request({
      url: '/jokes/index',
      method: 'get',
      params:data,
      // data
    })
  }
  */

export function addAJoke(data) {
  return request({
    url: '/jokes/addJokes',
    method: 'post',
    params: data
  })
}

export function addALike(data) {
  return request({
    url: '/jokes/addALike',
    method: 'post',
    params: data
  })
}

export function addADisLike(data) {
  return request({
    url: '/jokes/addADisLike',
    method: 'post',
    params: data
  })
}

export function submitMyNewComment(data) {
  return request({
    url: '/jokes/addComments',
    method: 'post',
    params: data
  })
}

/*
export function getSpecificItem(data) {
  return request({
    url: '/jokes/getSpecificItem',
    method: 'post',
    params: data
  })
}
*/

//申请记录查询
export function listStandingBookMyself(data) {
  return request({
    //url: '/user/listmyselfrecord',
    url: '/user/listpersonalloaninfo',
    method: 'post',
    params: data
  })
}
