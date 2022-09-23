const baseUrl = 'stores/';

export default function (instance) {
    return {
        getAll(store_id) {
            return instance.get(`${baseUrl}${store_id}/products`);
        },
        get(id) {
            return instance.get(`${baseUrl}${id}`);
        },
        update(payload) {
            return instance.post(baseUrl, payload);
        }
    };
};