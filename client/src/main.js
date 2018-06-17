import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from "vue2-google-maps";
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import 'vue-material/dist/theme/default.css'

Vue.use(VueMaterial)
Vue.use(VueGoogleMaps, {
  load: {
    key: "put_your_key_here"
  }
});

new Vue({
  el: '#app',
  components: { App },
  render: h => h(App)
})
