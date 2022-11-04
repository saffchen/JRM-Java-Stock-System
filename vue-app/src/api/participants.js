const baseUrl = 'participants/';

export default function ({ guest }) {
    return {
        getAll() {
            return guest.get(baseUrl);
        }
    };
}