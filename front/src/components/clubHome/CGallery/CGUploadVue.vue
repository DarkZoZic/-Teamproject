<template>
<div>
<HeaderVue style="height: 220px;"></HeaderVue>
    <v-app>
        <v-main style="padding: 10px;">      
            <v-row dense>
                <!-- 사이드 -->
                <v-col sm="2"></v-col>
                
                <!-- 메인 -->
                <v-col sm="8" style="border-bottom: 1px solid #CCC;">
                    <v-row dense="" style="border-bottom: 1px solid #CCC;">
                        <v-col sm="6">
                            <h5><router-link to="/chome">클럽홈</router-link> > <router-link to="/cblist">{{state.galleryName}}</router-link> > 업로드</h5>
                        </v-col>
                    </v-row>
                    
                    <v-row dense style="padding-top: 10px; padding-bottom: 5px; padding-left: 10px;">
                        <v-col sm="6" class="col_left">
                            <h2>업로드</h2>
                        </v-col>
                    </v-row>
                    <v-row dense>
                        <v-col>
                            <v-file-input
                            multiple
                            label="File input"
                            @change="insertimage($event)"
                            ></v-file-input>
                        </v-col>
                    </v-row>
                    <v-row dense style="padding:10px; ">

                        <v-col class="col_right">
                            <v-btn @click="upload()" style="width: 100px; height:40px; background-color: gold;">
                                <h3>업로드</h3>
                            </v-btn>

                            <v-btn @click="handleCancel" style="width: 100px; height:40px; margin-left:20px; background-color: white;">
                                <h3>취소</h3>
                            </v-btn>
                        </v-col>

                    </v-row>
                </v-col>

                <!-- 사이드 -->
                <v-col sm="2"></v-col>
            </v-row>
        </v-main>
    </v-app>
<FooterVue></FooterVue>
</div>
</template>

<script>
import { reactive } from '@vue/reactivity';
import FooterVue from '../../FooterVue.vue';
import HeaderVue from '../../HeaderVue.vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

export default {
    components: { HeaderVue, FooterVue },
    setup () {
        const router = useRouter();
        
        const state = reactive({
            galleryName: '일상갤러리',
            imageFile : [],
            imageUrl : []
        })

        const insertimage = (e) =>
        {
            if(e.target.files.length > 0)
            {
                for(let i=0; i<e.target.files.length; i++)
                {
                    state.imageUrl[i] = URL.createObjectURL(e.target.files[i]);
                    state.imageFile[i] = e.target.files[i];
                }
            }
            else
            {
                state.imageUrl = null;
                state.imageFile = null;
            }
        }

        const upload = async() => {
            const url = `/ROOT/api/clubgallery/insert`;
            const headers = {"Content-Type":"multipart/form-data"};
            const body = new Forsmata();
            for(let i=0; i<state.imageFile.length; i++)
            {
                body.append("file", state.imageFile[i]);
            }
            body.append("cgname", state.galleryName);
            console.log(body);
            const response = await axios.post(url, body, {headers});
            console.log(response.data);
            if(response.data.status === 200)
            {
                router.push({name:'CGalleryVue'});
            }
        }

        const handleCancel = () =>
        {
            router.push({name : 'CGalleryVue'});
        }

        return { state, upload, insertimage, handleCancel }
    }
}
</script>

<style lang="scss" scoped>

</style>