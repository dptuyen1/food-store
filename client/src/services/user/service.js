import * as axios from '~/utils/axios';
import { ANONYMOUS_REQUEST, AUTH_REQUEST, ENDPOINTS } from '~/utils/axios';

const login = async (username, password) => {
    try {
        let res = await axios.post(ANONYMOUS_REQUEST, ENDPOINTS['login'], {
            username: username,
            password: password,
        });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

const register = async (form) => {
    try {
        let res = await axios.post(ANONYMOUS_REQUEST, ENDPOINTS['register'], form);

        return res;
    } catch (err) {
        console.log(err);
    }
};

const details = async () => {
    try {
        let res = await axios.get(AUTH_REQUEST(), ENDPOINTS['current-user']);

        return res;
    } catch (err) {
        console.log(err);
    }
};

const existed = async (username) => {
    try {
        let res = await axios.get(ANONYMOUS_REQUEST, ENDPOINTS['existed-user'], { params: { username } });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

const update = async (id, data) => {
    try {
        let res = await axios.patch(AUTH_REQUEST(), ENDPOINTS['update-user'](id), {
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            address: data.address,
            phoneNumber: data.phoneNumber,
            avatar: data.avatar,
        });

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

const updateAvatar = async (form) => {
    try {
        let res = await axios.post(AUTH_REQUEST(), ENDPOINTS['user'], form);

        return res;
    } catch (err) {
        console.log(err);
        return err;
    }
};

export { details, existed, login, register, update, updateAvatar };
