<template>
  <v-app>
    <v-main>
      <v-row dense>
        <!-- 좌측상단공란 -->
        <v-col md="2"></v-col>

        <v-col md="8" >
          <!-- 최상단 -->
          <v-row dense style="margin-top: 5px;">
            <!-- 중앙상단 공란 -->
            <v-col md="7"></v-col>

            <!-- 우측중앙 로그인 회원가입 -->
            <v-col md="5" class="col_right">
              <router-link :to="{ name:'LoginVue' }" v-if="!state.logged"><h4 style="margin-left:10px;">로그인</h4></router-link>

              <router-link :to="{ name:'ChoiceJoinVue' }" v-if="!state.logged"><h4 style="margin-left:10px;">회원가입</h4></router-link>

              <router-link :to="{ name:'LogoutVue' }" v-if="state.logged"><h4 style="margin-left:10px;">로그아웃</h4></router-link>

              <router-link :to="{ name:'MypageVue' }" v-if="state.logged"><h4 style="margin-left:10px;">마이페이지</h4></router-link>
            </v-col>
          </v-row>
        </v-col>
        
        <!-- 우측상단 공란 -->
        <v-col md="2"></v-col>
      </v-row>

      <v-row dense>
        <!-- 좌측중앙 공란 -->
        <v-col md="2"></v-col>

        <v-col md="8">
          <v-row dense="">
            <!-- 로고좌측 공란 -->
            <v-col md="1"></v-col>

            <!-- 로고 -->
            <v-col md="3" class="col_center">
              <router-link to="/"><img :src="require('../assets/img/cluver.jpg')" style="height: 90px;" /></router-link>
            </v-col>

            <!-- 중앙 -->
            <v-col md="5">
              <!-- 검색창 -->
              <v-row dense style="display: flex; align-items: center;">
                <v-col md="10" style="height: 60px; border:3px solid gold; border-radius: 4px; padding-left: 10px;">
                  <input type="text" style="width: 100%; height: 100%; outline-width: 0;">
                </v-col>

                <v-col md="2">
                  <v-btn type="submit" style="height: 60px;" class="search_button"><h3>검색</h3></v-btn>
                </v-col>
              </v-row>

              <!-- 추천검색어 -->
              <v-row dense style="margin:5px;">
                <v-col md="10" align="center">
                  <a style="padding-right:15px;">축구</a>
                  <a style="padding-right:15px;">캠핑</a>
                  <a style="padding-right:15px;">리그오브레전드</a>
                  <a style="padding-right:15px;">공인중개사</a>
                  <a style="padding-right:15px;">주식</a>
                </v-col>

                <v-col md="2"></v-col>
              </v-row>
            </v-col>

            <!-- 검색창우측 -->
            <v-col md="3"></v-col>
          </v-row>

          <!-- 중앙하단. 메뉴용 -->
          <v-row dense style="padding-bottom: 5px; padding-top: 10px;">
            <v-col>
              <v-card>
                <v-tabs v-model="state.tab" background-color="white">
                  <router-link to="/clist">
                    <v-tab value="clist" class="tab_menu">
                      <h3>클럽목록</h3>
                    </v-tab>
                  </router-link>

                  <v-tab value="1" class="tab_menu" disabled>
                    <h3>인기클럽</h3>
                  </v-tab>

                  <v-tab value="2" class="tab_menu" disabled>
                    <h3>클럽홍보</h3>
                  </v-tab>

                  <v-tab value="3" class="tab_menu" disabled>
                    <h3>클럽랭킹</h3>
                  </v-tab>

                  <v-tab value="4" class="tab_menu" disabled>
                    <h3>클럽샵</h3>
                  </v-tab>

                  <v-tab value="5" class="tab_menu" disabled>
                    <h3>페이지소개</h3>
                  </v-tab>

                  <v-tab value="6" class="tab_menu" disabled>
                    <h3>채팅</h3>
                  </v-tab>                                    

                  <router-link to="/blist">
                    <v-tab value="board" class="tab_menu">
                      <h3>자유게시판</h3>
                    </v-tab>
                  </router-link>

                  <router-link to="/h">
                    <v-tab value="help" class="tab_menu">
                      <h3>고객센터</h3>
                    </v-tab>
                  </router-link>
                </v-tabs>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
        
        <!-- 우측 중단 -->
        <v-col md="2"></v-col>
      </v-row>

      <v-row>
        <v-col style="border-bottom:2px solid gold; padding-bottom: 10px;"></v-col>
      </v-row>
    </v-main>
  </v-app>
</template>

<script>
import { reactive } from '@vue/reactivity';
import { useRouter } from 'vue-router';
import { onMounted,computed } from '@vue/runtime-core';
import { useStore } from 'vuex'
export default {
  
  setup () {
    const router = useRouter();
    const store = useStore();
    const state = reactive({
      tab: '',
      logged : computed(() =>  store.getters['moduleA/getLogged']),
      token  : sessionStorage.getItem("TOKEN")
    })
     onMounted(() => {
      if(state.token === null){
        store.commit('moduleA/setLogged',false)
      } 
      else{
        store.commit('moduleA/setLogged',true)
      }
    })

    return { router, state }
  },
}
</script>
<style>
  @import url(../assets/css/style.css);
</style>
