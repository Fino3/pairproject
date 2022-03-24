import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import axios from "axios"
import qs from "qs"
import VueAxios from 'vue-axios'

import 'element-plus/theme-chalk/index.css'
import ElementPlus from 'element-plus'


createApp(App).use(store).use(VueAxios, axios).use(ElementPlus).use(router).mount('#app')

