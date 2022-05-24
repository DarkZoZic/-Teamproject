<template>
<div>
<CHHeaderVue style="height: 150px;"></CHHeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
      <v-row dense>
        <!-- 사이드 -->
        <v-col sm="2"></v-col>
        
        <!-- 메인 -->
        <v-col sm="8">
          <v-row dense style="padding: 10px;">
            <!-- 게시판 -->
            <v-col sm="6" style="padding: 15px;">
              <v-row dense style="height: 200px;">
                <v-col>
                  <v-row dense style="border-bottom: 1px solid #CCC; margin-bottom: 10px;">
                    <v-col sm="5">
                      <h3>게시판</h3>
                    </v-col>

                    <v-col sm="7" class="col_right" style="padding-right: 20px;">
                      <router-link to="/cblist"><v-icon icon="smi-plus" style="width: 10px;" /></router-link>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 10px;" v-for="tmp in state.B.slice(0, 5)" :key="tmp">
                    <v-col>
                      <h4 @click="B(tmp.cbno)" style="cursor: pointer;">{{tmp.cbtitle}}</h4>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>

              <!-- 게시판 -->
              <v-row dense style="height: 300px;">
                <v-col>
                  <v-row dense style="border-bottom: 1px solid #CCC; margin-bottom: 10px;">
                    <v-col sm="5">
                      <h3>일정</h3>
                    </v-col>
                    
                    <v-col sm="7" class="col_right" style="padding-right: 20px;">
                      <router-link to="/cschedule"><v-icon icon="smi-plus" style="width: 10px;" /></router-link>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 10px;">
                    <v-col>
                      <h4 @click="B()" style="cursor: pointer;">{{state.S.title1}}</h4>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 10px;">
                    <v-col>
                      <h4 @click="B()" style="cursor: pointer;">{{state.S.title2}}</h4>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 10px;">
                    <v-col>
                      <h4 @click="B()" style="cursor: pointer;">{{state.S.title3}}</h4>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 10px;">
                    <v-col>
                      <h4 @click="B()" style="cursor: pointer;">{{state.S.title4}}</h4>
                    </v-col>
                  </v-row>                  
                </v-col>
              </v-row>
            </v-col>

            <!-- 갤러리 -->
            <v-col sm="6" style="padding: 15px;">
              <!-- 상단 -->
              <v-row dense style="border-bottom: 1px solid #CCC; margin-bottom: 10px;">
                <v-col sm="5">
                  <h3>갤러리</h3>
                </v-col>

                <v-col sm="7" class="col_right" style="padding-right: 20px;">
                  <router-link to="/cgallery"><v-icon icon="smi-plus" style="width: 10px;" /></router-link>
                </v-col>
              </v-row>

              <v-row dense style="padding-left: 10px; padding-right: 10px;">
                <v-col sm="6" style="padding-right: 10px;">
                  <v-row dense style="height: 200px; border: 1px solid #CCC;">
                    <v-col class="col_center">
                      <img :src="require('../../assets/img/photo1.jpg')" @click="G()" style="cursor: pointer; max-width: auto; height: 190px;">
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-top: 10px;">
                    <v-col>
                      <h4 @click="G()" style="cursor: pointer;">{{state.G.title1}}</h4>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col sm="6" style="padding-left: 10px;">
                  <v-row dense style="height: 200px; border: 1px solid #CCC;">
                    <v-col class="col_center">
                      <img :src="require('../../assets/img/photo2.jpg')" @click="G()" style="cursor: pointer; max-width: auto; height: 190px;">
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-top: 10px;">
                    <v-col>
                      <h4 @click="G()" style="cursor: pointer;">{{state.G.title2}}</h4>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              
              <v-row dense style="padding-left: 10px; padding-right: 10px;">
                <v-col sm="6" style="padding-right: 10px;">
                  <v-row dense style="height: 200px; border: 1px solid #CCC;">
                    <v-col class="col_center">
                      <img :src="require('../../assets/img/photo1.jpg')" @click="G()" style="cursor: pointer; max-width: auto; height: 190px;">
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-top: 10px;">
                    <v-col>
                      <h4 @click="G()" style="cursor: pointer;">{{state.G.title1}}</h4>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>              
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
import axios from 'axios';
import { reactive } from '@vue/reactivity';
import FooterVue    from '../FooterVue.vue';
import CHHeaderVue  from './CHHeaderVue.vue';
import { useRoute, useRouter } from 'vue-router';
import { onMounted } from '@vue/runtime-core';

export default {
  components: { CHHeaderVue, FooterVue },
  setup () {
    const router = useRouter();
    const route = useRoute();

    const state = reactive({
      B: [],

      S: [],

      G: [],

      cno : route.query.cno,
      token : sessionStorage.getItem("TOKEN"),
      page : 1 //스크립트용 변수. 변경X
    })

    const B = (cbno) => {
      router.push({ name: "CBoardContentVue", query : {cbno : cbno, cno : state.cno}});
    };

    const G = () => {
      router.push({ name: "CGContentVue" , query : {cno : state.cno}});
    };

    const blist = async() =>
    {
      if(state.token !== null)
            {
                const url = `/ROOT/api/clubboard/selectlist?page=${state.page}&cno=${state.cno}`;
                const headers = {"Content-Type":"application/json", "token" : state.token};
                const response = await axios.get(url, {headers});
                console.log(response.data.result);
                if(response.data.status === 200)
                {
                    state.B = response.data.result.list;
                }
            }
            else
            {
                router.push({name:'LoginVue'});
            }
    }

    onMounted(async() =>
    {
      await blist();
    })

    return { state, B, G }
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