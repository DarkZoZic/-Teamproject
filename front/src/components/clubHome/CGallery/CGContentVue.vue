<template>
<div>
<CHHeaderVue style="height: 150px;"></CHHeaderVue>
    <v-app>
        <v-main style="padding: 10px;" >      
            <v-row dense>
                <v-col sm="2"></v-col>
                
                <!-- 메인 -->
                <v-col sm="8">
                    <v-row dense class="border-b_1_CCC">
                        <v-col>
                            <h5><router-link :to="{ name: 'CHomeVue', query: { cno: state.cno } }">클럽홈</router-link> > <router-link :to="{ name: 'CGalleryVue', query: { cno: state.cno } }">{{state.gallery.cgname}}</router-link> > 글읽기</h5>
                        </v-col>
                    </v-row>

                    <v-row dense>
                        <v-col class="row_bwrite1">
                            <h2>{{state.gallery.cgname}}</h2>
                        </v-col>
                    </v-row>

                    <!-- 상단메뉴 -->
                    <v-row dense class="row_bwrite2">
                        <v-col sm="6" class="col_pad-l25">
                        <h4 style="color: #787878" v-if="state.nick.mcname === null">{{state.nick.mpnickname}}</h4>
                        <h4 style="color: #787878" v-if="state.nick.mpnickname === null">{{state.nick.mcname}}</h4>
                        </v-col>

                        <v-col sm="6" class="col_right1">
                        <h5 style="color: #787878">
                            조회 {{state.gallery.cghit}} &nbsp; | &nbsp; 
                            <img :src="require('../../../assets/img/thumb.png')" style="width: 15px; margin-right: 3px;"/> {{state.like}}
                            &nbsp; | &nbsp; {{state.gallery.cgregdate}}
                        </h5>
                        </v-col>
                    </v-row>

                    <!-- 내용 -->
                    <v-row dense>
                        <v-col class="col_center" style="padding: 20px; height: 600px;" v-for="tmp in state.imageurl" :key="tmp">
                            <img :src="tmp" style="max-height: 100%; max-width: 100%; padding: 5px; border: 1px solid #CCC;"/>
                        </v-col>
                    </v-row>

                    <v-row dense class="border-b_1_CCC">
                        <v-col style="padding: 20px;" class="col_center">
                            <v-btn style="height: 50px;" @click="like">
                                <img :src="require('../../../assets/img/thumb.png')" style="width: 40px; margin-right: 3px;"/>
                                <h3 style="margin-left: 10px;">{{state.like}}</h3>
                            </v-btn>
                        </v-col>
                    </v-row>

                    <v-col class="col_right">
                        <div>
                            <h5 @click="handleUpdate()" style="padding-right: 10px; cursor: pointer;" v-if="state.tokenid === state.mid">수정</h5>
                        </div>

                        <div>
                            <h5 @click="handleDelete()" style="cursor: pointer;" v-if="state.tokenid === state.mid">삭제</h5>
                        </div>
                    </v-col>

                    <v-row dense>
                        <v-col style="display: flex; padding-top: 10px; padding-left: 10px;" class="col_left">
                        <h5>댓글</h5>&nbsp;<h5 style="color: #fca103">{{state.reply.length}}</h5><h5>개</h5>
                        </v-col>
                    </v-row>

                    <!-- 댓글창 -->
                    <!-- 자기가 남긴 댓글에만 수정 삭제 버튼이 뜨게 -->
                    <v-row dense style="background-color: #504ea31d;">
                        <v-col style="border-top: 1px solid #CCC; border-bottom: 1px solid #CCC; padding-left: 20px; padding-right: 20px;">

                            <!-- 댓글하나 -->
                            <v-row dense style="padding-top: 10px; border-bottom: 1px solid #CCC;" v-for="(tmp, idx) in state.reply" :key="tmp">
                                <v-col v-if="tmp.reparentnumber === 0">
                                    <!-- 댓글작성자 -->
                                    <v-row dense>
                                        <v-col class="col_left">
                                            <h5 style="padding-right: 10px;" v-if="state.replynicklist[idx].mcname === null">{{state.replynicklist[idx].mpnickname}}</h5> 
                                            <h5 style="padding-right: 10px;" v-if="state.replynicklist[idx].mpnickname === null">{{state.replynicklist[idx].mcname}}</h5>
                                            <h5 style="color: #676767;">{{tmp.reregdate}}</h5>
                                            <a><img :src="require('../../../assets/img/thumb.png')" style="width: 15px; margin-left: 10px; margin-right: 3px;"/></a>
                                            <h5 style="color: #676767;">{{state.like}}</h5>
                                            <a><h5 style="color: #676767; padding-left: 10px;" @click="rereplytoggle(tmp.renumber)">댓글</h5></a>
                                        </v-col>
                                    </v-row>

                                    <!-- 댓글내용 -->
                                    <v-row dense style="padding-right: 10px;" >
                                        <v-col>
                                            <h4 style="padding-left: 10px; padding-right: 10px;" v-if="!state.replyupdate.update[idx]">{{tmp.recontent}}</h4>
                                            <div v-if="state.replyupdate.update[idx]" class="col_left">
                                                <textarea v-model="tmp.recontent" 
                                                style="background-color: white; resize: none; border: 1px solid #CCC; border-radius: 5px; padding: 10px; width: 900px;">
                                                </textarea>
                                            </div>
                                        </v-col>
                                    </v-row>
                                    
                                    <!-- 댓글 수정/삭제 -->
                                    <v-col class="col_right" v-if="state.tokenid === tmp.member.mid">
                                        <div v-if="!state.replyupdate.update[idx]">
                                            <h5 @click="handleReplyUpdate(idx)" style="padding-right: 10px; cursor: pointer; display: inline-block;">수정</h5>
                                            <h5 @click="handleReplyDelete(tmp.renumber, tmp.reparentnumber)" style="cursor: pointer; display: inline-block;" >삭제</h5>
                                        </div>
                                        
                                        <div v-if="state.replyupdate.update[idx]">
                                            <v-btn style="height: 68px; margin-right: 10px;" @click="handleReplyUpdate(idx)"><h4>취소</h4></v-btn>
                                            <v-btn style="height: 68px;" @click="handleReplyUpdateAct(tmp.renumber, idx)"><h4 >수정</h4></v-btn>
                                        </div>
                                    </v-col>

                                    <!-- 대댓글 -->
                                    <v-row dense style="padding-left: 10px;" v-for="(tmp1, idx1) in state.rereply" :key="tmp1">
                                        <v-col v-if="tmp1.reparentnumber === tmp.renumber">
                                            <v-row dense>
                                                <v-col style="display: flex">
                                                    <img :src="require('../../../assets/img/reply.png')" style="margin-right: 10px; width: 17px; height: 17px; transform: scaleX(-1) scaleY(-1); margin-right: 3px;"/>
                                                    <!-- 대댓글작성자 -->
                                                    <v-row dense>
                                                        <v-col class="col_left">
                                                            <h5 style="padding-right: 10px;" v-if="state.rereplynicklist[idx1].mcname === null">{{state.rereplynicklist[idx1].mpnickname}}</h5> 
                                                            <h5 style="padding-right: 10px;" v-if="state.rereplynicklist[idx1].mpnickname === null">{{state.rereplynicklist[idx1].mcname}}</h5>
                                                            <h5 style="color: #676767;">{{tmp1.reregdate}}</h5>
                                                            <a><img :src="require('../../../assets/img/thumb.png')" style="width: 15px; margin-left: 10px; margin-right: 3px;"/></a>
                                                            <h5 style="color: #676767;">{{state.like}}</h5>

                                                            <!-- <a><h5 style="color: #676767; padding-left: 10px;">댓글</h5></a> -->
                                                        </v-col>
                                                    </v-row>
                                                </v-col>
                                            </v-row>

                                            <!-- 대댓글내용 -->
                                            <v-row dense style="padding-right: 10px;">
                                                <v-col>
                                                    <h4 style="padding-left: 30px;" v-if="!state.rereplyupdate.update[idx1]">{{tmp1.recontent}}</h4>
                                                    <div v-if="state.rereplyupdate.update[idx1]" class="col_left">
                                                        <textarea v-model="tmp1.recontent" 
                                                        style="background-color: white; resize: none; border: 1px solid #CCC; border-radius: 5px; padding: 10px; width: 900px;">
                                                        </textarea>
                                                    </div>
                                                </v-col>
                                            </v-row>
                                        </v-col>

                                        <!-- 대댓글 수정/삭제 -->
                                         <v-col class="col_right" v-if="state.tokenid === tmp1.member.mid && tmp1.reparentnumber === tmp.renumber">
                                            <div v-if="!state.rereplyupdate.update[idx1]">
                                                <h5 @click="handleReReplyUpdate(idx1)" style="padding-right: 10px; cursor: pointer; display: inline-block">수정</h5>
                                                <h5 @click="handleReplyDelete(tmp1.renumber, tmp1.reparentnumber)" style="cursor: pointer; display: inline-block;" >삭제</h5>
                                            </div>
                                            
                                            <div v-if="state.rereplyupdate.update[idx1]">
                                                <v-btn style="height: 68px; margin-right: 10px;" @click="handleReReplyUpdate(idx1)"><h4>취소</h4></v-btn>
                                                <v-btn style="height: 68px;" @click="handleReReplyUpdateAct(tmp1.renumber, idx1)"><h4 >수정</h4></v-btn>
                                            </div>
                                        </v-col>
                                    </v-row>

                                     <!-- 대댓글 작성 -->
                                    <v-row dense="" v-if="state.reparentnumber === tmp.renumber">
                                        <v-col sm="9" style="padding-top: 10px;">
                                        <textarea 
                                            style="border: 1px solid #CCC; padding: 10px; background-color: white; width: 100%; height: 70px; outline-width: 0; resize: none;"
                                            v-model="state.rereplycontent"
                                        ></textarea>
                                        </v-col>
                                        
                                        <v-col sm="1" style="padding: 10px;" class="col_center">
                                        <v-btn style="width: 100%; height:69px; border: 1px solid #CCC;" @click="insertrereply(state.reparentnumber)"><h4>댓글작성</h4></v-btn>
                                        </v-col>
                                    </v-row>
                                </v-col>
                            </v-row>

                            <v-row dense="">
                                <v-col sm="9" style="padding-top: 10px;">
                                    <textarea 
                                        style="border: 1px solid #CCC; padding: 10px; background-color: white; width: 100%; height: 70px; outline-width: 0; resize: none;"
                                        v-model="state.replycontent"
                                    ></textarea>
                                </v-col>
                                
                                <v-col sm="1" style="padding: 10px;" class="col_center">
                                    <v-btn style="width: 100%; height:69px; border: 1px solid #CCC;" @click="insertreply"><h4>댓글작성</h4></v-btn>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    
                    <v-row dense style="padding-top: 10px; padding-bottom: 20px;">
                        <v-col sm="3">
                            <router-link :to="{ name: 'CGalleryVue', query: { cno: state.cno } }">
                                <v-btn class="col_center"><img :src="require('../../../assets/img/list.png')" style="width: 20px; margin-right: 3px;"/><h4>목록</h4></v-btn>
                            </router-link>
                        </v-col>

                        <v-col class="col_right">
                            <router-link :to="{ name: 'CGUploadVue', query: { cno: state.cno } }">
                                <v-btn class="col_center"><img :src="require('../../../assets/img/pencil.png')" style="width: 20px; margin-right: 3px;"/><h4>글쓰기</h4></v-btn>
                            </router-link>
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
import { reactive } from '@vue/reactivity';
import FooterVue    from '../../FooterVue.vue';
import CHHeaderVue  from '../CHHeaderVue.vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';
import { onMounted } from '@vue/runtime-core';

