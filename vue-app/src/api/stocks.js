export default function (instance) {
    return {
        getAll() {
            return instance.get('satellites/')
        },
        get(id) {
            return instance.get(`satellites/${id}`)
        },
        update(payload) {
            return instance.post('satellites/', payload)
        }
    }
}