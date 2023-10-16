import * as axios from '~/utils/axios';
import { ANONYMOUS_REQUEST, ENDPOINTS } from '~/utils/axios';

const getProducts = async (kw = {}, categoryId = {}) => {
    try {
        let res = await axios.get(ANONYMOUS_REQUEST, ENDPOINTS['product'], {
            params: {
                kw,
                categoryId,
            },
        });

        return res;
    } catch (err) {
        console.log(err);
    }
};

const getById = async (id) => {
    try {
        let res = await axios.get(ANONYMOUS_REQUEST, ENDPOINTS['product-details'](id));

        return res;
    } catch (err) {
        console.log(err);
    }
};

export { getById, getProducts };
