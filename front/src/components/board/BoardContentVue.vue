<template>
<div v-if="state.items">
<HeaderVue style="height: 220px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;" >      
      <v-row dense>
        <v-col sm="2"></v-col>
        
        <!-- 메인 -->
        <v-col sm="8">
          <v-row dense class="border-b_1_CCC">
            <v-col>
              <h5><router-link to="/">홈</router-link> > <router-link to="/blist">자유게시판</router-link> > 글읽기</h5>
            </v-col>
          </v-row>

          <v-row dense>
            <v-col class="row_bwrite1">
              <h2>{{state.items.btitle}}</h2>
            </v-col>
          </v-row>

          <!-- 상단메뉴 -->
          <v-row dense class="row_bwrite2">
            <v-col sm="6" class="col_pad-l25">
              <h4 style="color: #787878">{{state.items.mid}}</h4>
            </v-col>

            <v-col sm="6" class="col_right1">
              <h5 style="color: #787878">
                조회 {{state.items.bhit}} &nbsp; | &nbsp; 
                <img :src="require('../../assets/img/thumb.png')" style="width: 15px; margin-right: 3px;"/> {{state.items.blike}}
                &nbsp; | &nbsp; {{state.bregdate1}}
              </h5>
            </v-col>
          </v-row>

          <v-row dense>
            <v-col class="col_pad20">
            
              <div v-html="state.items.bcontent">
              </div>
            </v-col>

            <!-- <v-col style="width: 100%; height: 165px;" class="col_center">
              <img  :src="state.items.bimageurl"  style="width: 160px; border: 1px solid #CCC;"/>
            </v-col> -->
<!-- 
            <v-col class="col_center" style="padding: 20px;" v-for="tmp in state.imageurl" :key="tmp">   
              <img :src="tmp" style="height: 600px;  padding: 5px; border: 1px solid #CCC;"/>
            </v-col> -->

            <v-row dense>
              <v-col style="width: 100%; height: 165px;" class="col_center">
                  <img :src="state.bimageurl"  style="width: 160px; border: 1px solid #CCC;"/>
              </v-col>
            </v-row>
          </v-row>

          <v-row dense class="border-b_1_CCC">
            <v-col style="padding: 20px;" class="col_center">
              <v-btn style="height: 50px;" @click="like()">
                <img :src="state.likeimage" style="width: 40px; margin-right: 3px;"/>
                <h3 style="margin-left: 10px;">{{state.items.blike}}</h3>
              </v-btn>
            </v-col>
          </v-row>

          <v-row dense>
            <v-col sm="5" style="padding: 10px;" class="col_left">
              <h5>댓글</h5>&nbsp;<h5 style="color: #fca103">{{}}</h5><h5>개</h5>
            </v-col>

            <!-- 토큰이 일치할 때 -->
            <v-col class="col_right">
              <h5 @click="handleUpdate()" style="padding-right: 10px; cursor: pointer;">수정</h5>
              <h5 @click="handleDelete()" style="cursor: pointer;">삭제</h5>
            </v-col>
          </v-row>

          <!-- 댓글창 -->
          <!-- 자기가 남긴 댓글에만 수정 삭제 버튼이 뜨게 -->
          <v-row dense style="background-color: #504ea31d;">
            <v-col style="border-top: 1px solid #CCC; border-bottom: 1px solid #CCC; padding-left: 20px; padding-right: 20px;">

              <!-- 댓글하나 -->
              <v-row dense style="padding-top: 10px; border-bottom: 1px solid #CCC;" v-for="tmp in state.replylist" :key="tmp">
                <v-col>
                  <!-- 댓글작성자 -->
                  <v-row dense>
                    <v-col class="col_left">
                      <h5 style="padding-right: 10px;">{{tmp.rewriter}}</h5> 
                      <h5 style="color: gray;">{{tmp.reregdate}}</h5>
                      <img :src="require('../../assets/img/thumb.png')" @click="like()"
                        style="width: 15px; margin-left: 10px; margin-right: 3px; cursor: pointer;"/>
                      <h5 style="color: gray;">{{tmp.like}}</h5>
                      <h5 style="color: gray; padding-left: 10px; cursor: pointer;">댓글</h5>
                    </v-col>
                  </v-row>

                  <!-- 댓글내용 -->
                  <v-row dense style="padding-right: 10px;">
                    <v-col>
                      <h4 style="padding-left: 10px; padding-right: 10px;">{{tmp.recontent}}</h4>
                    </v-col>
                  </v-row>

                  <!-- 대댓글. 대댓글이 있으면 테두리가 없게 하는게 가능한가? -->
                  <v-row dense style="padding-left: 10px;" v-if="tmp.reparentnumber !== null">
                    <v-col>
                      <v-row dense>
                        <v-col style="display: flex">
                          <img :src="require('../../assets/img/reply.png')" style="margin-right: 10px; width: 17px; height: 17px; transform: scaleX(-1) scaleY(-1); margin-right: 3px;"/>
                          <!-- 댓글작성자 -->
                          <v-row dense>
                            <v-col class="col_left">
                              <h5 style="padding-right: 10px;">{{state.reply.writer1}}</h5> 
                              <h5 style="color: gray;">{{state.reply.date1}}</h5>
                              <img :src="require('../../assets/img/thumb.png')" style="width: 15px; margin-left: 10px; margin-right: 3px; cursor: pointer;"/>
                              <h5 style="color: gray;">{{state.blike}}</h5>
                              <h5 @click="handleUpdate()" style="padding-left: 10px; color: gray; padding-right: 10px; cursor: pointer;">수정</h5>
                              <h5 @click="handleDelete()" style="cursor: pointer; color: gray;">삭제</h5>
                              
                            </v-col>
                          </v-row>
                        </v-col>
                      </v-row>

                      <!-- 댓글내용 -->
                      <v-row dense style="padding-right: 10px;">
                        <v-col>
                          <h4 style="padding-left: 30px;">{{state.items.recontent}}</h4>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>

              <v-row dense>
                <v-col sm="9" style="padding-top: 10px;">
                  <textarea 
                    style="border: 1px solid #CCC; padding: 10px; background-color: white; width: 100%; height: 70px; outline-width: 0; resize: none;"
                    v-model="recontent"
                  ></textarea>
                </v-col>
                
                <v-col sm="1" style="padding: 10px;" class="col_center">
                  <v-btn style="width: 100%; height:69px; border: 1px solid #CCC;" @click="handleReplyInsert"><h4>댓글작성</h4></v-btn>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          
          <v-row dense style="padding-top: 10px; padding-bottom: 20px;">
            <v-col sm="3">
              <router-link to="/blist">
                <v-btn class="col_center"><img :src="require('../../assets/img/list.png')" style="width: 20px; margin-right: 3px;"/><h4>목록</h4></v-btn>
              </router-link>
            </v-col>

            <v-col class="col_right">
              <router-link to="/bwrite">
                <v-btn class="col_center"><img :src="require('../../assets/img/pencil.png')" style="width: 20px; margin-right: 3px;"/><h4>글쓰기</h4></v-btn>
              </router-link>
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
import { reactive } from '@vue/reactivity';
import FooterVue from '../FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import dayjs from 'dayjs';

