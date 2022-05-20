<template>
<v-app>
    <v-main >      
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import { useRouter } from 'vue-router';
export default {
  
  setup () {
    const router = useRouter();

    return { router }
  },
}
</script>
<style>

</style>
