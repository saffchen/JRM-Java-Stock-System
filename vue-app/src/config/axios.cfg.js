import axios from "axios";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Accept': 'application/json'
    }
});

export default axiosInstance;