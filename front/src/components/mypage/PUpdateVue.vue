<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">
            <v-row dense>
                <v-col md="2"></v-col>

                <v-col md="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > <router-link to="/mypage">마이페이지</router-link> > 회원정보수정</h5>
                        </v-col>
                    </v-row>

                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>회원정보수정</h2>
                        </v-col>

                        <v-col class="col_right">
                            <router-link to="/pupdate"><v-btn><h3>회원정보수정</h3></v-btn></router-link>
                            <router-link to="/pwupdate"><v-btn><h3>비밀번호변경</h3></v-btn></router-link>
                            <router-link to="/dontgo"><v-btn><h3>회원탈퇴</h3></v-btn></router-link>
                        </v-col>
                    </v-row>
                </v-col>
            </v-row>
            
            <v-row dense style="padding-top: 20px;">
                <v-col sm="3"></v-col>

                <!-- 프로필 사진 -->
                <v-col sm="2">
                    <v-expansion-panels>
                        <v-expansion-panel style="width: 300px;">
                            <v-row dense>
                                <v-col style="padding-left: 20px; padding-top: 10px;">
                                    <h3>프로필 사진</h3>
                                </v-col>
                            </v-row>

                            <v-row dense>
                                <v-col style="width: 100%; height: 165px;" class="col_center">
                                    <img :src="state.imageUrl"  style="width: 160px; border: 1px solid #CCC;"/>
                                </v-col>
                            </v-row>

                            <v-row dense style="padding: 10px;">
                                <v-col class="col_center">
                                    <input type="file" name="file" @change="handleImage($event)" style="width: 80px;">
                                </v-col>
                            </v-row>
                        </v-expansion-panel>
                    </v-expansion-panels>
                </v-col>
            </v-row>

            <v-row dense>
                <v-col sm="3"></v-col>

                <v-col sm="6" style="display: flex; align-items: center;">
                    <v-expansion-panels style="width:100%">
                        <v-form v-model="valid" style="width:100%">

                            <!-- 닉네임 -->
                            <v-expansion-panel class="panel">
                                <v-row>
                                    <v-col style="height: 80px;">
                                        <v-text-field
                                        label="닉네임"
                                        v-model="state.nickname"
                                        variant="plain"
                                        :rules="nicknameRules"
                                        density="compact"
                                        required
                                        ></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel>

                            <!-- 이메일 -->
                            <v-expansion-panel class="panel">
                                <v-row>
                                    <v-col style="height:80px;">
                                        <v-text-field
                                        label="이메일"
                                        v-model="state.email"
                                        variant="plain"
                                        :rules="emailRules"
                                        density="compact"
                                        required
                                        ></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel>

                            <!-- 연락처 -->
                            <v-expansion-panel class="panel">
                                <v-row>
                                    <v-col sm="10" style="height: 80px;">
                                        <v-text-field
                                        label="연락처"
                                        v-model="state.phone"
                                        variant="plain"
                                        :rules="phoneRules"
                                        hint="숫자만 입력하세요"
                                        density="compact"
                                        required
                                        ></v-text-field>
                                    </v-col>

                                    <v-col sm="2">
                                        <v-btn style="width: 100%; height:40px;">
                                        <h4>인증번호전송</h4>
                                        </v-btn>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel>

                            <!-- 인증번호 -->
                            <v-expansion-panel class="panel">
                                <v-row>
                                    <v-col sm="10" style="height: 80px;">
                                        <v-text-field
                                        label="인증번호"
                                        v-model="validnumber"
                                        variant="plain"
                                        :rules="validnumberRules"
                                        density="compact"
                                        hint="숫자만 입력하세요"
                                        required
                                        ></v-text-field>
                                    </v-col>
                                    <v-col sm="2">
                                        <v-btn style="width: 100%; height:40px;">
                                        <h4>확인</h4>
                                        </v-btn>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel>

                            <!-- 주소 -->
                            <v-expansion-panel class="panel">
                                <v-row>
                                    <!-- 주소 -->
                                    <v-col sm="10" style="height: 80px;">
                                        <v-text-field
                                        id="address"
                                        label="주소"
                                        v-model="state.address"
                                        variant="plain"
                                        :rules="nameRules"
                                        density="compact"
                                        required
                                        ></v-text-field>
                                    </v-col>

                                    <!-- 우편번호찾기버튼 -->
                                    <v-col sm="2">
                                        <v-btn @click="post" style="width: 100%; height:40px;">
                                        <h4>우편번호찾기</h4>
                                        </v-btn>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel>

                            <!-- 상세주소, 우편번호 -->
                            <v-expansion-panel class="panel">
                                <v-row>
                                    <v-col sm="8" style="height: 80px;">
                                        <v-text-field
                                        label="상세주소"
                                        v-model="state.detailAddress"
                                        id="detailAddress"
                                        variant="plain"
                                        :rules="nameRules"
                                        density="compact"
                                        required
                                        ></v-text-field>
                                    </v-col>

                                    <v-col sm="4" style="height: 80px;">
                                        <v-text-field
                                        label="우편번호"
                                        v-model="state.postcode"
                                        id="postcode"
                                        variant="plain"
                                        :rules="nameRules"
                                        density="compact"
                                        required
                                        ></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel>
                        </v-form>
                    </v-expansion-panels>
                </v-col>
                
                <v-col sm="3"></v-col>
            </v-row>

            <v-row dense="">
                <v-col md="2"></v-col>

                <v-col md="8" >
                    <!-- 가입버튼 -->
                    <v-row dense style="padding-top: 20px;">
                        <v-col sm="4"></v-col>

                        <v-col sm="4">
                            <v-row dense>
                                <v-btn @click="handleUpdate()" style="width:100%; height:80px; background-color: gold;">
                                    <h2>회원정보수정</h2>
                                </v-btn>
                            </v-row>
                        </v-col>

                        <v-col sm="4"></v-col>
                    </v-row>
                </v-col>

                <v-col md="2"></v-col>
            </v-row>
        </v-main>
    </v-app>
    <FooterVue></FooterVue>
