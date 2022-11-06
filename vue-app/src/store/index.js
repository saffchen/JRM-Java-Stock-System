import { createStore } from 'vuex';
import axios from 'axios';
import api from '@/api';

const store = createStore({
    state: {
        Store: {
            type: Object,
            default() {}
        },
        UserStore: {
            status: '',
            token: '',
            email: 'opa'
        }
    },
    mutations: {
        auth_request(state){
            state.UserStore.status = 'loading';
        },
        auth_success(state, token, email){
            state.UserStore.status = 'success';
            state.UserStore.token = token;
            state.UserStore.email = email;
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
        auth({ commit }, payload) {
            commit('auth_request');
            api.security.checkAuthAndGetToken(payload)
                .then(async(response) => {
                    const data = await response.data;
                    console.log(data);
                    commit('auth_success', data.jwt, 'testAdmin@email.ru');
                })
                .catch(error => {
                    commit('auth_error');
                    console.log(error);
                });
        },
        login({ commit }, user) {
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
        logout({ commit }) {
            return new Promise((resolve) => {
                commit('logout');
                localStorage.removeItem('token');
                delete axios.defaults.headers.common['Authorization'];
                resolve();
            });
        }
    },
    getters: {
        isLoggedIn: state => !!state.UserStore.token,
        authStatus: state => state.UserStore.status,
        token: state => state.UserStore.token,
        email: state => state.UserStore.email
    }
});


export default store;
