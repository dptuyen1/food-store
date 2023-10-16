import cookie from 'react-cookies';

const reducer = (currentState, action) => {
    switch (action.type) {
        case 'LOGIN':
        case 'UPDATE':
            return action.payload;
        case 'LOGOUT':
            cookie.remove('token');
            cookie.remove('user');
            return null;
        default:
    }

    return currentState;
};

export default reducer;
