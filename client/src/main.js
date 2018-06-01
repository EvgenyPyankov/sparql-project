import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from "vue2-google-maps";

Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyDzL82aG_EmgsaY0fQTT7GQQRxyxSBiyxw"
  }
});

new Vue({
  el: '#app',
  components: { App },
  render: h => h(App)
})
