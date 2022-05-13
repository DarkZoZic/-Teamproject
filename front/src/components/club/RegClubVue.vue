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

                    <v-row dense>
                        <v-col>

                        </v-col>
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

        })
        const handleReg = () => {

        }
        return { state, handleReg }
    }
}
</script>

<style lang="scss" scoped>

</style>