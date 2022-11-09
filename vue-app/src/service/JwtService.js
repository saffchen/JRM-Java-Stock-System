import jwt_decode from 'jwt-decode';

export default class JwtService {
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

    getUsername() {
        return this.user.username || '';
    }

    getRole() {
        return this.user.role || '';
    }
}