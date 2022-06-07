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
                  <h5><router-link to="/chome">클럽홈</router-link> > 회원관리</h5>
              </v-col>
          </v-row>

          <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px; ">
              <v-col class="col_left">
                  <h2>회원관리</h2>
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
                    <td style="border-bottom: 2px solid #CCC; width: 120px;"><h3>현재등급</h3></td>
                    <td style="border-bottom: 2px solid #CCC; width: 120px;"><h3>등급변경</h3></td>
                    <td style="border-bottom: 2px solid #CCC;"><h3>닉네임</h3></td>
                    <td style="border-bottom: 2px solid #CCC; width:170px;"><h3>가입일</h3></td>
                    <td style="border-bottom: 2px solid #CCC; width:170px;"><h3>버튼</h3></td>
                  </tr>
                </thead>
                <tbody>
                 <tr
                    v-for="(item,idx) in state.items"
                    :key="item"
                  >
                    <td>{{ idx }}</td>
                    <td v-if="item.scode == 101"><router-link to="/cbcontent">마스터</router-link></td>
                    <td v-if="item.scode == 102"><router-link to="/cbcontent">운영진</router-link></td>
                    <td v-if="item.scode == 104"><router-link to="/cbcontent">클럽원</router-link></td>
                    <!-- <td><router-link to="/cbcontent">{{ item.scode }}</router-link></td> -->
                    <td>
                    <v-row dense style="padding-top: 10px;">
                        <v-col sm="1" ></v-col>
                            <v-col sm="10" >
                                <v-select :change="handleClick(item.no)"  :items="state.grade"  v-if="state.class === '마스터'" v-model="state.master"
                                    variant="outlined" density="compact" label="등급변경"
                                      style="height: 40px; padding-right: 10px; width: 150px; margin-left: 50%;">

                                </v-select>
                            </v-col>
                        <v-col sm="1"></v-col>
                    </v-row>
                    </td>
                    <td>{{ item.mname }}</td>
                    <td>{{ item.jcdate }}</td>
                     <td>
                      <v-btn style="background-color: gold" @click="handleJoinclub(item.mid)" ><h4>권한변경</h4></v-btn>
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
      master : '마스터',
      management : '운영진',
      member: '클럽원',
      items: [
        '등급', '닉네임', '가입일'
      ],
      grade: [
        '마스터', '운영진', '클럽원'
      ],
      option: '닉네임',
      cno : route.query.cno,
      token : sessionStorage.getItem("TOKEN")
    });

    onMounted(() =>
    {
      memberList();
    });
    
    const memberList = async() => {
      const url = `/cluver/combineview/cmemberlist.json?no=${state.cno}`;
      const headers = {"Content-Type" : "application/json",
      token : state.token};
      const response = await axios.get(url, {headers});
      console.log(response.data);
      if(response.data.status === 200) {
      state.items = response.data.results;
      console.log(state.items);

      }
    }
    // const authlist = async() => {
    //   const url = `/cluver/steptbl/authlist`;
    //   const headers = {"Content-Type" : "application/json"};
    //   const response = await axios.get(url, {headers});
    //   console.log(response.data);
    //   if(response.data.status === 200) {
    //   state.class = response.data.results;
    //   console.log(state.class);

    //   }
    // }
    
    const handleClick = async(idx) => {
      console.log(idx);
    }

    

    return { state,handleClick }
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