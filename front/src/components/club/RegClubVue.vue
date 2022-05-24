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
                            <h5><router-link to="/">홈</router-link> > <router-link to="/clist">클럽</router-link> > 클럽등록</h5>
                        </v-col>
                    </v-row>
                    
                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>클럽생성</h2>
                        </v-col>
                    </v-row>

                    <v-row dense style="padding-top: 20px;">
                        <v-col sm="1" ></v-col>

                        <v-col style="display: flex; align-items: center;">
                            <v-expansion-panels style="width:100%">
                                <v-form v-model="state.valid" style="width:100%">
                                    <!-- 클럽명 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col style="height: 80px;">
                                                <v-text-field
                                                label="클럽명"
                                                v-model="state.name"
                                                variant="plain"
                                                hint="10자 이하로 입력하세요"                      
                                                :rules="state.numberRules"
                                                :counter="10"                      
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>                                  

                                    <!-- 주소 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col sm="10" style="height: 80px;">
                                                <v-text-field
                                                label="장소명"
                                                v-model="state.area"
                                                variant="plain"
                                                hint="장소 이름을 입력하세요"
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>

                                            <v-col sm="2">
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

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

                                    <!-- 상세주소 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col sm="8" style="height: 80px;">
                                                <v-text-field
                                                label="상세주소"
                                                v-model="state.detailAddress"
                                                id="detailAddress"
                                                variant="plain"
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>
                                            <v-col class="col_right">
                                                <h4 style="padding-right: 10px;">온라인</h4><input type="checkbox" @change="online()" style="width: 15px; height: 15px;">
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <v-expansion-panel class="panel" style="height: 70px;">
                                        <v-row>
                                            <v-col sm="2" style="height: 50px;">
                                                <v-text-field
                                                label="동호회 최대인원"
                                                v-model="state.max"
                                                id="max"
                                                variant="plain"
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>
                                            <v-col sm="4" style="height: 65px;" class="col_left">
                                                명
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <v-expansion-panel class="panel" style="height: 70px;">
                                        <v-row>
                                            <v-col sm="2">
                                                <v-select variant="outlined" density="compact" :items="state.items1" v-model="state.cate" style="height: 40px; padding-right: 10px;"></v-select>

                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <!-- 설명글 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col style="height: 200px;">
                                                <v-textarea
                                                solo
                                                v-model="state.desc"
                                                variant="plain"
                                                label="설명글"
                                                density="compact"
                                                hint="클럽 설명을 입력하세요"
                                                ></v-textarea>                                            
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col>
                                                <v-radio-group
                                                label="클럽 공개 여부"
                                                v-model="state.private"
                                                inline style="height:70px; align-items: right;">
                                                <v-radio
                                                    label="공개"
                                                    value="공개"
                                                ></v-radio>
                                                <v-radio
                                                    label="비공개"
                                                    value="비공개"
                                                ></v-radio>
                                                </v-radio-group>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col>
                    <img :src="state.imageUrl"  style="width: 160px; border: 1px solid #CCC;"/>
                                                <v-file-input
                                                accept="image/*"
                                                label="로고 사진을 넣어주세요"
                                                multiple
                                                @click="handleImage($event)"
                                                name="file" @change="handleImage($event)" >
                                                ></v-file-input>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>
                                </v-form>
                            </v-expansion-panels>
                        </v-col>
                        
                        <v-col sm="1"></v-col>
                    </v-row>

                    <v-row dense style="padding-top: 20px;">
                        <v-col sm="4"></v-col>

                        <v-col sm="4">
                            <v-row dense>
                                <v-btn @click="handleReg()" style="width:100%; height:80px; background-color: gold;">
                                    <h2>클럽등록</h2>
                                </v-btn>
                            </v-row>
                        </v-col>

                        <v-col sm="4"></v-col>
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
import { reactive }  from '@vue/reactivity';
import FooterVue from '../../components/FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const router = useRouter();

        const state = reactive({
            items : [{}],
            birth : '2020년',
            token : sessionStorage.getItem("TOKEN"),
            imageUrl : require('../../assets/img/profile_sample.png'),
            imageFile : null,
            // private : '공개',
            datechk: [],
            timechk: [],
            gender : [],
            age    : [],

            name          : '',
            area          : '',
            postcode      : '',
            detailAddress : '',
            address       : '',
            max           : '',
            desc: '',
            nameRules: [
                v => !!v || '필수 입력 사항입니다',
                v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다'
            ],
            valid: '',
        })
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

        const online = () => {

        }

        const reset = async() => {
            state.datechk = [];
            state.timechk = [];
        };
                
        const handleReg = async() => {
            const url = `/ROOT/club/insert.json`;
            const headers = {"Content-Type":"multipart/form-data",
            token : state.token};
            const body = new FormData;
                body.append("cname",  state.name);
                body.append("cdesc",  state.desc);
                body.append("cmax", state.max);
                body.append("cprivate",  state.private);
                body.append("carea",  state.area);
                body.append("cbirth",  state.birth);
                body.append("caddress", state.address)
            const response = await axios.post(url,body,{headers});
                console.log(response.data);
            if(response.data.status === 200){
                alert('클럽생성완료');
                // console.log(response.data.result);
                state.items = response.data.result;
                state.cno = response.data.result.cno;
                state.mid = response.data.mid;

                console.log(state.cno);
                handleJoinclub();
                Clubimage();

                // router.push({path : 'login'});
            }
        }
        const handleJoinclub = async() => {
            console.log(state.cno);
            console.log(state.mid);
            const url = `/ROOT/joinclub/insert.json`;
            const headers = {"Content-Type":"multipart/form-data"};
            const body = new FormData;
                body.append("member",  state.mid);
                body.append("steptbl",  101);
                body.append("club", state.cno);
            const response = await axios.post(url,body,{headers});
                console.log(response.data);
                if(response.data.status === 200){
            router.push({path : 'clist'})

        }

        }
        const Clubimage = async() => {
            console.log(state.cno);
            console.log(state.mid);
            const url = `/ROOT/club/cbimage`;
            const headers = {"Content-Type":"multipart/form-data"};
            const body = new FormData;
                body.append("file",  state.imageFile);
                body.append("cno", state.cno);
            const response = await axios.post(url,body,{headers});
                console.log(response.data);
                if(response.data.status === 200){

        }

        }
        return { state, handleReg, online, reset,handleImage }
    }
}
</script>

<style lang="scss" scoped>

</style>