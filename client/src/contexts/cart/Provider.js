import { useReducer } from 'react';
import cookie from 'react-cookies';

import Context from './Context';
import reducer from './reducer';

const Provider = ({ children }) => {
    const getCartInitState = () => {
        let cart = cookie.load('cart') || {};

        if (!!Object.keys(cart).length) {
            return Object.values(cart).reduce((init, current) => init + current['quantity'], 0);
        }
        return 0;
    };

    const [state, dispatch] = useReducer(reducer, getCartInitState());

    return <Context.Provider value={[state, dispatch]}>{children}</Context.Provider>;
};

export default Provider;
