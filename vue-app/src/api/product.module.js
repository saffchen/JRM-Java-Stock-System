const baseUrl = 'stores/';

export default function ({ user, admin }) {
    return {
        getAll(storeId) {
            return user.get(`${baseUrl}${storeId}/products`);
        },
        update(storeId, payload) {
            return admin.post(`${baseUrl}${storeId}/products/${payload.id}`, payload);
        }
    };
}