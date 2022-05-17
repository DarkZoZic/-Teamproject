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
              <h5><router-link to="/chome">클럽홈</router-link> > {{state.galleryName}}</h5>
            </v-col>                                
          </v-row>

          <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px; ">
            <v-col class="col_left">
              <h2>갤러리</h2>
            </v-col>

            <v-col md="6" class="col_right">
              <v-select variant="outlined" density="compact" :items="state.items" v-model="state.option" style="height: 40px;" ></v-select>
              <input type="text" class="board_search_box" style="outline-width: 0;" v-model="state.search">
              <v-btn style="height: 40px;" @click="search()"><h4>검색</h4></v-btn>
              <router-link to="/cgupload">
                <v-btn style="margin-left: 10px; height: 40px; background-color: gold;">
                  <h4>업로드</h4>
                </v-btn>
              </router-link>
            </v-col>
          </v-row>

          <v-row dense style="border: 1px solid #CCC; border-top: 1px solid #CCC; padding: 10px; padding-left: 20px;  ">
            <v-col
              v-for="n in 12"
              :key="n"
              cols="4"
            >
              <v-card height="300px" class="club_card" style="padding: 20px;" @click="content()">
                <v-row dense>
                  <v-col class="col_center">
                    <img :src="require(`../../../assets/img/${state.image2}.jpg`)" style="height: 200px;  padding: 5px; border: 1px solid #CCC;"/>
                  </v-col>
                </v-row>

                <v-row dense style="padding-top: 5px;">
                  <v-col sm="8">
                    <h4>{{state.title}}</h4>
                  </v-col>

                  <v-col class="col_right">
                    <h5 style="color: gray">조회 {{state.hit}}</h5>
                    <a><img :src="require('../../../assets/img/thumb.png')" style="width: 15px; margin-left: 10px; margin-right: 3px;"/></a>
                    <h5 style="color: gray;">{{state.like}}</h5>
                  </v-col>
                </v-row>

                <v-row dense>
                  <v-col style="padding-left: 10px;">
                    <h5 style="color: gray">{{state.writer}}</h5>
                  </v-col>

                  <v-col sm="6" class="col_right">
                    <h5 style="color: gray">{{state.date}}</h5>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
          </v-row>
        </v-col>

        <!-- 사이드 -->
        <v-col md="2"></v-col>
      </v-row>

      <v-row dense style="margin-top: 10px;">
        <v-col>
          <v-pagination
          v-model="state.page"
          :length="10"
          ></v-pagination>
        </v-col>
      </v-row>
    </v-main>
  </v-app>
  <FooterVue></FooterVue>
</div>
</template>

<script>
import { reactive }  from '@vue/reactivity';
import { useRouter } from 'vue-router';
import FooterVue     from '../../FooterVue.vue';
import CHHeaderVue   from '../CHHeaderVue.vue';

export default {
  components: { CHHeaderVue, FooterVue },
  setup () {
    const router = useRouter();

    const state = reactive({
      galleryName: '일상갤러리',
      title: '탁구왕 김탁구~ㅋ',
      image1: 'photo1',
      image2: 'photo2',
      date: '2022-05-10 10:42',
      writer: '탁생탁사',
      like: 7,
      hit: 22,

      page: 1,

      items: [
        '전체', '제목', '내용', '글쓴이'
      ],
      option: '전체'
    });

    const content = () => {
      router.push({ name: "CGContentVue" });
    }

    const search = () => {

    }

    return { state, content, search }
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
.select {
    min-width: 150px;
    padding-right: 10px;
}
</style>