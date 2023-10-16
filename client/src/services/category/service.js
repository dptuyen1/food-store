import * as axios from '~/utils/axios';
import { ANONYMOUS_REQUEST, ENDPOINTS } from '~/utils/axios';

const getCategories = async () => {
    try {
        let res = await axios.get(ANONYMOUS_REQUEST, ENDPOINTS['category'], {});

        return res;
    } catch (err) {
        console.log(err);
    }
};

export { getCategories };
