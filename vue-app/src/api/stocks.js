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
            return admin.put(`${baseUrl}${id}`, payload);
        },
        create(payload) {
            return admin.post(baseUrl, payload);
        },
        delete(id) {
            return admin.delete(`${baseUrl}${id}`);
        }
    };
}