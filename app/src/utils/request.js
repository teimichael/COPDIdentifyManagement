import axios from 'axios'
import Message from 'muse-ui-message'
import Toast from 'muse-ui-toast'
import store from '../store'
import { getToken } from '@/utils/auth'

const service = axios.create({
  baseURL: process.env.BASE_API,
  withCredentials: true,
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['Authorization'] = getToken()
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code !== 200) {
      if (res.code === 403) {
        Toast.error('Authentication failed, please retry')
      } else {
        Message.alert('System failure', 'Warning').then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
