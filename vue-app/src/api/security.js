const baseUrl = 'check_auth/';

export default function ({ auth }) {
    return {
        checkAuthAndGetToken(payload) {
            return auth.post(baseUrl, payload);
        }
    };
}
