<template>
<CHHeaderVue style="height: 150px;"></CHHeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
      <v-row dense>
        <!-- 사이드 -->
        <v-col md="2"></v-col>
        
        <!-- 메인 -->
        <v-col md="8">
          <v-row dense>
            <v-col>채-팅</v-col>
          </v-row>
        </v-col>

        <!-- 사이드 -->
        <v-col md="2"></v-col>
      </v-row>
    </v-main>
  </v-app>
  <FooterVue></FooterVue>
</template>

<script>
import { reactive } from '@vue/reactivity';
import FooterVue    from '../../FooterVue.vue';
import CHHeaderVue  from '../CHHeaderVue.vue';

export default {
  components: { CHHeaderVue, FooterVue },
  setup () {
    const state = reactive({

    })


    return { state }
  }
}
</script>

<style lang="scss" scoped>
/* Helper classes */
.bg-basil {
  background-color: #FFFBE6 !important;
}
.text-basil {
  color: #356859 !important;
}
@font-face {
    font-family: 'HallymGothic-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2204@1.0/HallymGothic-Regular.woff2') format('woff2');
    font-weight: 400;
    font-style: normal;
}
</style>