</div>
</template>

<script>
import { useRouter } from 'vue-router';
import FooterVue from '../FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import axios from 'axios';
import { reactive } from 'vue';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const state = reactive({
            id : '',
            pw : '',
            pw1 : '',
            mname : '',
            phone : '',
            address : '',
            detailAddress : '',
            postcode : '',
            email : '',
            nickname : '',
            gender : [{ value: "남", text: "남성", },
            { value: "여", text: "여성", }],
            birth : '',
            role : 'PERSONAL',
            imageUrl : require('../../assets/img/profile_sample.png'),
            imageFile : null,
        })
        
        // onMounted(() => {
        //       handleJoin2();
        //   })
        const router = useRouter();

        const handleImage = (e) => {
                if(e.target.files[0]){
                    state.imageUrl = URL.createObjectURL(e.target.files[0]);
                    state.imageFile = e.target.files[0];
                }
                else{
                    state.imageUrl = require('../../assets/img/profile_sample.png');
                    state.imageFile = null;
                }
            }

        const handleJoin2 = async() => {
        const url = `/ROOT/member/psjoin.json`;
        const headers = {"Content-Type":"multipart/form-data"};

        const body = new FormData;
            body.append("mpnickname", state.nickname);
            body.append("mpgender",state.gender);
            body.append("mpbirth",state.birth);
            body.append("mprole",state.role);
            body.append("member",state.id);
        const response = await axios.post(url,body,{headers});
        console.log(response.data);
        if(response.data.status === 200){
            alert('회원가입완료')
            router.push({ path : 'login' });
        }
        }
        const handleJoin = async() => {
        // 유효성검사 통과후
            
        const url = `/ROOT/member/join.json`;
        const headers = {"Content-Type":"multipart/form-data"};

        const body = new FormData;
            body.append("mname",state.mname);
            body.append("mphone",state.phone);
            body.append("maddress",state.address + state.detailAddress + state.postcode);
            body.append("memail",state.email);
            body.append("file",state.imageFile);
        const response = await axios.post(url,body,{headers});
        console.log(response.data);
        if(response.data.status === 200){
            handleJoin2();
        }
        }
        return { handleJoin, state, handleImage }
    },
    data () {
        return {
        column: null,
        row: null,
        e6: 1,
        valid: false,
        id: '',
        pw: '',
        pw1: '',
        name: '',
        nickname: '',
        email: '',
        birth: '',
        gender: '',
        phone: '',
        postcode: '',
        address: '',
        extraAddress: '',
        detailAddress: '',
        validnumber: '',

        show1: false,
        show2: false,

        idRules: [
            v => !!v || '필수 입력 사항입니다',
            v => v.length >= 6 || '6자 이상 입력하세요',
        ],

        nameRules: [
            v => !!v || '필수 입력 사항입니다',
        ],

        nicknameRules: [
            v => !!v || '필수 입력 사항입니다',
        ],

        emailRules: [
            v => !!v || '필수 입력 사항입니다',
            v => /.+@.+/.test(v) || '이메일 형식이 아닙니다',
        ],

        birthRules: [
            v => !!v || '필수 입력 사항입니다',
            v => v.length >= 8 || '8자만 입력하세요',
            v => v.length <= 8 || '8자만 입력하세요',
        ],

        phoneRules: [
            v => !!v || '필수 입력 사항입니다',
            v => v.length >= 8 || '',
            v => v.length <= 11 || '',
        ],

        validnumberRules: [
            v => !!v || '필수 입력 사항입니다',
            v => v.length >= 6 || '',
            v => v.length <= 6 || '',
        ],
        }
    },
    methods: {
        // 주소 API
        post() {
        new window.daum.Postcode({
            oncomplete: (data) => {
            if (this.extraAddress !== "") {
                this.extraAddress = "";
            }
            if (data.userSelectedType === "R") {
                // 사용자가 도로명 주소를 선택했을 경우
                this.address = data.roadAddress;
            } else {
                // 사용자가 지번 주소를 선택했을 경우(J)
                this.address = data.jibunAddress;
            }
    
            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === "R") {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                this.extraAddress += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== "" && data.apartment === "Y") {
                this.extraAddress +=
                    this.extraAddress !== ""
                    ? `, ${data.buildingName}`
                    : data.buildingName;
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (this.extraAddress !== "") {
                this.extraAddress = `(${this.extraAddress})`;
                }
            } else {
                this.extraAddress = "";
            }
            // 우편번호를 입력한다.
            this.postcode = data.zonecode;
            },
        }).open();
        },
    },
}
</script>

<style lang="scss" scoped>
  @import '../../assets/css/style';

</style>