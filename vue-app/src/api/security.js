export default function (instance) {
    return {
        getAll() {
            return instance.get('/api/v1/auth/check_auth/')
        }
       }
      }
