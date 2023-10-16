import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { promotionService } from '~/services/promotion';
import { userService } from '~/services/user';

import classNames from 'classnames/bind';
import styles from './Register.module.scss';

const cx = classNames.bind(styles);

const Register = () => {
    const [message, setMessage] = useState('');

    const [user, setUser] = useState({
        firstName: '',
        lastName: '',
        email: '',
        username: '',
        password: '',
        confirm: '',
    });

    const nav = useNavigate();

    const handleChange = (e, field) => {
        setUser({
            ...user,
            [field]: e.target.value,
        });
    };

    const handleRegister = async (e) => {
        e.preventDefault();

        let existedRes = await userService.existed(user.username);

        if (existedRes.status === 200) {
            if (user.password !== user.confirm) {
                setMessage('Mật khẩu không khớp...');
                return;
            }

            let form = new FormData();

            for (let field in user) {
                if (field !== 'confirm') {
                    form.append(field, user[field]);
                }
            }

            let regRes = await userService.register(form);

            if (regRes.status === 201) {
                let id = regRes.data.id;

                await promotionService.add(id, 1);

                nav('/login');
            }
        } else {
            setMessage('Tài khoản đã có người sử dụng...');
            return;
        }
    };

    return (
        <>
            <h1 className={cx('heading')}>Đăng ký tài khoản</h1>

            {message && <h3 className={cx('error-message')}>{message}</h3>}

            <div className={cx('form-wrapper')}>
                <form className="w-50" onSubmit={handleRegister}>
                    <div className="mb-4">
                        <label className="form-label">Họ</label>
                        <input
                            type="text"
                            className="form-control"
                            value={user.lastName}
                            onChange={(e) => handleChange(e, 'lastName')}
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="form-label">Tên</label>
                        <input
                            type="text"
                            className="form-control"
                            value={user.firstName}
                            onChange={(e) => handleChange(e, 'firstName')}
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="form-label">Email</label>
                        <input
                            type="email"
                            className="form-control"
                            value={user.email}
                            onChange={(e) => handleChange(e, 'email')}
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="form-label">Tài khoản</label>
                        <input
                            type="text"
                            className="form-control"
                            value={user.username}
                            onChange={(e) => handleChange(e, 'username')}
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="form-label">Mật khẩu</label>
                        <input
                            type="password"
                            className="form-control"
                            value={user.password}
                            onChange={(e) => handleChange(e, 'password')}
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="form-label">Xác nhận mật khẩu</label>
                        <input
                            type="password"
                            className="form-control"
                            value={user.confirm}
                            onChange={(e) => handleChange(e, 'confirm')}
                            required
                        />
                    </div>
                    <button type="submit" className={cx('register-btn')}>
                        Đăng ký
                    </button>
                </form>
            </div>
        </>
    );
};

export default Register;
