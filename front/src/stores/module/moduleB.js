export const moduleB = {
    
    namespaced : true,

    state : {
        num : 10,
    },
    getters : {
        getNum(state) {
            return state.num;
        }
    },
    mutations : {
        setNum(state, value) {
            state.num = value;
        }
    },
    actions : {
        
    }
}