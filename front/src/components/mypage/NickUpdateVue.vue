<template>
<div>
<HeaderVue style="height: 150px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
      <v-row dense>
        <!-- 사이드 -->
        <v-col sm="2"></v-col>
        
        <!-- 메인 -->
        <v-col sm="8">
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
            <v-col sm="6">
              <h5><router-link to="/">홈</router-link> > <router-link to="/mypage">마이페이지</router-link> > 닉네임변경 </h5>
            </v-col>
          </v-row>

          <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
            <v-col sm="6" class="col_left">
              <h2>닉네임변경</h2>
            </v-col>

            <v-col class="col_right">
              <router-link to="/pupdate"><v-btn><h3>회원정보수정</h3></v-btn></router-link>
              <router-link to="/pwupdate"><v-btn><h3>비밀번호변경</h3></v-btn></router-link>
              <router-link to="/nupdate"><v-btn><h3>닉네임변경</h3></v-btn></router-link>
              <router-link to="/likelist"><v-btn><h3>찜목록</h3></v-btn></router-link>
              <router-link to="/activity"><v-btn><h3>내활동</h3></v-btn></router-link>              
              <router-link to="/dontgo"><v-btn><h3>회원탈퇴</h3></v-btn></router-link>
            </v-col>
          </v-row>

          <v-row dense style="padding-top: 20px; padding-bottom: 20px;">
            <v-col sm="2"></v-col>
            
            <v-col sm="8">
              <v-row dense>
                <v-col sm="3" class="col_right">
                  <h4>현재닉네임</h4>
                </v-col>

                <v-col style="height: 80px; padding-left: 10px;" class="col_left">
                  <input type="text" readonly class="board_search_box1" style="outline-width: 0;" v-model="state.nick">
                </v-col>
              </v-row>

              <v-row dense>
                <v-col sm="3" class="col_right">
                  <h4>바꿀닉네임</h4>
                </v-col>
                
                <v-col style="height: 80px; padding-left: 10px;" class="col_left">
                  <input type="text" class="board_search_box1" style="outline-width: 0;" v-model="state.nick1">
                </v-col>
              </v-row>
            </v-col>

            <v-col sm="2"></v-col>
          </v-row>

          <v-row>
            <v-col class="col_center">
              <v-btn style="background-color: gold;" @click="handlenickupdate()"><h3>닉네임변경</h3></v-btn>
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
import FooterVue from '../FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { onMounted } from '@vue/runtime-core';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
       onMounted(() => {
            handlenick();
          })
      const router = useRouter();
      const state = reactive({
        token : sessionStorage.getItem("TOKEN"),
        nick: '',
        nick1: '',
      })

      const handlenickupdate = async() => {
            const url = `/cluver/member/updatenickname`;
            const headers = {"Content-Type":"application/json", 
            token : state.token};
            const body = {
                mpnickname   : state.nick1
            };
            const response = await axios.put(url,body,{headers});
            console.log(state.nick);
            if(response.data.status === 200){
              alert('닉네임 수정완료');
            router.push({path : 'mypage'})
                console.log(state.nick);
            }
        }
        const handlenick = async() => {
            const url = `/cluver/member/psmynick`;
            const headers = {"Content-Type":"application/json", 
            token : state.token};
            const response = await axios.get(url, {headers});
            console.log(response.data.result);
            if(response.data.status === 200){
                state.nick = response.data.result.mpnickname;
                console.log(state.nick);
            }
        }

        return { state,handlenickupdate }
    }
}
</script>

<style lang="scss" scoped>
  @import '../../assets/css/style';

</style>