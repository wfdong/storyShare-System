import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/jokes/login',
    method: 'post',
    // headers: {
    //   'Content-Type': 'application/json;charset=UTF-8'
    // },
    data
  })
}

/*
export function userRegister(data) {
  return request({
    url: '/jokes/userRegister',
    method: 'post',
     //headers: {
       //'Content-Type': 'application/json;charset=UTF-8'
     //  'Access-Control-Allow-Origin': '*'
     //},
   data
  })
}
*/

export function getInfo(token) {
  return request({
    url: '/jokes/userinfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/jokes/logout',
    method: 'post'
  })
}
