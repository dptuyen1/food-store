import axios from 'axios';
import cookie from 'react-cookies';

const SERVER_URL = 'http://localhost:8080';

const ENDPOINTS = {
    category: '/api/categories',
    product: '/api/products',
    'product-details': (id) => `/api/products/${id}`,
    invoice: '/api/invoices',
    details: '/api/details',
    promotion: '/api/promotions',
    payment: '/api/payments',
    coupon: '/api/coupons',
    review: '/api/reviews',
    user: '/api/users',
    'update-user': (id) => `/api/users/${id}`,
    login: '/api/login',
    register: '/api/register',
    'current-user': '/api/current-user',
    'existed-user': '/api/existed-user',
};

const createInstance = (baseURL, headers = {}) => {
    return axios.create({
        baseURL: baseURL,
        headers: headers,
    });
};

const ANONYMOUS_REQUEST = createInstance(SERVER_URL);

const AUTH_REQUEST = () => {
    const headers = {
        Authorization: cookie.load('token'),
    };

    return createInstance(SERVER_URL, headers);
};

const get = (instance, url, config = {}) => {
    return instance.get(url, config);
};

const post = (instance, url, data = {}, config = {}) => {
    return instance.post(url, data, config);
};

const put = (instance, url, data = {}, config = {}) => {
    return instance.put(url, data, config);
};

const patch = (instance, url, data = {}, config = {}) => {
    return instance.patch(url, data, config);
};

const remove = (instance, url, config = {}) => {
    return instance.delete(url, config);
};

export { ANONYMOUS_REQUEST, AUTH_REQUEST, ENDPOINTS, get, patch, post, put, remove };
