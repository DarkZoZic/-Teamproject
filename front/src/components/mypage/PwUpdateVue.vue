<template>
<HeaderVue style="height: 220px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
      <v-row dense>
        <!-- 사이드 -->
        <v-col md="2"></v-col>
        
        <!-- 메인 -->
        <v-col md="8">
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
            <v-col sm="6">
              <h5><router-link to="/">홈</router-link> > <router-link to="/mypage">마이페이지</router-link> > 비밀번호변경 </h5>
            </v-col>
          </v-row>

          <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
            <v-col sm="6" class="col_left">
              <h2>비밀번호변경</h2>
            </v-col>

            <v-col class="col_right">
              <router-link to="/pupdate"><v-btn><h3>회원정보수정</h3></v-btn></router-link>
              <router-link to="/mypage"><v-btn><h3>비밀번호변경</h3></v-btn></router-link>
              <router-link to="/dontgo"><v-btn><h3>회원탈퇴</h3></v-btn></router-link>
            </v-col>
          </v-row>

          <v-row dense style="padding-top: 20px; padding-bottom: 20px;">
            <v-col sm="2"></v-col>
            
            <v-col sm="8">
              <v-row dense>
                <v-col sm="3" class="col_right">
                  <h4>현재비밀번호</h4>
                </v-col>

                <v-col style="height: 80px; padding-left: 10px;" class="col_left">
                  <input type="password" class="board_search_box1" style="outline-width: 0;" v-model="state.pw">
                </v-col>
              </v-row>

              <v-row dense>
                <v-col sm="3" class="col_right">
                  <h4>바꿀비밀번호</h4>
                </v-col>
                
                <v-col style="height: 80px; padding-left: 10px;" class="col_left">
                  <input type="password" class="board_search_box1" style="outline-width: 0;" v-model="state.pw1">
                </v-col>
              </v-row>

              <v-row dense>
                 <v-col sm="3" class="col_right">
                  <h4>바꿀비밀번호확인</h4>
                </v-col>
                
                <v-col style="height: 80px; padding-left: 10px;" class="col_left">
                  <input type="password" class="board_search_box1" style="outline-width: 0;" v-model="state.pw2">
                </v-col>
              </v-row>
            </v-col>

            <v-col sm="2"></v-col>
          </v-row>

          <v-row>
            <v-col class="col_center">
              <v-btn style="background-color: gold;"><h3>비밀번호변경</h3></v-btn>
            </v-col>
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
import FooterVue from '../FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
      const state = reactive({
        pw: '',
        pw1: '',
        pw2: '',
      })

        return { state }
    }
}
</script>

<style lang="scss" scoped>
  @import '../../assets/css/style';

</style>