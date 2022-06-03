<template>
<div>
<HeaderVue style="height: 150px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">
      <v-row dense>
        <v-col sm="2"></v-col>

        <v-col sm="8" style="border-bottom: 1px solid #CCC;">
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
            <v-col sm="6">
              <h5><router-link to="/">홈</router-link> > <router-link to="/join">회원가입</router-link> > 개인회원가입</h5>
            </v-col>
          </v-row>

          <v-row dense style="padding-top: 19px; padding-bottom: 11px; padding-left: 10px;">
            <v-col>
              <h2>개인회원가입</h2>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      
      <v-row dense style="padding-top: 10px;">
        <v-col sm="3"></v-col>

        <v-col sm="6" style="display: flex; align-items: center;">
          <!-- 약관동의 -->
          <v-expansion-panels variant="accordion" style="width:100%" class="mb-6">
            <v-expansion-panel>
              <v-expansion-panel-title dense>
                <v-row>
                  <v-col sm="6" class="col_left">
                    <h4 style="color:red;">[필수]</h4>만 15세 이상입니다
                  </v-col>
                </v-row>
              </v-expansion-panel-title>
              <v-expansion-panel-text>
                <v-row dense style="border: 1px solid #CCC;">
                  <v-col>
                    <v-row dense >
                      <v-col class="col_right" style="background-color: gold;">
                        <h4 style="padding-right: 10px;">위 내용에 동의합니다</h4>
                        <input type="checkbox" v-model="state.chk1" style="height: 15px; width: 15px;">
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
                
              </v-expansion-panel-text>

            </v-expansion-panel>

            <v-expansion-panel>
              <v-expansion-panel-title style="height: 30px;">

                <v-row dense style="height: 100%;">
                  <v-col sm="6" class="col_left">
                    <h4 style="color: red;">[필수]</h4>서비스 이용약관 동의 
                  </v-col>
                </v-row>
              </v-expansion-panel-title>
              <v-expansion-panel-text style="overflow: scroll; height: 300px;">
                <v-row style="border: 1px solid #CCC;">
                  <v-col>
                    <v-row dense >
                      <v-col>
                        본 약관은 (주)미디어윌네트웍스(이하 “회사”)가 운영하는 알바천국, 간호잡, 
                        강사닷컴 등의 온라인 웹사이트를 통하여 직업정보 관련 제반 “서비스”를 제공함
                        에 있어 이용 고객(이하 “회원”)과 “회사”간의 권리, 의무 및 책임사항, 
                        기타 필요한 사항을 규정함을 목적으로 합니다.<br /><br />

                        제 2 조 (용어의 정의)<br />
                        본 약관에서 사용하는 용어의 정의는 아래와 같습니다.<br /><br />

                        ① “사이트”라 함은 “회사”가 “회원”에게 “서비스”를 제공하기 위하여 
                        컴퓨터 및 휴대폰 단말기 등의 정보통신설비를 이용하여 설정한 가상의 영업장 
                        또는 “회사”가 운영하는 웹사이트를 말하며, 통합된 하나의 “회원 아이디(ID)” 
                        및 “비밀번호”를 이용하여 “서비스”를 제공받을 수 있는 아래의 “사이트”
                        를 말합니다.<br />
                        - 알바천국 = http://www.alba.co.kr / 지역 알바천국<br /><br />

                        ② “서비스”라 함은 “회사”가 운영하는 “사이트”를 통해 “회원”이 등록하
                        는 자료 등을 각각의 목적에 맞게 분류 가공, 집계하여 정보를 제공하는 
                        서비스와 기타 관련된 모든 부대 서비스를 말합니다.<br /><br />
                        ③ “개인 회원”이라 함은 “회사”가 운영하는 “사이트”를 통하여 본 약관에 
                        동의하고, “회사” 와 이용 계약을 체결하여 “아이디(ID)”를 부여 받은 
                        개인을 말합니다.<br /><br />
                        ④ “기업 회원”이라 함은 인력 채용 및 교육을 목적으로 “회사”가 운영하는 
                        “사이트”를 통하여 본 약관에 동의하고, “회사”와 이용 계약을 체결하여 
                        “아이디(ID)”를 부여 받은 기업과 단체를 말합니다.<br /><br />
                        ⑤ “아이디(ID)”라 함은 “회원” 식별과 “회원”의 “서비스” 이용을 위하여 
                        “회원”이 직접 지정하고 이를 “회사”가 부여하는 문자 또는 숫자의 조합을 
                        말합니다.<br /><br />
                        ⑥ “비밀번호”라 함은 “서비스”를 이용하려는 사람이 “아이디(ID)”를 부여 
                        받은 자와 동일인임을 확인하고, “회원”의 권익을 보호하기 위하여 “회원”이 
                        직접 지정한 문자 또는 숫자의 조합을 말합니다.<br /><br />
                      </v-col>
                    </v-row>

                    <v-row dense>
                      <v-col class="col_right" style="background-color: gold;">
                        <h4 style="padding-right: 10px;">위 내용에 동의합니다</h4>
                        <input type="checkbox" v-model="state.chk2" style="height: 15px; width: 15px;">
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
                
              </v-expansion-panel-text>
            </v-expansion-panel>

            <v-expansion-panel>
              <v-expansion-panel-title style="height:30px;">
                <v-row dense style="height: 100%;">
                  <v-col sm="6" class="col_left">
                    <h4 style="color:red;">[필수]</h4>개인정보 이용 동의
                  </v-col>
                </v-row>
              </v-expansion-panel-title>
              <v-expansion-panel-text style="overflow: scroll; height: 300px;">
                <v-row style="border: 1px solid #CCC;">
                  <v-col>
                    <v-row dense >
                      <v-col>
                        1.이용자 식별, 본인인증, 회원서비스 제공을 위해 다음과 같은 정보를 수집하고 
                        있습니다.<br>
                        ①알바천국 일반 회원가입<br />
                        -(필수) 아이디, 비밀번호, 이름, 이메일, 휴대폰번호, 본인인증기관을 통해 인증된 
                        개인식별번호<br />
                        ②알바천국 Social계정으로 가입(네이버)<br />
                        -(필수) 이용자 고유 식별자, 이름, 이메일주소<br />
                        ③알바천국 Social계정으로 가입(카카오)<br />
                        -(필수) 닉네임, 프로필사진<br /><br />
                        2.이력서 관리 및 지원 서비스 제공을 위해 다음과 같은 정보를 수집하고 있습니다.<br />
                        (필수) 학력정보, 경력사항, 주소, 자기소개서<br />
                        알바천국 이력서 등록 시 수집하는 개인정보 항목(선택)<br /><br />
                        3.보유 기한 : 회원탈퇴 또는 동의 철회 시 까지<br />
                        ※ 위와 같이 개인정보의 수집 및 이용에 동의를 거부할 권리가 있으며, 
                        동의를 거부할 경우 서비스 사용에 제한이 있습니다.<br /><br />
                      </v-col>
                    </v-row>

                    <v-row dense>
                      <v-col class="col_right" style="background-color: gold;">
                        <h4 style="padding-right: 10px;">위 내용에 동의합니다</h4>
                        <input type="checkbox" v-model="state.chk3" style="height: 15px; width: 15px;">
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
              </v-expansion-panel-text>
            </v-expansion-panel>

            <v-expansion-panel>
              <v-expansion-panel-title expand-icon="mdi-menu-down" style="height:30px;">
                <h4>[선택]</h4>광고성 개인정보 수신 동의(SMS/MMS)
              </v-expansion-panel-title>
              <v-expansion-panel-text style="overflow: scroll; height: 300px;" >
                <v-row style="border: 1px solid #CCC;">
                  <v-col>
                    <v-row dense >
                      <v-col>
                        1.수집·이용의 목적<br />
                        -회원의 SMS/MMS을 이용하여 뉴스(맞춤 알바 포함), 이벤트, 소식, 설문, 광고 정보를 전송<br /><br />
                        2.수집·이용 항목<br />
                        -SMS/MMS<br /><br />
                        3.보유 기한<br />
                        -관계 법령의 규정에 따라 보존할 의무가 있는 경우가 아닌 한, 회원탈퇴 또는 동의 철회 시 까지<br /><br />
                        4.수신동의 거부 철회 방법<br />
                        -본 동의는 거부하실 수 있습니다. 다만 거부 시 동의를 통해 제공 가능한 각종 혜택, 이벤트 안내를 받아 보실 수 없습니다.
                        본 수신동의를 철회하고자 할 경우에는 회원정보 수정 페이지에서 수신여부를 변경하실 수 있습니다.<br /><br />
                      </v-col>
                    </v-row>

                    <v-row dense>
                      <v-col class="col_right" style="background-color: gold;">
                        <h4 style="padding-right: 10px;">위 내용에 동의합니다</h4>
                        <input type="checkbox" v-model="state.chk4" style="height: 15px; width: 15px;">
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
              </v-expansion-panel-text>
            </v-expansion-panel>

            <v-expansion-panel>
              <v-expansion-panel-title style="height:30px;">
                <h4>[선택]</h4>광고성 개인정보 수신 동의(EMAIL)
              </v-expansion-panel-title >
              <v-expansion-panel-text style="overflow: scroll; height: 300px;">
                <v-row style="border: 1px solid #CCC;">
                  <v-col>
                    <v-row dense >
                      <v-col>                
                        1.수집·이용의 목적<br />
                        -회원의 이메일을 이용하여 뉴스(맞춤 알바 포함), 이벤트, 소식, 설문, 광고 정보를 전송<br /><br />
                        2.수집·이용 항목<br />
                        -이메일<br /><br />
                        3.보유 기한<br />
                        -관계 법령의 규정에 따라 보존할 의무가 있는 경우가 아닌 한, 회원탈퇴 또는 동의 철회 시 까지<br /><br />
                        4.수신동의 거부 철회 방법<br />
                        -본 동의는 거부하실 수 있습니다. 다만 거부 시 동의를 통해 제공 가능한 각종 혜택, 이벤트 안내를 받아 보실 수 없습니다.<br /><br />
                        본 수신동의를 철회하고자 할 경우에는 회원정보 수정 페이지에서 수신여부를 변경하실 수 있습니다.<br /><br />
                      </v-col>
                    </v-row>

                    <v-row dense>
                      <v-col class="col_right" style="background-color: gold;">
                        <h4 style="padding-right: 10px;">위 내용에 동의합니다</h4>
                        <input type="checkbox" v-model="state.chk5" style="height: 15px; width: 15px;">
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
              </v-expansion-panel-text>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
        
        <v-col sm="3"></v-col>
      </v-row>

      <v-row dense="">
        <v-col sm="3"></v-col>

        <v-col sm="4" style="display: flex; align-items: center;">
          <v-expansion-panels style="width:100%">
            <v-form v-model="valid" style="width:100%">

              <!-- 아이디 -->
              <v-expansion-panel class="panel" >
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="아이디"
                      :rules="state.idRules"
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
                      v-model="state.pw"
                      variant="plain"
                      density="compact"
                      required
                      :append-icon="state.show1 ? 'mdi-eye' : 'mdi-eye-off'"
                      @click:append="state.show1 = !state.show1"
                      :type="state.show1 ? 'text' : 'password'"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 비밀번호확인 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="비밀번호확인"
                      v-model="state.pw1"
                      variant="plain"
                      density="compact"
                      required
                      :append-icon="state.show2 ? 'mdi-eye' : 'mdi-eye-off'"
                      @click:append="state.show2 = !state.show2"
                      :type="state.show2 ? 'text' : 'password'"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>
            </v-form>
          </v-expansion-panels>
        </v-col>

        <!-- 프로필 사진 -->
        <v-col sm="2">
          <v-expansion-panels>
              <v-expansion-panel>
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
              <!-- 이름 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="이름"
                      v-model="state.mname"
                      variant="plain"
                      :rules="state.nameRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 닉네임 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="닉네임"
                      v-model="state.nickname"
                      variant="plain"
                      :rules="state.nicknameRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col sm="2">
                    <v-btn style="width: 100%; height:40px;" @click="handlenick()">
                      <h4>닉네임 중복확인</h4>
                    </v-btn>
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
                      :rules="state.emailRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 생년월일, 성별 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col sm="9" style="height: 80px;">
                    <v-text-field
                      label="생년월일"
                      :rules="state.birthRules"
                      :counter="8"
                      v-model="state.birth"
                      density="compact"
                      hint="예) 19980613"
                      variant="plain"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col sm="3" >
                    <v-radio-group
                      v-model="state.gender"
                      inline style="height:40px; align-items: right;"
                    >
                      <v-radio
                        label="남"
                        value="남"
                      ></v-radio>
                      <v-radio
                        label="여"
                        value="여"
                      ></v-radio>
                    </v-radio-group>
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
                      :rules="state.phoneRules"
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
                      v-model="state.validnumber"
                      variant="plain"
                      :rules="state.validnumberRules"
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
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>

                  <!-- 우편번호찾기버튼 -->
                  <v-col sm="2">
                    <v-btn @click="post" style="width: 100%; height:40px;">
                      <h4>주소찾기</h4>
                    </v-btn>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 상세주소, 우편번호 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="상세주소"
                      v-model="state.extraAddress"
                      id="extraAddress"
                      variant="plain"
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
        <v-col sm="2"></v-col>

        <v-col sm="8" >
          <!-- 가입버튼 -->
          <v-row dense style="padding-top: 20px;">
            <v-col sm="4"></v-col>

            <v-col sm="4">
              <v-row dense>
                <v-btn @click="handleJoin" style="width:100%; height:80px; background-color: gold;">
                  <h2>개인회원가입</h2>
                </v-btn>
              </v-row>
            </v-col>

            <v-col sm="4"></v-col>
          </v-row>
        </v-col>

        <v-col sm="2"></v-col>
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
        chk1: true,
        chk2: true,
        chk3: true,
        chk4: false,
        chk5: false,
        id : '',
        pw : '',
        pw1 : '',
        mname : '',
        phone : '',
        email : '',
        nickname : '',
        validnumber: '',
        postcode: '',
        address: '',
        extraAddress: '',
        detailAddress: '',
        nickcheck: '',
        gender : [{ value: "남" },
         { value: "여" }],
        birth : '',
        role : 'PERSONAL',
        imageUrl : require('../../assets/img/profile_sample.png'),
        imageFile : null,
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
        body.append("mpgender",   state.gender);
        body.append("mpbirth",    state.birth);
        body.append("mprole",     state.role);
        body.append("member",     state.id);
      const response = await axios.post(url,body,{headers});
      console.log(response.data);
      if(response.data.status === 200){
          alert('회원가입완료')
          router.push({path : 'login'});
      }
    }
    
    const handlenick = async() => {
      const url = `/ROOT/member/nickcheck?nick=${state.nickname}`;
      const headers = {"Content-Type":"application/json"};
      const response = await axios.post(url,[],{headers});
      console.log(response.data);
      if(response.data.status === 200){
        alert('사용할수있는 닉네임')
        state.nickcheck = response.data.status
      }
      else{
        alert('중복된 닉네임')
        state.nickcheck = '';
      }
    }

    const handleJoin = async() => {

      if(state.chk1 === false) {
        alert('만 15세 이상만 가입할 수 있습니다')
        return false;
      }

      else if(state.chk2 === false) {
        alert('서비스 이용약관에 동의하세요')
        return false;
      }

      else if(state.chk3 === false) {
        alert('개인정보 이용에 동의하세요')
        return false;
      }

      else if(state.id === '' || state.id.length < 6) {
        alert('아이디를 6자 이상 입력하세요')
        return false;
      }

      else if(state.pw === '') {
        alert('비밀번호를 입력하세요')
        return false;
      }

      else if(state.pw1 === '') {
        alert('비밀번호 확인을 입력하세요')
        return false;
      }
      
      else if(state.mname === '') {
        alert('이름을 입력하세요')
        return false;
      }
      
      else if(state.nickname === '' || state.nickname.length < 1) {
        alert('닉네임을 2자 이상 입력하세요')
        return false;
      } 

      else if(state.email === '' || state.email < 1) {
        alert('이메일을 입력하세요')
        console.log(state.nickcheck);
        return false;
      } 

      else if(state.birth === '' || state.birth.length < 8 || state.birth.length > 8) {
        alert('생년월일 8자리를 입력하세요')
        return false;
      }

      else if(state.gender.length !== 1) {
        alert('성별을 입력하세요')
        return false;
      }
      

      else if(state.phone === '') {
        alert('연락처를 입력하세요')
        return false;
      }

      else if(state.validnumber === '') {
        alert('인증번호를 입력하세요')
        return false;
      }
      else if(state.nickcheck !== 200) {
        alert('닉네임을 확인하세요')
        return false;
      }

      // 유효성검사 통과후
      const url = `/ROOT/member/join.json`;
      const headers = {"Content-Type":"multipart/form-data"};
      const body = new FormData;
        body.append("mid",      state.id);
        body.append("mpw",      state.pw);
        body.append("mname",    state.mname);
        body.append("mphone",   state.phone);
        body.append("maddress", state.address);
        body.append("detailaddress" ,state.extraAddress);
        body.append("memail",   state.email);
        body.append("file",     state.imageFile);
      const response = await axios.post(url,body,{headers});
      console.log(response.data);
      if(response.data.status === 200){
        handleJoin2();
      }
    }

    const post = () => {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (state.extraAddress !== "") {
            state.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            state.address = data.roadAddress;

          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            state.address = data.jibunAddress;
          }
 
          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              state.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              state.extraAddress +=
                state.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (state.extraAddress !== "") {
              state.extraAddress = `(${state.extraAddress})`;
            }
          } else {
            state.extraAddress = "";
          }
          // 우편번호를 입력한다.
          state.postcode = data.zonecode;
        },
      }).open();
    }

    return { post, handleJoin, state, handleImage,handlenick }
  }
}
</script>

<style lang="scss" scoped>
  @import url(../../assets/css/style.css);

</style>