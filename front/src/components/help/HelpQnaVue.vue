<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
      <v-row dense>
        <!-- 사이드 -->
        <v-col md="2"></v-col>
        
        <!-- 메인 -->
        <v-col md="8" style="border-bottom: 1px solid #CCC;">
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
            <v-col sm="6">
              <h5><router-link to="/">홈</router-link> > <router-link to="/h">고객센터</router-link> > 질문및답변</h5>
            </v-col>
          </v-row>

          <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px;">
            <v-col sm="6" class="col_left">
              <h2>질문및답변</h2>
            </v-col>

            <v-col md="6" class="col_right">
              <v-select variant="outlined" density="compact" :items="state.items1" v-model="state.option" style="height: 40px; padding-right: 10px;"></v-select>

              <input type="text" class="board_search_box" style="outline-width: 0;" v-model="state.search">
              <v-btn style="height: 40px;" @click="search()"><h4>검색</h4></v-btn>
              <router-link to="/hqwrite">
                <v-btn style="margin-left: 10px; height: 40px; background-color: gold;">
                  <h4>글쓰기</h4>
                </v-btn>
              </router-link>
            </v-col>
          </v-row>

          <v-row dense style="border: 1px solid #CCC; border-top: 1px solid #CCC; padding: 10px; padding-left: 20px;  ">
            <v-col>
              <v-table height="800px">
                <thead>
                  <tr>
                    <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>No</h3></td>
                    <td style="border-bottom: 2px solid #CCC;"><h3>제목</h3></td>
                    <td style="border-bottom: 2px solid #CCC; width:120px;"><h3>글쓴이</h3></td>
                    <td style="border-bottom: 2px solid #CCC; width:170px;"><h3>날짜</h3></td>
                    <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>조회</h3></td>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="item in state.notice"
                    :key="item.no"
                  >
                    <td style="background-color: gold;"><h4>{{ item.qno }}</h4></td>
                    <td style="background-color: gold;"><router-link to="/hqcontent">{{ item.qtitle }}</router-link></td>
                    <td style="background-color: gold;">{{ item.mid }}</td>
                    <td style="background-color: gold;">{{ item.qregdate }}</td>
                    <td style="background-color: gold;">{{ item.qhit }}</td>
                  </tr>
                  <tr
                      v-for="item in state.board"
                      :key="item.no"
                  >
                    <td>{{ item.qno }}</td>
                    <td style="cursor: pointer;" @click="handlePage(item.qno)" >{{ item.qtitle }}</td>
                    <td>{{ item.mid }}</td>
                    <td>{{ item.qregdate }}</td>
                    <td>{{ item.qhit }}</td>
                  </tr>
                </tbody>
              </v-table>
            </v-col>
          </v-row>
        </v-col>

        <!-- 사이드 -->
        <v-col md="2"></v-col>
      </v-row>
      <v-row dense>
        <v-col>
          <v-pagination
          v-model="state.page"
          :length="6"
          ></v-pagination>
        </v-col>
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

export default {
  components: { HeaderVue, FooterVue },
  setup () {
    const state = reactive({
 
        notice: [
          {
            no: '[공지]',
            title: '회원 탈퇴하고 싶어요? 싫어요!',
            writer: '운영자',
            date: '2022-05-13',
            hit: 452,
          },
        ],
        items: [
          '전체', '제목', '내용', '글쓴이'
        ],

        token : sessionStorage.getItem("TOKEN"),

        search: '검색내용', // 검색어
        page: 1,
    })

    const search = async() => {

    }

    return { state, search }
  },
}
</script>

<style lang="scss" scoped>
th, td {
  text-align: center;
}
table, td, th {
  border-collapse : collapse;
}
.select {
    min-width: 150px;
    padding-right: 10px;
}
</style>