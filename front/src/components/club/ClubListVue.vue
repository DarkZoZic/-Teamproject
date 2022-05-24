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

                        <v-col sm="7" class="col_right">
                            <router-link to="/regclub">
                                <v-btn style="background-color: gold;"><h3>클럽생성</h3></v-btn>
                            </router-link>
                            <router-link to="/comeclub">
                                <v-btn style="background-color: gold;"><h3>공고등록</h3></v-btn>
                            </router-link>                            
                        </v-col>
                    </v-row>
                    
                    <v-row dense="">
                        <!-- 구 -->
                        <v-col style="border: 5px solid gold;">
                            <v-row dense="" class="row_pad5" style="padding-top: 10px;">
                                <v-col sm="1"></v-col>
                                <v-col sm="2" @click="all()"><a><h4>전체</h4></a></v-col>
                                <v-col sm="2" @click="Clicksearch('강서구')"><a><h4>강서구</h4></a></v-col>
                                <v-col sm="2" @click="Clicksearch('금정구')"><a><h4>금정구</h4></a></v-col>
                                <v-col sm="2" @click="Clicksearch('기장군')"><a><h4>기장군</h4></a></v-col>
                                <v-col sm="2" @click="Clicksearch('남구')"><a><h4>남구</h4></a></v-col>
                                <v-col sm="1"></v-col>
                            </v-row>

                            <v-row dense="" class="row_pad5">
                                <v-col sm="1"></v-col>
                                <v-col sm="2"><a><h4>동구</h4></a></v-col>
                                <v-col sm="2"><a><h4>동래구</h4></a></v-col>
                                <v-col sm="2"><a><h4>부산진구</h4></a></v-col>
                                <v-col sm="2"><a><h4>북구</h4></a></v-col>
                                <v-col sm="2"><a><h4>사상구</h4></a></v-col>
                                <v-col sm="1"></v-col>
                            </v-row>

                            <v-row dense="" class="row_pad5">
                                <v-col sm="1"></v-col>
                                <v-col sm="2"><a><h4>사하구</h4></a></v-col>
                                <v-col sm="2"><a><h4>서구</h4></a></v-col>
                                <v-col sm="2"><a><h4>수영구</h4></a></v-col>
                                <v-col sm="2"><a><h4>연제구</h4></a></v-col>
                                <v-col sm="2"><a><h4>영도구</h4></a></v-col>
                                <v-col sm="1"></v-col>
                            </v-row>

                            <v-row dense="" class="row_pad5" style="padding-bottom: 10px;">
                                <v-col sm="1"></v-col>
                                <v-col sm="2"><a><h4>중구</h4></a></v-col>
                                <v-col sm="2"><a><h4>해운대구</h4></a></v-col>
                                <v-col sm="2"></v-col>
                                <v-col sm="2"></v-col>
                                <v-col sm="2"></v-col>
                                <v-col sm="1"></v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    <div id="app">
                        <div class="search">
                            <p>
                                <input type="text" style="border: 1px solid;" @change="input"/>
                                <button @click="click">click</button>
                            </p> 
                            <p>{{state.text}}</p> 
                        </div> 
                    </div>
                    <v-row dense style="margin-top: 20px;">
                        <v-col>
                            <v-row dense style="border: 1px solid #CCC">
                                <v-col>
                                    <v-row dense style="border-bottom: 1px solid #CCC;">
                                        <v-col sm="1" class="col_left" style="vertical-align: middle;" ><h4 class="club_list_h">지역</h4></v-col>
                                        <v-col sm="11"><input type="text" id="" @keyup.enter="Clicksearch(state.area)" v-model="state.area" class="club_list_input" style="outline-width: 0;"></v-col>
                                    </v-row>

                                    <v-row dense style="padding-top: 5px;">
                                        <v-col sm="1" class="col_left" style="vertical-align: middle;"><h4 class="club_list_h">테마</h4></v-col>
                                        <v-col sm="11" style="vertical-align: middle; outline-width: 0;"><input type="text" class="club_list_input"></v-col>
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
                                            <v-btn class="club_list_btn"  @click="all()" ><h4>초기화</h4></v-btn>
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
            <v-row>
                <v-col sm="2"></v-col>
                
                <v-col sm="8">
                    <v-row dense="">
                        <v-col>
                            <h3>클럽정보</h3>

                        </v-col>
                        
                    </v-row>
                    <div v-if="state.items">
                    <v-row >
                        <v-col v-for="item in state.items" :key="item"
                            cols="4">
                            <v-card height="200px" class="club_card" style="padding: 20px;" >
                                <v-row dense>
                                    <v-col sm="3"></v-col>
                                    <v-col sm="6" class="col_center">
                                        <router-link to="/cdetail" class="col_center">
                                        <img  :src="item.imgurl"  style="width: 70%;"/>
                                            <!-- <img :src="require(`../../assets/img/${state.logo}.png`)" style="width: 100%"/> -->
                                        </router-link>
                                    </v-col>
                                    <v-col sm="3" class="col_right">
                                        <v-btn style="height: 100%; width: 10px;" id="like"  @click="changeheart(item.obj.cno)">
                                            <img  :src="state.imgName"  style="width: 30px"/>
                                        </v-btn>
                                    </v-col>
                                </v-row>
                                
                                <v-row dense>
                                    <v-col>
                                        <h4>{{item.obj.cname}}</h4>
                                            

                                    </v-col>
                                </v-row>

                                <v-row dense style="height: 70px;" >
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
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const state = reactive({
            token : sessionStorage.getItem("TOKEN"),
            items1 : '',
            datechk: [],
            timechk: [],
            imgName: require(`../../assets/img/heart.png`),
            imgName1: require(`../../assets/img/heart1.png`),
            logo: 'club_logo',

            card: {
                clubname: '삥뽕탁구클럽',
                desc: '설명글을 잔뜩!zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz',
                desc1: '',
                area1: '부산',
                area2: '연제구',
            },

            text: '',
            inputText: '',

        });

        onMounted (()=>{
            handleData();
            if (state.card.desc.length >= 20) {
                state.card.desc1 = state.card.desc.substring(0, 20) + '...'
            }
        })
        const all = async() => {
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
                    console.log(response.data.result[0].imgurl);
                    console.log(response.data.result.length);
                    // console.log(state.items.target);
                    // state.imageUrl = response.data.result.imgurl
                    // console.log(state.imageUrl);
        }
   
                
        }
        const Clicksearch = async(area) => {
            const url =`/ROOT/club/searchclub?address=${area}`
            const headers = {"Content-Type":"application.json"};
            const response = await axios.get(url,{headers:headers});
                console.log(response.data);
                if(response.data.status === 200){
                    console.log(area);
                    state.items = response.data.result;
        }
        }
         const changeheart = async(cno) => {
             console.log(cno);
            const url =`/ROOT/api/like/insert`
            const headers = {"Content-Type":"multipart/form-data",
                            token : state.token};
            const body = new FormData;
        body.append("club", cno);
            const response = await axios.post(url,body,{headers:headers});
                console.log(response.data);
                if(response.data.status == 200){
                    state.imgName = state.imgName1
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
        const unlike = async(cno) => {
            if(state.imgName == 'heart1'){

                console.log("unlike", cno);
                const url = `/ROOT/api/like/deleteone?cno=${cno}`
                const headers = {"Content-Type":"multipart/form-data",
                                token : state.token};
                const body = new FormData;
                    body.append("club", cno);   
                const response = await axios.delete(url,{headers:headers, data:{}});
                console.log(response.data);
                if(response.data.status == 200){
                    console.log(response.data);
                        state.imgName = 'heart'
                    }
            }

        }
       

        const input = (e) => {
            return state.inputText = e.target.value
        };
    //     const like = async() => {
    //    const url =`/ROOT/like/insert`
    //         const headers = {"Content-Type":"application.json"};
    //         const body = {
    //             club = state.items.obj.cno
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

        const search = async() => {
            
        }

        const area = async() => {

        }
        
        return { input,
        // like,
        state, reset, search,Clicksearch,all,changeheart }
    }
}
</script>

<style lang="scss" scoped>

</style>