export const moduleA = {
    
    namespaced : true,

    state : {
        logged : false,
        menu   : '',
    },

    getters : {
        getLogged(state) {
            return state.logged;
        },

        getMenu(state) {
            return state.menu;
        },
    },

    mutations : {
        setLogged(state, value) {
            state.logged = value;
        },

        setMenu(state, value) {
            state.menu = value;
        },
    },
    
    actions : {

    }
}