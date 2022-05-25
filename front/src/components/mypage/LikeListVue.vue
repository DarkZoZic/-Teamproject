<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <!-- 사이드 -->
                <v-col sm="2"></v-col>
                
                <!-- 메인 -->
                <v-col sm="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > <router-link to="/mypage">마이페이지</router-link> > 찜목록 </h5>
                        </v-col>
                    </v-row>

                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>찜목록</h2>
                        </v-col>
                        <v-col class="col_right">
                            <router-link to="/pupdate"><v-btn><h3>회원정보수정</h3></v-btn></router-link>
                            <router-link to="/pwupdate"><v-btn><h3>비밀번호변경</h3></v-btn></router-link>
                            <router-link to="/nupdate"><v-btn><h3>닉네임변경</h3></v-btn></router-link>
                            <router-link to="/likelist"><v-btn><h3>찜목록</h3></v-btn></router-link>
                            <router-link to="/activity"><v-btn><h3>내활동</h3></v-btn></router-link>
                            <router-link to="/dontgo"><v-btn><h3>회원탈퇴</h3></v-btn></router-link>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col
                            v-for="(item,idx) in state.items12" :key="item"
                            cols="4"
                        >
                            <v-card height="200px" class="club_card" style="padding: 20px;">
                                <v-row dense>
                                    <v-col sm="8" class="col_center">
                                        <router-link to="/cdetail" class="col_center">
                                            <img  :src="item.imgurl"  style="height: 50px;"/>
                                        </router-link>

                                    </v-col>
                                    <v-col sm="3" class="col_right">
                                        <v-btn style="height: 100%; width: 10px;" id="like"  @click="changeheart(item.obj.club.cno,idx)">
                                            <img   v-if="state.imgcheck[idx].type === 0" :src="state.imgName"  style="width: 30px"/>
                                            <img   v-if="state.imgcheck[idx].type === 1" :src="state.imgName1"  style="width: 30px"/>
                                        </v-btn>
                                    </v-col>
                                </v-row>
                                
                                <v-row dense>
                                    <v-col>
                                    <h4>{{item.obj.club.cname}}</h4>
                                    </v-col>
                                </v-row>

                                <v-row dense>
                                    <v-col sm="12">
                                        <h4>
                                       {{item.obj.club.cdesc}}
                                        </h4>
                                            
                                    </v-col>
                                </v-row>

                                <v-row dense>
                                    <v-col>
                                        <h4>{{item.obj.club.carea}}</h4>
                                    </v-col>
                                </v-row>
                            </v-card>                        
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col class="col_center" style="margin-top: 15px;">
                            <v-btn style="margin-left: 15px;"><h3>전체삭제</h3></v-btn>
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
import { useRouter } from 'vue-router';
import FooterVue from '../../components/FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const router = useRouter();

        const state = reactive({
           token : sessionStorage.getItem("TOKEN"),
            items1 : '',
            datechk: [],
            timechk: [],
            likecheck : [],
            imgcheck : [],
            imgName: require(`../../assets/img/heart.png`),
            imgName1: require(`../../assets/img/heart1.png`),
            logo: 'club_logo',

            card: {
                clubname: '삥뽕탁구클럽',
                desc: '설명글을 잔뜩!',
                desc1: '',
                area1: '부산',
                area2: '연제구',
            },
        });


        onMounted (()=>{
            // handleData(),
            handleData1(),Lkelist();
            if (state.card.desc.length >= 40) {
                state.card.desc1 = state.card.desc.substring(0, 40) + '...'
            }
            state.card.desc1 = state.card.desc;
        });

        // const handleData = async() => {
        //     const url = `/ROOT/club/selectlist`;
        //     const headers = {"Content-Type":"application.json"};
        //     const response = await axios.get(url,{headers:headers});
        //         console.log(response.data);
        //         if(response.data.status === 200){
        //             state.items = response.data.result;
        //             for(var i =0; i < state.items.length; i++){
        //                 state.imgcheck.push({cno:state.items[i].obj.cno,type:0})
        //                 // state.imgcheck[i] = 0;s
        //             }
                        
        //             console.log(response.data.result[0].imgurl);
        //             console.log(response.data.result.length);
        //         }
        // };
        const handleData1 = async() => {
            const url = `/ROOT/club/likelist`;
            const headers = {"Content-Type":"application.json",
            token : state.token};
            const response = await axios.get(url,{headers:headers});
                console.log(response.data);
                if(response.data.status === 200){
                    state.items12 = response.data.result;
                    console.log("=============",state.items12);
                    console.log(state.items12[1]);
                    for(var i =0; i < state.items12.length; i++){
                        state.imgcheck.push({cno:state.items12[i].obj.club.cno,type:0})
                        // state.imgcheck[i] = 0;s
                    }
                }
        };

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
                                // state.items1 = state.imgcheck[j].type = 1;
    
                                
                            }
    
                        }
                    }
                    console.log(state.likelist);
        }
   
                
        }

        
                 const changeheart = async(cno,idx) => {
             console.log(state.likelist.clubCno);
             console.log(cno);
              if(state.imgcheck[idx].cno === cno ){


                  if(state.imgcheck[idx].type === 1){
                      state.imgcheck[idx].type =0;
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
        const deleteall = async() => {
            
        }

        const clubDetail = () => {
            router.push({ name: "ClubDetailVue" });
        }

        return { state, changeheart, clubDetail }
    }
}
</script>

<style lang="scss" scoped>

</style>