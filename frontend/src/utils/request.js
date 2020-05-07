import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
// import { getToken } from '@/utils/auth'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 15000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data

    console.log("res:" + JSON.stringify(res))
    // if the custom code is not 20000, it is judged as an error.
    if (res.code != null && res.code != 20000) {
      console.log("res2:" + JSON.stringify(res))
      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        // Message({
        //   message: '会话过期，请重新登录',
        //   type: 'error',
        //   duration: 5 * 1000
        // }).then(() => {
        //     store.dispatch('user/resetToken').then(() => {
        //       location.reload()
        //     })
        //   })
        // store.dispatch('user/resetToken').then(() => {
        //   location.reload()
        // })
        MessageBox.confirm('you have logout, please relogin', 'Confirm logout', {
          confirmButtonText: 'relogin',
          cancelButtonText: 'cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      } else if(res.code === 40001){
        res.msg = 'sorry, have no access';
        Message({
          message: res.msg || 'sorry, have no access',
          type: 'error',
          duration: 5 * 2000
        })
      } else{
        res.msg = 'network error';
        Message({
          message: res.msg || 'network error',
          type: 'error',
          duration: 5 * 2000
        })
      }
      return Promise.reject(res.msg || 'network error')
    } else {
      return response
    }
  },
  error => {
    console.log('err3:' + error) // for debug
    Message({
      message:response.msg || '网络延时，请稍后再试',
      type: 'error',
      duration: 5 * 2000
    })
    // removeToken()
    // resetRouter()
    // window.location = "/#/login"
    return Promise.reject(error)
  }
)


export default service
