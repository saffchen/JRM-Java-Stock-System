import instance from "./instance";

import productModule from "./products";

export default {
    products: productModule(instance)
}

