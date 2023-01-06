import instance from './instance';

import productModule from './product.module';
import participantModule from './participant.module';
import stockModule from './stock.module';
import securityModule from './auth.module';

export default {
    products: productModule(instance),
    participants: participantModule(instance),
    stocks: stockModule(instance),
    security: securityModule(instance)
};

