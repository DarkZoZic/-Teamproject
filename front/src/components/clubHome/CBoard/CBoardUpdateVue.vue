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
                            <h5><router-link :to="{ name: 'CHomeVue', query: { cno: state.cno } }">클럽홈</router-link> > 
                            <router-link :to="{ name: 'CBoardListVue', query: { cno: state.cno } }">{{state.boardname}}</router-link> > 글수정</h5>
                        </v-col>                                
                    </v-row>

                    <v-row dense style="padding-top: 15px; padding-bottom: 10px; padding-left:10px; border-bottom: 1px solid #CCC;">
                        <v-col>
                            <h2>글수정</h2> 
                        </v-col>
                    </v-row>

                    <v-row dense>
                        <v-col class="col_center">                        
                            <v-card style="width:100%; margin: 10px; margin-top: 20px; margin-bottom: 30px;">
                                <v-expansion-panels style="width:100%">
                                    <v-form v-model="state.valid" style="width:100%">
                                        <!-- {{state.valid}} -->

                                        <!-- 제목 -->
                                        <v-expansion-panel class="panel">
                                            <v-row dense style="padding:10px;">
                                                <v-col sm="2" style="justify-content: right; display: flex; align-items: center; ">
                                                    제목:
                                                </v-col>

                                                <v-col sm="8" style="display: flex; align-items: center; width:100%;">
                                                    <input type="text" v-model="state.cbtitle" style="outline-width: 0; padding-left: 3px; width: 100%; border-bottom: 1px solid #CCC;"/>
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
                                                    <ckeditor :editor="state.editor" v-model="state.cbcontent" @ready="onReady"></ckeditor>
                                                </v-col>

                                                <v-col sm="2"></v-col>
                                            </v-row>
                                        </v-expansion-panel>

                                        <!-- 파일첨부 -->
                                        <v-expansion-panel class="panel">
                                            <v-row dense style="padding:10px;">
                                                <v-col sm="2" style="justify-content: right; display: flex; align-items: center;">
                                                    파일첨부:
                                                </v-col>

                                                <v-col sm="8" style="display: flex; align-items: center;">
                                                    <img :src="state.imageUrl" style="width:200px;" v-if="state.imageUrl !== null"/>
                                                    <input type="file" @change="updateFile($event)">
                                                </v-col>

                                                <v-col sm="2"></v-col>
                                            </v-row>
                                        </v-expansion-panel>

                                        <!-- 글쓰기버튼 -->
                                        <v-expansion-panel class="panel">

                                            <v-row dense style="padding:10px; ">
                                                <v-col sm="4"></v-col>

                                                <v-col sm="4" style="justify-content: center; display: flex; align-items: center;">
                                                    <v-btn @click="handleUpdate" style="width: 100px; height:40px; background-color: gold;">
                                                        <h3>수정</h3>
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
</div>
</template>

<script>
import { onMounted } from '@vue/runtime-core';
import CHHeaderVue  from '../CHHeaderVue.vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import CKEditor      from '@ckeditor/ckeditor5-vue'
import { reactive }  from '@vue/reactivity';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';


export default {
    components: { CHHeaderVue, ckeditor: CKEditor.component },
    setup () {
        const route  = useRoute();
        const router = useRouter();

        const state = reactive({
            cbno      : route.query.cbno,
            cno       : route.query.cno,
            cbtitle   : '',
            cbcontent : '',
            imageFile : '',
            imageUrl  : '',
            editor    : ClassicEditor, // ckeditor종류
            editorData: '',
            token     : sessionStorage.getItem("TOKEN"),
            cbhit     : 0,
            cbregdate : '',
            mid       : ''
        })

        const handleData = async() => {
            const url      = `/ROOT/api/clubboard/select?cbno=${state.cbno}&cno=${state.cno}`;
            const headers  = { "Content-Type": "application/json", token: state.token };
            const response = await axios.get(url, { headers });
            // console.log(response.data);
            if(response.data.status === 200){
                console.log(response.data.result);
                const clubboard = response.data.result.clubboard;
                state.cbtitle   = clubboard.cbtitle;
                state.cbcontent = clubboard.cbcontent;
                state.imageFile = response.data.result.file;
                state.imageUrl  = clubboard.cbimageurl;
                state.cbhit     = clubboard.cbhit;
            }
        }
        

        const handleUpdate = async() => { //미완성
            if(state.imageFile === null) {
                state.imageFile = require('../../../assets/img/default-logo.jpg');
            }
            const url     = `/ROOT/api/clubboard/update`;
            const headers = { "Content-Type": "multipart/form-data", token: state.token };
            const body    = new FormData();
            body.append("cbno"     , state.cbno);
            body.append("cno"      , state.cno);
            body.append("cbtitle"  , state.cbtitle);
            body.append("cbcontent", state.cbcontent);
            body.append("file"     , state.imageFile);
            body.append("cbhit"    , state.cbhit);

            const response = await axios.put(url, body, { headers });
            console.log(response.data);
            if(response.data.status === 200){
                alert('수정되었습니다.');
                router.push({ name: 'CBoardContentVue', query: { cbno: state.cbno, cno: state.cno } });
            }

        }

        const handleCancel = async() => {
            if (confirm('취소하시겠습니까?') == true) {
                router.push({ name: 'CBoardContentVue', query: { cbno: state.cbno, cno: state.cno } });
            }
        }

        const onReady = ( editor ) => {
            // console.log(editor);
            editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                return new UploadAdapter(loader);
            };
            
            editor.editing.view.change( writer => {
                writer.setStyle('height', '600px', editor.editing.view.document.getRoot());
            });
            // console.log(editor.editing.view);
        }

        const updateFile = (e) => {
            if(e.target.files[0]) {
                state.imageUrl = URL.createObjectURL(e.target.files[0]);
                state.imageFile = e.target.files[0];
                console.log("updatedFile = ", state.imageFile);
            }
        }


        onMounted( async() => {
            await handleData();
        })

        return { state, onReady, handleCancel, handleUpdate, updateFile }
    },
}
</script>

<style lang="scss" scoped>

</style>