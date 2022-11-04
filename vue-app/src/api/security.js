const baseUrl = 'auth/check_auth/';

export default function (instance) {
    return {
        checkAuthAndGetToken(payload) {
            return instance.post(baseUrl, payload);
        }
    };
}
