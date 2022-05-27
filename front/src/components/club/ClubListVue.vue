<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">

            <!-- 상단 표 -->
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
                    
                    <v-row dense="">
                        <!-- 구 -->
                        <v-col style="border: 5px solid gold;">
                            <v-row dense style="border-bottom: 1px solid #CCC; padding: 10px;">
                                <v-col class="col_center">
                                    <h3 style="cursor: pointer; margin-left: 10px;">전국</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">서울</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">경기</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">인천</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">부산</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">대전</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">대구</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">울산</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">강원</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">충북</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">충남</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">전북</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">전남</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">경북</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">경남</h3>
                                    <h3 style="cursor: pointer; margin-left: 10px;">제주</h3>
                                </v-col>
                            </v-row>
                            <v-row dense>
                                <v-col>
                                    <v-tabs
                                        v-model="state.tab"
                                        grow
                                        style="height: 40px;"
                                        >
                                        <v-tab v-for="tmp in state.tab" :key="tmp" :value="tmp"><h4>{{ tmp }}</h4></v-tab>
                                    </v-tabs>

                                    <v-card-text>
                                        <v-window v-model="state.tab2">
                                            <v-window-item v-for="tmp in state.tab" :key="tmp" :value="tmp">
                                                {{ tmp }}
                                            </v-window-item>
                                        </v-window>
                                    </v-card-text>
                                </v-col>
                            </v-row>

                            <v-row dense>
                                <v-col>
                                    <!-- 전체 포함한 배열로 변수 만들고 v-row 반복문 만들고, v-col 반복문 만들고.
                                    ex) 12개 => 1일 때 1, 2, 3, 4 / 2일 때 1, 2, 3, 4 / 
                                    (1*4+1)-5, (1*4+2)-5, (1*4+3)-5, (1*4+4)-5 / 2*4+1, 2*4+2-->
                                    <v-row dense="" class="row_pad5" style="padding-top: 10px;">
                                        <v-col sm="1"></v-col>
                                        <v-col sm="2"><h4 @click="all()" style="cursor: pointer;">전체</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('강서구')" style="cursor: pointer;">강서구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('금정구')" style="cursor: pointer;">금정구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('기장군')" style="cursor: pointer;">기장군</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('남구')" style="cursor: pointer;">남구</h4></v-col>
                                        <v-col sm="1"></v-col>
                                    </v-row>

                                    <v-row dense="" class="row_pad5">
                                        <v-col sm="1"></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('동구')" style="cursor: pointer;">동구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('동래구')" style="cursor: pointer;">동래구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('부산진구')" style="cursor: pointer;">부산진구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('북구')" style="cursor: pointer;">북구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('사상구')" style="cursor: pointer;">사상구</h4></v-col>
                                        <v-col sm="1"></v-col>
                                    </v-row>

                                    <v-row dense="" class="row_pad5">
                                        <v-col sm="1"></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('사하구')" style="cursor: pointer;">사하구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('서구')" style="cursor: pointer;">서구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('수영구')" style="cursor: pointer;">수영구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('연제구')" style="cursor: pointer;">연제구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('영도구')" style="cursor: pointer;">영도구</h4></v-col>
                                        <v-col sm="1"></v-col>
                                    </v-row>

                                    <v-row dense="" class="row_pad5" style="padding-bottom: 8px;">
                                        <v-col sm="1"></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('중구')" style="cursor: pointer;">중구</h4></v-col>
                                        <v-col sm="2"><h4 @click="Clicksearch('해운대구')" style="cursor: pointer;">해운대구</h4></v-col>
                                        <v-col sm="2"></v-col>
                                        <v-col sm="2"></v-col>
                                        <v-col sm="2"></v-col>
                                        <v-col sm="1"></v-col>
                                    </v-row>
                                </v-col>
                            </v-row>
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
                                                <v-col class="col_center" style="vertical-align: middle; margin-bottom: 10px;">
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="월"><h4>월</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="화"><h4>화</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="수"><h4>수</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="목"><h4>목</h4>
                                                    <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="금"><h4>금</h4>
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
                                        </v-col>             
                                    </v-row>

                                    <v-row dense>
                                        <v-col class="col_center">
                                            <v-btn class="club_list_btn"  v-model="state.area" @click="Clicksearch(state.area)"><h4>검색</h4></v-btn>
                                            <v-btn class="club_list_btn"  @click="reset()" ><h4>초기화</h4></v-btn>
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
                        <v-row>
                            <v-col v-for="(item,idx) in state.items" :key="item" cols="4">
                                <v-card height="200px" class="club_card" style="padding: 20px;" >
                                    <v-row dense>
                                        <v-col sm="3"></v-col>

                                        <v-col sm="6" class="col_center">
                                            <router-link to="/cdetail" class="col_center">
                                            <img v-if="item.imgurl" :src="item.imgurl"  style="height: 50px;"/>
                                            <img v-if="!item.imgurl" :src="require(`../../assets/img/default-logo.jpg`)"  style="height: 50px;"/>
                                            </router-link>
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
                                            <h3>{{item.obj.cname}}</h3>
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
import { useStore } from 'vuex';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const store = useStore();
        const state = reactive({
            token : sessionStorage.getItem("TOKEN"),
            logged: computed(() => store.getters['moduleA/getLogged']),
            area: [],
            tab: [],
            items1 : '',
            datechk: [],
            timechk: [],
            likecheck : [],
            imgcheck : [],
            imgName: require(`../../assets/img/heart.png`),
            imgName1: require(`../../assets/img/heart1.png`),
            logo: 'club_logo',
            show: 'true',

            card: {
                clubname: '삥뽕탁구클럽',
                desc: '설명글을 잔뜩!zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz',
                desc1: '',
                area1: '부산',
                area2: '연제구',
            },
        });

        onMounted (async()=>{
            handleData(), Lkelist();
            if (state.card.desc.length >= 20) {
                state.card.desc1 = state.card.desc.substring(0, 20) + '...'
            }
        })

        // 장소 등록해논거 삭제
        const del = (idx) => {
            console.log(idx);
            state.area.splice(idx, 1)
        }

        // 장소 전체선택
        const all = async() => {
            state.area= [];
            const url = `/ROOT/club/selectlist`;
            const headers = {"Content-Type":"application.json"};
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);
                if(response.data.status === 200){
                state.items = response.data.result;
            }
        }

        const handleData = async() => {
            const url = `/ROOT/club/selectlist`;
            const headers = {"Content-Type":"application.json"};
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);
            if(response.data.status === 200){
                state.items = response.data.result;
                for(var i =0; i < state.items.length; i++){
                    state.imgcheck.push({cno:state.items[i].obj.cno,type:0})
                    // state.imgcheck[i] = 0;s
                }
                console.log(response.data.result[0].imgurl);
                console.log(response.data.result.length);
            }   
        }

        const Lkelist = async() => {
            const url = `/ROOT/api/like/selectlist`;
            const headers = {"Content-Type":"application.json",
            token : state.token};
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);
            if(response.data.status === 200){
                state.likelist = response.data.result;

                for(var j =0; j<state.imgcheck.length; j++){

                    for(var i =0; i < state.likelist.length; i++){
                        if(state.imgcheck[j].cno === Number(state.likelist[i].clubCno) ){
                            state.imgcheck[j].type = 1;
                        }
                    }
                }
            console.log(state.likelist);
            }
        }

        const Clicksearch = async(area) => {
            state.area.push(area)
            const url =`/ROOT/club/searchclub?address=${area}`
            const headers = {"Content-Type":"application.json"};
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);

            if(response.data.status === 200){
                console.log(area);
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
             
            const url =`/ROOT/api/like/insert`
            const headers = {"Content-Type":"multipart/form-data",
                            token : state.token};
            const body = new FormData;
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
                // else {
                //     console.log( state.imgName);
                // }
            // if (state.imgName === 'heart') {
            //     state.imgName = 'heart1'
            //     console.log(state.imgName);
            // }
            // if(response.data.status == -1){
            //     // state.imgName = 'heart'
            //     unlike(cno);
            //     console.log(state.imgName);
            // }
                
        };

        const unlike = async(cno,idx) => {
            console.log("unlike", state.imgcheck[idx]);
            const url = `/ROOT/api/like/deleteone`
            const headers = {"Content-Type":"multipart/form-data",
                            token : state.token};
            const body = new FormData;
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
        };
        
        return {
        // like,
        state, del, reset ,Clicksearch, all, changeheart, unlike }
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