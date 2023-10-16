import * as axios from '~/utils/axios';
import { AUTH_REQUEST, ENDPOINTS } from '~/utils/axios';

const getCoupons = async () => {
    try {
        let res = await axios.get(AUTH_REQUEST(), ENDPOINTS['coupon']);

        return res;
    } catch (err) {
        console.log(err);
    }
};

export { getCoupons };
