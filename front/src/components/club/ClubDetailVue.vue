<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <v-col sm="2"></v-col>
                
                <v-col sm="8">
                    <v-row dense="" style="padding-bottom: 4px;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > <router-link to="/cdetail">클럽목록</router-link> > 상세정보</h5>
                        </v-col>                      
                        <v-col class="col_right">
                            <h5>등록일: {{state.writeDate}}, 수정일: {{state.updateDate}}</h5>
                        </v-col>
                    </v-row>

                    <v-row dense class="club_detail_box2" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="7">
                            <v-row dense="">
                                <v-col sm="6">
                                    <h4>{{state.clubname}}</h4>
                                </v-col>
                                <v-col sm="6" class="col_right">
                                    <h4>({{state.now}}/{{state.limit}})</h4>
                                </v-col>                                
                            </v-row>
                            <v-row dense="">
                                <v-col>
                                    <h2>{{state.title}}</h2>
                                </v-col>
                            </v-row>
                        </v-col>

                        <v-col sm="3" class="col_center">
                            <img :src="require('../../assets/img/club_logo.png')" style="width: 100%"/>
                        </v-col>

                        <v-col sm="1" style="vertical-align: middle;">
                            <router-link target="_blank" to="/chome" >
                                <v-btn style="height: 100%; width: 100%;">
                                    <img :src="require(`../../assets/img/home_icon.png`)" style="width: 40px"/>
                                </v-btn>
                            </router-link>
                        </v-col>

                        <v-col sm="1" style="vertical-align: middle;">
                            <v-btn style="height: 100%; width: 100%;" id="like" @click="changeheart()">
                                <img :src="require(`../../assets/img/${state.imgName}.png`)" style="width: 40px"/>
                            </v-btn>
                        </v-col>
                    </v-row>

                    <v-row dense class="club_detail_box1" style="border: 3px solid;">
                        <v-row dense>
                            <!-- 왼쪽 -->
                            <v-col sm="8" style="padding: 10px;">
                                <v-row dense style="padding-bottom: 10px; border-bottom: 1px solid #CCC;">
                                    <v-col sm="6">
                                        <v-carousel cycle hide-delimiters show-arrows="hover" style="height:120px;"> 
                                            <v-carousel-item
                                            v-for="(item,i) in items"
                                            :key="i"
                                            :src="item.src"
                                            cover                                            
                                            ></v-carousel-item>
                                        </v-carousel>
                                    </v-col>

                                    <v-col sm="3">
                                        <v-row dense>
                                            <v-col class="col_center" style="margin-top: 10%; padding-left: 10px;">
                                                <img :src="require('../../assets/img/calendar_icon.png')" style="width: 50px;"/>
                                            </v-col>
                                        </v-row>
                                        <v-row dense>
                                            <v-col class="col_center" style="padding-left: 10px;">
                                                <h4>{{state.date}}</h4>
                                            </v-col>
                                        </v-row>
                                    </v-col>

                                    <v-col sm="3">
                                        <v-row dense>
                                            <v-col class="col_center" style="margin-top: 10%; padding-right: 10px;">
                                                <img :src="require('../../assets/img/clock_icon.png')" style="width: 50px;"/>
                                            </v-col>
                                        </v-row>
                                        <v-row dense>
                                            <v-col class="col_center" style="padding-right: 10px;">
                                                <h4>{{state.time}}</h4>
                                            </v-col>
                                        </v-row>
                                    </v-col>
                                </v-row>

                                <v-row dense>
                                    <v-col style="padding: 20px;">
                                        <h2>모집조건</h2>
                                    </v-col>
                                </v-row>

                                <v-row dense style="padding-left: 20px;">
                                    <v-col sm="2">
                                        <h4 style="color: #787878">성별</h4>
                                        <h4 style="color: #787878; margin-top: 10px;">연령</h4>
                                        <h4 style="color: #787878; margin-top: 10px;">모집인원</h4>
                                    </v-col>

                                    <v-col sm="10">
                                        <h4>{{state.gender}}</h4>
                                        <h4 style="margin-top: 10px;">{{state.age}}</h4>
                                        <h4 style="margin-top: 10px;">{{state.howmany}}명</h4>
                                    </v-col>
                                </v-row>
                            </v-col>

                            <!-- 오른쪽 -->
                            <v-col sm="4" style="height: 100%; border-left: 1px solid #CCC; padding: 10px;">
                                <v-row dense>
                                    <v-col style="border-bottom: 1px solid #CCC; padding-bottom: 10px;">
                                        <router-link to="/crequest">
                                            <v-btn style="width: 100%; height: 120px;"><h2>지원하기</h2></v-btn>
                                        </router-link>
                                        <h3 style="padding-top: 10px; padding-left: 10px;">모집마감일</h3>
                                        <h4 style="padding-left: 15px;">{{state.writeDate}} ~ {{state.endDate}}</h4>
                                    </v-col>
                                </v-row>
                                <v-row dense style="padding: 10px;" >
                                    <v-col>
                                        <h3>클럽담당자정보</h3>
                                    </v-col>
                                </v-row>
                                <v-row dense>
                                    <v-col sm="3" style="padding-left: 15px;"> 
                                        <v-row dense>
                                            <v-col>
                                                <h5 style="color: #787878">담당자명</h5>
                                                <h5 style="color: #787878">연락처</h5>
                                                <h5 style="color: #787878">이메일</h5>
                                            </v-col>
                                        </v-row>
                                    </v-col>
                                    <v-col sm="9">
                                        <v-row dense>
                                            <v-col>
                                                <h5>{{state.manager.name}}</h5>
                                                <h5>{{state.manager.number}}</h5>
                                                <h5>{{state.manager.email}}</h5>
                                            </v-col>
                                        </v-row>
                                    </v-col>
                                </v-row>
                            </v-col>
                        </v-row>
                    </v-row>

                    <!-- 장소정보 -->
                    <v-row dense="" style="margin-top: 20px; padding: 20px; border: 3px solid;">
                        <v-col style="padding-left: 22px;">
                            <v-row dense>
                                <h2>장소정보</h2>
                            </v-row>

                            <v-row dense style="padding-left: 20px; margin-top: 10px;">
                                <v-col sm="1">
                                    <h4 style="color: #787878">장소</h4>
                                    <h4 style="color: #787878; margin-top: 10px;">주소</h4>
                                    <h4 style="color: #787878; margin-top: 10px;">지도</h4>
                                </v-col>

                                <v-col sm="11">
                                    <h4>{{state.addrname}}</h4>
                                    <h4 style="margin-top: 10px;">{{state.addr}}</h4>
                                    <MapVue style="margin-top: 10px; width: 800px; border: 1px solid #CCC;"></MapVue>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>

                    <!-- 상세모집내용 -->
                    <v-row dense style="margin-top: 20px; padding: 20px; border: 3px solid;">
                        <v-col style="padding-left: 22px;">
                            <v-row dense>
                                <h2>상세내용</h2>
                            </v-row>

                            <v-row dense style="padding-left: 20px; margin-top: 10px; padding-right: 32px;">
                                <v-col>
                                    <img :src="require('../../assets/img/mozip.jpg')" style="width: 100%;"/>
                                </v-col>
                            </v-row>
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
import MapVue       from '../MapVue.vue';
import HeaderVue    from '../HeaderVue.vue';

