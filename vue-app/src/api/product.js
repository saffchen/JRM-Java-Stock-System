export default function (instance) {
    return {
        getProducts(payload) {
            return instance.getAll('products/')
        }
    }
}