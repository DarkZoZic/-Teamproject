<template>
<div>
<CHHeaderVue style="height: 150px;"></CHHeaderVue>
    <v-app>
        <v-main style="padding: 10px;">
            <v-row dense>
                <v-col md="2"></v-col>

                <v-col md="8">
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
                                    <v-form v-model="valid" style="width:100%">
                                        <!-- 작성자 -->
                                        <v-expansion-panel class="panel">
                                            <v-row dense style="padding:10px;">
                                                <v-col sm="2" style="justify-content: right; display: flex; align-items: center;">
                                                    작성자:
                                                </v-col>

                                                <v-col sm="8" style="display: flex; align-items: center;">
                                                    {{state.writer}}
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
                                                    <input type="text" v-model="state.title" style="outline-width: 0; padding-left: 3px; width: 100%; border-bottom: 1px solid #CCC;"/>
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
                                                    <ckeditor :editor="state.editor" v-model="state.editorData" @ready="onReady"></ckeditor>
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
                                                    <input type="file">
                                                </v-col>

                                                <v-col sm="2"></v-col>
                                            </v-row>
                                        </v-expansion-panel>

                                        <!-- 글쓰기버튼 -->
                                        <v-expansion-panel class="panel">

                                            <v-row dense style="padding:10px; ">
                                                <v-col sm="4"></v-col>

                                                <v-col sm="4" style="justify-content: center; display: flex; align-items: center;">
                                                    <v-btn @click="create()" style="width: 100px; height:40px; background-color: gold;">
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

                <v-col md="2"></v-col>
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


export default {
    components: { CHHeaderVue, FooterVue, ckeditor: CKEditor.component },
    setup () {
        const router = useRouter();

        const state = reactive({
            writer     : '작성자입니다',
            title      : 'asdf',
            editor     : ClassicEditor, // ckeditor종류
            editorData : '',
            boardname  : '자유게시판'
        })

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
        
        const handleCancel = async() => {
            if (confirm('정말 취소하시겠습니까?') == true) {
                router.push({ name: "BoardListVue"});
            }
        }

        const create = async() => {

        }

        return { state, onReady, handleCancel, create }
    },
}
</script>

<style lang="scss" scoped>

</style>