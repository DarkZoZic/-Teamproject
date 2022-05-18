<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <!-- 사이드 -->
                <v-col md="2"></v-col>
                
                <!-- 메인 -->
                <v-col md="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > <router-link to="/mypage">마이페이지</router-link> > 찜목록 </h5>
                        </v-col>
                    </v-row>

                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>찜목록</h2>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col
                            v-for="n in 12"
                            :key="n"
                            cols="4"
                        >
                            <v-card height="200px" class="club_card" style="padding: 20px;">
                                <v-row dense>
                                    <v-col sm="3">
                                        <input type="checkbox" style="width: 15px; height: 15px;">
                                    </v-col>
                                    <v-col sm="6" class="col_center">
                                        <router-link to="/cdetail" class="col_center">
                                            <img :src="require(`../../assets/img/${state.logo}.png`)" style="width: 100%"/>
                                        </router-link>
                                    </v-col>
                                    <v-col sm="3" class="col_right">
                                        <v-btn style="height: 100%; width: 10px;" id="like" @click="changeheart()">
                                            <img :src="require(`../../assets/img/${state.imgName}.png`)" style="width: 30px"/>
                                        </v-btn>
                                    </v-col>
                                </v-row>
                                
                                <v-row dense>
                                    <v-col>
                                        <h4>{{state.card.clubname}}</h4>
                                    </v-col>
                                </v-row>

                                <v-row dense>
                                    <v-col sm="12">
                                        {{state.card.desc}}
                                    </v-col>
                                </v-row>

                                <v-row dense>
                                    <v-col>
                                        <h5>{{state.card.area1}}&nbsp;{{state.card.area2}}</h5>
                                    </v-col>
                                </v-row>
                            </v-card>                        
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col class="col_center" style="margin-top: 15px;">
                            <v-btn style="margin-right: 15px;"><h3>선택삭제</h3></v-btn>
                            <v-btn style="margin-left: 15px;"><h3>전체삭제</h3></v-btn>
                        </v-col>
                    </v-row>
                </v-col>

                <!-- 사이드 -->
                <v-col md="2"></v-col>
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

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const router = useRouter();

        const state = reactive({
            imgName: 'heart',
            logo: 'club_logo',

            card: {
                clubname: '삥뽕탁구클럽',
                desc: '설명글을 잔뜩!',
                desc1: '',
                area1: '부산',
                area2: '연제구',
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
        })

        const clubDetail = () => {
            router.push({ name: "ClubDetailVue" });
        }

        return { state, changeheart, clubDetail }
    }
}
</script>

<style lang="scss" scoped>

</style>