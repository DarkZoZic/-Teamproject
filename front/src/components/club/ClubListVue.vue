<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">

            <!-- 상단 표zzz -->
            <v-row dense>
                <v-col sm="2"></v-col>
                
                <v-col sm="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="5">
                            <h5><router-link to="/">홈 > </router-link><router-link to="/clist">클럽목록</router-link></h5>
                        </v-col>
                    </v-row>

                    <v-row dense style="padding-top: 22px; padding-bottom: 10px; padding-left: 10px;">
                        <v-col>
                            <h2>지역명</h2>
                            <!-- {{keyword}} -->
                        </v-col>

                        <v-col sm="7" class="col_right" v-if="state.logged">
                            <router-link to="/regclub">
                                 <v-btn style="background-color: gold; margin-right: 10px;"><h3>클럽생성</h3></v-btn>
                            </router-link>

                            <router-link to="/comeclub">
                                <v-btn style="background-color: gold;"><h3>공고등록</h3></v-btn>
                            </router-link>                            
                        </v-col>
                    </v-row>
                    
                    <v-row dense>
                        <!-- 구 -->
                        <v-col style="border: 5px solid gold;" >
                            <v-row dense style="border-bottom: 1px solid #CCC; height: 60px;">
                                <v-col v-for="tmp in state.tab" :key="tmp" :value="tmp" @click="search1(tmp)" @change="Clicksearch(tmp.a1)" class="col_center">
                                    <v-btn style="width: 20px; margin: -5px;"><h3>{{tmp}}</h3></v-btn>
                                </v-col>
                            </v-row>                                

                            <v-card-text>
                                <v-window v-model="state.tab2">
                                    <v-window-item v-for="tmp in state.tab" :key="tmp" :value="tmp">
                                        <v-row dense class="addr2">
                                            <v-col sm="2" v-for="(tmp, idx) in state.addr1" :key="tmp" @click="Clicksearch(tmp.a1 +' '+ tmp.a2)">
                                                <h3 class="addr1">{{state.addr1[idx].a2}}</h3>
                                            </v-col>
                                        </v-row>
                                    </v-window-item>
                                </v-window>
                            </v-card-text>
                        </v-col>
                    </v-row>
                                    
                    <v-row dense style="margin-top: 20px;">
                        <v-col>
                            <v-row dense style="border: 1px solid #CCC">
                                <v-col>
                                    <v-row dense style="border-bottom: 1px solid #CCC;">
                                        <v-col sm="1" class="col_left" style="vertical-align: middle;" ><h4 class="club_list_h">지역</h4></v-col>

                                        <v-col sm="11" class="col_left">
                                            <div class="club_list_input" style="justify-content: left; display: flex; align-items: center;outline-width: 0;"> 
                                                <div class="col_center">
                                                    <h5 v-for="(tmp, idx) in state.area" :key="tmp" class="h5" @click="del(idx)" style="cursor: pointer; margin-right: 10px;">
                                                        {{tmp}}
                                                        <div class="hov1"><img :src="require('../../assets/img/x.png')" style="width: 10px"></div>
                                                    </h5>
                                                </div>
                                            </div>
                                            <v-btn class="club_list_btn" @click="all()" ><h4>초기화</h4></v-btn>
                                        </v-col>
                                    </v-row>

                                    <v-row dense style="padding-top: 5px;">
                                        <v-col sm="1" class="col_left" style="vertical-align: middle;"><h4 class="club_list_h">테마</h4></v-col>

                                        <v-col sm="11" class="col_left" style="vertical-align: middle; outline-width: 0;">
                                            <input type="text" class="club_list_input">
                                            <v-btn class="club_list_btn"><h4>초기화</h4></v-btn>
                                        </v-col>
                                    </v-row>

                                    <v-row dense style="margin-top: 5px; border-top: 1px solid #CCC;">
                                        <v-col sm="6" style="border-right: 1px solid #ccc;" class="col_center"><h4>요일</h4></v-col>

                                        <v-col sm="6" class="col_center"><h4>시간</h4></v-col>
                                    </v-row>

                                    <v-row dense style="border-top: 1px solid #CCC; border-bottom: 1px solid #CCC;">
                                        <v-col sm="6" style="border-right: 1px solid #CCC;">
                                            <v-row dense style="padding-top: 10px;">
                                                <v-col class="col_center" style="vertical-align: middle;">
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="평일전체"><h4>평일전체</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="주말"><h4>주말</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="토"><h4>토</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="일"><h4>일</h4>
                                                </v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col class="col_center" style="vertical-align: middle;">
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="월"><h4>월</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="화"><h4>화</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="수"><h4>수</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="목"><h4>목</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="금"><h4>금</h4>
                                                </v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col class="col_center">
                                                    <v-btn class="club_list_btn" @click="resetdate()" ><h4>초기화</h4></v-btn>
                                                </v-col>
                                            </v-row>
                                        </v-col>

                                        <v-col sm="6">
                                            <v-row dense style="padding-top: 10px;">
                                                <v-col class="col_center" style="vertical-align: middle;">
                                                    <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="종일"><h4>종일</h4>
                                                    <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="오전"><h4>오전</h4>
                                                    <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="오후"><h4>오후</h4>
                                                    <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="저녁"><h4>저녁</h4>
                                                </v-col>
                                            </v-row>
                                            
                                            <v-row dense>
                                                <v-col class="col_center" style="vertical-align: middle;">
                                                    <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="새벽"><h4>새벽</h4>
                                                    <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="상관없음"><h4>상관없음</h4>
                                                </v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col class="col_center">
                                                    <v-btn class="club_list_btn" @click="resettime()" ><h4>초기화</h4></v-btn>
                                                </v-col>
                                            </v-row>
                                        </v-col>             
                                    </v-row>

                                    <v-row dense>
                                        <v-col class="col_center">
                                            <v-btn class="club_list_btn" v-model="state.area" @click="Clicksearch(state.area)"><h4>검색</h4></v-btn>
                                            <v-btn class="club_list_btn" @click="reset()" ><h4>초기화</h4></v-btn>
                                        </v-col>
                                    </v-row>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-col>

                <v-col sm="2"></v-col>
            </v-row>

            <!-- 클럽정보 -->
            <v-row dense>
                <v-col sm="2"></v-col>
                
                <v-col sm="8">
                    <v-row dense="" style="padding: 10px;">
                        <v-col sm="3" class="col_left">
                            <h3>클럽정보</h3>
                        </v-col>

                        <v-col class="col_right">
                            <router-link to="/likelist">
                                <v-btn style="background-color: gold;"><h4>찜목록</h4></v-btn>
                            </router-link>
                        </v-col>
                    </v-row>

                    <div v-if="state.items">
                        <v-row dense>
                            <v-col v-for="(item,idx) in state.items" :key="item" cols="4">
                                <v-card height="200px" class="club_card" style="padding: 20px;" >
                                    <v-row dense>
                                        <v-col sm="3"></v-col>

                                        <v-col sm="6" class="col_center">
                                            <img v-if="item.imgurl" :src="item.imgurl" @click="handlePage(item.obj.cno, idx)" style="height: 50px; cursor: pointer;"/>
                                            <img v-if="!item.imgurl" :src="require(`../../assets/img/default-logo.jpg`)" @click="handlePage(item.obj.cno, idx)" style="height: 50px; cursor: pointer;"/>
                                        </v-col>

                                        <v-col sm="3" class="col_right">
                                            <v-btn style="height: 100%; width: 10px;" id="like"  @click="changeheart(item.obj.cno,idx)">
                                                <img v-if="state.imgcheck[idx].type === 0" :src="state.imgName" style="width: 30px"/>
                                                <img v-if="state.imgcheck[idx].type === 1" :src="state.imgName1" style="width: 30px"/>
                                            </v-btn>
                                        </v-col>
                                    </v-row>
                                    
                                    <v-row dense>
                                        <v-col>
                                            <h3 @click="handlePage(items.obj.cno, idx)" style="cursor: pointer;">{{item.obj.cname}}</h3>
                                        </v-col>
                                    </v-row>

                                    <v-row dense style="height: 60px;" >
                                        <v-col sm="12">
                                            <h4>{{item.obj.cdesc}}</h4>
                                            <!-- {{items.cdesc}} -->
                                            <!-- {{state.card.desc1}} -->
                                        </v-col>
                                    </v-row>

                                    <v-row dense>
                                        <v-col>
                                            <h4>{{item.obj.carea}}</h4>
                                            <!-- <h5>{{state.card.area1}}&nbsp;{{state.card.area2}}</h5> -->
                                        </v-col>
                                    </v-row>
                                </v-card>                        
                            </v-col>
                        </v-row>
                    </div>
                </v-col>

                <v-col sm="2"></v-col>
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
import { onMounted, computed } from '@vue/runtime-core';
import axios from 'axios';
import {useRoute, useRouter} from 'vue-router';
import { useStore } from 'vuex';

