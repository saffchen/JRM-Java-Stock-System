import api from '@/api';
import JwtService from '@/service/JwtService';

const TokenService = new JwtService(localStorage.getItem('token') || '');

const userModule = {
    namespaced: true,
    state() {
        return {
            user: TokenService.getUser(),
            token: TokenService.getToken()
        };
    },
    mutations: {
        SET_USER(state, user) {
            state.user = user;
        },
        REMOVE_USER(state) {
            state.user = null;
        },
        STORE_TOKEN(state, token) {
            state.token = token;
        },
        DELETE_TOKEN(state) {
            state.token = '';
        },
        RESET_STATE(state) {
            state.user = null;
            state.token = '';
        }
    },
    actions: {
        login({ commit }, payload) {
            return api.security.checkAuthAndGetToken(payload)
                .then(async (response) => {
                    if (await response.status === 200) {
                        const token = response.data.jwt;
                        const service = new JwtService(token);
                        commit('SET_USER', service.getUser());
                        commit('STORE_TOKEN', service.getToken());
                        localStorage.setItem('token', service.getToken());
                        return 'ok';
                    }
                    return 'error';
                })
                .catch(error => {
                    console.log(error);
                    commit('RESET_STATE');
                    return 'error';
                });
        },
        logout({ commit }) {
            return new Promise((resolve) => {
                commit('REMOVE_USER');
                commit('DELETE_TOKEN');
                localStorage.removeItem('token');
                resolve();
            });
        }
    },
    getters: {
        token: state => state.token,
        userName: state => (state.user ? state.user.username : ''),
        loggedIn: state => state.token !== '',
        isAdmin: (state, getters) => getters.loggedIn && state.user.role === 'ROLE_ADMIN'
    }
};

export default userModule;