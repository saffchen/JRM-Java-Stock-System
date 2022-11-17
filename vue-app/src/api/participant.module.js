const baseUrl = 'participants/';

export default function ({ user }) {
    return {
        getAll() {
            return user.get(baseUrl);
        }
    };
}