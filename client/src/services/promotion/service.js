import * as axios from '~/utils/axios';
import { ANONYMOUS_REQUEST, ENDPOINTS } from '~/utils/axios';

const add = async (userId, couponId) => {
    try {
        let res = await axios.post(ANONYMOUS_REQUEST, ENDPOINTS['promotion'], {
            userId: userId,
            couponId: couponId,
        });

        return res;
    } catch (err) {
        console.log(err);
    }
};

export { add };
