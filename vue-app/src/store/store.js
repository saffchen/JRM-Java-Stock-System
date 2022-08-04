import { createStore } from 'vuex'

const store = createStore({
    state() {
        return {
            Store: {
                type: Object,
                default() {}
            }

        }
    },
    mutations: {
        add(state, newStore) {
            state.Store = newStore;
        }
    }
})

export default store