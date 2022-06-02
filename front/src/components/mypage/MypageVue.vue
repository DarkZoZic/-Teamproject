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
                            <h5><router-link to="/">홈</router-link> > 마이페이지</h5>
                        </v-col>
                    </v-row>
                    
                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>마이페이지</h2>
                        </v-col>
                    </v-row>

                    <v-row dense style="padding-top: 20px;">
                        <v-col class="col_center" style="padding: 10px;">
                            <router-link to="/pupdate">
                                <v-card style="width: 170px; height: 170px; padding: 30px;">
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <img :src="require(`../../assets/img/update.png`)" style="width: 80px"/>
                                        </v-col>
                                    </v-row>
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <h3>회원정보수정</h3>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </router-link>
                            <router-link to="/pwupdate">
                                <v-card style="width: 170px; height: 170px; padding: 30px;">
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <img :src="require(`../../assets/img/pw change.png`)" style="width: 80px"/>
                                        </v-col>
                                    </v-row>
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <h3>비밀번호변경</h3>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </router-link>
                            <router-link to="/nupdate">
                                <v-card style="width: 170px; height: 170px; padding: 30px;">
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <img :src="require(`../../assets/img/nick_change.png`)" style="width: 80px"/>
                                        </v-col>
                                    </v-row>
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <h3>닉네임변경</h3>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </router-link>
                            <router-link to="/likelist">
                                <v-card style="width: 170px; height: 170px; padding: 30px;">
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <img :src="require(`../../assets/img/heart1.png`)" style="width: 80px"/>
                                        </v-col>
                                    </v-row>
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <h3>찜목록</h3>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </router-link>
                            
                            <router-link to="/activity">
                                <v-card style="width: 170px; height: 170px; padding: 30px;">
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <img :src="require(`../../assets/img/act.png`)" style="width: 80px"/>
                                        </v-col>
                                    </v-row>
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <h3>내활동</h3>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </router-link>
                            <router-link to="/dontgo">
                                <v-card style="width: 170px; height: 170px; padding: 30px;">
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <img :src="require(`../../assets/img/exit.png`)" style="width: 80px"/>
                                        </v-col>
                                    </v-row>
                                    <v-row dense>
                                        <v-col class="col_center">
                                            <h3>회원탈퇴</h3>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </router-link>
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

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const router = useRouter();

        const state = reactive({
            tabs: [
                'a', 'b'
            ]
        })

        return { state }
    }
}
</script>

<style lang="scss" scoped>

</style>