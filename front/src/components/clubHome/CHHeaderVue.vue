<template>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <!-- 사이드 -->
                <v-col md="2"></v-col>
                
                <!-- 메인 -->
                <v-col md="8">
                    <v-row dense>                        
                        <v-card color="basil" width="100%">
                            <v-row dense>
                            <v-col sm="2"></v-col>

                            <v-col sm="8" class="col_center">
                                <v-card-title class="text-center justify-center py-6">
                                    <h1 style="font-family: 'HallymGothic-Regular'; cursor: pointer;" @click="Home()" >
                                        {{state.clubname}}
                                    </h1>
                                </v-card-title>
                            </v-col>
                            
                            <v-col sm="2" class="col_center" style="padding-right: 20px;">
                                <img :src="require(`../../assets/img/${state.logo}.png`)" style="width: 100%; cursor: pointer;" @click="Home()"/>

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

                                <v-tab @click="member()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[3] }}</h3>
                                </v-tab>

                                <v-tab @click="chat()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[4] }}</h3>
                                </v-tab>

                                <v-tab @click="setting()">
                                    <h3 style="font-family: 'HallymGothic-Regular'">{{ state.items[5] }}</h3>
                                </v-tab>
                            </v-tabs>
                        </v-card>
                    </v-row>
                </v-col>

                <!-- 사이드 -->
                <v-col md="2"></v-col>
            </v-row>
        </v-main>
    </v-app>
</template>

<script>
import { reactive } from '@vue/reactivity';
import { useRouter } from 'vue-router';

export default {
    components: {  },
    setup () {
        const router = useRouter();
        const state = reactive({
            clubname: '삥뽕탁구클럽',
            tab: 'Appp',
            items: [
                '홈', '게시판', '갤러리', '클럽원', '채팅', '설정'
            ],
            text: 'Lorem ipsum',
            chk: 0,
            logo: 'club_logo',

        });

        const Home = () => {
            router.push({ name: "CHomeVue"});
        };

        const boardList = () => {
            router.push({ name: "CBoardListVue"});
        };

        const gallery = () => {
            router.push({ name: "CGalleryVue"});
        };

        const member = () => {
            router.push({ name: "CMemberVue"});
        };

        const chat = () => {
            router.push({ name: "CChatVue"});
        };

        const setting = () => {
            router.push({ name: "CSettingVue"});
        };

        return { state, Home, boardList, gallery, member, chat, setting }
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