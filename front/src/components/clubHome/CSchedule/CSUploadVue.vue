<template>
<div>
<CHHeaderVue style="height: 150px;"></CHHeaderVue>
    <v-app>
        <v-main style="padding: 10px;">
            <v-row dense>
                <v-col sm="2"></v-col>

                <v-col sm="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/chome">홈</router-link> > <router-link to="/cschedule">일정</router-link> > 일정생성</h5>
                        </v-col>                                
                    </v-row>

                    <v-row dense style="padding-top: 15px; padding-bottom: 10px; padding-left:10px; border-bottom: 1px solid #CCC;">
                        <v-col>
                            <h2>일정생성</h2>
                        </v-col>
                    </v-row>

                    <v-row dense>
                        <v-col class="col_center">                        
                            <v-card style="width:100%; margin: 10px; margin-top: 20px; margin-bottom: 30px;">
                                <v-expansion-panels style="width:100%">
                                    <v-form v-model="state.valid" style="width:100%">
                                        <!-- 작성자 -->
                                        <v-expansion-panel class="panel">
                                            <v-row dense style="padding:10px;">
                                                <v-col sm="2" style="justify-content: right; display: flex; align-items: center;">
                                                    작성자:
                                                </v-col>

                                                <v-col sm="8" style="display: flex; align-items: center;">
                                                    {{state.schedule.swriter}}
                                                </v-col>

                                                <v-col sm="2"></v-col>
                                            </v-row>
                                        </v-expansion-panel>

                                        <!-- 제목 -->
                                        <v-expansion-panel class="panel">
                                            <v-row dense style="padding:10px;">
                                                <v-col sm="2" style="justify-content: right; display: flex; align-items: center; ">
                                                    제목:
                                                </v-col>

                                                <v-col sm="8" style="display: flex; align-items: center; width:100%;">
                                                    <input type="text" v-model="state.schedule.sname" style="outline-width: 0; padding-left: 3px; width: 100%; border-bottom: 1px solid #CCC;"/>
                                                </v-col>

                                                <v-col sm="2"></v-col>
                                            </v-row>
                                        </v-expansion-panel>

                                        <!-- 제목 -->
                                        <v-expansion-panel class="panel">
                                            <v-row dense style="padding:10px;">
                                                <v-col sm="2" style="justify-content: right; display: flex; align-items: center; ">
                                                    일시:
                                                </v-col>

                                                <v-col sm="3" class="col_left">
                                                    <Datepicker style="width: 100%;" v-model="state.schedule.startdate" :month-year-component="monthYear" />
                                                </v-col>
                                                <v-col sm="2" class="col_center">
                                                    <h4>-</h4>
                                                </v-col>
                                                
                                                <v-col sm="3" class="col_left">
                                                    <Datepicker style="width: 100%;" v-model="state.schedule.enddate" :month-year-component="monthYear" />
                                                </v-col>

                                                <v-col sm="2"></v-col>
                                            </v-row>
                                        </v-expansion-panel>
                                        
                                        <!-- 내용 -->
                                        <v-expansion-panel class="panel">
                                            <v-row dense style="padding:10px;">
                                                <v-col sm="2" style="justify-content: right; display: flex; align-items: center; margin-top: 13px;">
                                                    내용:
                                                </v-col>

                                                <v-col sm="8" >
                                                    <ckeditor :editor="state.editor" v-model="state.schedule.scontent" @ready="onReady"></ckeditor>
                                                </v-col>

                                                <v-col sm="2"></v-col>
                                            </v-row>
                                        </v-expansion-panel>

                                        <!-- 파일첨부 -->
                                        <v-expansion-panel class="panel">
                                            <v-row dense style="padding:10px;">
                                                <v-col sm="2" style="justify-content: right; display: flex; align-items: center;">
                                                    썸네일:
                                                </v-col>

                                                <v-col sm="8" style="display: flex; align-items: center;">
                                                    <input type="file" @change="insertThumbnail($event)">
                                                </v-col>

                                                <v-col sm="2"></v-col>
                                            </v-row>
                                        </v-expansion-panel>

                                        <!-- 글쓰기버튼 -->
                                        <v-expansion-panel class="panel">

                                            <v-row dense style="padding:10px; ">
                                                <v-col sm="4"></v-col>

                                                <v-col sm="4" style="justify-content: center; display: flex; align-items: center;">
                                                    <v-btn @click="create" style="width: 100px; height:40px; background-color: gold;">
                                                        <h3>일정생성</h3>
                                                    </v-btn>

                                                    <v-btn @click="handleCancel" style="width: 100px; height:40px; margin-left:20px; background-color: white;">
                                                        <h3>취소</h3>
                                                    </v-btn>
                                                </v-col>

                                                <v-col sm="4"></v-col>
                                            </v-row>
                                        </v-expansion-panel>
                                    </v-form>
                                </v-expansion-panels>
                            </v-card>
                        </v-col>
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
import FooterVue     from '../../FooterVue.vue';
import CHHeaderVue  from '../CHHeaderVue.vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import UploadAdapter from '../../UploadAdapter.js';
import CKEditor      from '@ckeditor/ckeditor5-vue'
import { reactive }  from '@vue/reactivity';
import { useRouter } from 'vue-router';
import axios from 'axios';


