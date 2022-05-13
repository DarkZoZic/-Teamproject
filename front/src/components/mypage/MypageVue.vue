<template>
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
                            <h5><router-link to="/">홈</router-link> > 마이페이지</h5>
                        </v-col>
                    </v-row>
                    
                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>마이페이지</h2>
                        </v-col>
                    </v-row>

                    <v-row dense>
                        <v-col>
                            <router-link to="/pupdate"><v-btn><h3>회원정보수정</h3></v-btn></router-link>
                            <router-link to="/pwupdate"><v-btn><h3>비밀번호변경</h3></v-btn></router-link>
                            <router-link to="/dontgo"><v-btn><h3>회원탈퇴</h3></v-btn></router-link>
                            <router-link to="/likelist"><v-btn><h3>찜목록</h3></v-btn></router-link>
                        </v-col>
                    </v-row>
                </v-col>

                <!-- 사이드 -->
                <v-col md="2"></v-col>
            </v-row>
        </v-main>
    </v-app>
    <FooterVue></FooterVue>
</template>

<script>
import { useRouter } from 'vue-router';
import FooterVue from '../../components/FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';

export default {
    components: { HeaderVue, FooterVue },
    // setup () {
    //     const router = useRouter();

    //     const state = reactive({

    //     })

    //     return { state }
    // }
}
</script>

<style lang="scss" scoped>

</style>