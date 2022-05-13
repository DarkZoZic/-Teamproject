<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <!-- 사이드 -->
                <v-col md="2"></v-col>
                
                <!-- 메인 -->
                <v-col md="8">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/">홈</router-link> > <router-link to="/clist">클럽</router-link> > 클럽등록</h5>
                        </v-col>
                    </v-row>
                    
                    <v-row dense class="row_bwrite2" style="padding-top: 20px; padding-bottom: 15px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>클럽등록</h2>
                        </v-col>
                    </v-row>

                    <v-row dense style="padding-top: 20px;">
                        <v-col sm="1" ></v-col>

                        <v-col style="display: flex; align-items: center;">
                            <v-expansion-panels style="width:100%">
                                <v-form v-model="valid" style="width:100%">
                                    <!-- 클럽명 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col style="height: 80px;">
                                                <v-text-field
                                                label="클럽명"
                                                v-model="state.number"
                                                variant="plain"
                                                hint="10자 이하로 입력하세요"                      
                                                :rules="numberRules"
                                                :counter="10"                      
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <!-- 설명글 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col style="height: 200px;">
                                                <v-textarea
                                                solo
                                                v-model="state.desc"
                                                variant="plain"
                                                label="설명글"
                                                density="compact"
                                                hint="클럽 설명을 입력하세요"
                                                ></v-textarea>                                            
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <v-expansion-panel>
                                        <v-row dense>
                                            <v-col style="padding: 20px;">
                                                <v-row dense>
                                                    <v-col sm="6" style="border-right: 1px solid #ccc;" class="col_center"><h4>성별</h4></v-col>
                                                    <v-col sm="6" class="col_center"><h4>연령</h4></v-col>
                                                </v-row>

                                                <v-row dense style="border-top: 1px solid #CCC; ">
                                                    <v-col sm="6" style="border-right: 1px solid #CCC;">
                                                        <v-row dense style="padding-top: 10px;">
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.gender" class="club_list_chk" value="성별무관"><h4>성별무관</h4>
                                                                <input type="checkbox" v-model="state.gender" class="club_list_chk" value="남"><h4>남</h4>
                                                                <input type="checkbox" v-model="state.gender" class="club_list_chk" value="여"><h4>여</h4>
                                                            </v-col>
                                                        </v-row>
                                                    </v-col>

                                                    <v-col sm="6">
                                                        <v-row dense style="padding-top: 10px;">
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="10대"><h4>10대</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="20대"><h4>20대</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="30대"><h4>30대</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="40대"><h4>40대</h4>
                                                            </v-col>
                                                        </v-row>

                                                        <v-row dense>
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="50대"><h4>50대</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="60대 이상"><h4>60대 이상</h4>
                                                                <input type="checkbox" v-model="state.age" class="club_list_chk" value="성인만"><h4>성인만</h4>
                                                            </v-col>
                                                        </v-row>
                                                    </v-col>
                                                </v-row>
                                                <v-row dense>
                                                    <v-col class="col_center">
                                                        <v-btn class="club_list_btn" @click="reset()"><h4>초기화</h4></v-btn>
                                                    </v-col>
                                                </v-row>                                                
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <!--  -->
                                    <v-expansion-panel>
                                        <v-row dense>
                                            <v-col style="padding: 20px;">
                                                <v-row dense>
                                                    <v-col sm="6" style="border-right: 1px solid #ccc;" class="col_center"><h4>요일</h4></v-col>
                                                    <v-col sm="6" class="col_center"><h4>시간</h4></v-col>
                                                </v-row>

                                                <v-row dense style="border-top: 1px solid #CCC; ">
                                                    <v-col sm="6" style="border-right: 1px solid #CCC;">
                                                        <v-row dense style="padding-top: 10px;">
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="평일전체"><h4>평일전체</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="주말"><h4>주말</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="토"><h4>토</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="일"><h4>일</h4>
                                                            </v-col>
                                                        </v-row>

                                                        <v-row dense>
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="월"><h4>월</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="화"><h4>화</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="수"><h4>수</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="목"><h4>목</h4>
                                                                <input type="checkbox" v-model="state.datechk" class="club_list_chk" value="금"><h4>금</h4>
                                                            </v-col>
                                                        </v-row>
                                                    </v-col>

                                                    <v-col sm="6">
                                                        <v-row dense style="padding-top: 10px;">
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="종일"><h4>종일</h4>
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="오전"><h4>오전</h4>
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="오후"><h4>오후</h4>
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="저녁"><h4>저녁</h4>
                                                            </v-col>
                                                        </v-row>
                                                        
                                                        <v-row dense>
                                                            <v-col class="col_center" style="vertical-align: middle;">
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="새벽"><h4>새벽</h4>
                                                                <input type="checkbox" v-model="state.timechk" class="club_list_chk" value="상관없음"><h4>상관없음</h4>
                                                            </v-col>
                                                        </v-row>
                                                    </v-col>
                                                    <v-row dense>
                                                        <v-col class="col_center">
                                                            <v-btn class="club_list_btn" @click="reset()"><h4>초기화</h4></v-btn>
                                                        </v-col>
                                                    </v-row>
                                                </v-row>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>                                    

                                    <!-- 주소 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col sm="10" style="height: 80px;">
                                                <v-text-field
                                                label="장소명"
                                                v-model="state.area"
                                                variant="plain"
                                                hint="장소 이름을 입력하세요"
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>

                                            <v-col sm="2">
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <!-- 주소 -->
                                            <v-col sm="10" style="height: 80px;">
                                                <v-text-field
                                                id="address"
                                                label="주소"
                                                v-model="state.address"
                                                variant="plain"
                                                :rules="nameRules"
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>

                                            <!-- 우편번호찾기버튼 -->
                                            <v-col sm="2">
                                                <v-btn @click="post" style="width: 100%; height:40px;">
                                                <h4>우편번호찾기</h4>
                                                </v-btn>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <!-- 상세주소, 우편번호 -->
                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col sm="8" style="height: 80px;">
                                                <v-text-field
                                                label="상세주소"
                                                v-model="state.detailAddress"
                                                id="detailAddress"
                                                variant="plain"
                                                :rules="nameRules"
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>

                                            <v-col sm="4" style="height: 80px;">
                                                <v-text-field
                                                label="우편번호"
                                                v-model="state.postcode"
                                                id="postcode"
                                                variant="plain"
                                                :rules="nameRules"
                                                density="compact"
                                                required
                                                ></v-text-field>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>

                                    <v-expansion-panel class="panel">
                                        <v-row>
                                            <v-col>
                                                <v-file-input
                                                accept="image/*"
                                                label="사진을 넣어주세요"
                                                multiple
                                                ></v-file-input>
                                            </v-col>
                                        </v-row>
                                    </v-expansion-panel>
                                </v-form>
                            </v-expansion-panels>
                        </v-col>
                        
                        <v-col sm="1"></v-col>
                    </v-row>

                    <v-row dense style="padding-top: 20px;">
                        <v-col sm="4"></v-col>

                        <v-col sm="4">
                            <v-row dense>
                                <v-btn @click="handleReg()" style="width:100%; height:80px; background-color: gold;">
                                    <h2>클럽등록</h2>
                                </v-btn>
                            </v-row>
                        </v-col>

                        <v-col sm="4"></v-col>
                    </v-row>

                </v-col>

                <!-- 사이드 -->
                <v-col md="2"></v-col>
            </v-row>
        </v-main>
    </v-app>
    <FooterVue></FooterVue>
</div>
</template>

<script>
import { reactive }  from '@vue/reactivity';
import FooterVue from '../../components/FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const state = reactive({
            datechk: [],
            timechk: [],
            gender: [],
            age: [],

            name: '',
            area: '',
            postcode: '',
            detailAddress: '',
            address: '',
            desc: '',

            nameRules: [
                v => !!v || '필수 입력 사항입니다',
                v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다'
            ],
        })
        const handleReg = () => {

        };

        const reset = async() => {
            state.datechk = [];
            state.timechk = [];
        };
        return { state, handleReg, reset }
    }
}
</script>

<style lang="scss" scoped>

</style>