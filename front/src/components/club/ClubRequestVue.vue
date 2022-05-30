<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">
            <v-row dense>
                <v-col sm="2"></v-col>

                <v-col sm="8">
                    <v-row dense>
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > <router-link to="/cdetail">클럽상세</router-link> > 가입신청</h5>
                        </v-col>
                    </v-row>

                    <v-card style="width:100%; margin: 10px; margin-top: 30px; margin-bottom: 30px;">
                        <v-expansion-panels style="width:100%">
                            <v-form v-model="state.valid" style="width:100%">
                                <!-- 지원클럽 -->
                                <v-expansion-panel class="panel">
                                    <v-row dense style="padding:10px;">
                                        <v-col sm="2" style="justify-content: right; display: flex; align-items: center;">
                                            클럽:
                                        </v-col>

                                        <v-col sm="8" style="justify-content: left; display: flex; align-items: center;">
                                            {{state.clubname}}
                                        </v-col>

                                        <v-col sm="2"></v-col>
                                    </v-row>
                                </v-expansion-panel>

                                <!-- 작성자 -->
                                <v-expansion-panel class="panel">
                                    <v-row dense style="padding: 10px;">
                                        <v-col sm="2" style="justify-content: right; display: flex; align-items: center;">
                                            작성자:
                                        </v-col>

                                        <v-col sm="8" style="justify-content: left; display: flex; align-items: center;">
                                            {{state.id}}
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
                                            <input type="text" v-model="state.title" class="board_title;" style="outline-width: 0; padding-left: 3px; width: 100%; border-bottom: 1px solid #CCC;"/>
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
                                            파일첨부:
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
                                            <v-btn @click="submit" style="width: 100px; height:40px; background-color: gold;">
                                                <h3>신청</h3>
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

                <v-col sm="2"></v-col>
            </v-row>
        </v-main>
    </v-app>
    <FooterVue></FooterVue>
</div>
</template>

<script>
import FooterVue     from '../FooterVue.vue';
import HeaderVue     from '../HeaderVue.vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import UploadAdapter from '../UploadAdapter.js';
import CKEditor      from '@ckeditor/ckeditor5-vue'
import { reactive }  from '@vue/reactivity';
import { useRouter } from 'vue-router';

export default {
    components: { HeaderVue, FooterVue, ckeditor: CKEditor.component },
    setup () {
        const router = useRouter();

        const state = reactive({
            editor    : ClassicEditor, // ckeditor종류
            editorData: '',
            clubname  : '삥뽕탁구클럽',
            id        : 'aaa',
            title     : '제목입니다',
            valid     : '',
        })

        const onReady = ( editor ) => {
            console.log(editor);
            editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                    return new UploadAdapter(loader);
            };
            
            editor.editing.view.change(writer => {
                writer.setStyle( 'height', '600px', editor.editing.view.document.getRoot());
            });
            console.log(editor.editing.view);
        }
        
        const handleCancel = async() => {
            if (confirm('정말 취소하시겠습니까?') == true) {
                router.push({ name: "BoardListVue" });
            }
        }

        return { state, onReady, handleCancel }
    },
}
</script>

<style lang="scss" scoped>

</style>