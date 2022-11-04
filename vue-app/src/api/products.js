const baseUrl = 'stores/';

export default function ({ guest, admin }) {
    return {
        getAll(id) {
            return guest.get(`${baseUrl}${id}/products`);
        },
        get(id) {
            return guest.get(`${baseUrl}${id}`);
        },
        update(payload) {
            return admin.post(baseUrl, payload);
        }
    };
}