export default {
    components: { HeaderVue, FooterVue },

    setup () {
        const route = useRoute();
        const router = useRouter();
        const store  = useStore();
        const store1  = useStore();
        const keyword = computed(() => store1.getters.getKeyword);

        const state  = reactive({
            joinSearch : [],
            search : route.query.title,
            token    : sessionStorage.getItem("TOKEN"),
            logged   : computed(() => store.getters['moduleA/getLogged']),
            area     : '',
            items1   : '',
            datechk  : [],
            timechk  : [],
            likecheck: [],
            imgcheck : [],
            imgName  : require(`../../assets/img/heart.png`),
            imgName1 : require(`../../assets/img/heart1.png`),
            logo     : 'club_logo',
            show     : 'true',

            tab: ['서울', '경기', '인천', '부산', '대전', '대구', '울산', 
                '강원', '충북', '충남', '전북', '전남', '경북', '경남', '제주'],

            card: {
                clubname: '삥뽕탁구클럽',
                desc    : '설명글을 잔뜩!zzzzzzzzzzzz',
                desc1   : '',
                area1   : '부산',
                area2   : '연제구',
            },
        });

        onMounted (async()=>{
            handleData(), Likelist();
            console.log(state.search);
            if (state.card.desc.length >= 20) {
                state.card.desc1 = state.card.desc.substring(0, 20) + '...'
            }
        });

        // 카테고리 검색 
        const catesearch = async() => {
            console.log(state.search);
            if(state.search !== undefined){
                if(state.search !== ''){
                    const url      = `/ROOT/club/searchcateclub?cate=${state.search}`;
                    const headers  = { "Content-Type": "application.json" };
                    const response = await axios.get(url,{ headers: headers });
                    console.log(response.data);

                    if(response.data.status === 200){
                        state.items = response.data.result;

                        for(var i = 0; i < state.items.length; i++){
                            state.imgcheck.push({ cno: state.items[i].obj.cno, type: 0 })
                        }
                    }   
                }
            }
        }

        // 장소 등록해논거 삭제
        const del = (idx) => {
            console.log(idx);
            state.area.splice(idx, 1)
            state.area
        }

        // 장소 전체선택
        const all = async() => {
            state.area     = [];
            const url      = `/ROOT/club/selectlist`;
            const headers  = { "Content-Type": "application.json" };
            const response = await axios.get(url,{ headers: headers });
            console.log(response.data);

            if(response.data.status === 200){
                state.items = response.data.result;
            }
        }

        // 대분류 검색
        const search1 = async(e) => {
            state.area     = [];
            const url      = `/ROOT/address/search1?address=${e}`;
            const headers  = { "Content-Type": "application.json" };
            const response = await axios.get(url,{ headers: headers });
            console.log(response.data);

            if(response.data.status === 200){
                state.addr1 = response.data.result;
                console.log(state.addr1);
            }
            Clicksearch(e);
        }

        // 중분류 검색
        // const search2 = async() => {
        //     const url      = `/ROOT/address/search2?address=${state.search2}`;
        //     const headers  = { "Content-Type": "application.json" };
        //     const response = await axios.get(url,{ headers: headers });
        //     console.log(response.data);

        //     if(response.data.status === 200){
        //         state.addr2 = response.data.result;
        //         console.log(state.addr2);
        //     };
        // }

        // 전체불러오기
        const addr1 = async() => {
            const url      = `/ROOT/address/addr1`;
            const headers  = { "Content-Type": "application.json" };
            const response = await axios.get(url,{ headers: headers });
            console.log(response.data);

            if(response.data.status === 200){
                state.addr = response.data.result;
                console.log(state.addr);
            }
        }

        const handleData = async() => {
            const url      = `/ROOT/club/selectlist`;
            const headers  = { "Content-Type": "application.json" };
            const response = await axios.get(url, { headers: headers });
            console.log(response.data);

            if(response.data.status === 200){
                state.items = response.data.result;
                for(var i = 0; i < state.items.length; i++){
                    state.imgcheck.push({ cno: state.items[i].obj.cno, type: 0 })
                }
            }   
            catesearch();
        }

        const Likelist = async() => {
            const url      = `/ROOT/api/like/selectlist`;
            const headers  = { "Content-Type": "application.json", "token": state.token };
            const response = await axios.get(url,{ headers: headers });
            console.log(response.data);

            if(response.data.status === 200){
                state.likelist = response.data.result;

                for(var j = 0; j < state.imgcheck.length; j++){
                    for(var i = 0; i < state.likelist.length; i++){
                        if(state.imgcheck[j].cno === Number(state.likelist[i].clubCno)){
                            state.imgcheck[j].type = 1;
                        }
                    }
                }
            console.log(state.likelist);
            }
        }

        const Clicksearch = async(area) => {
            state.area.push(area)

            state.joinSearch = state.area.join("|", state.area);
            
            console.log(state.joinSearch);
            
            const url      =`/ROOT/club/searchclub?address=${area}`
            const headers  = { "Content-Type": "application.json" };
            const response = await axios.get(url, { headers: headers });

            if(response.data.status === 200){
                state.items = response.data.result;
            }
            const arr = [...new Set(state.area)];
            console.log(arr);
            state.area = arr;
        }

         const changeheart = async(cno,idx) => {
            console.log(state.likelist.clubCno);
            console.log(cno);
            if(state.imgcheck[idx].cno === cno ){
                if(state.imgcheck[idx].type === 1){
                    state.imgcheck[idx].type = 0;
                }
                else{
                    state.imgcheck[idx].type = 1;
                }
            }
            const url      = `/ROOT/api/like/insert`
            const headers  = { "Content-Type": "multipart/form-data", "token": state.token };
            const body     = new FormData;
            body.append("club", cno);
            const response = await axios.post(url,body,{headers:headers});
            console.log(response.data);

            if(response.data.status == 200){
                console.log("찜하기성공");
            }
            if(response.data.status == -1 ){
                console.log("찜하기 취소");
                unlike(cno, idx);
            }
        };

        const handlePage = async(cno) => {
            router.push({ name: "ClubDetailVue", query: { cno: cno } })
            console.log(cno);
        }

        const unlike = async(cno,idx) => {
            console.log("unlike", state.imgcheck[idx]);
            const url      = `/ROOT/api/like/deleteone`
            const headers  = { "Content-Type":"multipart/form-data", token: state.token};
            const body     = new FormData;
            body.append("club", cno);   
            const response = await axios.post(url, body, {headers:headers});
            console.log(response.data);

            if(response.data.status == 200){
                    // state.imgName = state.imaName1
            }
        }

    //     const like = async() => {
    //    const url =`/ROOT/like/insert`
    //         const headers = {"Content-Type":"application.json"};
    //         const body = {
    //             club : state.items.obj.cno
    //         }
    //         const response = await axios.post(url,body,{headers:headers});
    //             console.log(response.data);
    //             if(response.data.status === 200){
    //                 console.log(area);
    //                 state.items = response.data.result;
    //     }
    //     }

        const reset = async() => {
            state.datechk = [];
            state.timechk = [];
            all();
        };

        const resetdate = async() => {
            state.datechk = [];
        };

        const resettime = async() => {
            state.timechk = [];
        }
        
        return {
        state, addr1, search1, del, reset, resetdate, 
        resettime, Clicksearch, all, changeheart, unlike, handlePage }
    }
}
</script>

<style lang="scss" scoped>
.h5:hover {
    border: 3px solid gold;
    border-radius: 3px;

}
.hov1 {
    display: none;
}
.h5:hover .hov1 {
    display: inline;
    margin-left: 5px;
    margin-right: 5px;
    color: red;
}
</style>