export default function (instance) {
    return {
        getAll() {
            return instance.get('/auth/check_auth/')
        }
       }
      }
