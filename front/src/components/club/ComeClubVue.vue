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
                    <v-row dense style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > <router-link to="/clist">클럽</router-link> > 공고등록</h5>
                        </v-col>
                    </v-row>
                    
                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>공고등록</h2>
                        </v-col>
                    </v-row>

                    <v-row dense style="padding-top: 10px;">
                        <v-col sm="1" ></v-col>
                        <div v-if="state.items">
                            <v-col sm="10" >
                                <v-select :change="handleCno()" :items=state.cnamelist variant="outlined" density="compact" label="클럽선택" 
                                    v-model="state.club" style="height: 40px; padding-right: 10px; width: 200px;">
                                </v-select>
                            </v-col>
                        </div>
                        <v-col sm="1"></v-col>
                    </v-row>

                    <v-row dense style="padding-top: 10px;">
                        <v-col sm="1" ></v-col>

                        <v-col style="display: flex; align-items: center;">
                            <v-expansion-panels style="width:100%">
                                <v-form v-model="state.valid" style="width:100%">
                                    <!-- 클럽명 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col style="height: 80px;" class="col_left">
                                                <h2>{{state.club}}</h2>
                                            </v-col>
                                            
                                        </v-row>
                                    </v-expansion-panel>
                                    <!-- 클럽명 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col style="height: 80px;">
                                                <v-text-field
                                                label="공고 제목"
                                                v-model="state.cname"
                                                variant="plain"
                                                :rules="state.numberRules"
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>   
                                    

                                    <v-expansion-panel>
                                        <v-row dense>
                                            <v-col style="padding: 20px;">
                                                <v-row dense>
                                                    <v-col sm="6" style="border-right: 1px solid #ccc;" class="col_center"><h4>성별</h4></v-col>
                                                    <v-col sm="6" class="col_center"><h4>연령</h4></v-col>
                                                </v-row>

                                                <v-row dense style="border-top: 1px solid #CCC; ">
                                                    <v-col sm="6" style="border-right: 1px solid #CCC;">
                                                        <v-row dense style="padding-top: 10px;">
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="radio" v-model="state.gender" class="club_list_chk" value="성별무관"><h4>성별무관</h4>
                                                                <input type="radio" v-model="state.gender" class="club_list_chk" value="남"><h4>남</h4>
                                                                <input type="radio" v-model="state.gender" class="club_list_chk" value="여"><h4>여</h4>
                                                            </v-col>
                                                        </v-row>
                                                    </v-col>

                                                    <v-col sm="6">
                                                        <v-row dense style="padding-top: 10px;">
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="10대"><h4>10대</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="20대"><h4>20대</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="30대"><h4>30대</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="40대"><h4>40대</h4>
                                                            </v-col>
                                                        </v-row>

                                                        <v-row dense>
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="50대"><h4>50대</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="60대 이상"><h4>60대 이상</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="성인만"><h4>성인만</h4>
                                                            </v-col>
                                                        </v-row>
                                                    </v-col>
                                                </v-row>
                                                <v-row dense>
                                                    <v-col class="col_center">
                                                        <v-btn class="club_list_btn" @click="reset()"><h4>초기화</h4></v-btn>
                                                    </v-col>
                                                </v-row>                                                
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <!--  -->
                                    <v-expansion-panel>
                                        <v-row dense>
                                            <v-col style="padding: 20px;">
                                                <v-row dense>
                                                    <v-col sm="6" style="border-right: 1px solid #ccc;" class="col_center"><h4>요일</h4></v-col>
                                                    <v-col sm="6" class="col_center"><h4>시간</h4></v-col>
                                                </v-row>

                                                <v-row dense style="border-top: 1px solid #CCC; ">
                                                    <v-col sm="6" style="border-right: 1px solid #CCC;">
                                                        <v-row dense style="padding-top: 10px;">
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="평일전체"><h4>평일전체</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="주말"><h4>주말</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="토요일"><h4>토</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="일요일"><h4>일</h4>
                                                            </v-col>
                                                        </v-row>

                                                        <v-row dense>
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="월요일"><h4>월</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="화요일"><h4>화</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="수요일"><h4>수</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="목요일"><h4>목</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="금요일"><h4>금</h4>
                                                            </v-col>
                                                        </v-row>
                                                    </v-col>

                                                    <v-col sm="6">
                                                        <v-row dense style="padding-top: 10px;">
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="종일"><h4>종일</h4>
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="오전"><h4>오전</h4>
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="오후"><h4>오후</h4>
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="저녁"><h4>저녁</h4>
                                                            </v-col>
                                                        </v-row>
                                                        
                                                        <v-row dense>
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="새벽"><h4>새벽</h4>
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="상관없음"><h4>상관없음</h4>
                                                            </v-col>
                                                        </v-row>
                                                    </v-col>
                                                    <v-row dense>
                                                        <v-col class="col_center">
                                                            <v-btn class="club_list_btn" @click="reset()"><h4>초기화</h4></v-btn>
                                                        </v-col>
                                                    </v-row>
                                                </v-row>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    

                                    <v-expansion-panel class="panel">
                                        <v-row dense style="padding:10px;">
                                            <v-col sm="2" style="justify-content: right; display: flex; align-items: center; ">
                                                모집마감일:
                                            </v-col>
                                            
                                            <v-col sm="3" class="col_left">
                                                <Datepicker style="width: 100%;" v-model="state.enddate" :month-year-component="monthYear" />
                                            </v-col>

                                            <v-col sm="2"></v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <!-- 설명글 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col style="height: 670px;" >
                                                <ckeditor :editor="state.editor" v-model="state.editorData" @ready="onReady"></ckeditor>                                          
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col>
                                                <v-file-input
                                                accept="image/*"
                                                label="사진을 넣어주세요"
                                                multiple
                                                @change="handleImage($event)"
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
                                    <h2>공고등록</h2>
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
import FooterVue from '../FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import UploadAdapter from '../UploadAdapter.js';
import CKEditor      from '@ckeditor/ckeditor5-vue'
import { useRouter } from 'vue-router';
import axios from 'axios';
import { onMounted } from '@vue/runtime-core';