export default {
  components: { HeaderVue, FooterVue },

  setup () {

    onMounted( async() => {
      await handleData(); 
      date();
    
    })

    const date = () => {
      state.bregdate1 = dayjs(state.items.bregdate).format('YY.MM.DD HH:MM');
    }

    const route = useRoute();
    const router = useRouter();

    const state = reactive({
      bno : route.query.bno,
      btitle: '',
      mid: '',
      bhit: 0,
      blike: 0,
      bcontent: '',
      bregdate: '',
      likeimage : require('../../assets/img/thumb.png'),
      bimageurl : '',
      token  : sessionStorage.getItem("TOKEN"),

      recontent : '',
      reparentnumber : 0,

      replylist: []
    })

    const handleData = async() => {
      const url = `/ROOT/api/board1/selectone?bno=${state.bno}`;
      const headers = {"Content-Type":"application/json",
                      "token" : state.token };
      const response = await axios.get(url, {headers});
      console.log(response.data);
      if(response.data.status === 200){
        state.items = response.data.result;
        console.log(state.items);
      }
    }

    const handleDelete = async() => {
      if (confirm('정말 삭제하시겠습니까?')) {
        const url = `/ROOT/api/board1/delete`;
        const headers = {"Content-Type":"application/json",
                          "token" : state.token };
        const response = await axios.delete(url, {headers, data : {mid : state.mid, bno : state.bno}});
        console.log(response.data);
        if(response.data.status === 200){
          alert('삭제되었습니다.');
          router.push({name:"BoardListVue"})
        }
      }
    }

    const handleUpdate = async() => {
      if (confirm('수정하시겠습니까?')) {
        router.push({name:"BoardUpdateVue", query : {bno : state.bno }})
      }
    }

    // const like = async() => {
    //   const url = `ROOT/reaction/like.json`;
    //   const headers = {"Content-Type":"multipart/form-data"};
    //         const body = new FormData;
    //         body.append("token", state.token);
    //         body.append("mpw",state.pw);
    //         const response = await axios.post(url, body,{headers});
    //         console.log(response.data);
    //         if(response.data.status === 200){
    //             sessionStorage.setItem("TOKEN", response.data.token);
    //             alert('로그인성공');
    //             router.push({path : '/'})

    //         }
    // }

    const replylike = async() => {

    }

    const reply = async() => {

      // const url = `/ROOT/api/creply/board_selectone=bno${state.bno}`;
      // const headers = {"Content-Type":"application/json",
      //                 "token" : state.token };
      // const response = await axios.get(url, {headers});
      // console.log(response.data);
      // if(response.data.status === 200){
      //   state.items = response.data.result;
      //   console.log(state.items);
      // }

    }

    const handleReplyInsert = async() => {
      // const url = `/ROOT/api/creply/board_insert`;
      // const headers = {"Content-Type":"application/json",
      //                 "token" : state.token };
      // const body = new FormData;
      // body.append("token", state.token);
      // body.append("bno",state.bno);
      // body.append("recontent",state.recontent);
      // body.append("reparentnumber",state.reparentnumber);
      // // body.append("reprivate",state.reprivate);
      // const response = await axios.post(url, body,{headers});
      // console.log(response.data);
      // if(response.data.status === 200){
      //   state.items = response.data.result;
      //   console.log(state.items);
      // }

    }

    return { state, date, handleUpdate, handleDelete, replylike, reply, handleReplyInsert}
  }
}
</script>

<style lang="scss" scoped>
  @import '../../assets/css/style';
</style>