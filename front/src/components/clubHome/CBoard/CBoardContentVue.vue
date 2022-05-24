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
              <h5><router-link :to="{name:'CHomeVue', query :{cno : state.cno}}">클럽홈</router-link> > <router-link :to="{name:'CBoardListVue', query :{cno : state.cno}}">{{state.boardname}}</router-link> > 글읽기</h5>
            </v-col>
          </v-row>

          <v-row dense>
            <v-col class="row_bwrite1">
              <h2>{{state.board.cbtitle}}</h2>
            </v-col>
          </v-row>

          <!-- 상단메뉴 -->
          <v-row dense class="row_bwrite2">
            <v-col sm="6" class="col_pad-l25">
              <h4 style="color: #787878">{{state.board.mid}}</h4>
            </v-col>

            <v-col sm="6" class="col_right1">
              <h5 style="color: #787878">
                조회 {{state.board.cbhit}} &nbsp; | &nbsp; 
                <img :src="require('../../../assets/img/thumb.png')" style="width: 15px; margin-right: 3px;"/> {{state.like}}
                &nbsp; | &nbsp; {{state.board.like}}
              </h5>
            </v-col>
          </v-row>

          <img :src="state.board.cbimageurl" style="width:200px; margin: 10px; padding: 5px; border: 1px solid #CCC;" />

          <v-row dense>
            <v-col class="col_pad20">
              {{state.board.cbcontent}}
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

              <v-row dense style="padding-top: 10px; border-bottom: 1px solid #CCC;" v-for="tmp in state.reply" :key="tmp">
                <v-col>
                  <!-- 댓글작성자 -->
                  <v-row dense>
                    <v-col class="col_left">
                      <h5 style="padding-right: 10px;">{{tmp.writer}}</h5> 
                      <h5 style="color: #676767;">{{tmp.reregdate}}</h5>
                      <a><img :src="require('../../../assets/img/thumb.png')" style="width: 15px; margin-left: 10px; margin-right: 3px;"/></a>
                      <h5 style="color: #676767;">{{tmp.like}}</h5>
                      <a><h5 style="color: #676767; padding-left: 10px;">댓글</h5></a>
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
                          <img :src="require('../../../assets/img/reply.png')" style="margin-right: 10px; width: 17px; height: 17px; transform: scaleX(-1) scaleY(-1); margin-right: 3px;"/>
                          
                          <v-row dense>
                            <v-col class="col_left">
                              <h5 style="padding-right: 10px;">{{state.reply.writer1}}</h5> 
                              <h5 style="color: #676767;">{{state.reply.date1}}</h5>
                              <a><img :src="require('../../../assets/img/thumb.png')" style="width: 15px; margin-left: 10px; margin-right: 3px;"/></a>
                              <h5 style="color: #676767;">{{state.like}}</h5>

                              <a><h5 style="color: #676767; padding-left: 10px;">댓글</h5></a>
                            </v-col>
                          </v-row>
                        </v-col>
                      </v-row>

                      <v-row dense style="padding-right: 10px;">
                        <v-col>
                          <h4 style="padding-left: 30px;">{{state.reply.content}}</h4>
                        </v-col>
                      </v-row>
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
              <router-link :to="{name:'CBoardListVue', query :{cno : state.cno}}">
                <v-btn class="col_center"><img :src="require('../../../assets/img/list.png')" style="width: 20px; margin-right: 3px;"/><h4>목록</h4></v-btn>
              </router-link>
            </v-col>

            <v-col class="col_right">
              <router-link :to="{name:'CBoardWriteVue', query :{cno : state.cno}}">
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
      cbno : route.query.cbno,
      cno : route.query.cno,
      board : '',
      imageurl : '',
      boardname: '클럽게시판',
      replycontent: '',
      token : sessionStorage.getItem("TOKEN"),

      reply: [],
      rereply : []
    })

    const content = async() =>
    {
      const url = `/ROOT/api/clubboard/updatehit?cbno=${state.cbno}`;
      const headers = {"Content-Type" : "application/json", "token" : state.token};
      const body = {cbno : state.cbno};
      const response = await axios.post(url, body, {headers});
      // console.log(response.data);
      if(response.data.status === 200)
      {
        const url = `/ROOT/api/clubboard/select?cbno=${state.cbno}&cno=${state.cno}`;
        const headers = {"Content-Type":"application/json", "token" : state.token};
        
        const response = await axios.get(url, {headers});
        console.log(response.data.result);
        if(response.data.status === 200)
        {
          state.board = response.data.result.clubboard;
          state.reply = response.data.result.replylist;
          state.imageurl = response.data.result.clubboard.cbimageurl;
        }
      }
    }

    const like = async() => {

    }

    const replylike = async() => {

    }

    const insertreply = async() => {
      const url = `/ROOT/api/clubboard/insertreply`;
      const headers = {"Content-Type":"application/json", "token" : state.token};
      const body = 
      {
        cbno : state.cbno,
        recontent : state.replycontent
      };
      const response = await axios.post(url, body, {headers});
      console.log(response.data);
      if(response.data.status === 200)
      {
        router.push({name:'CBoardContentVue', query:{cbno:state.cbno, cno:state.cno}});
      }
    }

    onMounted(() =>
    {
      content();
    });

    return { state, like, replylike, insertreply }
  }
}
</script>

<style lang="scss" scoped>
  @import '../../../assets/css/style';
</style>