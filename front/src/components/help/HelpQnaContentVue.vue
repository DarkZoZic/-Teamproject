<template>
<div v-if="state.items">
<HeaderVue style="height: 220px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;" >      
      <v-row dense>
        <v-col sm="2"></v-col>
        
        <!-- 메인 -->
        <v-col sm="8">
          <v-row dense style="border-bottom: 1px solid #CCC;">
            <v-col>
              <h5><router-link to="/">홈</router-link> > <router-link to="/h">고객센터</router-link> > 질문및답변</h5>
            </v-col>
          </v-row>

          <v-row dense>
            <v-col sm="6" style="padding-top: 23px; padding-left: 15px;">
              <h2>{{state.items.qtitle}}</h2>
            </v-col>
          </v-row>

          <!-- 상단메뉴 -->
          <v-row dense style="padding-bottom: 10px; border-bottom: 1px solid #CCC;">
            <v-col sm="6" style="padding-left: 25px; ">
              <!-- 닉네임 -->
              <h4 style="color: #787878">{{state.nick.mpnickname}}</h4>
            </v-col>

            <v-col sm="6" class="col_right" style="padding-right: 25px;">
              <h5 style="color: #787878">
                조회 {{state.items.qhit}} | {{state.qregdate1}}
              </h5>

              <!-- <h5 style="color: #787878" v-if="state.nick.mpnickname === null"> -->
                <!-- 기업이름 --> 
                <!-- {{state.nick.mcname}} &nbsp; | &nbsp; 
                조회 {{state.items.qhit}} &nbsp; | &nbsp; 
                <img :src="require('../../assets/img/thumb.png')" style="width: 15px; margin-right: 3px;"/> {{state.rno}}
                &nbsp; | &nbsp; {{state.qregdate1}} -->
              <!-- </h5> -->
            </v-col>
          </v-row>

          <v-row dense style="border-bottom: 1px solid #CCC;">
            <v-col style="padding: 20px;">
              <div v-html="state.items.qcontent"></div>
            </v-col>
          </v-row>

          <v-row dense>
            <v-col style="display: flex; padding-top: 10px; padding-left: 10px;" class="col_left">
              <h5>댓글</h5>&nbsp;<h5 style="color: #fca103">{{state.replylist.length}}</h5><h5>개</h5>
            </v-col>

            <!-- 글 수정, 삭제 : 아이디가 일치할 때 -->
            <v-col class="col_right">
              <div v-if="state.items.member.mid === state.mid1">
                <h5 @click="handleUpdate()" style="padding-right: 10px; cursor: pointer;">수정</h5>
              </div>

              <div v-if="state.items.member.mid === state.mid1">
                <h5 @click="handleDelete()" style="cursor: pointer;">삭제</h5>
              </div>
            </v-col>
          </v-row>

          <!-- 내용 -->
          <v-row dense>
            <v-col class="row_bwrite11">
              <h2>{{state.recontent}}</h2>
            </v-col>
          </v-row>

          <!-- 댓글창 -->
          <!-- 자기가 남긴 댓글에만 수정 삭제 버튼이 뜨게 -->          
          {{state.reply1.reupdate}}
          {{state.reply1.clickReply}}
          <v-row dense style="background-color: #504ea31d;">
            <v-col style="border-top: 1px solid #CCC; border-bottom: 1px solid #CCC; padding-left: 20px; padding-right: 20px;">

              <!-- 댓글하나 -->
              <v-row dense style="padding-top: 10px; border-bottom: 1px solid #CCC;" v-for="(tmp,idx) in state.replylist" :key="tmp">
                <v-col>
                  <!-- 댓글작성자 -->
                  <v-row dense>
                    <!-- <v-col class="col_left">
                      <h5 style="padding-right: 10px;" v-if="state.replynicklist[idx].mcname === null">{{state.replynicklist[idx].mpnickname}} &nbsp; | </h5>
                      <h5 style="padding-right: 10px;" v-if="state.replynicklist[idx].mpnickname === null">{{state.replynicklist[idx].mcname}} &nbsp; | </h5> 
                      <h5 style="color: gray;">{{tmp.reregdate1}}</h5>
                      <h5 style="color: #676767;">{{state.reply.reupdate[idx]}}</h5>
                      <a><h5 style="color: #676767; padding-left: 10px;">댓글</h5></a>
                    </v-col> -->
                  </v-row>

                   <!-- 닉네임, 날짜 -->
                  <v-row dense>
                    <div v-if="tmp.reparentnumber !== tmp.renumber" >
                      <img :src="require('../../assets/img/reply.png')" style="margin-top: 5px; margin-right: 10px; width: 17px; height: 17px; transform: scaleX(-1) scaleY(-1); margin-right: 3px;"/>
                    </div>
                    <v-col class="col_left">                      
                      <!-- <h5 style="padding-right: 10px;" v-if="state.replynicklist[idx].mcname === null">{{state.replynicklist[idx].mpnickname}} &nbsp; | </h5>
                      <h5 style="padding-right: 10px;" v-if="state.replynicklist[idx].mpnickname === null">{{state.replynicklist[idx].mcname}} &nbsp; | </h5>  -->
                      <h5 style="color: gray;">{{tmp.reregdate1}}</h5>
                      <img :src="require('../../assets/img/thumb.png')" @click="replylike()" style="width: 15px; margin-left: 10px; cursor: pointer; " />
                      <h5 style="color: gray; padding-left: 5px;">{{state.blike}}</h5>
                    </v-col>
                  </v-row>

                    <!-- 댓글내용 -->
                  <v-row dense>
                    <v-col sm="10">
                      <!-- <div style="padding-left: 10px; padding-right: 10px;" >{{tmp.recontent}}</div> -->
                      <div v-if="!state.reply1.reupdate[idx]" style="padding: 10px; border: 1px solid #CCC; border-radius: 5px; height: 70px; width: 900px;" class="collapse multi-collapse-{{id}} show">{{tmp.recontent}}</div>
                      <div v-if="state.reply1.reupdate[idx]" class="col_left">
                        <textarea v-model="tmp.recontent" 
                          style="background-color: white; resize: none; border: 1px solid #CCC; border-radius: 5px; padding: 10px; width: 900px;"></textarea>
                      </div>
                    </v-col>

                     <v-col class="col_center" v-if="state.reply1.reupdate[idx]">
                      <!-- 댓글수정버튼 -->
                      <v-btn style="height: 68px; margin-right: 10px;" @click="handleReplyUpdate(idx)"><h4>취소</h4></v-btn>
                      <v-btn style="height: 68px;" @click="handleReUpdate(idx)"><h4 >수정</h4></v-btn>
                    </v-col>
                  </v-row>

                  <v-row dense style="padding-left: 10px;">
                    <!-- v-if="tmp.reparentnumber !== 0" -->
                    <v-col>
                      <v-row dense>
                        <v-col>
                          <!-- 댓글 수정, 삭제 : 아이디가 일치할 때 -->
                          <v-row dense >
                            <div v-show="tmp.member.mid === state.mid1" style="float:left;">
                              <h5 v-if="!state.reply1.reupdate[idx]" @click="handleReplyUpdate(idx)" style="padding-left: 10px; color: gray; cursor: pointer; float:left;" >수정</h5>
                              <h5 @click="handleReplyDelete(tmp.renumber, idx)" style="padding-left: 10px; color: gray; cursor: pointer; float:left">삭제</h5>
                            </div>
                            <div style="float:left;">
                              <h5 @click="clickReply(idx)" style="color: gray; padding-left: 10px; cursor: pointer;">답댓글</h5>
                            </div>
                          </v-row>
                          
                          <!-- 답댓글 -->
                          <v-row dense v-if="state.reply1.clickReply[idx]">
                            <v-col sm="11" style="padding-top: 10px;">
                              <textarea  
                                style="border: 1px solid #CCC; padding: 10px; background-color: white; border-radius: 5px; width: 930px; height: 70px; outline-width: 0; resize: none;"
                                v-model="state.reply1.rerecontent" placeholder="댓글내용">
                              </textarea>
                            </v-col>

                            <v-col sm="1" style="padding: 10px;" class="col_center">
                              <v-btn style="width: 100%; height: 70px; border: 1px solid #CCC;" @click="handleReplyAdd(tmp.renumber, idx)"><h4>댓글작성</h4></v-btn>
                            </v-col>
                          </v-row>
                        </v-col>
                      </v-row>

                  <!-- 댓글내용 -->
                  <!-- <v-row dense>
                    <v-col style="border-bottom: 1px solid #CCC;">
                      <h4 style="padding-left: 10px;">{{state.reply.content}}</h4>
                    </v-col>
                  </v-row> -->
                </v-col>
              </v-row>

              <!-- 댓글하나 -->
              <!-- <v-row dense style="padding-top: 10px; border-bottom: 1px solid #CCC;">
                <v-col> -->
                  <!-- 댓글작성자 -->
                  <!-- <v-row dense>
                    <v-col class="col_left">
                      <h5 style="padding-right: 10px;">{{state.reply.writer}}</h5> 
                      <h5 style="color: #676767;">{{state.reply.date}}</h5>
                      <a><h5 style="color: #676767; padding-left: 10px;">댓글</h5></a>
                    </v-col>
                  </v-row> -->

                  <!-- 댓글내용 -->
                  <!-- <v-row dense style="padding-right: 10px;">
                    <v-col>
                      <h4 style="padding-left: 10px; padding-right: 10px;">{{state.reply.content}}</h4>
                    </v-col>
                  </v-row> -->

                  <!-- 대댓글. 대댓글이 있으면 테두리가 없게 하는게 가능한가? -->
                  <!-- <v-row dense style="padding-left: 10px;">
                    <v-col>
                      <v-row dense>
                        <v-col style="display: flex">
                          <img :src="require('../../assets/img/reply.png')" style="margin-right: 10px; width: 17px; height: 17px; transform: scaleX(-1) scaleY(-1); margin-right: 3px;"/> -->
                          <!-- 댓글작성자 -->
                          <!-- <v-row dense>
                            <v-col class="col_left">
                              <h5 style="padding-right: 10px;">{{state.replynicklist[idx].writer1}}</h5> 
                              <h5 style="color: #676767;">{{state.reply.date1}}</h5>
                              <a><h5 style="color: #676767; padding-left: 10px;">댓글</h5></a>
                            </v-col>
                          </v-row>
                        </v-col>
                      </v-row> -->

                      <!-- 댓글내용 -->
                      <!-- <v-row dense style="padding-right: 10px;">
                        <v-col>
                          <h4 style="padding-left: 30px;">{{state.reply.content1}}</h4>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row> -->

              <!-- 댓글하나 -->
              <!-- <v-row dense style="padding-top: 10px; border-bottom: 1px solid #CCC;">
                <v-col> -->
                  <!-- 댓글작성자 -->
                  <!-- <v-row dense>
                    <v-col class="col_left">
                      <h5 style="padding-right: 10px;">{{state.reply.writer}}</h5> 
                      <h5 style="color: #676767;">{{state.reply.date}}</h5>
                      <a><h5 style="color: #676767; padding-left: 10px;">댓글</h5></a>
                    </v-col>
                  </v-row> -->

                  <!-- 댓글내용 -->
                  <!-- <v-row dense style="padding-right: 10px;">
                    <v-col>
                      <h4 style="padding-left: 10px;">{{state.reply.content}}</h4>
                    </v-col>
                  </v-row>  -->
                </v-col>
              </v-row>

              <v-row dense="">
                <v-col sm="9" style="padding-top: 10px;">
                  <textarea 
                    style="border: 1px solid #CCC; padding: 10px; background-color: white; width: 100%; height: 70px; outline-width: 0; resize: none;"
                    v-model="state.reply1.recontent" placeholder="댓글내용">
                  </textarea>
                </v-col>
                
                <v-col sm="1" style="padding: 10px;" class="col_center">
                  <v-btn style="width: 100%; height:69px; border: 1px solid #CCC;"  @click="handleReplyInsert()"><h4>댓글작성</h4></v-btn>
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
              <router-link to="/hqwrite">
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
import FooterVue    from '../FooterVue.vue';
import HeaderVue    from '../HeaderVue.vue';
import { onMounted } from '@vue/runtime-core';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import dayjs from 'dayjs';

