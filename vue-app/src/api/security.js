const baseUrl = 'auth/check_auth/';

export default function (instance) {
    return {
        getAll() {
            return instance.get(baseUrl)
        }
       }
      }
