import store from '@/store';

const baseUrl = 'stores/';

export default function ({ user, admin }) {
    return {
        getAll() {
            return user.get(baseUrl);
        },
        get(id) {
            return user.get(`${baseUrl}${id}`);
        },
        update(payload, id) {
            return admin.put(`${baseUrl}${id}`, payload, {
                headers: {
                    Accept: 'application/json',
                    Authorization: `Bearer ${store.getters['user/token']}`
                }
            });
        },
        create(payload) {
            return admin.post(baseUrl, payload, {
                headers: {
                    Accept: 'application/json',
                    Authorization: `Bearer ${store.getters['user/token']}`
                }
            });
        },
        delete(id) {
            return admin.delete(`${baseUrl}${id}`, {
                headers: {
                    Accept: 'application/json',
                    Authorization: `Bearer ${store.getters['user/token']}`
                }
            });
        }
    };
}