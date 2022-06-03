<template>
<div>
<HeaderVue style="height: 150px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
      <v-row dense>
        <!-- 사이드 -->
        <v-col sm="2"></v-col>
        
        <!-- 메인 -->
        <v-col sm="8" style="border-bottom: 1px solid #CCC;">
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
            <v-col sm="6">
              <h5><router-link to="/">홈</router-link> > <router-link to="/h">고객센터</router-link> > 회원탈퇴</h5>
            </v-col>
          </v-row>
          
          <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px;">
            <v-col sm="6" class="col_left">
              <h2>회원탈퇴</h2>
            </v-col>
          </v-row>
        </v-col>

        <!-- 사이드 -->
        <v-col sm="2"></v-col>
      </v-row>
    </v-main>
  </v-app>
  <FooterVue></FooterVue>
</div>
</template>

<script>
import { reactive } from '@vue/reactivity';
import FooterVue from '../../components/FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const state = reactive({
          
        })

        return { state }
    }
}
</script>

<style lang="scss" scoped>

</style>