export default {
  components: { HeaderVue, FooterVue },
  setup () {

    onMounted( async() => {
      await handleData(); 
      await handleReplyView();
      await date();
 
    })

    const date = () => {
      state.qregdate1 = dayjs(state.items.qregdate).format('YY.MM.DD hh:mm:ss');
    }

    const date1 = (i) => {
      console.log(state.replylist[i].reregdate);
      state.replylist[i].reregdate1 = dayjs(state.replylist[i].reregdate).format('YY.MM.DD hh:mm:ss');
    }

    const route = useRoute();
    const router = useRouter();

    const state = reactive({
      qno : route.query.qno,
      qtitle    : '',
      mid       : '',
      qhit      : 0,
      qlike     : 0,
      qcontent  : '',
      qregdate  : '',
      likeimage : require('../../assets/img/thumb.png'),
      token     : sessionStorage.getItem("TOKEN"),
      mid1      : sessionStorage.getItem("MID"),
      liked     : true,
      items     : '',
      item      : '',
      likestatus: false,
      rno       : [],
      replylist : [],
      page      : 1,
      nick      : '',
      replynicklist : [],

      reply1   : {
        mid           : '',
        renumber      : 0,
        recontent     : '',
        reparentnumber: 0,
        reprivate     : 'n',
        reregdate     : '',
        reregdate1    : '',
        reupdatedate  : '',
        reupdate      : [],
        clickReply    : [],
        rerecontent   : '',
      },
    })

    

    const handleData = async() => {
      const url      = `/ROOT/api/qna/selectone?qno=${state.qno}`;
      const headers  = {"Content-Type": "application/json", "token": state.token };
      const response = await axios.get(url, {headers});
      // console.log(response.data);

      if(response.data.status === 200){
        state.items      = response.data.result;
        state.nick       = response.data.nick;
        console.log(state.items);
      }
    }

    // 글삭제
     const handleDelete = async() => {
      if(confirm('삭제하시겠습니까?')){
        const url      = `/ROOT/api/qna/delete?qno=${state.qno}`;
        const headers  = { "Content-Type": "application/json", "token": state.token };
        const response = await axios.delete(url, { headers: headers, data: {} });
        console.log(response.data);

        if(response.data.status === 200){
          alert('삭제되었습니다.');
          router.push({ name: "HelpQnaVue" })
        }
      }    
    }

    const handleUpdate = async() => {
      if (confirm('수정하시겠습니까?')) {
        router.push({ name: "HelpQnaUpdateVue", query: { qno: state.qno } })
      }
    }

    // 댓글조회
    const handleReplyView = async() => {
      const url      = `/ROOT/api/creply/qna_selectone?qno=${state.qno}`;
      const headers  = { "Content-Type": "application/json", "token": state.token };
      const response = await axios.get(url, { headers });
      console.log(response.data);

      if(response.data.status === 200){
        state.replylist     = response.data.result;
        state.replynicklist = response.data.replynicklist;
        console.log(state.replylist)

        for(i = 0; i < state.replylist.length; i++){
          state.reply1.reupdate.push(false);
          state.reply1.clickReply.push(false);
        }
        // state.page = response.data.result.page;

        // for(let i=0; i<0; i++){
        //   state.replylist[i] = state.
        // }

        // state.items = response.data.result;
        // console.log(state.items);
        // state.reply = response.data.result;
      }

      // handledata가 출력되고 나서 날짜
      for(var i = 0; i<state.replylist.length; i++){
        date1(i);
      }
    }

    // 댓글 등록하기
    const handleReplyInsert = async() => {
      const url     = `/ROOT/api/creply/qna_insert`;
      const headers = { "Content-Type":"application/json", "token": state.token };
      const body    = {
        mid           : state.mid,
        qna        : { qno: state.qno },
        recontent     : state.reply1.recontent,
        // reparentnumber: 0,
        reprivate     : state.reply1.reprivate,
      };
      const response = await axios.post(url, body,{headers});
      console.log(response.data);

      if(response.data.status === 200){
        alert('댓글 등록 완료');
        // await handleData(state.bno);
        await handleReplyView(state.qno);
        state.reply1.recontent = "";

        // await handleReplyView(state.bno);
        // this.router.go(this.router.currentRoute);

        // this.router.push(this.router.currentRoute);
        // state.items = response.data.result;
        // console.log(state.items);
      }
    }

     // 답댓글 버튼 클릭
    const clickReply = (idx) => {
      if(state.reply1.clickReply[idx] == false) {
        state.reply1.clickReply[idx] = true;
      } 
      else if(state.reply1.clickReply[idx] == true){
        state.reply1.clickReply[idx] = false;
      }
    }

     // 답댓글 등록 
    const handleReplyAdd = async(no, idx) => {
      const url     = `/ROOT/api/creply/qna_insert`;
      const headers = { "Content-Type": "application/json", "token" : state.token };
      const body = {
        mid : state.mid,
        qno        : { qno :state.qno },
        recontent     : state.reply1.rerecontent,
        reparentnumber: no,
        reprivate     : state.reply1.reprivate,
      };
      const response = await axios.post(url, body,{headers});
      console.log(response.data);
      if(response.data.status === 200){
        alert('댓글 등록 완료');
      
        await handleData(state.qno);
        await handleReplyView(state.qno);
        state.reply1.rerecontent     = "";
        state.reply1.clickReply[idx] = false;
      }
    }

    // 댓글 수정 버튼 (태그)
    const handleReplyUpdate = async(idx) => {
      console.log(state.reply1.reupdate[idx]);
      if(state.reply1.reupdate[idx] == false) {
        state.reply1.reupdate[idx] = true;
      }
      else if(state.reply1.reupdate[idx] == true) {
        state.reply1.reupdate[idx] = false;
      }

    }

    // 댓글 삭제
    const handleReplyDelete = async(no) => {
      if(confirm('삭제하시겠습니까?')){
        const url      = `/ROOT/api/creply/board_delete?renumber=${no}`;
        const headers  = { "Content-Type": "application/json", "token": state.token };
        const response = await axios.delete(url, { headers: headers, data: {} });
        console.log(response.data);

        if(response.data.status === 200){
            alert('삭제되었습니다.');
            handleReplyView();
            router.push({ name: "BoardContentVue", query: { bno: state.bno } })
        }
      }
    }


    return { 
      state, 
      date1, 
      date, 
    
     
      handleDelete, 
      handleUpdate,
      handleReplyInsert, 
      handleReplyAdd, 
      // handlePage, 
      handleReplyUpdate, 
      handleReplyDelete,
      // handleReUpdate, 
      clickReply,
     }
  }
}
</script>

<style lang="scss" scoped>

</style>