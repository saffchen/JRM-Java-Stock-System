const baseUrl = 'stores/';

export default function ({ guest, admin }) {
    return {
        getAll(storeId) {
            return guest.get(`${baseUrl}${storeId}/products`);
        },
        update(storeId, payload) {
            return admin.post(`${baseUrl}${storeId}/products/${payload.id}`, payload);
        }
    };
}