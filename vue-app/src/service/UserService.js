import jwt_decode from 'jwt-decode';

export default class UserService {
    constructor(token) {
        this.token = token;
        if (token === '') {
            this.user = {};
        } else {
            this.user = jwt_decode(token);  
        }
    }

    getToken() {
        return this.token;
    }

    getUser() {
        return this.user;
    }

    getExpired() {
        return this.user.exp * 1000 || 0;
    }

    getUsername() {
        return this.user.username || '';
    }

    getRole() {
        return this.user.role || '';
    }
}