export default {
    components: { CHHeaderVue, FooterVue, ckeditor: CKEditor.component },
    setup () {
        const router = useRouter();

        const state = reactive({
            schedule : {},
            // writer     : '작성자입니다',
            // title      : 'asdf',
            editor     : ClassicEditor, // ckeditor종류
            // editorData : '',
            // boardname  : '일정표',
            // startDate  : '',
            // endDate    : '',
            // valid      : '',
            thumbnail  : '',
            thumbnailUrl : ''
        })

        const onReady = ( editor ) => {
            // console.log(editor);
            editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader ) => {
                    return new UploadAdapter( loader );
            };
            
            editor.editing.view.change( writer => {
                writer.setStyle( 'height', '600px', editor.editing.view.document.getRoot() );
            });
            // console.log(editor.editing.view);
        }
        
        const handleCancel = async() => {
            if (confirm('정말 취소하시겠습니까?') == true) {
                router.push({ name: "CScheduleVue"});
            }
        }

        const create = async() => {
            // console.log(state);
            // 일정 시작일자(startdate) 형식 yyyy-MM-dd hh:mm로 변환
            const sd = state.schedule.startdate;
            var year = sd.getFullYear(); //년
            var month = ('0' + (sd.getMonth() + 1)).slice(-2); //월
            var day = ('0' + sd.getDate()).slice(-2); //일

            var sdateString = year + '-' + month  + '-' + day;

            var hours = ('0' + sd.getHours()).slice(-2); //시
            var minutes = ('0' + sd.getMinutes()).slice(-2); //분
            // var seconds = ('0' + today.getSeconds()).slice(-2);  //초

            var stimeString = hours + ':' + minutes;

            // console.log(sdateString, stimeString);

            const startdate = sdateString + ' ' + stimeString;
            // console.log(startdate);

            // 예상 일정종료일자(enddate) 형식 yyyy-MM-dd hh:mm로 변환
            const ed = state.schedule.enddate;
            var year = ed.getFullYear();
            var month = ('0' + (ed.getMonth() + 1)).slice(-2);
            var day = ('0' + ed.getDate()).slice(-2);

            var edateString = year + '-' + month  + '-' + day;

            var hours = ('0' + ed.getHours()).slice(-2); 
            var minutes = ('0' + ed.getMinutes()).slice(-2);
            // var seconds = ('0' + today.getSeconds()).slice(-2);  //초

            var etimeString = hours + ':' + minutes;

            // console.log(edateString, etimeString);

            const enddate = edateString + ' ' + etimeString;
            // console.log(enddate);

            const url = `/ROOT/api/schedule/insert`;
            const headers = {"Content-Type":"multipart/form-data"};
            const body = new FormData();
            // body.append("swriter", state.schedule.swriter); //미구현
            body.append("sname", state.schedule.sname);
            body.append("scontent", state.schedule.scontent);
            body.append("startdate", startdate); //localdatetime으로 변환
            body.append("enddate", enddate); //localdatetime으로 변환
            body.append("file", state.thumbnail);

            const response = await axios.post(url, body, {headers});
            console.log(response.data);
            if(response.data.status === 200)
            {
                alert('일정이 등록되었습니다.');
                router.push({name:'CScheduleVue'});
            }
        }

        const insertThumbnail = (e) =>
        {
            console.log(e.target.files);
            if(e.target.files[0])
            {
                state.thumbnail = e.target.files[0];
                // state.thumbnailUrl = URL.createObjectURL(e.target.files[0]);
            }
            else
            {
                state.thumbnail = null;
                // state.thumbnailUrl = null;
            }
        }
        

        return { state, onReady, handleCancel, create, insertThumbnail}
    },
}
</script>

<style lang="scss" scoped>

</style>