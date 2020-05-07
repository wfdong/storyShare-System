import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
// import { getToken } from '@/utils/auth'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  //withCredentials: true, // send cookies when cross-domain requests
  withCredentials: false,
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
      config.headers['X-Token'] = null
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

      Message({
        message: res.data,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(res.data)
    } else {
      
        /*
      Message({
        message: res.data,
        type: 'success',
        duration: 5 * 1000
      });
      */
      return response
    }
  },
  error => {
    console.log('err3:' + error) // for debug
    Message({
      message:response.msg || '网络延时，请稍后再试',
      type: 'error',
      duration: 5 * 1000
    })
    // removeToken()
    // resetRouter()
    // window.location = "/#/login"
    return Promise.reject(error)
  }
)


export default service
