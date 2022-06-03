<template>
<div>
<HeaderVue style="height: 150px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <!-- 사이드 -->
                <v-col sm="2"></v-col>
                
                <!-- 메인 -->
                <v-col sm="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > <router-link to="/mypage">마이페이지</router-link> > 나의 활동</h5>
                        </v-col>
                    </v-row>
                    
                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>나의 활동</h2>
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

                    <v-row dense>
                        <v-col>
                            <v-tabs v-model="state.tab" fixed-tabs>
                                <v-tab value='1'><h3>내 글</h3></v-tab>
                                <v-tab value='2'><h3>내 댓글</h3></v-tab>
                                <v-tab value='3'><h3>내 활동</h3></v-tab>
                            </v-tabs>

                            <v-window v-model="state.tab">
                                <v-window-item value='1'>
                                    <v-card style="padding: 10px;">
                                        <v-row dense>
                                            <v-col class="col_right">
                                                <v-btn><h4>선택삭제</h4></v-btn>
                                            </v-col>
                                        </v-row>

                                        <v-row dense>
                                            <v-col>
                                                <v-table>
                                                    <thead>
                                                        <tr>
                                                            <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>체크</h3></td>
                                                            <td style="border-bottom: 2px solid #CCC;"><h3>제목</h3></td>
                                                            <td style="border-bottom: 2px solid #CCC; width:270px;"><h3>날짜</h3></td>
                                                            <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>조회</h3></td>
                                                            <td style="border-bottom: 2px solid #CCC; width:80px;"><h3>좋아요</h3></td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td><input type="checkbox"></td>
                                                            <td>{{state.my.title}}</td>
                                                            <td>{{state.my.date}}</td>
                                                            <td>{{state.my.hit}}</td>
                                                            <td>{{state.my.like}}</td>
                                                        </tr>
                                                    </tbody>
                                                </v-table>
                                            </v-col>
                                        </v-row>

                                        <v-row>
                                            <v-col>
                                                <v-pagination
                                                v-model="state.page"
                                                :length="state.total" 
                                                >
                                                </v-pagination>
                                            </v-col>
                                        </v-row>
                                    </v-card>
                                </v-window-item>
                                <v-window-item value='2'>
                                    <v-card style="padding: 10px;">
                                        <v-row dense>
                                            <v-col class="col_right">
                                                <v-btn><h4>선택삭제</h4></v-btn>
                                            </v-col>
                                        </v-row>

                                        <v-row dense>
                                            <v-col>
                                                <v-table>
                                                    <thead>
                                                        <tr>
                                                            <td style="border-bottom: 2px solid #CCC; width:70px;"><h3>체크</h3></td>
                                                            <td style="border-bottom: 2px solid #CCC;"><h3>내용</h3></td>
                                                            <td style="border-bottom: 2px solid #CCC; width:270px;"><h3>날짜</h3></td>
                                                            <td style="border-bottom: 2px solid #CCC; width:80px;"><h3>좋아요</h3></td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td><input type="checkbox"></td>
                                                            <td>{{state.myreply.title}}</td>
                                                            <td>{{state.myreply.date}}</td>
                                                            <td>{{state.myreply.like}}</td>
                                                        </tr>
                                                    </tbody>
                                                </v-table>
                                            </v-col>
                                        </v-row>
                                        
                                        <v-row>
                                            <v-col>
                                                <v-pagination
                                                v-model="state.page"
                                                :length="state.total" 
                                                >
                                                </v-pagination>
                                            </v-col>
                                        </v-row>                  
                                    </v-card>
                                </v-window-item>
                                <v-window-item value='3'>
                                    <v-card style="padding: 10px;">
                                        <v-row dense style="border-top: 1px solid #ccc; border-bottom: 1px solid #ccc;">
                                            <v-col>
                                                <v-row dense style="height: 400px;">
                                                    <v-col class="col_center">
                                                        <h4>준비중</h4>
                                                    </v-col>
                                                </v-row>
                                            </v-col>
                                        </v-row>

                                        <v-row>
                                            <v-col>
                                                <v-pagination
                                                v-model="state.page"
                                                :length="state.total" 
                                                >
                                                </v-pagination>
                                            </v-col>
                                        </v-row>
                                    </v-card>
                                </v-window-item>
                            </v-window>
                        </v-col>
                    </v-row>

                    <v-row dense>

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

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const router = useRouter();

        const state = reactive({
            tab: '',
            page: 1,

            my: {
                chk: false,
                title: '아니 어젴ㅋㅋ',
                date: '2021-04-24 14:22:03',
                hit: 53,
                like: 4,
                content: '어제 포켓몬빵을 사러 갔는데 진짜 레전드였음ㅋㅋㅋzzzzzzzzzzzzzzzzzzzzzzzzzzㅋ',
                content1: ''
            },

            myreply: {
                chk: false,
                title: '아 ㅋㅋㅋㅋㅋ너무웃기네 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
                date: '2021-04-24 14:22:03',
                like: 4,
                content: '어제 포켓몬빵을 사러 갔는데 진짜 레전드였음ㅋㅋㅋzzzzzzzzzzzzzzzzzzzzzzzzzzㅋ',
                content1: ''
            }
        })

        onMounted (()=>{
            if (state.my.content.length >= 15) {
                state.my.content1 = state.my.content.substring(0, 15) + '...'
            }
            state.my.content1 = state.my.content;

            if (state.myreply.content.length >= 15) {
                state.myreply.content1 = state.myreply.content.substring(0, 15) + '...'
            }
            state.myreply.content1 = state.myreply.content;
        });

        return { state }
    }
}
</script>

<style lang="scss" scoped>

</style>