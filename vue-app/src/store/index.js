import { createStore } from 'vuex';
import userModule from './userModule';

const store = createStore({
    state: {
        Store: {
            type: Object,
            default() {}
        }
    },
    mutations: {
        add(state, newStore) {
            state.Store = newStore;
        }
    },
    modules: {
        user: userModule
    }
});


export default store;
