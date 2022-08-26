export default function (instance) {
    return {
        getAll() {
            return instance.get('/api/v1/participants/')
        }
    }
}