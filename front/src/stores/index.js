import { createStore } from 'vuex';
import { moduleA } from './module/moduleA';
import { moduleB } from './module/moduleB';

export default createStore({
  state: {
    counter:0,  
},
getters: {
getCounter(state) {
  return state.counter;
}
},
mutations: {
  setCounter(state, value) {
    state.counter = value;
  },
},
actions: { 
  
},
    modules : { moduleA, moduleB }
})