<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app id="inspire"> 
        <!-- 메인 리스트 -->
        <v-main>
            <div style="padding: 20px;">
                <v-row>
                    <v-col sm="2"><img :src="require('../assets/img/banner_left.jpg')" style="width: 100%; cursor: pointer;" @click="clubDetail()"/></v-col>

                    <v-col>
                        <v-row>
                            <v-col sm="9">
                                <v-row>
                                    <v-col sm="8">
                                        <v-card height="150" style="border-width: 4px; border-color: gold;">
                                            <vueper-slides ref="myVueperSlides" autoplay fixed-height="100%">
                                                <vueper-slide
                                                    v-for="tmp1 in state.slides" :key="tmp1" :title="tmp1.title"
                                                    :image="tmp1.image" />
                                            </vueper-slides>
                                        </v-card>
                                    </v-col>

                                    <v-col sm="4">
                                        <v-card height="150" style="padding: 10px; border-width: 4px; border-color: gold;">
                                            <v-row dense style="padding-bottom: 10px;">
                                                <v-col sm="5" class="col_left"><h4>공지사항</h4></v-col>

                                                <v-col sm="5"></v-col>

                                                <v-col sm="2" class="col_right">
                                                    <router-link to="/hqna" class="col_right">
                                                        <v-icon style="margin-top: -5px;" icon="smi-arrow-right-bold" />
                                                    </router-link>
                                                </v-col>
                                            </v-row>

                                            <v-row dense @click="notice()" style="cursor: pointer;">
                                                <v-col sm="3">
                                                    <h4 style="color: navy;">[공지]</h4>
                                                </v-col>

                                                <v-col class="col_left">
                                                    <h4>{{state.notice}}</h4>
                                                </v-col>
                                            </v-row>

                                            <v-row dense @click="notice()" style="cursor: pointer;">
                                                <v-col sm="3">
                                                    <h4 style="color: navy;">[공지]</h4>
                                                </v-col>

                                                <v-col class="col_left">
                                                    <h4>{{state.notice}}</h4>
                                                </v-col>
                                            </v-row>
                                            
                                            <v-row dense @click="notice()" style="cursor: pointer;">
                                                <v-col sm="3">
                                                    <h4 style="color: navy;">[공지]</h4>
                                                </v-col>

                                                <v-col class="col_left">
                                                    <h4>{{state.notice}}</h4>
                                                </v-col>
                                            </v-row>
                                        </v-card>
                                    </v-col>
                                </v-row>

                                <v-row>
                                    <v-col sm="6" class="home_box">
                                        <v-card height="150" style="border-width: 4px; border-color: gold;">
                                            <v-row dense>
                                                <v-col class="col_center"><h3 style="margin-top: 10px;">지역별 클럽</h3></v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col style="justify-content: center; display: flex;">
                                                    <a class="region">전국</a> <a class="region">서울</a> <a class="region">경기</a> <a class="region">인천</a>
                                                    <a class="region">부산</a> <a class="region">대전</a> <a class="region">대구</a> <a class="region">울산</a>
                                                </v-col>
                                            </v-row>
                                            
                                            <v-row dense>
                                                <v-col style="justify-content: center; display: flex;">
                                                    <a class="region">강원</a> <a class="region">충북</a> <a class="region">충남</a> <a class="region">전북</a>
                                                    <a class="region">전남</a> <a class="region">경북</a> <a class="region">경남</a> <a class="region">제주</a> 
                                                </v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col sm="1"></v-col>

                                                <v-col sm="10" style="margin-top: 5px; border-top: 1px solid #CCC; justify-content: center; display: flex;">
                                                    <v-row dense="">
                                                        <v-col sm="6" class="col_center"><a>온라인</a></v-col>
                                                        <v-col sm="6" class="col_center"><a>준비중</a></v-col>
                                                    </v-row>
                                                </v-col>

                                                <v-col sm="1"></v-col>
                                            </v-row>
                                        </v-card>
                                    </v-col>

                                    <v-col sm="6">
                                        <v-card height="150" style="border-width: 4px; border-color: gold;">
                                            <v-row dense>
                                                <v-col class="col_center"><h3 style="margin-top: 10px;">테마별 클럽</h3></v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col sm="1"></v-col>

                                                <v-col sm="10" style="justify-content: center; display: flex;">
                                                    <a class="region">스포츠</a> <a class="region">헬스</a> <a class="region">음악</a> <a class="region">영화</a>
                                                    <a class="region">IT</a> <a class="region">아웃팅</a> <a class="region">여행</a> 
                                                </v-col>

                                                <v-col sm="1"></v-col>
                                            </v-row>
                                            
                                            <v-row dense>
                                                <v-col sm="1"></v-col>

                                                <v-col sm="10" style="justify-content: center; display: flex; border-bottom: 1px solid #CCC;">
                                                    <a class="region">게임</a> <a class="region">스터디</a> <a class="region">캠핑</a> <a class="region">주식/재테크</a>
                                                    <a class="region">자기계발</a>
                                                </v-col>

                                                <v-col sm="1"></v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col sm="1"></v-col>

                                                <v-col sm="10" style="margin-top: 5px; justify-content: center; display: flex;">
                                                    <v-row dense="">
                                                        <v-col sm="4" class="col_center"><a>연령별</a></v-col>
                                                        <v-col sm="4" class="col_center"><a>고민된다</a></v-col>
                                                        <v-col sm="4" class="col_center"><a>잠온다</a></v-col>
                                                    </v-row>
                                                </v-col>

                                                <v-col sm="1"></v-col>
                                            </v-row>
                                        </v-card>
                                    </v-col>
                                </v-row>
                            </v-col>

                            <v-col sm="3" style="align:center;" v-if="!state.logged">
                                <v-row>
                                    <v-col>
                                        <router-link to="/login">
                                            <v-card height="150" style="padding-top: 6%; border-width: 4px; border-color: gold;">
                                                <v-row dense>
                                                    <v-col class="col_center">
                                                        <img :src="require('../assets/img/login.png')" style="width:80px;"/>
                                                    </v-col>
                                                </v-row>
                                                
                                                <v-row dense><v-col class="col_center"><h3>로그인</h3></v-col></v-row>
                                            </v-card>
                                        </router-link>
                                    </v-col>
                                </v-row>

                                <v-row>
                                    <v-col>
                                        <router-link to="/join">
                                            <v-card height="150" style="padding-top: 6%; border-width: 4px; border-color: gold; align-item: center;">
                                                <v-row dense>
                                                    <v-col class="col_center">
                                                        <img :src="require('../assets/img/join.png')" style="width:80px;"/>
                                                    </v-col>
                                                </v-row>
                                                
                                                <v-row dense><v-col class="col_center"><h3>회원가입</h3></v-col></v-row>
                                            </v-card>
                                        </router-link>
                                    </v-col>
                                </v-row>
                            </v-col>

                            <v-col sm="3" style="align:center;" v-if="state.logged">
                                <v-row dense>
                                    <v-col>
                                        <v-card style="padding: 10px; border-width: 4px; border-color: gold; align-item: center; height: 313px;">
                                            <v-row dense>
                                                <v-col sm="6">
                                                    <img  :src="state.imageUrl"  style="width: 100px; border: 1px solid #CCC;"/>
                                                </v-col>

                                                <v-col sm="6">
                                                    <v-row dense>
                                                        <v-col sm="8" v-if="state.cname" style="padding-top: 5px;">
                                                            <h5>기업회원</h5>
                                                        </v-col>
                                                        
                                                        <v-col sm="8" v-if="state.nick" style="padding-top: 5px;">
                                                            <h5>개인회원</h5>
                                                        </v-col>

                                                        <v-col class="col_right">
                                                            <router-link to="/activity"><img :src="require(`../assets/img/bell.png`)" style="width: 20px"/></router-link>
                                                        </v-col>
                                                    </v-row>

                                                    <v-row dense>
                                                        <router-link to="/regclub">
                                                            <v-btn style="background-color: gold;"><h3>클럽생성</h3></v-btn>
                                                        </router-link>
                                                    </v-row>
                                                </v-col>
                                            </v-row>

                                            <v-row dense style="padding-left: 10px; height: 155px;">
                                                <v-col>
                                                    <v-row v-if="state.cname">
                                                        <v-col class="col_left" >
                                                            <h4>{{state.cname.mcname}}</h4>님 환영합니다!
                                                        </v-col>
                                                    </v-row>
                                                    <v-row v-if="state.nick">
                                                        <v-col class="col_left" >
                                                                <h4>{{state.nick.mpnickname}}</h4>님 환영합니다!
                                                        </v-col>
                                                    </v-row>

                                                    <v-row>
                                                        <v-col>

                                                        </v-col>
                                                    </v-row>
                                                </v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col sm="4" class="col_center">
                                                    <router-link to="/mypage"><h4>내정보</h4></router-link>
                                                </v-col>

                                                <v-col sm="4" class="col_center">
                                                    <router-link to="/mypage"><h4>찜목록</h4></router-link>
                                                </v-col>

                                                <v-col sm="4" class="col_center">
                                                    <router-link to="/logout"><h4>로그아웃</h4></router-link>
                                                </v-col>
                                            </v-row>
                                        </v-card>
                                    </v-col>
                                </v-row>
                            </v-col>

                        </v-row>

                        <v-row style="border-top: 1px solid #CCC; margin-top: 20px;"><h4 style="padding: 10px;">슈퍼 클럽</h4></v-row>

                        <v-row>
                            <v-col
                             v-for="items in state.items1" :key="items"
                            cols="4"   
                            >
                                <v-card height="200px" class="club_card" style="padding: 20px; ">
                                    <v-row dense>
                                        <v-col sm="3"></v-col>
                    
                                        <v-col sm="6" class="col_center">
                                            <router-link to="/cdetail" class="col_center">
                                                <img :src="items.imgurl" style="height: 50px;"/>
                                            </router-link>
                                        </v-col>

                                        <v-col sm="3" class="col_right">
                                            <v-btn style="height: 100%; width: 10px;" id="like" @click="changeheart()">
                                                <img :src="require(`../assets/img/${state.imgName}.png`)" style="width: 30px"/>
                                            </v-btn>
                                        </v-col>
                                    </v-row>
                                    
                                    <v-row dense>
                                        <v-col>
                                            <h4>{{items.obj.cname}}</h4>
                                        </v-col>
                                    </v-row>

                                    <v-row dense style="height:60px;">
                                        <v-col>
                                            <h5 style="display: flex">{{items.obj.cdesc}}</h5>
                                        </v-col>
                                    </v-row>

                                    <v-row dense style="vertical-align: baseline;">
                                        <v-col>
                                            <h5>{{items.obj.carea}}</h5>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </v-col>
                        </v-row>
                    </v-col>

                    <v-col sm="2"><img :src="require('../assets/img/banner_right.jpg')" style="width:100%; cursor: pointer;" @click="clubDetail()"/></v-col>
                </v-row>
            </div>
        </v-main>
    </v-app>
