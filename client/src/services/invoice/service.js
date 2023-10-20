import * as axios from '~/utils/axios';
import { AUTH_REQUEST, ENDPOINTS } from '~/utils/axios';

const getInvoices = async () => {
    try {
        let res = await axios.get(AUTH_REQUEST(), ENDPOINTS['invoice']);

        return res;
    } catch (err) {
        console.log(err);
    }
};

const add = async (totalPrice, totalQuantity, discountPrice = 0, statusId, shoppingId, paymentId, userId) => {
    try {
        let res = await axios.post(AUTH_REQUEST(), ENDPOINTS['invoice'], {
            totalPrice: totalPrice,
            totalQuantity: totalQuantity,
            discountPrice: discountPrice,
            statusId: statusId,
            shoppingId: shoppingId,
            paymentId: paymentId,
            userId: userId,
        });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

export { add, getInvoices };
