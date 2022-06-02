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
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
              <v-col sm="6">
                  <h5><router-link to="/chome">클럽홈</router-link> > 가입신청관리</h5>
              </v-col>
          </v-row>

          <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px; ">
              <v-col class="col_left">
                  <h2>가입신청관리</h2>
              </v-col>

              <v-col sm="6" class="col_right">
                <v-select variant="outlined" density="compact" :items="state.items" v-model="state.option" style="height: 40px;" ></v-select>
                <input type="text" class="board_search_box" style="outline-width: 0;" v-model="state.search">
                <v-btn style="height: 40px; background-color: gold;" @click="search()"><h4>검색</h4></v-btn>
              </v-col>
          </v-row>

          <v-row dense style="border: 1px solid #CCC; border-top: 1px solid #CCC; padding: 10px; padding-left: 20px;  ">
            <v-col>
              <v-table height="800px">
                <thead>
                  <tr>
                    <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>No</h3></td>
                    <td style="border-bottom: 2px solid #CCC;"><h3>닉네임</h3></td>
                    <td style="border-bottom: 2px solid #CCC; width:170px;"><h3>신청일</h3></td>
                    <td style="border-bottom: 2px solid #CCC; width:170px;"><h3>버튼</h3></td>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="item in state.items"
                    :key="item"
                  >
                    <!-- <td v-show="disable">{{ idx }}</td> -->
                    <td>{{ item.no }}</td>
                    <td>{{ item.mname }}</td>
                    <td>{{ item.jcdate }}</td>
                    <td>
                      <v-btn style="background-color: gold" @click="handleJoinclub(item.mid)" ><h4>승인</h4></v-btn>
                      <v-btn><h4>거절</h4></v-btn>
                    </td>
                  </tr>
                </tbody>
              </v-table>
            </v-col>
          </v-row>

          <v-row dense>
              <v-col class="col_center" style="padding-top: 10px;">
                  <v-btn style="background-color: gold; width: 100px;"><h3>적용</h3></v-btn>
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
import { useRoute } from 'vue-router';
import { onMounted } from '@vue/runtime-core';


export default {
  components: { CHHeaderVue, FooterVue },
  setup () {
    const route = useRoute();

    const state = reactive({
      list : [],
      mid : '',
      cno : route.query.cno,
      token : sessionStorage.getItem("TOKEN"),
      items: [
        '등급', '닉네임', '가입일'
      ],
      grade: [
        '클럽장', '매니저', '클럽원'
      ],
      option: '닉네임',
      
    });

        onMounted(() =>
        {
          ClubCheck();
        });
      const handleJoinclub = async(mid) => {
          console.log(mid);
          console.log(state.cno);
          const url = `/ROOT/joinclub/insert.json`;
          const headers = {"Content-Type":"multipart/form-data"};
          const body = new FormData;
              body.append("member",  mid);
              body.append("steptbl",  104);
              body.append("club", state.cno);
          const response = await axios.post(url,body,{headers});
          console.log(response.data);

          if(response.data.status === 200){
            memberList();
          }
      }
      const handleJoinclub1 = async() => {
          console.log(state.cno);
          console.log(state.mid);
          const url = `/ROOT/joinclub/insert.json`;
          const headers = {"Content-Type":"multipart/form-data"};
          const body = new FormData;
              body.append("member",  state.mid);
              body.append("steptbl",  101);
              body.append("club", state.cno);
          const response = await axios.post(url,body,{headers});
          console.log(response.data);

          if(response.data.status === 200){
              router.push({path : 'clist'})
          }
      }
   const ClubCheck = async() => {
      const url = `/ROOT/combineview/clubmasterget.json?cno=${state.cno}`;
      const headers = {"Content-Type" : "application/json", "token" : state.token};
      const response = await axios.get(url, {headers});
      // console.log(response.data);
      if(response.data.status === 200) {
        memberList();

      }
    }
    const memberList = async() => {
      const url = `/ROOT/combineview/selectnumber.json?no=${state.cno}`;
      const headers = {"Content-Type" : "application/json"};
      const response = await axios.get(url, {headers});
      console.log(response.data);
      if(response.data.status === 200) {
      state.items = response.data.total;
      console.log(response.data.total);
      // state.mids = response.data.total.mid;
      // for(var i=0; i < state.items; i++){
      //   state.mid = state.items
      // }

      }
    }


    return { state,handleJoinclub,handleJoinclub1 }
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