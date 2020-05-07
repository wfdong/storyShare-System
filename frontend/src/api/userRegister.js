import request from '@/utils/requestInternet'
//import request from '@/utils/request'

export function userRegister(data) {
  return request({
    url: '/jokes/userRegister',
    method: 'post',
     /*headers: {
       //'Content-Type': 'application/json;charset=UTF-8'
       'Access-Control-Allow-Origin': '*'
     },*/
   data
  })
}

