<template>
    <div>

    </div>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { onMounted } from '@vue/runtime-core';
export default {
    setup () {
        const store = useStore();
        const router = useRouter();
        onMounted(() => {
            alert('로그아웃 성공');
            sessionStorage.removeItem("TOKEN");
            store.commit('moduleA/setLogged', false);
            router.push({name:'HomeVVue'});
        });
        return {}
    }
}
</script>

<style lang="scss" scoped>
</style>