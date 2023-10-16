import * as axios from '~/utils/axios';
import { ANONYMOUS_REQUEST, AUTH_REQUEST, ENDPOINTS } from '~/utils/axios';

const getDetails = async (invoiceId = {}) => {
    try {
        let res = await axios.get(AUTH_REQUEST(), ENDPOINTS['details'], {
            params: {
                invoiceId,
            },
        });

        return res;
    } catch (err) {
        console.log(err);
    }
};

const add = async (id, carts) => {
    try {
        let res = await axios.post(ANONYMOUS_REQUEST, ENDPOINTS['details'], {
            invoiceId: id,
            carts: carts,
        });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

export { add, getDetails };
