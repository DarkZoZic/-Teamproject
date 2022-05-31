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
              <h4 style="color: #787878"></h4>
            </v-col>

            <v-col sm="6" class="col_right1">
              <h5 style="color: #787878" v-if="state.nick.mcname === null">
                <!-- 닉네임 --> 
                {{state.nick.mpnickname}} &nbsp; | &nbsp; 
                조회 {{state.items.bhit}} &nbsp; | &nbsp; 
                <img :src="require('../../assets/img/thumb.png')" style="width: 15px; margin-right: 3px;"/> {{state.rno}}
                &nbsp; | &nbsp; {{state.bregdate1}}
              </h5>

              <h5 style="color: #787878" v-if="state.nick.mpnickname === null">
                <!-- 기업이름 --> 
                {{state.nick.mcname}} &nbsp; | &nbsp; 
                조회 {{state.items.bhit}} &nbsp; | &nbsp; 
                <img :src="require('../../assets/img/thumb.png')" style="width: 15px; margin-right: 3px;"/> {{state.rno}}
                &nbsp; | &nbsp; {{state.bregdate1}}
              </h5>
            </v-col>
          </v-row>

          <v-row dense>
            <v-col class="col_pad20">
              <div v-html="state.items.bcontent"></div>
            </v-col>
          </v-row>

          <v-row dense class="border-b_1_CCC">
            <v-col style="padding: 20px;" class="col_center">
              <v-btn v-if="state.likestatus === false" style="height: 50px;" @click="like()">
                <img :src="state.likeimage" style="width: 40px; margin-right: 3px;"/>
                <h3  style="margin-left: 10px;">{{state.rno}}</h3>
              </v-btn>
              <v-btn v-if="state.likestatus === true" style="background-color: gold; height: 50px;" @click="like()">
                <img :src="state.likeimage" style="width: 40px; margin-right: 3px;"/>
                <h3 style="margin-left: 10px;">{{state.rno}}</h3>
              </v-btn>
            </v-col>
          </v-row>

          <v-row dense>
            <v-col sm="5" style="padding: 10px;" class="col_left">
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
                    
                    <!-- =========================================================================================== -->
                    <!-- <v-col class="col_left">
                      <div v-for="tmp in state.reply" :key="tmp">
                        <h5 style="padding-right: 10px;">{{tmp.mid}}</h5> 
                        <h5 style="padding-right: 10px;">{{tmp.recontent}}</h5> 
                        <h5 style="color: gray;">{{tmp.reregdate}}</h5>
                        <img :src="require('../../assets/img/thumb.png')" @click="like()"
                          style="width: 15px; margin-left: 10px; margin-right: 3px; cursor: pointer;"/>
                        <h5 style="color: gray;">{{tmp.like}}</h5>
                        <h5 style="color: gray; padding-left: 10px; cursor: pointer;">댓글</h5>
                      </div>
                      {{state.reply1}}
                      <textarea rows="6" v-model="state.reply1.recontent" placeholder="댓글내용"></textarea>
                       <v-btn @click="handleReplyInsert1">댓글저장</v-btn>
                    </v-col> -->

                  </v-row>

                  <!-- 닉네임, 날짜 -->
                  <v-row dense>
                    <div v-if="tmp.reparentnumber !== 0" >
                      <img :src="require('../../assets/img/reply.png')" style="margin-top: 5px; margin-right: 10px; width: 17px; height: 17px; transform: scaleX(-1) scaleY(-1); margin-right: 3px;"/>
                    </div>
                    <v-col class="col_left">                      
                      <h5 style="padding-right: 10px;" v-if="state.replynicklist[idx].mcname === null">{{state.replynicklist[idx].mpnickname}} &nbsp; | </h5>
                      <h5 style="padding-right: 10px;" v-if="state.replynicklist[idx].mpnickname === null">{{state.replynicklist[idx].mcname}} &nbsp; | </h5> 
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
                      <!-- <v-row dense style="padding-right: 10px;">
                        <v-col>
                          <h4 style="padding-left: 30px;">{{tmp.recontent}}</h4>
                        </v-col>
                      </v-row> -->
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>

              <v-row dense>
                <v-col sm="11" style="padding-top: 10px;">
                  <textarea  
                    style="border: 1px solid #CCC; padding: 10px; background-color: white; border-radius: 5px; width: 930px; height: 70px; outline-width: 0; resize: none;"
                    v-model="state.reply1.recontent" placeholder="댓글내용">
                  </textarea>
                </v-col>
                
                <v-col sm="1" style="padding: 10px;" class="col_center">
                  <v-btn style="width: 100%; height: 70px; border: 1px solid #CCC;" @click="handleReplyInsert"><h4>댓글작성</h4></v-btn>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          
          <v-row dense style="padding-top: 10px; padding-bottom: 20px;">
            <v-col sm="7" class="col_left">
              <router-link to="/blist">
                <v-btn class="col_center"><img :src="require('../../assets/img/list.png')" style="width: 20px; margin-right: 3px;"/><h4>목록</h4></v-btn>
              </router-link>
              <v-btn @click="handlePage(1)" v-if="state.items.prev>0"><h4>이전글</h4></v-btn>
              <v-btn @click="handlePage(2)" v-if="state.items.next>0"><h4>다음글</h4></v-btn>
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
      await handleReplyView();
      await date();
      await likecount();
      await likestatus();
    })

    const date = () => {
      state.bregdate1 = dayjs(state.items.bregdate).format('YY.MM.DD hh:mm:ss');
    }

    const date1 = (i) => {
      console.log(state.replylist[i].reregdate);
      state.replylist[i].reregdate1 = dayjs(state.replylist[i].reregdate).format('YY.MM.DD hh:mm:ss');
    }

    const route = useRoute();
    const router = useRouter();

    const state = reactive({
      bno       : route.query.bno,
      btitle    : '',
      mid       : '',
      bhit      : 0,
      blike     : 0,
      bcontent  : '',
      bregdate  : '',
      likeimage : require('../../assets/img/thumb.png'),
      bimageurl : '',
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
      const url      = `/ROOT/api/board1/selectone?bno=${state.bno}`;
      const headers  = {"Content-Type": "application/json", "token": state.token };
      const response = await axios.get(url, {headers});
      // console.log(response.data);

      if(response.data.status === 200){
        state.items      = response.data.result;
        state.items.prev = response.data.prev;
        state.items.next = response.data.next;
        state.nick       = response.data.nick;
        console.log(state.items);
      }
    }

    //글 삭제
    const handleDelete = async() => {
      if(confirm('삭제하시겠습니까?')){
        const url      = `/ROOT/api/board1/delete1?bno=${state.bno}`;
        const headers  = { "Content-Type": "application/json", "token": state.token };
        const response = await axios.delete(url, { headers: headers, data: {} });
        console.log(response.data);

        if(response.data.status === 200){
          alert('삭제되었습니다.');
          router.push({ name: "BoardListVue" })
        }
      }    
    }

    const handleUpdate = async() => {
      if (confirm('수정하시겠습니까?')) {
        router.push({ name: "BoardUpdateVue", query: { bno: state.bno } })
      }
    }

    // 게시글 좋아요 상태 가져오기
    const likestatus = async() => {
      const url      = `/ROOT/reaction/likeone?bno=${state.bno}`;
      const headers  = { "Content-Type": "application/json", "token": state.token };
      const response = await axios.get(url, {headers});
      console.log("좋아요상태", response.data);
      if(response.data.status === 1){ // 좋아요 누른상태
        console.log(state.likestatus);
        state.likestatus = true;
      }
      else if(response.data.status === 0){ // 좋아요 없음
        console.log(state.likestatus);
        state.likestatus = false;
      }
    }

    // 게시글 좋아요 
    // 1이면 unlike로 0이면 like로
    const like = async() => {
      if( state.likestatus === false){
        const url      = `/ROOT/reaction/like.json`;
        const headers  = { "Content-Type": "application/json", "token": state.token };
        const body     = new FormData();
        body.append("board1", state.bno);
        body.append("member", state.mid1);
        const response = await axios.post(url, body,{headers});
        // console.log(response.data);
        if(response.data.status === 200){ // 좋아요 성공
          // await like();
          await handleData(state.bno);
          await likestatus();
          await likecount();
        }
      }
      else if(state.likestatus === true){
        // 좋아요 취소
        const url      = `/ROOT/reaction/unlike.json?bno=${state.bno}`;
        const headers  = { "Content-Type": "application/json", "token": state.token };
        const response = await axios.delete(url, { headers: headers, data: {} });
        // console.log(response.data);
        if(response.data.status === 200){
          // await like();
          await handleData(state.bno);
          await likestatus();
          await likecount();
        }
      }
    }

    // 게시글 좋아요 개수 조회
    const likecount = async() => {
      const url      = `/ROOT/reaction/likelist.json?bno=${state.bno}`;
      const headers  = { "Content-Type": "application/json" };
      const response = await axios.get(url, {headers});
      console.log(response.data);
      if(response.data.status === 200){
        state.rno = response.data.result;
        await handleData(state.bno);
      }
    }

    // 댓글 좋아요 등록
    const replylike = async() => {
      const url     = `/ROOT/reaction/relike.json`;
      const headers = { "Content-Type": "application/json", "token": state.token };
      const body    = new FormData;
      body.append("creply", state.reply1.renumber);
      body.append("member", state.mid1);
      const response = await axios.post(url, body,{headers});
      // console.log(response.data);

      if(response.data.status === 200){ // 좋아요 성공
        await like();
        // await handleData(state.bno);
      }

    }

    // 댓글조회
    const handleReplyView = async() => {
      const url      = `/ROOT/api/creply/board_selectone?bno=${state.bno}`;
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
      const url     = `/ROOT/api/creply/board_insert`;
      const headers = { "Content-Type":"application/json", "token": state.token };
      const body    = {
        mid           : state.mid,
        board1        : { bno: state.bno },
        recontent     : state.reply1.recontent,
        reparentnumber: state.reply1.reparentnumber,
        reprivate     : state.reply1.reprivate,
      };
      const response = await axios.post(url, body,{headers});
      console.log(response.data);

      if(response.data.status === 200){
        alert('댓글 등록 완료');
        // await handleData(state.bno);
        await handleReplyView(state.bno);
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
      const url     = `/ROOT/api/creply/board_insert`;
      const headers = { "Content-Type": "application/json", "token" : state.token };
      const body = {
        mid : state.mid,
        board1        : { bno :state.bno },
        recontent     : state.reply1.rerecontent,
        reparentnumber: no,
        reprivate     : state.reply1.reprivate,
      };
      const response = await axios.post(url, body,{headers});
      console.log(response.data);
      if(response.data.status === 200){
        alert('댓글 등록 완료');
      
        await handleData(state.bno);
        await handleReplyView(state.bno);
        state.reply1.rerecontent     = "";
        state.reply1.clickReply[idx] = false;
      }
    }

    // 댓글 수정 handleReUpdate
    const handleReUpdate = async(no) => {
      // const url = `/ROOT/api/creply/board_update`;
      // const headers = {"Content-Type":"application/json",
      //                 "token" : state.token };
      // const body = {
      //   mid : state.mid,
      //   board1 : {bno : state.bno },
      //   recontent : state.reply1.recontent,
      //   reparentnumber : no,
      //   reprivate : state.reply1.reprivate,
      // };
      // const response = await axios.post(url, body,{headers});
      // console.log(response.data);
      // if(response.data.status === 200){
      //   alert('댓글 등록 완료');
        // 댓글 그대로 남아있음. 다시 새로고침 해야 함!!!!

        // await handleData(state.bno);
        // await handleReplyView(state.bno);

        // await handleReplyView(state.bno);
        // this.router.go(this.router.currentRoute);

        // this.router.push(this.router.currentRoute);
        // state.items = response.data.result;
        // console.log(state.items);
      // }
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

      // const url = `/ROOT/api/creply/board_update`;
      // const headers = {
      //     "Content-Type" : "application/json",
      //     "token"        : state.token,
      // };
      // const body= {
      //   mid : state.reply1.mid,
      //   recontent : state.reply1.recontent,
      //   reprivate : state.reply1.reprivate,
      //   reupdatedate : state.reply1.reupdatedate,
      //    수정시에는 수정날짜 옆에 (수정됨) 보이도록
      // };
      // const response = await axios.put(url, body, {headers});
      // console.log(response.data);
      // if(response.data.status === 200){
      //     alert('수정완료');
      //     // 원래 게시글로 돌아가야 함
      //     router.push({name:'BoardListVue'});
      // }
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

    // 이전글, 다음글 메소드 생성
    const handlePage = async(idx) => {
      if(idx === 1){ // 이전글
        const url      = `/ROOT/api/board1/prev?bno=${state.bno}`;
        const headers  = { "Content-Type": "application/json", "token": state.token };
        const response = await axios.get(url, {headers});
        console.log(response.data);
        
        if(response.data.status === 200){
          router.push({ name: "BoardContentVue", query: { bno: state.items.prev } });
          state.bno = state.items.prev;
          await handleData();
          await handleReplyView();
        }
      }

      else if(idx === 2){ // 다음글
        const url      = `/ROOT/api/board1/next?bno=${state.bno}`;
        const headers  = { "Content-Type": "application/json", "token": state.token };
        const response = await axios.get(url, {headers});
        console.log(response.data);

        if(response.data.status === 200){
          router.push({ name: "BoardContentVue", query: { bno: state.items.next } });
          state.bno = state.items.next;
          await handleData();
          await handleReplyView();
        }
      }
    }

    return { 
      state, 
      date1, 
      date, 
      like, 
      handleUpdate, 
      handleDelete, 
      handleReplyInsert, 
      handleReplyAdd, 
      handlePage, 
      handleReplyUpdate, 
      handleReplyDelete,
      handleReUpdate, 
      clickReply,
      replylike, 
    }
  }
}
</script>

<style lang="scss" scoped>
  @import '../../assets/css/style';
</style>