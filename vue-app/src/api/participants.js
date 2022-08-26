export default function (instance) {
    return {
        getAll() {
            return instance.get('/participants/')
        }
    }
}