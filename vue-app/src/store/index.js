import { createStore } from 'vuex';
import stockModule from './stock.module';
import userModule from './user.module';

const store = createStore({
    modules: {
        user: userModule,
        stock: stockModule
    }
});


export default store;
