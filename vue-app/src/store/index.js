import { createStore } from 'vuex';
import stockModule from './stockModule';
import userModule from './userModule';

const store = createStore({
    modules: {
        user: userModule,
        stock: stockModule
    }
});


export default store;
