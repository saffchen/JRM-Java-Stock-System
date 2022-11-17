import api from '@/api';

const stockModule = {
    namespaced: true,
    state() {
        return {
            stocks: [],
            status: '',
            edit: {
                activated: false,
                stock: {}
            }
        };
    },
    mutations: {
        SET_STATUS(state, status) {
            state.status = status;
        },
        STORE_STOCKS(state, stocks) {
            state.stocks = [...stocks];
        },
        ADD_STOCK(state, newStock) {
            state.stocks.push(newStock);
        },
        REMOVE_STOCK(state, id) {
            state.stocks = [...state.stocks].filter(stock => stock.id !== id);
        },
        UPDATE_STOCK(state, editedStock) {
            state.stocks = [...state.stocks].map(stock => {
                if (stock.id === editedStock.id) {
                    return {
                        ...stock,
                        ...editedStock
                    };
                }
                return stock;
            });
        },
        ACTIVATE_EDITING(state, stockToEdit) {
            state.edit = Object.assign(state.edit, {
                activated: true,
                stock: stockToEdit
            });
        },
        DEACTIVATE_EDITING(state) {
            state.edit = Object.assign(state.edit, {
                activated: false,
                stock: {}
            });
        }
    },
    actions: {
        fetchAll({ commit }) {
            commit('SET_STATUS', 'loading');
            api.stocks.getAll()
                .then(async response => {
                    const stocks = (await response).data;
                    commit('STORE_STOCKS', stocks);
                    commit('SET_STATUS', 'success');
                })
                .catch(error => {
                    commit('SET_STATUS', 'error');
                    console.log(error);
                });
        },
        add({ commit }, payload) {
            commit('SET_STATUS', 'adding');
            const result = api.stocks.create(payload)
                .then(result => {
                    if (result.status === 200) {
                        const newStock = JSON.parse(result.request.response);
                        commit('ADD_STOCK', newStock);
                        commit('SET_STATUS', 'success');
                        return newStock;
                    } else {
                        commit('SET_STATUS', 'error');
                    }
                })
                .catch(error => {
                    commit('SET_STATUS', 'error');
                    console.log(error);
                });
            return result;
        },
        delete({ commit }, id) {
            commit('SET_STATUS', 'deleting');
            api.stocks.delete(id)
                .then(result => {
                    if (result.status === 204) {
                        commit('REMOVE_STOCK', id);
                        commit('SET_STATUS', 'success');
                    } else {
                        commit('SET_STATUS', 'error');
                    }
                })
                .catch(error => {
                    commit('SET_STATUS', 'error');
                    console.log(error);
                });
        },
        update({ commit }, payload) {
            commit('SET_STATUS', 'updating');
            api.stocks.update(payload)
                .then(result => {
                    if (result.status === 204) {
                        commit('UPDATE_STOCK', payload);
                        commit('SET_STATUS', 'success');
                    } else {
                        commit('SET_STATUS', 'error');
                    }
                })
                .catch(error => {
                    commit('SET_STATUS', 'error');
                    console.log(error);
                });
        }
    },
    getters: {
        getAll: state => state.stocks,
        count: state => state.stocks.length,
        getStatus: state => state.status,
        editActivated: state => state.edit.activated,
        editObject: state => state.edit.stock
    }
};

export default stockModule;