export default {
    components: { CHHeaderVue, FooterVue },
    setup () {
        const route = useRoute();
        const router = useRouter();

        const state = reactive({
        cgno           : route.query.cgno,
        cno            : route.query.cno,
        token          : sessionStorage.getItem("TOKEN"),
        tokenid        : sessionStorage.getItem("MID"),
        gallery        : '',
        mid            : '',
        nick           : '',
        imagecount     : 0,
        imageurl       : [],
        replynicklist  : [],
        rereplynicklist: [],
        rereply        : [],
        replycontent   : '',
        reparentnumber : 0,
        rereplycontent : '',
        reply          : [],

        replyupdate : 
        {
            update : []
        },
        rereplyupdate    : {
            update : []
        }
    });

        // 조회수 증가 -> 갤러리 상세내용 + 이미지 + 댓글목록
        const gallery = async() => {
            const url      = `/ROOT/api/clubgallery/updatehit?cgno=${state.cgno}&cno=${state.cno}`;
            const headers  = { "Content-Type": "application/json", "token": state.token };
            const body     = { cgno: state.cgno} ;
            const response = await axios.post(url, body, { headers });
            if(response.data.status === 200) {
                const url      = `/ROOT/api/clubgallery/select?cgno=${state.cgno}&cno=${state.cno}`;
                const headers  = { "Content-Type": "application/json", "token": state.token };
                const response = await axios.get(url, {headers});
                console.log(response.data);

                if(response.data.status === 200) {
                    state.gallery          = response.data.result.clubgallery;
                    state.mid              = response.data.result.clubgallery.member.mid;
                    state.imagecount       = response.data.result.imagecount; //idx
                    state.nick             = response.data.result.nick;
                    state.reply            = response.data.result.replylist;
                    state.rereply          = response.data.result.rereplylist;
                    state.replynicklist    = response.data.result.replynicklist;
                    state.rereplynicklist  = response.data.result.rereplynicklist;
                    for(let i=0; i<state.imagecount; i++) {
                        state.imageurl[i]  = state.gallery.gimageurl + "&idx=" + i;
                        // console.log(state.imageurl[i]);
                    }
                    for(let i=0; i<state.reply.length; i++)
                    {
                        state.replyupdate.update.push(false);
                        // console.log(state.replyupdate.update[i]);
                    }
                }
            } else if(response.data.status === 0) {
                alert("로그인이 필요한 페이지입니다.");
                router.push({ name: 'LoginVue' });
            } else {
                alert('비정상적인 접근입니다.');
                router.push({ name: 'HomeVue' });
            }
        }

        // 글 작성자 닉네임
        // const nick = async() => {
        //     const url      = `/ROOT/api/clubmember/selectnick?mid=${state.mid}`;
        //     const headers  = { "Content-Type": "application/json" };
        //     const response = await axios.get(url, { headers });
        //     // console.log(response.data);

        //     if(response.data.status === 200) {
        //         state.nick = response.data.result.mpnickname;
        //     };
        // }

        const like = async() => {
            // const url = `/reaction/gallerylike.json`;
            // const headers = {"Content-Type":"application/json", "token" : state.token};
            // const body = new FormData();
            // body.append();
        }

        const insertreply = async() => {
            const url     = `/ROOT/api/clubgallery/insertreply`;
            const headers = { "Content-Type": "application/json", "token": state.token };
            const body    = { recontent: state.replycontent, cgno: state.cgno, reparentnumber : 0 }
            const response = await axios.post(url, body, {headers});
            console.log(response.data);
            if(response.data.status === 200) {
                location.reload();
            }
        }

        const handleDelete = async() =>
        {
            const url     = `/ROOT/api/clubgallery/delete`;
            const headers = { "Content-Type": "application/json", "token": state.token };
            const body    = { cgno : state.cgno };
            const response = await axios.post(url, body, { headers });
            console.log(response.data);

            if(response.data.status === 200) {
                alert('삭제되었습니다.');
                router.push({ name: "CGalleryVue", query: { cgno: state.cgno, cno: state.cno } });
            }
        }

        const handleReplyDelete = async(idx, idx1) => {
            const url      = `/ROOT/api/clubboard/deletereply`;
            const headers = { "Content-Type": "application/json", "token": state.token };
            const body     = { renumber: idx, reparentnumber: idx1 };
            const response = await axios.post(url, body, { headers });
            console.log(response.data);

            if(response.data.status === 200) {
                location.reload();
            }
        }

        const handleReplyUpdate = (idx) => {
            if(!state.replyupdate.update[idx]) {
                state.replyupdate.update[idx] = true;
            }else {
                state.replyupdate.update[idx] = false;
                gallery();
            }
        }

        const handleReplyUpdateAct = async(tmp, idx) => {
            const url     = `/ROOT/api/clubgallery/updatereply`;
            const headers = { "Content-Type": "application/json", "token": state.token };
            const body    = 
            {
                cgno : tmp,
                renumber : state.reply[idx].renumber,
                recontent: state.reply[idx].recontent
            };
            const response = await axios.put(url, body, { headers });
            console.log(response.data);

            if(response.data.status === 200){
                state.replyupdate.update[idx] = false;
                gallery();
            }
        }

        const rereplytoggle = (renumber) =>
        {
            if(state.reparentnumber === 0)
            {
                state.reparentnumber = renumber;
                console.log(state.reparentnumber);
            }
            else
            {
                state.reparentnumber = 0;
                console.log(state.reparentnumber);
            }
        } 

        const insertrereply = async(idx) => {
            const url     = `/ROOT/api/clubgallery/insertreply`;
            const headers = {"Content-Type":"application/json", "token" : state.token};
            const body    = 
            {
                cgno : state.cgno,
                recontent : state.rereplycontent,
                reparentnumber : idx
            };
            const response = await axios.post(url, body, {headers});
            console.log(response.data);

            if(response.data.status === 200)
            {
                location.reload();
            }
        }

        const handleReReplyUpdate = (idx1) => {
                // console.log(idx1);
                // console.log(state.rereplyupdate.update[idx1]);
                if(!state.rereplyupdate.update[idx1])
                {
                    state.rereplyupdate.update[idx1] = true;
                }
                else
                {
                    state.rereplyupdate.update[idx1] = false;
                    content();
            }
        }

        const handleReReplyUpdateAct = async(tmp1, idx1) => 
        {
            // console.log(tmp);
            // console.log(state.reply[idx].renumber);
            const url     = `/ROOT/api/clubgallery/updatereply`;
            const headers = {"Content-Type":"application/json", "token" : state.token};
            const body    = 
            {
                cbno      : tmp1,
                renumber  : state.rereply[idx1].renumber,
                recontent : state.rereply[idx1].recontent,
            };
            const response = await axios.put(url, body, {headers});
            console.log(response.data);

            if(response.data.status === 200) {
                state.rereplyupdate.update[idx1] = false;
                content();
            }
        }

        const replylike = async() => {

        }

        onMounted(async () => {
            await gallery();
            // nick();
        });

        return { state, like, insertreply, handleDelete, handleReplyDelete, handleReplyUpdate, handleReplyUpdateAct, rereplytoggle, insertrereply, handleReReplyUpdate, handleReReplyUpdateAct, replylike }
    }
}
</script>

<style lang="scss" scoped>
    @import '../../../assets/css/style';
</style>