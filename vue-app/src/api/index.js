import instance from "./instance";

import productModule from "./products";
import participantModule from "./participants";

export default {
    products: productModule(instance),
    participants: participantModule(instance)
}

