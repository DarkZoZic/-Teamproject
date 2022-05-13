<template>
  <router-view></router-view>
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
  @import url(assets/css/style.css);
</style>
