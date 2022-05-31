import { createStore } from 'vuex';
import { moduleA } from './module/moduleA';
import { moduleB } from './module/moduleB';

export default createStore({
  // 주소
  state: {
    counter:0,
    keyword:'검색어',    
},
getters: {
getCounter(state) {
  return state.counter;
},
getKeyword(state) {
  return state.keyword;
}
},
mutations: {
  setCounter(state, value) {
    state.counter = value;
  },
  setKeyword(state, value) {
    state.keyword = value;
  },
},
// 검색

actions: { 
  
},
    modules : { moduleA, moduleB }
})