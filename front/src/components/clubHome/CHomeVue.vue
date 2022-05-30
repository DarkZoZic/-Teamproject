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
                      <h3><router-link :to="{name : 'CBoardListVue', query : {cno : state.cno}}">게시판</router-link></h3>
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

              <!-- 일정 -->
              <v-row dense style="height: 300px;">
                <v-col>
                  <v-row dense style="border-bottom: 1px solid #CCC; margin-bottom: 10px;">
                    <v-col sm="5">
                      <router-link :to="{name : 'CScheduleVue', query : {cno : state.cno}}"><h3>일정</h3></router-link>
                    </v-col>
                    
                    <v-col sm="7" class="col_right" style="padding-right: 20px;">
                      <router-link to="/cschedule"><v-icon icon="smi-plus" style="width: 10px;" /></router-link>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 10px;" v-for="tmp in state.S.slice(0, 5)" :key="tmp">
                    <v-col>
                      <h4 @click="S()" style="cursor: pointer;">{{tmp.sname}}</h4>
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
                  <h3><router-link :to="{name : 'CGalleryVue', query : {cno : state.cno}}">갤러리</router-link></h3>
                </v-col>

                <v-col sm="7" class="col_right" style="padding-right: 20px;">
                  <router-link to="/cgallery"><v-icon icon="smi-plus" style="width: 10px;" /></router-link>
                </v-col>
              </v-row>

              <v-row dense style="padding-left: 10px; padding-right: 10px; float: left; margin: 10px;" v-for="tmp in state.G.slice(0,2)" :key="tmp">
                <v-col sm="6" style="padding-right: 10px;">
                  <v-row dense style="height: 200px; border: 1px solid #CCC;" >
                    <v-col class="col_center">
                      <img :src="tmp.gimageurl" @click="G(tmp.cgno)" style="cursor: pointer; width: 200px; height: 140px;" />
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-top: 10px;">
                    <v-col>
                      <h4 @click="G(tmp.cgno)" style="cursor: pointer;">{{tmp.cgname}}</h4>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              
              <!-- 한줄 -->
              <v-row dense style="padding-left: 10px; padding-right: 10px; " v-for="tmp in state.G.slice(2,4)" :key="tmp">
                <v-col sm="6" style="padding-right: 10px;">
                  <v-row dense style="height: 200px; border: 1px solid #CCC;">
                    <v-col class="col_center">
                      <img :src="tmp.gimageurl" @click="G(tmp.cgno)" style="cursor: pointer; width: 200px; max-height: 140px;">
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-top: 10px;">
                    <v-col>
                      <h4 @click="G(tmp.cgno)" style="cursor: pointer;">{{tmp.cgname}}</h4>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>           
            </v-col>
            <!-- 두줄-->
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

    const blist = async() =>
    {
      if(state.token !== null)
      {
          const url = `/ROOT/api/clubboard/selectlist?page=${state.page}&cno=${state.cno}`;
          const headers = {"Content-Type":"application/json", "token" : state.token};
          const response = await axios.get(url, {headers});
          // console.log(response.data.result);
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

    const B = (cbno) => {
      router.push({ name: "CBoardContentVue", query : {cbno : cbno, cno : state.cno}});
    };

    const glist = async() =>
    {
      if(state.token !== null)
      {
        const url = `/ROOT/api/clubgallery/selectlist?page=${state.page}&cno=${state.cno}`;
        const headers = {"Content-Type":"application/json", "token" : state.token};
        const response = await axios.get(url, {headers});
        // console.log(response.data.result);
        if(response.data.status === 200)
        {
          state.G = response.data.result.list;
        }
      }
      else
      {
          router.push({name:'LoginVue'});
      }
    }

    const G = (cgno) => {
      router.push({ name: "CGContentVue" , query : {cgno : cgno, cno : state.cno}});
    };

    const slist = async() =>
    {
      if(state.token !== null)
      {
        const url = `/ROOT/api/schedule/selectlist?page=${state.page}&cno=${state.cno}`;
        const headers = {"Content-Type":"application/json", "token" : state.token};
        const response = await axios.get(url, {headers});
        // console.log(response.data.result);
        if(response.data.status === 200)
        {
          state.S = response.data.result.list;
        }
      }
      else
      {
          router.push({name:'LoginVue'});
      }
    }

    const S = () => {
      router.push({ name: "CScheduleVue", query : {cno : state.cno}});
    };

    

    onMounted(async() =>
    {
      await blist();
      await glist();
      await slist();
    })

    return { state, B, G, S }
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