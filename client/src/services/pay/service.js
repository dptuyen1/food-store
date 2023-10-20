import * as axios from '~/utils/axios';
import { ANONYMOUS_REQUEST, ENDPOINTS } from '~/utils/axios';

const pay = async (price) => {
    try {
        let res = await axios.post(ANONYMOUS_REQUEST, ENDPOINTS['pay'], {}, { params: { price } });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

export { pay };
