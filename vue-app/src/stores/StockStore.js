import { defineStore } from 'pinia';

export const useStockStore = defineStore("StockStore", {
    state: () => ({
        store: {
            type: Object,
            default: {}
        }
    }),
    getters: {
        /*get: state => {
            return this.store;
        }*/
    },
    actions: {
        async clear() {
            this.store.$patch({store: {} });
        },
        add(newStore) {
            this.store.$patch({store: newStore});
        },
    },
})