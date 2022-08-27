const baseUrl = 'products/';

export default function (instance) {
    return {
        getAll() {
            return instance.get(baseUrl)
        },
        get(id) {
            return instance.get(`${baseUrl}${id}`)
        },
        update(payload) {
            return instance.post(baseUrl, payload)
        }
    }
}