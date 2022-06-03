<template>
<div>
<HeaderVue style="height: 150px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
      <v-row dense>
        <!-- 사이드 -->
        <v-col sm="2"></v-col>
        
        <!-- 메인 -->
        <v-col sm="8" style="border-bottom: 1px solid #CCC;">
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
            <v-col sm="6">
              <h5><router-link to="/">홈</router-link> > <router-link to="/h">고객센터</router-link> > 질문및답변</h5>
            </v-col>
          </v-row>

          <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px;">
            <v-col sm="6" class="col_left">
              <h2>질문및답변</h2>
            </v-col>

            <v-col sm="6" class="col_right">
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
                  <!-- <tr
                    v-for="item in state.notice"
                    :key="item"
                  >
                    <td style="background-color: gold;"><h4>{{ item.no }}</h4></td>
                    <td style="background-color: gold;"><router-link to="/hqcontent">{{ item.title }}</router-link></td>
                    <td style="background-color: gold;">{{ item.writer }}</td>
                    <td style="background-color: gold;">{{ item.date }}</td>
                    <td style="background-color: gold;">{{ item.hit }}</td>
                  </tr> -->
                  <tr v-for="(item, idx) in state.board" :key="item" >
                    <td>{{ item.qno }}</td>
                    <td style="cursor: pointer;" @click="handlePage(item.qno)" >{{ item.qtitle }}</td>
                    <td v-if="state.nicklist[idx].mpnickname !== null">{{ state.nicklist[idx].mpnickname }}</td>
                    <td v-if="state.nicklist[idx].mcname !== null">{{ state.nicklist[idx].mcname }}</td>
                    <td>{{ item.qregdate1 }}</td>
                    <td>{{ item.qhit }}</td>
                  </tr>
                </tbody>
              </v-table>
            </v-col>
          </v-row>
        </v-col>

        <!-- 사이드 -->
        <v-col sm="2"></v-col>
      </v-row>
      
      <v-row dense>
        <v-col>
          <v-pagination v-model="state.page" :length="state.total" @click="handlePagenation()">
          </v-pagination>
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
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';
import {useRouter} from 'vue-router';
import dayjs from 'dayjs';

export default {
  components: { HeaderVue, FooterVue },
  setup () {

    onMounted(() => {
      handleData();
    });

    const router = useRouter();

    const state = reactive({
        item : [],
        board: [],
        qregdate1: '',
        
        items: [
          '전체', '제목', '내용', '글쓴이'
        ],

        token : sessionStorage.getItem("TOKEN"),

        search: '검색내용', // 검색어
        page: 1,
        mid : sessionStorage.getItem("TOKEN"),
        nicklist : []
    });

    const date = (i) => {
      state.board[i].qregdate1 = dayjs(state.board[i].qregdate).format('YYYY.MM.DD hh:mm:ss');
    }

    // 조회수 1증가 시키기
    const handlePage = async(qno) => {
      if(state.token !== null){
        const url = `/ROOT/api/qna/updatehit?qno=${qno}`;
        const headers = { 
            "Content-Type":"application/json",
            "token" : state.token,
        };
        const response = await axios.put(url, {}, { headers });
        console.log(response.data);
            router.push({name:"HelpQnaContentVue", query:{ qno: qno }})

        console.log(qno);
      }  
    }

    const handleData = async() => {
      const url = `/ROOT/api/qna/selectlist?page=${state.page}`
      const headers = { 
          "Content-Type": "application/json", 
          "token" : state.token,
      };
      const response = await axios.get(url, { headers });
      console.log(response.data);
      if(state.token !== null){
          console.log("토큰있음");
      }
      else{
          console.log("토큰없음");
      }

      if(response.data.status === 200){
        state.board = response.data.result;
        state.nicklist.splice(0);
        state.nicklist = response.data.mlist;
        for(var i = 0; i<state.items.length; i++) {
                  date(i);
        }
        //  테이블에 좋아요 넣기 (for문을 돌려서 넣으므로 느림) 
        // for(var i = 0; i<state.items.length; i++){
        //     const url1 = `ROOT/reaction/likelist.json?bno=${state.items[i].bno}`;
        //     const headers1 = {"Content-Type":"application/json"};
        //     const response1 = await axios.get(url1, {headers1}); 
        //     state.items[i].blike = response1.data.result
        // }
        state.total = Math.floor(((response.data.result1)-1)/10+1);
        console.log(state.total);
      }

    }

    const search = async() => {
      // const url      = `/ROOT/api/qna/search?qtitle=${state.text}&page=${state.page}`
      // const headers  = { "Content-Type": "application/json" };
      // const response = await axios.get(url, { headers });
      // console.log('search =>', response.data);

      // if(response.data.status === 200){
      //     state.items = response.data.result;
      //     state.nicklist.splice(0);
      //     state.nicklist = response.data.mlist;
      //     console.log(response.data.result.length);
      // }

    }

    return { state, search, handlePage }
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