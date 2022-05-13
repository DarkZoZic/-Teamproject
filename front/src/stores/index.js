import { createStore } from 'vuex';
import { moduleA } from './module/moduleA';
import { moduleB } from './module/moduleB';

export default createStore({
    modules : { moduleA, moduleB }
})