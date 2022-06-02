<template>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <!-- 사이드 -->
                <v-col sm="2"></v-col>
                
                <!-- 메인 -->
                <v-col sm="8">
                    <v-row dense>                        
                        <v-card color="basil" width="100%">
                            <v-row dense>
                                <v-col sm="2">
                                    <router-link to="/">
                                        <img :src="require('../../assets/img/cluver_logo.png')" style="height: 80px;">
                                    </router-link>
                                </v-col>

                                <v-col sm="8" class="col_center">
                                    <v-card-title class="text-center justify-center py-6">
                                        <h1 style="font-family: 'HallymGothic-Regular'; cursor: pointer;" @click="Home()" >
                                            {{state.clubname}}
                                        </h1>
                                    </v-card-title>
                                </v-col>
                                
                                <v-col sm="2" class="col_center" style="padding-right: 20px;">
                                    <img :src="state.logo" style="cursor: pointer; height: 70px;" @click="Home()" v-if="state.logo !== null"/>
                                </v-col>
                            </v-row>
                        </v-card>
                    </v-row>

                    <v-row dense>
                        <v-card color="basil" width="100%">
                            <v-tabs
                                v-model="state.tab"
                                background-color="transparent"
                                color="basil"
                                grow
                            >
                                
                                <v-tab @click="Home()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[0] }}</h3>
                                </v-tab>
                                
                                <v-tab @click="boardList()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[1] }}</h3>
                                </v-tab>

                                <v-tab @click="gallery()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[2] }}</h3>
                                </v-tab>

                                <v-tab @click="schedule()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[3] }}</h3>
                                </v-tab>

                                <v-tab @click="member()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[4] }}</h3>
                                </v-tab>

                                <!-- <v-tab @click="chat()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[5] }}</h3>
                                </v-tab> -->

                                <v-tab @click="setting()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[6] }}</h3>
                                </v-tab>
                                
                                <v-tab @click="admin()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[7] }}</h3>
                                </v-tab>

                                <v-tab @click="admin1()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[8] }}</h3>
                                </v-tab>

                            </v-tabs>
                        </v-card>
                    </v-row>
                </v-col>

                <!-- 사이드 -->
                <v-col sm="2"></v-col>
            </v-row>
        </v-main>
    </v-app>
</template>

<script>
import { reactive } from '@vue/reactivity';
import { useRoute, useRouter } from 'vue-router';
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';
export default {
    components: {  },
    setup () {
        const router = useRouter();
        const route = useRoute();
        const state = reactive({
            clubname: '',
            tab: 'Appp',
            items: [
                '홈', '게시판', '갤러리', '일정', '클럽원', '채팅', '설정', '회원관리', '가입신청관리'
            ],
            text: 'Lorem ipsum',
            chk: 0,
            logo: '',
            cno : route.query.cno
        });
        const clubname = async() => {
            const url = `/ROOT/club/selectone?cno=${state.cno}`;
            const headers = {"Content-Type":"application/json"};
            const response = await axios.get(url, {headers});
            // console.log(response.data);
            if(response.data.status === 200)
            {
                state.clubname = response.data.result.cname;
                state.logo = response.data.result.cimageurl;
            }
        }

        const Home = () => {
            router.push({ name: "CHomeVue", query : {cno : state.cno}});
        };

        const boardList = () => {
            router.push({ name: "CBoardListVue", query : {cno : state.cno}});
        };

        const gallery = () => {
            router.push({ name: "CGalleryVue", query : {cno : state.cno}});
        };

        const schedule = () => {
            router.push({ name: "CScheduleVue", query : {cno : state.cno}});
        };        

        const member = () => {
            router.push({ name: "CMemberVue", query : {cno : state.cno}});
        };

        const chat = () => {
            router.push({ name: "CChatVue", query : {cno : state.cno}});
        };

        const setting = () => {
            router.push({ name: "CSettingVue", query : {cno : state.cno}});
        };

        const admin = () => {
            router.push({ name: "CAdminVue", query : {cno : state.cno}});
        };

        const admin1 = () => {
            router.push({ name: "CAdminVue1", query : {cno : state.cno}});
        };

        onMounted(() =>
        {
            clubname();
        });

        return { state, Home, boardList, gallery, schedule, member, chat, setting, admin, admin1 }
    }
}
</script>

<style lang="scss" scoped>
/* Helper classes */
.bg-basil {
  background-color: #FFFBE6 !important;
}
.text-basil {
  color: #356859 !important;
}
@font-face {
    font-family: 'HallymGothic-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2204@1.0/HallymGothic-Regular.woff2') format('woff2');
    font-weight: 400;
    font-style: normal;
}
</style>