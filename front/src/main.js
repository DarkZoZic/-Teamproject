import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import router from './routes'
import store from './stores/index'
import HeaderVue from './components/HeaderVue.vue';
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import dayjs from 'dayjs'
// import vueCookies from "vue-cookies";


loadFonts()

const app = createApp(App);
app.use(router);
app.use(vuetify);
app.mount('#app');
app.use(store);
app.use(dayjs);
app.component('Datepicker', Datepicker);
app.component('HeaderVue', HeaderVue);
