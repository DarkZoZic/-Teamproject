<template>
<div>
<CHHeaderVue style="height: 150px;"></CHHeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
      <v-row dense>
        <!-- 사이드 -->
        <v-col md="2"></v-col>
        
        <!-- 메인 -->
        <v-col md="8">
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
            <v-col sm="6">
              <h5><router-link to="/chome">클럽홈</router-link> > {{state.boardname}}</h5>
            </v-col>                                
          </v-row>
          <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px; ">
            <v-col class="col_left">
              <h2>자유게시판</h2>
            </v-col>

            <v-col md="8" class="col_right">
              <v-flex class="select">
                  <v-select variant="outlined" density="compact" :items="state.items" style="height: 40px;"></v-select>
              </v-flex>                            
              <input type="text" class="board_search_box" style="outline-width: 0;" v-model="state.search">
              <v-btn style="height: 40px;" @click="search"><h4>검색</h4></v-btn>
              <router-link to="/cbwrite">
                <v-btn style="margin-left: 10px; height: 40px; background-color: gold;">
                  <h4>채팅만들기</h4>
                </v-btn>
              </router-link>
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