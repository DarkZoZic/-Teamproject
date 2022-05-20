<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">      
        <v-row dense>
            <v-col sm="2"></v-col>
            
            <v-col sm="8">
                <v-row dense="" style="border-bottom: 1px solid #CCC;">
                    <v-col sm="6">
                        <h5><router-link to="/">홈</router-link> > 회원가입</h5>
                    </v-col>
                </v-row>

                <v-row dense style="padding-top: 19px; padding-bottom: 15px; padding-left: 10px;">
                    <v-col>
                        <h2>회원가입선택</h2>
                    </v-col>
                </v-row>

                <v-row dense>
                    <v-col sm="2"></v-col>
                    <v-col sm="4">
                        <v-btn width="100%" height="100%" style="background-color: #ffcc00;">
                            <router-link to="/joinp" style="padding: 20px;">
                                <v-row dense><v-col class="col_center">
                                    <img :src="require('../../assets/img/personal.png')" style="width: 300px;"/>
                                </v-col></v-row>
                                <v-row dense><v-col class="col_center">
                                    <h1 style="padding-top: 20px;">개인회원</h1>
                                </v-col></v-row>
                            </router-link>
                        </v-btn>
                    </v-col>

                    <v-col sm="4" >
                        <v-btn width="100%" height="100%">
                            <router-link to="/joinc" style="padding: 20px; background-color: #5d00ff;">
                                <v-row dense><v-col class="col_center">
                                        <img :src="require('../../assets/img/company.png')" style="width: 320px; "/>
                                </v-col></v-row>

                                <v-row dense><v-col class="col_center"><h1 style="color: white;">기업회원</h1></v-col></v-row>
                            </router-link>
                        </v-btn>
                    </v-col>
                    <v-col sm="2"></v-col>
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
import FooterVue from '../../components/FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        

        return {}
    }
}
</script>

<style lang="scss" scoped>

</style>