export default {
    components: { HeaderVue, FooterVue, MapVue },
    setup () {
        const state = reactive({
            writeDate: '2022년 5월 2일',
            updateDate: '2022년 5월 5일',
            endDate: '2022년 5월 27일',
            clubname: '삥뽕탁구클럽',
            title: '함께 탁구 칠 삥뽕러 모두 모여랏! XD',
            now: 10,
            limit: 50,
            date: '주말',
            time: '종일',
            gender: '성별무관',
            age: '20세 이상(2003년생 ~)',
            howmany: '0',
            addrname: '성찬미탁구클럽',
            addr: '부산광역시 부산진구 동평로 221',
            imgName: 'heart',

            manager: {
                name: '김이박',
                number: '010-4444-78941',
                email: 'a@c.com'
            }
        });

        const changeheart = async() => {
            if (state.imgName === 'heart') {
                state.imgName = 'heart1'
            } else {
                state.imgName = 'heart'
            }
        };
        
        return { state, changeheart }
    },
    data () {
        return {
            items: [
            {
                src: 'http://news.samsungdisplay.com/wp-content/uploads/2017/09/%EB%8F%84%EB%B9%84%EB%9D%BC-11.png',
            },
            {
                src: 'https://www.sonohotelsresorts.com/upload/image/faci/dmFaci_201607131119187730',
            },
            ],
        }
    },
}
</script>

<style lang="scss" scoped>

</style>