const baseUrl = 'participants/';

export default function (instance) {
    return {
        getAll() {
            return instance.get(baseUrl)
        }
    }
}