export const moduleA = {
    
    namespaced : true,

    state : {
        logged : false,
    },
    getters : {
        getLogged(state) {
            return state.logged;
        }
    },
    mutations : {
        setLogged(state, value) {
            state.logged = value;
        }
    },
    actions : {

    }
}