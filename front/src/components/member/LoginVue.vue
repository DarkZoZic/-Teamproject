<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">
      <v-row dense>
        <v-col sm="2"></v-col>

        <v-col sm="8">
            <v-row dense="" style="border-bottom: 1px solid #CCC;">
                <v-col sm="6">
                    <h5><router-link to="/">홈</router-link> > 로그인</h5>
                </v-col>
            </v-row>

            <v-row dense style="border-bottom: 1px solid #CCC; padding-top: 19px; padding-bottom: 11px; padding-left: 10px;">
                <v-col>
                    <h2>로그인</h2>
                </v-col>
            </v-row>
            <form>
                <v-row dense style="padding-top: 30px;">        
                    <v-col sm="2"></v-col>

                    <v-col style="justify-content: right;display: flex; align-items: center;">
                    <!-- 아이디 -->
                        <v-expansion-panels style="width:100%">
                            <v-form v-model="state.valid" style="width:100%">
                                <v-expansion-panel class="panel">
                                    <v-row>
                                        <v-col style="height: 80px;">
                                            <v-text-field
                                            label="아이디"
                                            :rules="idRules"
                                            :counter="6"
                                            v-model="state.id"
                                            density="compact"
                                            hint="6자 이상 입력하세요"
                                            variant="plain"
                                            required
                                            ></v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-expansion-panel>

                                <!-- 비밀번호 -->
                                <v-expansion-panel class="panel">
                                    <v-row>
                                        <v-col style="height: 80px;">
                                            <v-text-field
                                            label="비밀번호"
                                            :rules="state.pwRules"
                                            :counter="8"
                                            v-model="state.pw"
                                            variant="plain"
                                            density="compact"
                                            required
                                            hint="8자 이상 입력하세요"
                                            :append-icon="state.show1 ? 'mdi-eye' : 'mdi-eye-off'"
                                            @click:append="state.show1 = !state.show1"
                                            :type="state.show1 ? 'text' : 'password'"
                                            ></v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-expansion-panel>
                            </v-form>
                        </v-expansion-panels>
                    </v-col>

                    <v-col sm="2"></v-col>
                </v-row>

                <v-row dense>
                    <v-col sm="2"></v-col>

                    <v-col sm="8">
                        <v-row dense style="padding: 10px;">
                            <v-col sm="4" class="col_center">
                                <h4>아이디 저장</h4>
                                <input type="checkbox" style="margin-left:10px; width: 20px; height: 20px;">
                            </v-col>

                            <v-col sm="4" class="col_center">
                                <h4>비밀번호 저장</h4>
                                <input type="checkbox" style="margin-left:10px; width: 20px; height: 20px;">
                            </v-col>

                            <v-col sm="4" class="col_center">
                                <h4>자동로그인</h4>
                                <input type="checkbox" style="margin-left:10px; width: 20px; height: 20px;">
                            </v-col>
                        </v-row>
                        <v-row dense>
                            <v-btn @click="submit" style="width:100%; height:80px; background-color: gold;">
                                <h2>로그인</h2>
                            </v-btn>
                        </v-row>
                    </v-col>

                    <v-col sm="2"></v-col>
                </v-row>

                <v-row dense="">
                    <v-col sm="2"></v-col>

                    <v-col sm="8" style="display: flex; justify-content: center; padding: 20px; height: 50px;">
                        <v-col sm="3" class="col_center">
                            <router-link to="/join"><h4>회원가입</h4></router-link>
                        </v-col>

                        <v-col sm="3" class="col_center" style="border-right: 2px solid #CCC; border-left: 2px solid #CCC;">
                            <router-link to="/fid"><h4 style="margin-right: 30px; margin-left: 30px;">아이디 찾기</h4></router-link>
                        </v-col>

                        <v-col sm="3" class="col_center">
                            <router-link to="/fpw"><h4>비밀번호 찾기</h4></router-link>
                        </v-col>
                    </v-col>
                    <v-col sm="2"></v-col>
                </v-row>
            </form>        
        </v-col>

        <v-col sm="2"></v-col>

        <v-col sm="2"></v-col>
      </v-row>
    </v-main>
  </v-app>
  <FooterVue></FooterVue>
</div>
</template>

<script>
import FooterVue from '../../components/FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import axios  from 'axios';
import { reactive } from '@vue/reactivity';
import { useRouter } from 'vue-router';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const router = useRouter();

        const state = reactive({
            id   : '',
            pw   : '',
            valid: false,
            show1: false,

            pwRules: [
                v => !!v || '필수 입력 사항입니다',
                v => v.length >= 8 || '8자 이상 입력하세요',
            ],

            idRules: [
            v => !!v || '필수 입력 사항입니다',
            v => v.length >= 6 || '6자 이상 입력하세요',
            ],            
        })

        const submit = async() => {
            const url = `/ROOT/member/login`;
            const headers = {"Content-Type":"multipart/form-data"};
            const body = new FormData;
            body.append("mid", state.id);
            body.append("mpw",state.pw);
            const response = await axios.post(url, body,{headers});
            console.log(response.data);
            if(response.data.status === 200) {
                sessionStorage.setItem("TOKEN", response.data.token);
                sessionStorage.setItem("MID", state.id);
                router.push({path : '/'})
                // store.commit('moduleA/setMenu', "/");
            }
        }
        return {state,submit}
    }
}
</script>

<style lang="scss" scoped>

</style>