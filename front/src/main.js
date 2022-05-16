import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import router from './routes'
import store from './stores/index'

import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'


loadFonts()

const app = createApp(App);
app.use(router);
app.use(vuetify);
app.mount('#app');
app.use(store);
app.component('Datepicker', Datepicker);
