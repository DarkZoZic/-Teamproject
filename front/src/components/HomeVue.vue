<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app id="inspire"> 
        <!-- 메인 리스트 -->
        <v-main>
            <div style="padding: 20px;">
                <v-row>
                    <v-col md="2"><img :src="require('../assets/img/banner_left.jpg')" style="width: 100%; cursor: pointer;" @click="clubDetail()"/></v-col>

                    <v-col>
                        <v-row>
                            <v-col md="9">
                                <v-row>
                                    <v-col md="8">
                                        <v-card height="150" style="border-width: 4px; border-color: gold;">
                                            <vueper-slides ref="myVueperSlides" autoplay fixed-height="100%">
                                                <vueper-slide
                                                    v-for="tmp1 in state.slides" :key="tmp1" :title="tmp1.title"
                                                    :image="tmp1.image" />
                                            </vueper-slides>
                                        </v-card>
                                    </v-col>

                                    <v-col md="4">
                                        <v-card height="150" style="padding: 10px; border-width: 4px; border-color: gold;">
                                            <v-row dense style="padding-bottom: 10px;">
                                                <v-col md="5" class="col_left"><h4>공지사항</h4></v-col>

                                                <v-col md="5"></v-col>

                                                <v-col md="2" class="col_right">
                                                    <router-link to="/hqna" class="col_right">
                                                        <v-icon style="margin-top: -5px;" icon="mdi-arrow-right-bold" />
                                                    </router-link>
                                                </v-col>
                                            </v-row>

                                            <v-row dense @click="notice()" style="cursor: pointer;">
                                                <v-col md="3">
                                                    <h4 style="color: navy;">[공지]</h4>
                                                </v-col>

                                                <v-col class="col_left">
                                                    <h4>{{state.notice}}</h4>
                                                </v-col>
                                            </v-row>

                                            <v-row dense @click="notice()" style="cursor: pointer;">
                                                <v-col md="3">
                                                    <h4 style="color: navy;">[공지]</h4>
                                                </v-col>

                                                <v-col class="col_left">
                                                    <h4>{{state.notice}}</h4>
                                                </v-col>
                                            </v-row>
                                            
                                            <v-row dense @click="notice()" style="cursor: pointer;">
                                                <v-col md="3">
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
                                    <v-col md="6" class="home_box">
                                        <v-card height="150" style="border-width: 4px; border-color: gold;">
                                            <v-row dense>
                                                <v-col class="col_center"><h3 style="margin-top: 10px;">지역별 클럽</h3></v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col md="1"></v-col>

                                                <v-col md="10" style="justify-content: center; display: flex;">
                                                    <a class="region">전국</a> <a class="region">서울</a> <a class="region">경기</a> <a class="region">인천</a>
                                                    <a class="region">부산</a> <a class="region">대전</a> <a class="region">대구</a> <a class="region">울산</a>
                                                </v-col>

                                                <v-col md="1"></v-col>
                                            </v-row>
                                            
                                            <v-row dense>
                                                <v-col md="1"></v-col>

                                                <v-col md="10" style="justify-content: center; display: flex; border-bottom: 1px solid #CCC;">
                                                    <a class="region">강원</a> <a class="region">충북</a> <a class="region">충남</a> <a class="region">전북</a>
                                                    <a class="region">전남</a> <a class="region">경북</a> <a class="region">경남</a> <a class="region">제주</a> 
                                                </v-col>

                                                <v-col md="1"></v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col md="1"></v-col>

                                                <v-col md="10" style="margin-top: 5px; justify-content: center; display: flex;">
                                                    <v-row dense="">
                                                        <v-col md="4" class="col_center"><a>온라인</a></v-col>
                                                        <v-col md="4" class="col_center"><a>대대대</a></v-col>
                                                        <v-col md="4" class="col_center"><a>클럽맵</a></v-col>
                                                    </v-row>
                                                </v-col>

                                                <v-col md="1"></v-col>
                                            </v-row>
                                        </v-card>
                                    </v-col>

                                    <v-col md="6">
                                        <v-card height="150" style="border-width: 4px; border-color: gold;">
                                            <v-row dense>
                                                <v-col class="col_center"><h3 style="margin-top: 10px;">테마별 클럽</h3></v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col md="1"></v-col>

                                                <v-col md="10" style="justify-content: center; display: flex;">
                                                    <a class="region">스포츠</a> <a class="region">헬스</a> <a class="region">음악</a> <a class="region">영화</a>
                                                    <a class="region">IT</a> <a class="region">아웃팅</a> <a class="region">여행</a> 
                                                </v-col>

                                                <v-col md="1"></v-col>
                                            </v-row>
                                            
                                            <v-row dense>
                                                <v-col md="1"></v-col>

                                                <v-col md="10" style="justify-content: center; display: flex; border-bottom: 1px solid #CCC;">
                                                    <a class="region">게임</a> <a class="region">스터디</a> <a class="region">캠핑</a> <a class="region">주식/재테크</a>
                                                    <a class="region">자기계발</a>
                                                </v-col>

                                                <v-col md="1"></v-col>
                                            </v-row>

                                            <v-row dense>
                                                <v-col md="1"></v-col>

                                                <v-col md="10" style="margin-top: 5px; justify-content: center; display: flex;">
                                                    <v-row dense="">
                                                        <v-col md="4" class="col_center"><a>연령별</a></v-col>
                                                        <v-col md="4" class="col_center"><a>고민된다</a></v-col>
                                                        <v-col md="4" class="col_center"><a>잠온다</a></v-col>
                                                    </v-row>
                                                </v-col>

                                                <v-col md="1"></v-col>
                                            </v-row>
                                        </v-card>
                                    </v-col>
                                </v-row>
                            </v-col>

                            <v-col md="3" style="align:center;" v-if="!state.logged">
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

                            <v-col md="3" style="align:center;" v-if="state.logged">
                                <v-row dense>
                                    <v-col>
                                        <v-card style="padding: 10px; border-width: 4px; border-color: gold; align-item: center; height: 313px;">
                                            <v-row dense>
                                                <v-col md="6">
                                                    <img :src="require('../assets/img/profile_sample.png')" style="width: 100px;"/>
                                                    
                                                </v-col>

                                                <v-col md="6">
                                                    <v-row dense>
                                                        <v-col class="col_right">
                                                            <router-link to="/activity"><v-icon icon="mdi-bell-outline" /></router-link>
                                                            <v-icon icon="mdi-close" style="cursor: pointer;" @click="handlelogout()"/>
                                                        </v-col>
                                                    </v-row>
                                                </v-col>
                                            </v-row>

                                            <v-row dense style="padding-left: 10px; height: 155px;">
                                                <v-col>
                                                    <v-row>
                                                        <v-col class="col_left">
                                                            <h4>{{state.profile.nickname}}</h4>님 환영합니다!
                                                        </v-col>
                                                    </v-row>

                                                    <v-row>
                                                        <v-col></v-col>
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
                                                    <router-link to="/mypage"><h4>내정보</h4></router-link>
                                                </v-col>
                                            </v-row>
                                        </v-card>
                                    </v-col>
                                </v-row>
                            </v-col>

                        </v-row>

                        <v-row style="margin-top: 20px;"><h4 style="margin-left: 15px;">슈퍼 클럽</h4></v-row>

                        <v-row>
                            <v-col
                                v-for="n in 12"
                                :key="n"
                                cols="4"
                            >
                                <v-card height="200px" class="club_card" style="padding: 20px; ">
                                    <v-row dense>
                                        <v-col md="3"></v-col>

                                        <v-col md="6" class="col_center">
                                            <router-link to="/cdetail" class="col_center">
                                                <img :src="require(`../assets/img/${state.logo}.png`)" style="width: 100%"/>
                                            </router-link>
                                        </v-col>

                                        <v-col md="3" class="col_right">
                                            <v-btn style="height: 100%; width: 10px;" id="like" @click="changeheart()">
                                                <img :src="require(`../assets/img/${state.imgName}.png`)" style="width: 30px"/>
                                            </v-btn>
                                        </v-col>
                                    </v-row>
                                    
                                    <v-row dense>
                                        <v-col>
                                            <h4>{{state.card.clubname}}</h4>
                                        </v-col>
                                    </v-row>

                                    <v-row dense style="height: 70px;">
                                        <v-col md="12">
                                            <h5 style="display: flex">{{state.card.desc1}}</h5>
                                        </v-col>
                                    </v-row>

                                    <v-row dense style="vertical-align: baseline;">
                                        <v-col>
                                            <h5>{{state.card.area1}}&nbsp;{{state.card.area2}}</h5>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </v-col>
                        </v-row>
                    </v-col>

                    <v-col md="2"><img :src="require('../assets/img/banner_right.jpg')" style="width:100%; cursor: pointer;" @click="clubDetail()"/></v-col>
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

export default {
    components: { VueperSlides, VueperSlide, HeaderVue, FooterVue },
    setup () {
        const router = useRouter();
        const store = useStore();

        const state = reactive({
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
                desc: '설명글을 잔뜩!',
                desc1: '',
                area1: '부산',
                area2: '연제구',
            },
            notice: '공지글입니딩~',

            profile: {
                nickname: '탁구왕김제빵',
            },            
        });

        const changeheart = () => {
            if (state.imgName === 'heart') {
                state.imgName = 'heart1'
            } else {
                state.imgName = 'heart'
            }
        };

        onMounted (()=>{
            if (state.card.desc.length >= 40) {
                state.card.desc1 = state.card.desc.substring(0, 40) + '...'
            }
            state.card.desc1 = state.card.desc;
        });

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

        onMounted(() => {
            if(state.token === null){
                store.commit('moduleA/setLogged', false)
            } 
            else{
                store.commit('moduleA/setLogged', true)
            }
        })

        return { state, changeheart, handlelogout, notice, clubDetail }
    },
    data: () => ({
        autoPlaying: true,
    })
}
</script>

<style lang="scss" scoped>

</style>