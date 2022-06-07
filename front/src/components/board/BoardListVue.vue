<template>
<div v-if="state.items">
<HeaderVue style="height: 150px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">
            <v-row dense>
                <v-col sm="2"></v-col>

                <v-col sm="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > 자유게시판</h5>
                        </v-col>
                    </v-row>

                    <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px; ">
                        <v-col class="col_left"><h2>자유게시판</h2></v-col>

                        <v-col sm="8" class="col_right">
                            <v-select variant="outlined" density="compact" :change="searchOpt()" :items="state.items1" v-model="state.option" style="height: 40px; padding-right: 10px;"></v-select>
                            <input type="text" class="board_search_box" @keyup.enter="search()" style="outline-width: 0;" v-model="state.text">
                            <v-btn style="height: 40px;" @click="search()"><h4>검색</h4></v-btn>
                            <router-link to="/bwrite">
                                <v-btn style="margin-left: 10px; height: 40px; background-color: gold;">
                                    <h4>글쓰기</h4>
                                </v-btn>
                            </router-link>
                        </v-col>
                    </v-row>

                    <v-row dense style="border: 1px solid #CCC; border-top: 1px solid #CCC; padding: 10px; padding-left: 20px;">
                        <v-col>
                            <v-table height="800px">
                                <thead>
                                    <tr>
                                        <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>No</h3></td>
                                        <td style="border-bottom: 2px solid #CCC;"><h3>제목</h3></td>
                                        <td style="border-bottom: 2px solid #CCC; width:120px;"><h3>글쓴이</h3></td>
                                        <td style="border-bottom: 2px solid #CCC; width:170px;"><h3>날짜</h3></td>
                                        <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>조회</h3></td>
                                        <td style="border-bottom: 2px solid #CCC; width:80px;"><h3>좋아요</h3></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="(item, idx) in state.items" :key="item">
                                        <td>{{ item.bno }}</td>
                                        <td style="cursor: pointer;" @click="handlePage(item.bno)" >{{ item.btitle }}</td>
                                        <!-- 닉네임이 나와야 함 <= 나옴 -->
                                        <td v-if="state.nicklist[idx].mpnickname !== null">{{ state.nicklist[idx].mpnickname }}</td>
                                        <td v-if="state.nicklist[idx].mcname !== null">{{ state.nicklist[idx].mcname }}</td>
                                        <td>{{ item.bregdate1 }}</td>
                                        <td>{{ item.bhit }}</td>
                                        <td>{{ item.blike }}</td>
                                    </tr>
                                </tbody>
                            </v-table>
                        </v-col>
                    </v-row>
                </v-col>

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
import FooterVue from '../FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';
import {useRouter} from 'vue-router';
import dayjs from 'dayjs';

export default {
  components: { HeaderVue, FooterVue },
    setup () {
        
        onMounted(() => {
            handleData();
            // nick();
        });

        const router = useRouter();

        const state = reactive({
            data     : '',
            token    : sessionStorage.getItem("TOKEN"),
            pa       : 1,
            text     : '',  // 검색어
            page     : 1,   // 현재페이지
            option   : '',
            items    : [],
            bregdate1: '',
            searchOpt: [],
            nicklist : [],
            items1: [
                '전체', '제목', '내용', '글쓴이'
            ],
        });

        const date = (i) => {
            state.items[i].bregdate1 = dayjs(state.items[i].bregdate).format('YY.MM.DD hh:mm:ss');
        }

        // 조회수 1 증가
        const handlePage = async(bno) => {
            if(state.token !== null) {
            const url      = `/cluver/api/board1/updatehit?bno=${bno}`;
            const headers  = { "Content-Type": "application/json", "token": state.token };
            const response = await axios.put(url, {}, { headers });
            console.log("handlePage ==> ", response.data);

            router.push({ name: "BoardContentVue", query: { bno: bno } })
            console.log("handlePage - bno ==> ", bno);
            }  
        }

        const handlePagenation = () => {
            state.page = state.page;
            // state.total = Number(tmp);
            
            console.log("handlePagenation ==> ", state.page);
            handleData();
        }

        const searchOpt = async() => {
            const url      = `/cluver/api/board1/search?page=${state.page}&btitle=${state.btitle}`
            const headers  = {"Content-Type":"application.json"};
            const response = await axios.get(url,{headers:headers});
            console.log("searchOpt ==> ", response.data);
            if(response.data.status === 200){
                state.searchOpt = response.data.result;
            }
        }


        const handleData = async() => {
            const url      = `/cluver/api/board1/selectlist?page=${state.page}`
            const headers  = { "Content-Type": "application/json", "token" : state.token };
            const response = await axios.get(url, { headers });
            console.log("handleData ==> ", response.data);
            
            if(state.token !== null){
                console.log("토큰있음");
            }
            else{
                console.log("토큰없음");
            }
            console.log(state);

            if(response.data.status === 200) {
                state.items = response.data.result;
                state.nicklist.splice(0);
                state.nicklist = response.data.mlist;
                console.log(state.nicklist);
                // handledata가 출력되고 나서 날짜
                for(var i = 0; i<state.items.length; i++) {
                    date(i);
                }

                //  테이블에 좋아요 넣기 (for문을 돌려서 넣으므로 느림) 
                // for(var i = 0; i<state.items.length; i++){
                //     const url1 = `cluver/reaction/likelist.json?bno=${state.items[i].bno}`;
                //     const headers1 = {"Content-Type":"application/json"};
                //     const response1 = await axios.get(url1, {headers1}); 
                //     state.items[i].blike = response1.data.result
                // }
                state.total = Math.floor(((response.data.result1)-1)/10+1);
                console.log(state.total);
            }
        };

        const search = async() => {
            const url      = `/cluver/api/board1/search?btitle=${state.text}&page=${state.page}`
            const headers  = { "Content-Type": "application/json" };
            const response = await axios.get(url, { headers });
            console.log('search =>', response.data);

            if(response.data.status === 200){
                state.items = response.data.result;
                state.nicklist.splice(0);
                state.nicklist = response.data.mlist;
                console.log(response.data.result.length);
            }
        }

        //  const nick = async() => {
        //     const url      = `/cluver/member/psmynick`;
        //     const headers  = { "Content-Type": "application/json", "token": state.token };
        //     const response = await axios.get(url, {headers});
        //     console.log("nick ==> ", response.data.result);

        //     if(response.data.status === 200){
        //         state.nick = response.data.result;
        //     }
        //     else{
        //         role();
        //         console.log("기업회원입니다");
        //     }
        // }

        return { state, search, handlePagenation, handlePage, searchOpt }
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