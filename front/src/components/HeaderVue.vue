<template>
  <v-app>
    <v-main>
      <v-row dense>
        <!-- 좌측상단공란 -->

        <v-col>

          <!-- 최상단 -->
          <v-row dense style="margin-top: 5px;">
            
            <!-- 중앙상단 공란 -->
            <v-col sm="2">
              <img :src="require('../assets/img/cluver_logo.png')" style="height: 70px;" class="img_header" @click="handleClick('/home')"/>
            </v-col>
            <v-col sm="5"></v-col>

            <!-- 우측중앙 로그인 회원가입 -->
            <v-col sm="5" class="col_right">
              <h3 @click="handleClick('/home')" style="cursor: pointer;" class="mar-l_20">홈</h3>
              <h3 @click="handleClick('/clist')" style="cursor: pointer;" class="mar-l_20">클럽목록</h3>
              <h3 @click="handleClick('/blist')" style="cursor: pointer;" class="mar-l_20">게시판</h3>
              <h3 @click="handleClick('/h')" style="cursor: pointer;" class="mar-l_20">고객센터</h3>
              <router-link :to="{ name:'LoginVue' }" v-if="!state.logged"><h3 class="mar-l_20">로그인</h3></router-link>
              <router-link :to="{ name:'ChoiceJoinVue' }" v-if="!state.logged"><h3 class="mar-l_20">회원가입</h3></router-link>
              <router-link :to="{ name:'LogoutVue' }" v-if="state.logged"><h3 class="mar-l_20">로그아웃</h3></router-link>
              <router-link :to="{ name:'MypageVue' }" v-if="state.logged"><h3 class="mar-l_20">마이페이지</h3></router-link>
            </v-col>
          </v-row>
        </v-col>
        
        <!-- 우측상단 공란 -->
      </v-row>

      <v-row dense>
        <!-- 좌측중앙 공란 -->
        <v-col sm="2"></v-col>

        <v-col sm="8">
          <v-row dense="">
            <!-- 로고좌측 공란 -->
            <v-col sm="1"></v-col>

            <!-- 로고 -->

            <!-- 중앙 -->
            <v-col>
              <!-- 검색창 -->
              <v-row dense class="col_center">
                <v-col sm="10" style="height: 60px; border: 3px solid gold; border-radius: 4px; padding-left: 10px;">
                  <input type="text" style="width: 100%; height: 100%; outline-width: 0;" @keyup.enter="handleSend()" v-model="state.search">
                </v-col>

                <v-col sm="2">
                  <v-btn type="submit" style="height: 60px;" class="search_button"  @click="handleSend()"><h3>검색</h3></v-btn>
                </v-col>
              </v-row>

              <!-- 추천검색어 -->
              <v-row dense style="margin:5px;">
                <v-col sm="10" align="center">
                  <a style="padding-right:15px;" @click="handleSend1()">축구</a>
                  <a style="padding-right:15px;" @click="handleSend2()">캠핑</a>
                  <a style="padding-right:15px;" @click="handleSend3()">리그오브레전드</a>
                  <a style="padding-right:15px;" @click="handleSend4()">공인중개사</a>
                  <a style="padding-right:15px;" @click="handleSend5()">주식</a>
                </v-col>

                <v-col sm="2"></v-col>
              </v-row>
            </v-col>

            <!-- 검색창우측 -->
            <v-col sm="3"></v-col>
          </v-row>

          <!-- 중앙하단. 메뉴용 -->
          <!-- <v-row dense style="padding-bottom: 5px; padding-top: 10px;">
            <v-col>
              <v-card>
                <v-tabs v-model="state.tab" fixed-tabs>
                  <v-tab value=""      @click="handleClick('/')"><h2>홈</h2></v-tab>
                  <v-tab value="clist" @click="handleClick('clist')"><h2>클럽목록</h2></v-tab>
                  <v-tab value="blist" @click="handleClick('blist')"><h2>게시판</h2></v-tab>
                  <v-tab value="h"     @click="handleClick('h')"><h2>고객센터</h2></v-tab>
                </v-tabs>
              </v-card>
            </v-col>
          </v-row> -->
        </v-col>
        
        <!-- 우측 중단 -->
        <v-col sm="2"></v-col>
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
import HomeVue    from './HomeVue.vue';
import FooterVue    from './FooterVue.vue';
import BoardListVue    from './board/BoardListVue.vue';
import { onMounted, computed } from '@vue/runtime-core';
import { useStore } from 'vuex'
export default {
  components: { HomeVue, FooterVue, BoardListVue },
  setup () {
    const router = useRouter();
    const store = useStore();

    const state = reactive({
      op1 : '축구',
      op2 : '캠핑',
      op3 : '리그오브레전드',
      op4 : '부동산',
      op5 : '주식',
      search : '',
      logged: computed(() => store.getters['moduleA/getLogged']),
      token : sessionStorage.getItem("TOKEN"),
      tab: ''
    });

    onMounted(() => {
      if(state.token === null){
        store.commit('moduleA/setLogged', false)
      } 
      else{
        store.commit('moduleA/setLogged', true)
      }
      state.tab = sessionStorage.getItem("MENU");
    })
    const handleClick = (e) =>{
      console.log('handleClick', e)    
      sessionStorage.setItem("MENU", e);
      router.push(e);
    }

    const handleSend = async() => {
      console.log(state.search);
      store.commit("setKeyword", state.search);
      router.push({name:'ClubListVue', query:{title:state.search}});

    }
    const handleSend1 = async() => {
      console.log(state.search);
      store.commit("setKeyword", state.search);
      router.push({name:'ClubListVue', query:{title:state.op1}});

    }
    const handleSend2 = async() => {
      console.log(state.search);
      store.commit("setKeyword", state.search);
      router.push({name:'ClubListVue', query:{title:state.op2}});

    }
    const handleSend3 = async() => {
      console.log(state.search);
      store.commit("setKeyword", state.search);
      router.push({name:'ClubListVue', query:{title:state.op3}});

    }
    const handleSend4 = async() => {
      console.log(state.search);
      store.commit("setKeyword", state.search);
      router.push({name:'ClubListVue', query:{title:state.op4}});

    }
    const handleSend5 = async() => {
      console.log(state.search);
      store.commit("setKeyword", state.search);
      router.push({name:'ClubListVue', query:{title:state.op5}});

    }


    // store.subscribe((mutation, state) => {
    //   console.log('store.subscribe', mutation, state);
    // });


    return { handleClick, state,handleSend,handleSend1,handleSend2,handleSend3,
    handleSend4,handleSend5 }
  },
}
</script>
<style>
</style>