export default {
    components: { HeaderVue, FooterVue, ckeditor: CKEditor.component },
    setup () {

        onMounted(() => {
                handleData();
        })

        const router = useRouter();

        const state = reactive({
            zero : 0,
            cdno  : '',
            imageUrl : [],
            imageFile : [],
            cno : '',
            cnamelist : [],
            cname : '',
            clublist : [],
            datechk: [],
            timechk: [],
            gender : [],
            age    : [],
            enddate: '',
            name          : '클럽이름',
            area          : '',
            editor     : ClassicEditor, // ckeditor종류
            editorData : "미리 추가되는 내용",
            token      : sessionStorage.getItem("TOKEN"),

            club: '',
            nameRules: [
                v => !!v || '필수 입력 사항입니다',
                v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다'
            ],
            valid: '',
        })

        const handleImage = (e) => {
            console.log(e.target.files.length);
            if(e.target.files.length > 0)
            {
                for(let i=0; i<e.target.files.length; i++)
                {
                    state.imageUrl[i] = URL.createObjectURL(e.target.files[i]);
                    state.imageFile[i] = e.target.files[i];
                }
            }
            else
            {
                state.imageUrl = null;
                state.imageFile = null;
            }
    }

        const handleData = async() => {
            const url = `/ROOT/combineview/comclub`;
            const headers = {
                "Content-Type" : "application/json",
                "token"        : state.token,
            };
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);
            if(response.data.status === 200){
                state.items = response.data.results;
                console.log(response.data.results[2].cname);
                // state.club = response.data.results.length;
            }
                for(var i=0; i < state.items.length; i++){
                    state.clublist[i] = state.items[i].cname;
                    state.cnamelist[i] = state.items[i].cname;
                    console.log(state.cnamelist);
                    console.log(state.clublist);
                }
                console.log("sad",response.data.results.length);

        }
        const handleCno = async() => {
            if(state.club !== ''){

            const url = `/ROOT/club/cnamesearch?cname=${state.club}`;
            const headers = {
                "Content-Type" : "application/json"
            };
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);
            if(response.data.status === 200){
                state.cno = response.data.result.cname;
              console.log(response.data.result);
            }

                }
        }


        const handleReg = async() => {
            const url = `/ROOT/club/noticeinsert`;
            const headers = {
                "Content-Type" : "application/json",
                "token"        : state.token,
            };
            
            const body= new FormData();
            body.append("cdtitle",   state.cname);
            body.append("cdcontent", state.editorData);
            body.append("enddate",   state.enddate);
            body.append("gender",    state.gender);
            body.append("age",       state.age);
            body.append("date",      state.datechk);
            body.append("time",      state.timechk);
            body.append("club",      state.club);
                
            const response = await axios.post(url, body, {headers});
            console.log(response.data);
            if(response.data.status === 200){
            console.log(response.data.result);
            hanldcdno();

                // alert('등록완료');as
                // router.push({name: 'ClubListVue'});
            }

        };

        

        const hanldcdno = async() => {
            const url = `/ROOT/clubdetail/selectcno?cno=${state.club}`;
            const headers = {"Content-Type" : "application/json"};
            const response = await axios.get(url,{headers:headers});
            console.log(response.data);
            if(response.data.status === 200){
                console.log("hanldcdnohanldcdno",response.data.result.cdno);
                state.cdno = response.data.result.cdno;
                handlecdimage();
            }

        };

        const handlecdimage = async() => {
            console.log("ssssssss",state.cdno);
            const url = `/ROOT/clubdetail/cdimage`;
            const headers = {"Content-Type" : "multipart/form-data"};
            const body= new FormData();
            body.append("clubDetail",  state.cdno);
            for(let i=0; i<state.imageFile.length; i++)
            {
                body.append("file", state.imageFile[i]);
            }
            const response = await axios.post(url, body, {headers});
            console.log(response.data);
            if(response.data.status === 200){
                alert('등록완료');
                router.push({name: 'ClubListVue'});
            }

        };

        const reset = async() => {
            state.datechk = [];
            state.timechk = [];
        };

        const onReady = ( editor ) => {
            console.log(editor);
            editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader ) => {
                return new UploadAdapter( loader );
            };
            
            editor.editing.view.change( writer => {
                writer.setStyle( 'height', '600px', editor.editing.view.document.getRoot() );
            });
            console.log(editor.editing.view);
        }
        return { state, onReady, handleReg, reset,handleCno,handleImage }
    }
}
</script>

<style lang="scss" scoped>

</style>