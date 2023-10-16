import * as axios from '~/utils/axios';
import { ANONYMOUS_REQUEST, AUTH_REQUEST, ENDPOINTS } from '~/utils/axios';

const getReviews = async (productId) => {
    try {
        let res = await axios.get(ANONYMOUS_REQUEST, ENDPOINTS['review'], { params: { productId } });

        return res;
    } catch (err) {
        console.log(err);
    }
};

const add = async (content, id) => {
    try {
        let res = await axios.post(AUTH_REQUEST(), ENDPOINTS['review'], { content: content, productId: id });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

export { add, getReviews };