<FooterVue></FooterVue>
</div>
</template>

<script>
import { reactive } from '@vue/reactivity';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex'
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
import FooterVue from '../components/FooterVue.vue';
import HeaderVue from '../components/HeaderVue.vue';
import { onMounted, computed } from '@vue/runtime-core';
import axios from 'axios';

export default {
    components: { VueperSlides, VueperSlide, HeaderVue, FooterVue },
    setup () {
        const router = useRouter();
        const store = useStore();

        onMounted (()=>{
            handleData();
            if(state.token != null){
                role(),mypage(),nick();
                // console.log(state.role);
                // if(state.role3 === 'PERSONAL'){
                    //     handlenick();
                //     }
                // if(state.role2 =='COMPANY'){
                //     cname();
                // }
            }

            if(state.token === null){
                store.commit('moduleA/setLogged', false)
            } 
            else{
                store.commit('moduleA/setLogged', true)
            }

            if (state.card.desc.length >= 40) {
                state.card.desc1 = state.card.desc.substring(0, 40) + '...'
            }            
        });



        const state = reactive({
            role1 : 'PERSONAL',
            role2 : 'COMPANY',
            role3 : '',
            imageUrl1 : '',
            imageUrl : require('../assets/img/profile_sample.png'),
            logged: computed(() => store.getters['moduleA/getLogged']),
            token : sessionStorage.getItem("TOKEN"),

            slides: [
                { title: '', image: require('../assets/img/ad1.jpg') },
                { title: '', image: require('../assets/img/ad2.jpg') },
                { title: '', image: require('../assets/img/ad3.jpg') }
            ],
            imgName: 'heart',
            logo: 'club_logo',

            card: {
                clubname: '삥뽕탁구클럽',
                desc: '설명글을 잔뜩!zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz',
                desc1: '',
                area1: '부산',
                area2: '연제구',
            },
            notice: '공지글입니딩~',

            profile: {
                nickname: '탁구왕김제빵',
            },            
        });

        const handleData = async() => {
            const url = `/ROOT/club/selectlist`;
            const headers = {"Content-Type":"application.json"};
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);

            if(response.data.status === 200){
                state.items1 = response.data.result;
                console.log(response.data.result);
            }
        }

        const mypage = async() => {
            const url = `/ROOT/member/mypage`;
            const headers = {"Content-Type":"application/json", 
            token : state.token};
            const response = await axios.get(url, {headers});
            console.log(response.data.result);

            if(response.data.status === 200){
                state.items = response.data.result;
                if(state.items.mprofile != ""){
                    if(state.items.mprofile != null){
                        state.imageUrl = response.data.result.mimageurl
                        state.imageUrl1 = response.data.result.mimageurl
                    }
                }
                else{
                    state.imageUrl = '';
                }
            }
        }

            const cname = async() => {
                const url = `/ROOT/member/cname`;
                const headers = {"Content-Type":"application/json", 
                token : state.token};
                const response = await axios.get(url, {headers});
                console.log(response.data.result);
                if(response.data.status === 200){
                    state.cname = response.data.result;
                }
            }
            const nick = async() => {
                const url = `/ROOT/member/psmynick`;
                const headers = {"Content-Type":"application/json", 
                token : state.token};
                const response = await axios.get(url, {headers});
                console.log(response.data.result);
                if(response.data.status === 200){
                    state.nick = response.data.result;
                }
            }
            const role = async() => {
                const url = `/ROOT/member/role`;
                const headers = {"Content-Type":"application/json", 
                token : state.token};
                const response = await axios.get(url, {headers});
                console.log(response.data.result);
                if(response.data.status === 200){
                    state.role = response.data.result;
                    if(state.role === 'PERSONAL'){
                        // handlenick();
                    }
                    if(state.role === 'COMPANY'){
                        cname();
                    }
                }
            }
        // const handlenick = async() => {
        //     const url = `/ROOT/member/psmynick`;
        //     const headers = {"Content-Type":"application/json", 
        //     token : state.token};
        //     const response = await axios.get(url, {headers});
        //     console.log(response.data.result);
        //     if(response.data.status === 200){
        //         state.nick = response.data.result.mpnickname;
        //         console.log(state.nick);
        //     }
        // }

        const changeheart = () => {
            if (state.imgName === 'heart') {
                state.imgName = 'heart1'
            } else {
                state.imgName = 'heart'
            }
        };

        

        const notice = () => {
            router.push({ name: "HelpQnaContentVue" });
        };

        const handlelogout = () => {
            if(confirm('로그아웃 하시겠습니까?'))
            router.push({ name: "LogoutVue" });
        };

        const clubDetail = () => {
            router.push({ name: "ClubDetailVue" });
        }


        return { state, changeheart, handlelogout, notice, clubDetail }
    },
    data: () => ({
        autoPlaying: true,
    })
}
</script>

<style lang="scss" scoped>

</style>