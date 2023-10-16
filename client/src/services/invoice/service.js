import * as axios from '~/utils/axios';
import { ANONYMOUS_REQUEST, AUTH_REQUEST, ENDPOINTS } from '~/utils/axios';

const getInvoices = async () => {
    try {
        let res = await axios.get(AUTH_REQUEST(), ENDPOINTS['invoice']);

        return res;
    } catch (err) {
        console.log(err);
    }
};

const add = async (totalPrice, totalQuantity, discountPrice = 0) => {
    try {
        let res = await axios.post(AUTH_REQUEST(), ENDPOINTS['invoice'], {
            totalPrice: totalPrice,
            totalQuantity: totalQuantity,
            discountPrice: discountPrice,
            statusId: 2,
            shoppingId: 2,
        });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

const addInStore = async (totalPrice, totalQuantity, discountPrice = 0) => {
    try {
        let res = await axios.post(ANONYMOUS_REQUEST, ENDPOINTS['invoice'], {
            totalPrice: totalPrice,
            totalQuantity: totalQuantity,
            discountPrice: discountPrice,
            statusId: 1,
            shoppingId: 1,
            userId: 1,
        });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

export { add, addInStore, getInvoices };
