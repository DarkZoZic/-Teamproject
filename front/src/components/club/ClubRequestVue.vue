<template>
<div v-if="state.items">
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
                                            신청 클럽명 :
                                        </v-col>

                                        <v-col sm="8" style="justify-content: left; display: flex; align-items: center;">
                                            {{state.items.club.cname}}
                                        </v-col>

                                        <v-col sm="2"></v-col>
                                    </v-row>
                                </v-expansion-panel>

                                <!-- 작성자 -->
                                <v-expansion-panel class="panel">
                                    <v-row dense style="padding: 10px;">
                                        <v-col sm="2" style="justify-content: right; display: flex; align-items: center;">
                                            신청자:
                                        </v-col>

                                        <v-col sm="8" style="justify-content: left; display: flex; align-items: center;">
                                            {{state.nick}}
                                        </v-col>

                                        <v-col sm="2"></v-col>
                                    </v-row>
                                </v-expansion-panel>
                                <!-- 닉네임 -->
                                <v-expansion-panel class="panel">
                                    <v-row dense style="padding: 10px;">
                                        <v-col sm="2" style="justify-content: right; display: flex; align-items: center;">
                                            닉네임:
                                        </v-col>

                                        <v-col sm="8" style="justify-content: left; display: flex; align-items: center;">
                                            {{state.nickname}}
                                        </v-col>

                                        <v-col sm="2"></v-col>
                                    </v-row>
                                </v-expansion-panel>
                                

                                <!-- 연락처 -->
                                <v-expansion-panel class="panel">
                                    <v-row dense style="padding:10px;">
                                        <v-col sm="2" style="justify-content: right; display: flex; align-items: center; ">
                                            연락처:
                                        </v-col>

                                        <v-col sm="8" style="display: flex; align-items: center; width:100%;">
                                            <input type="text" v-model="state.title" class="board_title;" style="outline-width: 0; padding-left: 3px; width: 100%; border-bottom: 1px solid #CCC;"/>
                                        </v-col>

                                        <v-col sm="2"></v-col>
                                    </v-row>
                                </v-expansion-panel>

                               
                               

                                <!-- 글쓰기버튼 -->
                                <v-expansion-panel class="panel">
                                    <v-row dense style="padding:10px; ">
                                        <v-col sm="4"></v-col>

                                        <v-col sm="4" style="justify-content: center; display: flex; align-items: center;">
                                            <v-btn @click="handleJoinclub()" style="width: 100px; height:40px; background-color: gold;">
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
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import { onMounted } from '@vue/runtime-core';

export default {
    components: { HeaderVue, FooterVue, ckeditor: CKEditor.component },
    setup () {
        const route = useRoute();
        const router = useRouter();
        onMounted( () => {
            handleData(),Myid(),handlenick();
        })

        const state = reactive({
            nickname : '',
            nick : '',
            id : '',
            editor    : ClassicEditor, // ckeditor종류
            token     : sessionStorage.getItem("TOKEN"),
            cno       : route.query.cno,
            editorData: '',
            clubname  : '삥뽕탁구클럽',
            title     : '',
            valid     : '',
        })

        const handleData = async() => {
            const url      = `/ROOT/clubdetail/selectcno?cno=${state.cno}`;
            const headers  = { "Content-Type": "application/json" };
            const response = await axios.get(url, {headers});
            console.log(response.data);

            if(response.data.status === 200){
                console.log(response.data.result);
                state.items = response.data.result;
                state.cdno  = response.data.result.cdno;
                state.addr1 = state.items.club.caddress;
                console.log(state.cdno);
            }

        }
        const Myid = async() => {
            const url      = `/ROOT/member/mypage`;
            const headers  = { "Content-Type": "application/json",
            token : state.token };
            const response = await axios.get(url, {headers});
            console.log(response.data);

            if(response.data.status === 200){
                console.log("---------",response.data.result);
                console.log("---------",response.data.result.mname);
                state.nick = response.data.result.mname;
                console.log("---------",response.data.result.mid);
                state.id = response.data.result.mid;
            }

        }
        const handlenick = async() => {
            const url = `/ROOT/member/psmynick`;
            const headers = {"Content-Type":"application/json", 
            token : state.token};
            const response = await axios.get(url, {headers});
            console.log(response.data.result);
            if(response.data.status === 200){
                state.nickname = response.data.result.mpnickname;
                console.log(state.nick);
            }
        }

        const handleJoinclub = async() => {
            const url = `/ROOT/joinclub/insert.json`;
            const headers = {"Content-Type":"multipart/form-data"};
            const body = new FormData;
                body.append("member",  state.id);
                body.append("steptbl",  105);
                body.append("club", state.cno);
            const response = await axios.post(url,body,{headers});
            console.log(response.data);

            if(response.data.status === 200){
                alert('클럽신청완료');
                router.push({path : 'clist'});
            }
        }

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

        return { state, onReady, handleCancel,handleJoinclub }
    },
}
</script>

<style lang="scss" scoped>

</style>