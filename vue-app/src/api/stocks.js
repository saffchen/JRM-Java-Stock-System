import store from '@/store';

const baseUrl = 'stores/';

export default function ({ guest, admin }) {
    return {
        getAll() {
            return guest.get(baseUrl);
        },
        get(id) {
            return guest.get(`${baseUrl}${id}`);
        },
        update(payload, id) {
            return admin.put(`${baseUrl}${id}`, payload, {
                headers: {
                    Accept: 'application/json',
                    Authorization: `Bearer ${store.getters.token}`
                }
            });
        },
        create(payload) {
            return admin.post(baseUrl, payload, {
                headers: {
                    Accept: 'application/json',
                    Authorization: `Bearer ${store.getters.token}`
                }
            });
        },
        delete(id) {
            return admin.delete(`${baseUrl}${id}`, {
                headers: {
                    Accept: 'application/json',
                    Authorization: `Bearer ${store.getters.token}`
                }
            });
        }
    };
}