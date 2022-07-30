export default function (instance) {
    return {
        getAll() {
            return instance.get('satellites/')
        },
        get(id) {
            return instance.get(`satellites/${id}`)
        },
        update(payload) {
            return instance.put(`satellites/${id}`, payload)
        },
        create(payload) {
            return instance.post('satellites/', payload)
        },
        delete(id) {
            return instance.delete(`satellites/${id}`)
        }
    }
}