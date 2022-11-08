import { createStore } from 'vuex';
import axios from 'axios';
import api from '@/api';
import jwt_decode from 'jwt-decode';

const store = createStore({
    state() {
        return {
            Store: {
                type: Object,
                default() {}
            },
            UserStore: {
                users: [],
                status: '',
                username: '',
                role: '',
                token: ''
            }
        };
    },
    mutations: {
        ADD_USER(state, user) {
            state = Object.assign(state, {
                UserStore: {
                    users: [
                        ...state.UserStore.users,
                        user
                    ],
                    status: 'success',
                    username: user.username,
                    role: user.role
                }
            });
        },
        REMOVE_USER(state, username) {
            state = Object.assign(state, {
                UserStore: {
                    ...state.UserStore,
                    users: [
                        ...state.UserStore.users
                    ].filter(user => user.username !== username)    
                }
            });
        },
        STORE_TOKEN(state, token) {
            state = Object.assign(state, {
                UserStore: {
                    ...state.UserStore,
                    token
                }
            });
        },
        auth_request(state){
            state.UserStore.status = 'loading';
        },
        auth_success(state){
            state.UserStore = Object.assign(state.UserStore, {
                ...state.UserStore,
                status: 'success'
            });
        },
        auth_error(state){
            state.UserStore.status = 'error';
        },
        CLEAR_USER_DATA(state){
            state.UserStore.status = '';
            state.UserStore.username = '';
            state.UserStore.role = '';
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
                .then((response) => {
                    const token = response.data.jwt;
                    const user = jwt_decode(token);
                    console.log(user);
                    commit('ADD_USER', user);
                    commit('STORE_TOKEN', token);
                    localStorage.setItem('username', user.username);
                    localStorage.setItem('role', user.role);
                    localStorage.setItem('token', token);
                    //commit('auth_success');
                    return user;
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
        logout({ commit }, username) {
            return new Promise((resolve) => {
                commit('REMOVE_USER', username);
                commit('CLEAR_USER_DATA');
                localStorage.removeItem('username');
                localStorage.removeItem('role');
                localStorage.removeItem('token');
                delete axios.defaults.headers.common['Authorization'];
                resolve();
            });
        }
    },
    getters: {
        isLoggedIn: state => !!state.UserStore.token,
        //authStatus: state => state.UserStore.status,
        token: state => state.UserStore.token,
        //username: state => state.UserStore.username
        userName: state => state.UserStore.users[0].username
    }
});


export default store;
