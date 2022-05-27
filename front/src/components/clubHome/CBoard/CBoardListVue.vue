<template>
<div>
<CHHeaderVue style="height: 150px;"></CHHeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <v-col sm="2"></v-col>
                
                <v-col sm="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link :to="{name : 'CHomeVue', query : {cno : state.cno}}">클럽홈</router-link> > {{state.boardname}}</h5>
                        </v-col>                                
                    </v-row>

                    <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px; ">
                        <v-col class="col_left">
                            <h2>클럽게시판</h2>
                        </v-col>

                        <v-col sm="8" class="col_right">
                            <v-select variant="outlined" density="compact" :items="state.items" v-model="state.option" style="height: 40px;" ></v-select>
                            <input type="text" class="board_search_box" style="outline-width: 0;" v-model="state.search">
                            <v-btn style="height: 40px;" @click="search"><h4>검색</h4></v-btn>
                                <v-btn style="margin-left: 10px; height: 40px; background-color: gold;" @click="handleWrite">
                                    <h4>글쓰기</h4>
                                </v-btn>
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
                                        v-for="(item, idx) in state.board"
                                        :key="item.no"
                                    >
                                        <td>{{ item.cbno }}</td>
                                        <td @click="selectContent(item.cbno)" style="cursor:pointer;">{{ item.cbtitle }}</td>
                                        <td>{{ state.nicklist[idx]}}</td>
                                        <td>{{ item.cbregdate }}</td>
                                        <td>{{ item.cbhit }}</td>
                                        <td>{{ item.like }}</td>
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
                    <v-pagination
                    v-model="state.page" 
                    :length="state.pages"
                    @click="handlePage(state.page, state.option, state.search)"
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
import { useRoute, useRouter } from 'vue-router';

export default {
  components: { CHHeaderVue, FooterVue },
    setup () {
        const router = useRouter();
        const route = useRoute();

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
            search: '',
            page: 1,   // 현재 페이지
            pages : 1, // 총 페이지 수
            boardname: '클럽게시판',
            items: [
                '전체', '제목', '내용', '글쓴이'
            ],
            option : '전체',
            cno : route.query.cno, //미완성
            token : sessionStorage.getItem("TOKEN"),
            nicklist : []
        })

        const content = async() => 
        {
            if(state.token !== null)
            {
                const url = `/ROOT/api/clubboard/selectlist?page=${state.page}&cno=${state.cno}`;
                const headers = {"Content-Type":"application/json", "token" : state.token};
                const response = await axios.get(url, {headers});
                // console.log(response.data);
                if(response.data.status === 200)
                {
                    state.board = response.data.result.list;
                    state.pages = response.data.result.pages;
                }
            }
            else if(response.data.status === 0)
            {
                alert("로그인이 필요한 페이지입니다.");
                router.push({name:'LoginVue'});
            }
            else
            {
                alert('비정상적인 접근입니다.');
                router.push({name:'HomeVue'});
            }
        }   

        const handlePage = async(idx, option, search) =>
        {
            if(state.token !== null)
            {
                const url = `/ROOT/api/clubboard/selectlist?page=${idx}&text=${search}&option=${option}&cno=${state.cno}`;
                const headers = {"Content-Type":"application/json", "token" : state.token};
                const response = await axios.get(url, {headers});
                // console.log(response.data);
                if(response.data.status === 200)
                {
                    state.board = response.data.result.list;
                    nick();
                }
            }
            else if(response.data.status === 0)
            {
                alert("로그인이 필요한 페이지입니다.");
                router.push({name:'LoginVue'});
            }
            else
            {
                alert('비정상적인 접근입니다.');
                router.push({name:'HomeVue'});
            }
        }

        const selectContent = (cbno) =>
        {
            console.log(state.cno);
            router.push({name:"CBoardContentVue", query:{cbno:cbno, cno:state.cno}});
        }

        const search = async() => {
            if(state.token !== null)
            {
                const url = `/ROOT/api/clubboard/selectlist?page=${state.page}&text=${state.search}&option=${state.option}&cno=${state.cno}`;
                const headers = {"Content-Type":"application/json", "token" : state.token};
                const response = await axios.get(url, {headers});
                console.log(response.data);
                if(response.data.status === 200)
                {
                    state.board = response.data.result.list;
                    state.pages = response.data.result.pages;
                    nick();
                }
            }
            else
            {
                router.push({name:'LoginVue'});
            }
        }

        const handleWrite = () =>
        {
            router.push({name:'CBoardWriteVue', query:{cno : state.cno}});
        }

        const nick = async() => //mid(아이디)값으로 닉네임 받아오기 // 페이지 초기 로딩시, 검색기능, 페이지기능에 사용
        {
            state.nicklist.splice(0); // state.nicklist 초기화 //페이지 이동 시 닉네임 목록 갱신
            
            for(let i=0; i<state.board.length; i++)
            {
                const url = `/ROOT/api/clubmember/selectnick?mid=${state.board[i].member.mid}`;
                const headers = {"Content-Type":"application/json"};
                const response = await axios.get(url, {headers});
                // console.log(response.data);
                if(response.data.status === 200)
                {
                    state.nicklist.push(response.data.result.mpnickname);
                }
            }
        }

        onMounted(async() =>
        {
            await content();
            nick();
        });

        return { state, search, selectContent, handleWrite, handlePage}
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