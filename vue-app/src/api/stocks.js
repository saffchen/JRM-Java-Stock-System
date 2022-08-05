const baseUrl = 'satellites/';

export default function (instance) {
    return {
        getAll() {
            return instance.get(baseUrl)
        },
        get(id) {
            return instance.get(`${baseUrl}${id}`)
        },
        update(payload, id) {
            return instance.put(`${baseUrl}${id}`, payload)
        },
        create(payload) {
            return instance.post(baseUrl, payload)
        },
        delete(id) {
            return instance.delete(`${baseUrl}${id}`)
        }
    }
}