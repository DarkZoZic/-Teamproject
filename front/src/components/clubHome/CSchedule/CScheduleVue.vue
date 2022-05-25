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
              <h5><router-link :to="{name : 'CBoardListVue', query : {cno : state.cno}}">클럽홈</router-link> > 일정</h5>
            </v-col>                                
          </v-row>

          <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px; ">
            <v-col class="col_left">
              <h2>일정</h2>
            </v-col>

            <v-col sm="6" class="col_right">
              <v-select variant="outlined" density="compact" :items="state.items" v-model="state.option" style="height: 40px;" ></v-select>
              <input type="text" class="board_search_box" style="outline-width: 0;" v-model="state.search">
              <v-btn style="height: 40px;" @click="search()"><h4>검색</h4></v-btn>
              <router-link :to="{name : 'CSUploadVue', query : {cno : state.cno}}">
                <v-btn style="margin-left: 10px; height: 40px; background-color: gold;">
                  <h4>일정생성</h4>
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
                                      <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>일정명</h3></td>
                                      <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>일정 내용</h3></td>
                                      <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>일정 시작일시</h3></td>
                                      <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>일정 종료일시</h3></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="tmp in state.schedule" :key="tmp">
                                      <td>{{tmp.sno}}</td>
                                      <td>{{tmp.sname}}</td>
                                      <td>{{tmp.scontent}}</td>
                                      <td>{{tmp.startdate}}</td>
                                      <td>{{tmp.enddate}}</td>
                                    </tr>
                                </tbody>
                            </v-table>
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
import FooterVue    from '../../FooterVue.vue';
import CHHeaderVue  from '../CHHeaderVue.vue';
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';
import { useRoute } from 'vue-router';

export default {
  components: { CHHeaderVue, FooterVue },
  setup () {
    const route = useRoute();

    const state = reactive({
      schedule : [],
      option: '전체',
      items: [
        '전체', '제목', '생성자', '내용'
      ],
      cno : route.query.cno,
      token : sessionStorage.getItem("TOKEN")
    });

    const selectlist = async() =>
    {
      const url = `/ROOT/api/schedule/selectlist?&cno=${state.cno}`;
      const headers = {"Content-Type":"application/json", "token" : state.token};
      const response = await axios.get(url, {headers});
      // console.log(response.data);
      if(response.data.status === 200)
      {
        state.schedule = response.data.result.list;
      }
    }

    const deleteSchedule = async() => // 종료일시가 지난 일정을 자동으로 삭제
    {
      for(let i=0; i<state.schedule.length; i++)
      {
        // console.log("enddate : ", Date.parse(state.schedule[i].enddate));
        // console.log("now : ", new Date().getTime());
        if(Date.parse(state.schedule[i].enddate) < new Date().getTime())
        {
          const url = `/ROOT/api/schedule/delete`;
          const headers = {"Content-Type":"application/json"};
          const body = 
          {
            sno : state.schedule[i].sno
          }

          const response = await axios.post(url, body, {headers});
          console.log(response.data);
          if(response.data.status === 200)
          {
            window.location.reload();
          }
        }
      }
    }

    onMounted(async()=>
    {
      await selectlist();
      deleteSchedule();
    });

    return { state}
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