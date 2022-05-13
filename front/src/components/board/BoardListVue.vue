<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <v-col md="2"></v-col>

                <v-col md="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > 자유게시판</h5>
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
                            <v-btn style="height: 40px;" @click="search()"><h4>검색</h4></v-btn>
                            <router-link to="/bwrite">
                                <v-btn style="margin-left: 10px; height: 40px; background-color: gold;">
                                    <h4>글쓰기</h4>
                                </v-btn>
                            </router-link>
                        </v-col>
                    </v-row>
                    {{state.member[1]}}            
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
                                        <td style="border-bottom: 2px solid #CCC; width:80px;"><h3>좋아요</h3></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr
                                        v-for="item in state.items" :key="item">
                                        <td>{{ item.bno }}</td>
                                        <td><router-link to="/bcontent">{{ item.btitle }}</router-link></td>
                                        <td>{{ item.member }}</td>
                                        <td>{{ item.bregdate }}</td>
                                        <td>{{ item.bhit }}</td>
                                        <td>{{ item.blike }}</td>
                                    </tr>
                                </tbody>
                            </v-table>                            
                        </v-col>
                    </v-row>
                </v-col>

                <v-col md="2">
                </v-col>
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
import FooterVue from '../FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';

export default {
  components: { HeaderVue, FooterVue },
    setup () {

        
     onMounted(() => {
            handleData();
        });
         const state = reactive({
             member : [],
             mid : '',
             items : [],
             search: '검색내용',
             page: 1,
 
             items: [
                 '전체', '제목', '내용', '글쓴이'
             ]
         })

     const handleData = async() => {
            const url = `/ROOT/board1/selectlist?page=${state.page}`
            const headers = {"Content-Type":"application/json"};
            const response = await axios.get(url, {headers});
            console.log(response.data);
            if(response.data.status === 200){
                state.items = response.data.result
                state.items.member = response.data.result[0].member
                console.log(response.data.result[1].member);
                // for(var i=0;i <=state.items.length;i++){
                //     state.member[i] = response.data.result[i].member;
                // }
                
                console.log(state.items[0].member.mid);
            }
        };

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