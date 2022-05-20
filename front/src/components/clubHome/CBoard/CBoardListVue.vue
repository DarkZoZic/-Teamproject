<template>
<div>
<CHHeaderVue style="height: 150px;"></CHHeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <v-col md="2"></v-col>
                
                <v-col sm="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/chome">클럽홈</router-link> > {{state.boardname}}</h5>
                        </v-col>                                
                    </v-row>

                    <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px; ">
                        <v-col class="col_left">
                            <h2>클럽게시판</h2>
                        </v-col>

                        <v-col md="8" class="col_right">
                            <v-select variant="outlined" density="compact" :items="state.items" v-model="state.option" style="height: 40px;" ></v-select>
                            <input type="text" class="board_search_box" style="outline-width: 0;" v-model="state.search">
                            <v-btn style="height: 40px;" @click="search"><h4>검색</h4></v-btn>
                            <router-link to="/cbwrite">
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
                                        <td style="border-bottom: 2px solid #CCC; width:80px;"><h3>좋아요</h3></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr
                                        v-for="item in state.notice"
                                        :key="item.cbno"
                                    >
                                        <td style="background-color: gold;"><h4>{{ item.no }}</h4></td>
                                        <td style="background-color: gold;"><router-link to="/cbcontent">{{ item.title }}</router-link></td>
                                        <td style="background-color: gold;">{{ item.writer }}</td>
                                        <td style="background-color: gold;">{{ item.date }}</td>
                                        <td style="background-color: gold;">{{ item.hit }}</td>
                                        <td style="background-color: gold;">{{ item.like }}</td>
                                    </tr>
                                    <tr
                                        v-for="item in state.board"
                                        :key="item.no"
                                    >
                                        <td>{{ item.cbno }}</td>
                                        <td @click="selectContent(item.cbno)" style="cursor:pointer;">{{ item.cbtitle }}</td>
                                        <td>{{ item.cbwriter }}</td>
                                        <td>{{ item.cbregdate }}</td>
                                        <td>{{ item.cbhit }}</td>
                                        <td>{{ item.like }}</td>
                                    </tr>
                                </tbody>
                            </v-table>
                        </v-col>
                    </v-row>
                </v-col>
                <v-col md="2"></v-col>
            </v-row>

            <v-row dense>
                <v-col>
                    <v-pagination
                    v-model="state.page"
                    :length="state.page"
                    ></v-pagination>
                </v-col>
            </v-row>
                    
        </v-main>
    </v-app>
<FooterVue></FooterVue>
</div>
</template>

<script>
import axios from 'axios';
import { reactive } from '@vue/reactivity';
import CHHeaderVue  from '../CHHeaderVue.vue';
import FooterVue    from '../../FooterVue.vue';
import { onMounted } from '@vue/runtime-core';
import { useRouter } from 'vue-router';

export default {
  components: { CHHeaderVue, FooterVue },
    setup () {
        const router = useRouter();

        const state = reactive({
            board: [],

            notice: [
              {
                no: '[공지]',
                title: '삥뽕탁구클럽 규칙',
                writer: '운영자',
                date: '2022-05-13',
                hit: 452,
                like: 5
              },
              {
                no: '[공지]',
                title: '삥뽕탁구클럽에 오신 여러분들 진심으로 환영합니다 :-)',
                writer: '운영자',
                date: '2022-05-13',
                hit: 452,
                like: 5
              },
            ],
            search: '검색내용',
            page: 1,
            boardname: '자유게시판',
            items: [
                '전체', '제목', '내용', '글쓴이'
            ],
            option : '전체'
        })

        const content = async() => {
            const url = `/ROOT/api/clubboard/selectlist`;
            const headers = {"Content-Type":"application/json"};
            const response = await axios.get(url, {headers});
            console.log(response.data.result);
            if(response.data.status === 200)
            {
                state.board = response.data.result.list;
                state.page = response.data.result.pages;
            }
        }   

        const selectContent = (cbno) =>
        {
            router.push({name:"CBoardContentVue", query:{cbno:cbno}});
        }

        const search = async() => {
            const url = `/ROOT/api/clubboard/selectlist?page=${state.page}&text=${state.search}&option=${state.option}`;
            const headers = {"Content-Type":"application/json"};
            const response = await axios.get(url, {headers});
            console.log(response.data.result);
            if(response.data.status === 200)
            {
                state.board = response.data.result.list;
                state.page = response.data.result.pages;
            }
        }

        onMounted(() =>
        {
            content();
        });

        return { state, search, selectContent}
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