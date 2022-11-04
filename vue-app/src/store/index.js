import {
    createStore 
} from 'vuex';
import axios from 'axios';

const store = createStore({
    state() {
        return {
            Store: {
                type: Object,
                default() {}
            },
            UserStore: {
                status: '',
                token: localStorage.getItem('token') || '',
                user: {}
            }
        };
    },
    mutations: {
        auth_request(state){
            state.UserStore.status = 'loading';
        },
        auth_success(state, token, user){
            state.UserStore.status = 'success';
            state.UserStore.token = token;
            state.UserStore.user = user;
        },
        auth_error(state){
            state.UserStore.status = 'error';
        },
        logout(state){
            state.UserStore.status = '';
            state.UserStore.token = '';
        },
        add(state, newStore) {
            state.Store = newStore;
        }
    },
    actions: {
        login({
            commit 
        }, user){
            return new Promise((resolve, reject) => {
                commit('auth_request');
                axios({
                    url: 'http://localhost:8080/login', data: user, method: 'POST' 
                })
                    .then(resp => {
                        const token = resp.data.token;
                        const user = resp.data.user;
                        localStorage.setItem('token', token);
                        axios.defaults.headers.common['Authorization'] = token;
                        commit('auth_success', token, user);
                        resolve(resp);
                    })
                    .catch(err => {
                        commit('auth_error');
                        localStorage.removeItem('token');
                        reject(err);
                    });
            });
        },
        logout({
            commit 
        }){
            return new Promise((resolve) => {
                commit('logout');
                localStorage.removeItem('token');
                delete axios.defaults.headers.common['Authorization'];
                resolve();
            });
        }
    },
    getters : {
        isLoggedIn: state => !!state.UserStore.token,
        authStatus: state => state.UserStore.status
    }
});


export default store;
