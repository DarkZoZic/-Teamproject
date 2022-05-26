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

                                            <!-- 주소찾기버튼 -->
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
                                        <v-row >
                                    
                                            <v-col  
                                             sm="3"  >
                                                <v-select  :change="clickchange()"
                                                  variant="outlined" density="compact" :items= state.catemax1  label="카테고리 선택"
                                                  v-model="state.selectcate1"  style="height: 40px; padding-right: 10px; width: 200px;">
                                                </v-select>
                                            </v-col>
                                            <v-col  sm="3" :change="clickchange1()" v-if="state.selectcate1 !== ''">
                                                <v-select variant="outlined" density="compact" :items="state.catemax12" label="소분류 선택"
                                                 v-model="state.selectcate2" style="height: 40px; padding-right: 10px; width: 200px;"></v-select>
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
                                            <v-col sm="3">
                                                <img :src="state.imageUrl"  style="width: 160px; border: 1px solid #CCC;"/>
                                                <h5>170px X 60px</h5>
                                            </v-col>
                                            <v-col class="col_left">
                                                <v-file-input
                                                    style="width: 200px;"
                                                    accept="image/*"
                                                    label="로고 사진을 넣어주세요"
                                                    @click="handleImage($event)"
                                                    name="file" @change="handleImage($event)">
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
import { onMounted } from '@vue/runtime-core';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const router = useRouter();

        onMounted(() => {
            category1();
            console.log(state.cate.cate1);
        })

        const state = reactive({
            number : '',
            a1 : '',
            a2 : '',
            catemax : [],
            catemax1 : [],
            catemax11 : [],
            catemax12 : [],
            category : [],
            selectcate1 : '',
            selectcate2 : '',
            birth : '2020년',
            token : sessionStorage.getItem("TOKEN"),
            imageUrl : require('../../assets/img/default-logo.jpg'),
            imageFile : null,
            // private : '공개',
            datechk: [],
            timechk: [],
            gender : [],
            age    : [],

            name          : '',
            area          : '',
            detailAddress : '',
            address       : '',
            max           : '',
            desc: '',
            nameRules: [
                v => !!v || '필수 입력 사항입니다',
                v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다'
            ],
            valid: '',
            imageFile: '',
            content : '',

            cate: [],
            cate1: [],
            // cate: {
            //     title1: '대분류',
            //     title2: '상세분류',
            //     cate1: [
            //         '전체', '서울', '부산'
            //     ],
            //     cate2: [
            //         '어디구', '어디군'
            //     ],
            // }
        })
        const handleImage = (e) => {
            if(e.target.files[0]){
                state.imageUrl = URL.createObjectURL(e.target.files[0]);
                state.imageFile = e.target.files[0];
            }
            else{
                state.imageUrl = require('../../assets/img/default-logo.jpg');
                state.imageFile = require('../../assets/img/default-logo.jpg');
            }
        }

        const check = async() => {
            console.log(state.selectcate1); 
            console.log(state.selectcate2);
            const url = `/ROOT/cate/catetwo?cate1=${state.selectcate1}&cate2=${state.selectcate2}`;
            const headers = {"Content-Type":"application/json"};
            const response = await axios.get(url,{headers:headers});
            if(response.data.status === 200){
                console.log(response.data);
                state.number = response.data.result.cgcode;
                console.log(state.number);
                // router.push({path : 'login'});
            }
        }

        // const reset = async() => {
        //     state.datechk = [];
        //     state.timechk = [];
        // };
                
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
                body.append("category",  state.number);
                body.append("caddress", state.address)
            const response = await axios.post(url,body,{headers});
                console.log(response.data);
            if(response.data.status === 200){
                alert('클럽생성완료');
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
                console.log(state.imageFile); 
                body.append("file", state.imageFile);
                body.append("cno", state.cno);

            console.log(state);

            const response = await axios.post(url,body,{headers});
                console.log(response.data);
                if(response.data.status === 200){

            }
        }
        

        const category1 = async() => {
            const url = `/ROOT/cate/catelist1`;
            const headers = {"Content-Type":"application.json",
            token : state.token};
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);
            if(response.data.status === 200){
                state.category = response.data.result;
                console.log("1231432",state.category);
            }
                // for(var i=0; i < response.data.result.length;i++){
                //     console.log(state.category[i]);
                //     state.cate[i] = state.category[i];
                // //     // state.cate2[i] = state.category[i].cgcate2;
                // //     // state.cate1[i] = state.category[i].cgcate1;
                //     console.log("state.cate======",state.cate);
                //     const rrr = new Set(state.category.cgcate1);
                //     state.cate2 = [...rrr]
                //     console.log("========",state.cate2);
                // }
                console.log(state.category);
            // 검색  : [   { name: 'Max', age: 33 }, { name: 'Max', age: 23 }   ]
            // const max = '운동';
            // const a1 = state.category.filter((arr, index, cb) => {
            //     return arr.cgcate1 === max
            // });
            // console.log(a1);
            // for(var i=0; i < a1.length; i++){
            //     state.catemax[i] = a1[i].cgcate2;
            //     console.log(state.catemax);
            // }

            // // 중복제거 : [ { name: 'Max', age: 33 }, { name: 'John', age: 20 }, { name: 'Caley', age: 18 }  ]
            // const a2= state.category.filter( (arr, index, cb) => { 
            //     return index === cb.findIndex(t => t.cgcate1 === arr.cgcate1)
            // });
            // console.log(a2);
            // for(var i=0; i < a2.length; i++){
            //     state.catemax1[i] = a2[i].cgcate1;
            //     console.log(state.catemax1);
            // }
            
        click();

        } 

        const clickchange = () =>{
            if(state.selectcate1 === '운동'){
                state.catemax12 = state.catemax;
                console.log('운동');
            }
            if(state.selectcate1 === '공부'){
                state.catemax12 = state.catemax11;
                console.log('공부');
            }
        

        }
        const clickchange1 = () =>{
            console.log(state.selectcate2);
            check();

        }
        
        const click = () => {
            // 운동일때
            const max = '운동';
            const a1 = state.category.filter((arr, index, cb) => {
                return arr.cgcate1 === max
            });
            // 공부일때
            const max1 = '공부';
            const a11 = state.category.filter((arr, index, cb) => {
                return arr.cgcate1 === max1
            });
            console.log("213123131",a1);
            console.log(a11);
            for(var i=0; i < a1.length; i++){
                state.catemax[i] = a1[i].cgcate2;
                console.log(state.catemax);
            }
            for(var i=0; i < a11.length; i++){
                state.catemax11[i] = a11[i].cgcate2;
                console.log(state.catemax11);
            }

            

            const a2= state.category.filter( (arr, index, cb) => { 
                return index === cb.findIndex(t => t.cgcate1 === arr.cgcate1)
            });
            console.log(a2);
            for(var i=0; i < a2.length; i++){
                state.catemax1[i] = a2[i].cgcate1;
                console.log(state.catemax1);
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
        return { state, post, handleReg, handleImage,clickchange,clickchange1 }
    }
}
</script>

<style lang="scss" scoped>

</style>