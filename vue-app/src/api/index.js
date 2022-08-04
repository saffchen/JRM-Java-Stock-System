import instance from "./instance";

import productModule from "./products";
import participantModule from "./participants";
import satellitesModule from "./satellites";

export default {
    products: productModule(instance),
    participants: participantModule(instance),
    satellites: satellitesModule(instance)
}

