const baseUrl = 'stores/';

export default function ({ user, admin }) {
    return {
        getAll() {
            return user.get(baseUrl);
        },
        get(id) {
            return user.get(`${baseUrl}${id}`);
        },
        update(payload) {
            return admin.put(`${baseUrl}${payload.id}`, payload);
        },
        create(payload) {
            return admin.post(baseUrl, payload);
        },
        delete(id) {
            return admin.delete(`${baseUrl}${id}`);
        }
    };
}