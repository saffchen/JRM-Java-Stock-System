import instance from "./instance";

import productModule from "./products";
import participantModule from "./participants";
import stockModule from "./stocks";
import securityModule from "./security";

export default {
    products: productModule(instance),
    participants: participantModule(instance),
    stocks: stockModule(instance),
    security: securityModule(instance)
};

