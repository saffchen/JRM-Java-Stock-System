export default function (instance) {
    return {
        getAll() {
            return instance.get('products/')
        },
        get(id) {
            return instance.get(`products/${id}`)
        },
        update(payload) {
            return instance.post('products/', payload)
        }
    }
}