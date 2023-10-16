import { useState } from 'react';
import { Spinner } from 'react-bootstrap';
import cookie from 'react-cookies';
import { Link, Navigate, useNavigate } from 'react-router-dom';

import { useUserContext } from '~/hooks';
import { userService } from '~/services/user';

import classNames from 'classnames/bind';
import styles from './Login.module.scss';

const cx = classNames.bind(styles);

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const [user, dispatch] = useUserContext();

    const [disable, setDisable] = useState(false);

    const nav = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        setDisable((disable) => !disable);

        let token = await userService.login(username, password);

        if (token.status === 200) {
            cookie.save('token', token.data);

            let { data } = await userService.details();
            cookie.save('user', data);

            dispatch({
                type: 'LOGIN',
                payload: data,
            });

            nav('/');
        } else {
            setMessage('Sai tài khoản hoặc mật khẩu...');
        }

        setDisable((disable) => !disable);
    };

    if (user) return <Navigate to="/"></Navigate>;

    return (
        <>
            <h1 className={cx('heading')}>Đăng nhập hệ thống</h1>

            {message && <h3 className={cx('error-message')}>{message}</h3>}

            <div className="row my-5">
                <div className="col">
                    <form onSubmit={handleLogin}>
                        <div className="mb-4">
                            <label className="form-label">Tài khoản</label>
                            <input
                                type="text"
                                className="form-control"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label className="form-label">Mật khẩu</label>
                            <input
                                type="password"
                                className="form-control"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                        {!disable ? (
                            <button type="submit" className={cx('login-btn')}>
                                Đăng nhập
                            </button>
                        ) : (
                            <Spinner animation="border" variant="primary" />
                        )}
                    </form>
                </div>
                <div className="col text-center">
                    <h1 className="mb-5">Chưa có tài khoản?</h1>
                    <Link className={cx('register-btn')} to="/register">
                        Tạo tài khoản mới
                    </Link>
                </div>
            </div>
        </>
    );
};

export default Login;
