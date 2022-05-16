<template>
<div>
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
              <h5><router-link to="/">홈</router-link> > <router-link to="/h">고객센터</router-link></h5>
            </v-col>
          </v-row>

          <v-row dense style="padding-top: 22px; padding-bottom: 15px; padding-left: 10px;">
            <v-col>
              <h2>고객센터</h2>
            </v-col>
          </v-row>

          <!-- 공지사항, 문의게시판 -->
          <v-row dense style="border: 1px solid #CCC; border-top: 1px solid #CCC; padding: 10px; padding-left: 20px;">
            <!-- 공지사항 -->
            <v-col sm="4">
              <v-row dense style="padding-bottom: 5px; border-bottom: 1px solid;">
                <v-col sm="9">
                  <h3>공지사항</h3>
                </v-col>
                <v-col style="padding-right: 20px;" class="col_right">
                  <router-link to="/hqna"><v-icon icon="mdi-plus" style="width: 10px;" /></router-link>
                </v-col>
              </v-row>

              <!-- 공지글 -->
              <v-row dense style="padding-top: 10px;">
                <v-col>
                  <router-link to="/" style="display: flex;">
                    <h4 style="color: purple;">공지</h4>
                    <h4 style="padding-left: 10px;">{{state.title}}</h4>
                  </router-link>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <router-link to="/" style="display: flex;">
                    <h4 style="color: purple;">공지</h4>
                    <h4 style="padding-left: 10px;">{{state.title1}}</h4>
                  </router-link>
                </v-col>
              </v-row>
            </v-col>

            <!-- 문의게시판 -->
            <v-col class="col_right">
              <router-link to="/hqna" style="height: 100%;">
                <v-btn style="width: 300px; height: 100%; background-color: gold;"><h2 >문의게시판</h2></v-btn>
              </router-link>
            </v-col>
          </v-row>

          <!-- 자주묻는질문 -->
          <v-row dense style="padding-top: 10px;">
            <v-col>
              <v-row dense>
                <!-- 개인 -->
                <v-col sm="6" style="padding-right: 10px; border: 1px solid #CCC;">
                  <v-row dense style=" padding: 10px;">
                    <v-col class="col_center" style="border-bottom: 3px solid #ffcc00;">
                      <h2 style="color: #ffcc00">개인회원</h2>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 15px; padding-bottom: 5px;">
                    <v-col sm="6">
                      <h3>자주묻는 질문</h3>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 20px; padding-right: 20px; padding-bottom: 10px;">
                    <v-col>
                      <v-row dense style="border: 1px solid #CCC;">
                        <v-col>
                          <v-row dense style="border-bottom: 1px solid #CCC; padding: 5px;">
                            <v-col class="col_left">
                              <h3 style="color: gold;">Q)&nbsp;</h3><h3>{{state.q1}}</h3>
                            </v-col>
                          </v-row>

                          <v-row dense style="border-bottom: 1px solid #CCC; padding: 5px;">
                            <v-col class="col_left">
                              <h3 style="color: gold;">Q)&nbsp;</h3><h3>{{state.q2}}</h3>
                            </v-col>
                          </v-row>

                          <v-row dense style="border-bottom: 1px solid #CCC; padding: 5px;">
                            <v-col class="col_left">
                              <h3 style="color: gold;">Q)&nbsp;</h3><h3>{{state.q3}}</h3>
                            </v-col>
                          </v-row>

                          <v-row dense style="border-bottom: 1px solid #CCC; padding: 5px;">
                            <v-col class="col_left">
                              <h3 style="color: gold;">Q)&nbsp;</h3><h3>{{state.q4}}</h3>
                            </v-col>
                          </v-row>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>
                </v-col>

                <!-- 기업 -->
                <v-col sm="6" style="padding-left: 10px; border: 1px solid #CCC;">
                  <v-row dense style="padding: 10px;">
                    <v-col class="col_center" style="border-bottom: 3px solid #5d00ff;">
                      <h2 style="color: #5d00ff">기업회원</h2>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 15px; padding-bottom: 5px;">
                    <v-col sm="6">
                      <h3>자주묻는 질문</h3>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 20px; padding-right: 20px; padding-bottom: 10px;">
                    <v-col>
                      <v-row dense style="border: 1px solid #CCC;">
                        <v-col>
                          <v-row dense style="border-bottom: 1px solid #CCC; padding: 5px;">
                            <v-col class="col_left">
                              <h3 style="color: gold;">Q)&nbsp;</h3><h3>{{state.q1}}</h3>
                            </v-col>
                          </v-row>

                          <v-row dense style="border-bottom: 1px solid #CCC; padding: 5px;">
                            <v-col class="col_left">
                              <h3 style="color: gold;">Q)&nbsp;</h3><h3>{{state.q2}}</h3>
                            </v-col>
                          </v-row>

                          <v-row dense style="border-bottom: 1px solid #CCC; padding: 5px;">
                            <v-col class="col_left">
                              <h3 style="color: gold;">Q)&nbsp;</h3><h3>{{state.q5}}</h3>
                            </v-col>
                          </v-row>

                          <v-row dense style="border-bottom: 1px solid #CCC; padding: 5px;">
                            <v-col class="col_left">
                              <h3 style="color: gold;">Q)&nbsp;</h3><h3>{{state.q6}}</h3>
                            </v-col>
                          </v-row>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>

                </v-col>
              </v-row>
            </v-col>
          </v-row> 

          <v-row dense>
            <v-col class="col_right">
            </v-col>
          </v-row>
        </v-col>

        <!-- 사이드 -->
        <v-col md="2"></v-col>
      </v-row>
    </v-main>
  </v-app>
  <FooterVue></FooterVue>
</div>
</template>

<script>
import { reactive } from '@vue/reactivity';
import FooterVue    from '../FooterVue.vue';
import HeaderVue    from '../HeaderVue.vue';
import { useRouter } from 'vue-router';

export default {
  components: { HeaderVue, FooterVue },
  setup () {
    const router = useRouter();

    const state = reactive({
      title: '공지공지사항사항',
      title1: '사항사항공지공지',
      q1: '아이디와 비밀번호를 분실했어요!',
      q2: '클럽을 만들고 싶어요!',
      q3: '클럽을 가입하고 싶어요!',
      q4: '클럽을 탈퇴하고 싶어요!',
      q5: '클럽을 삭제하고 싶어요!',
      q6: '클럽마스터를 양도하고 싶어요!',
    });

    const dontgo = async() => {
      if (confirm('정말 탈퇴하시겠습니까?') == true) {
          router.push({ name: "PlzDontGoVue"});
      }
    }

    return { state, dontgo }
  }
}
</script>

<style lang="scss" scoped>

</style>