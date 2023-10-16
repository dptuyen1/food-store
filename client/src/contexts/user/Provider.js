import { useReducer } from 'react';
import cookie from 'react-cookies';
import reducer from '~/contexts/user/reducer';
import Context from './Context';

const Provider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, cookie.load('user') || null);

    return <Context.Provider value={[state, dispatch]}>{children}</Context.Provider>;
};

export default Provider;
