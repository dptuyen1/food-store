import { useRef, useState } from 'react';
import { Spinner } from 'react-bootstrap';
import cookie from 'react-cookies';

import images from '~/assets/Images';
import { useUserContext } from '~/hooks';
import { userService } from '~/services/user';

import classNames from 'classnames/bind';
import { useNavigate, useSearchParams } from 'react-router-dom';
import styles from './Profile.module.scss';

const cx = classNames.bind(styles);

const Profile = () => {
    const [user, dispatch] = useUserContext();

    const [userDTO, setUserDTO] = useState({
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
        address: user.address,
        phoneNumber: user.phoneNumber,
        avatar: user.avatar,
    });

    const avatar = useRef();

    const [disable, setDisable] = useState(false);

    const [query] = useSearchParams();

    const nav = useNavigate();

    const handleChange = (e, field) => {
        setUserDTO({
            ...userDTO,
            [field]: e.target.value,
        });
    };

    const handleUpdate = async (e) => {
        e.preventDefault();

        setDisable((disable) => !disable);

        let res = await userService.update(user.id, userDTO);

        cookie.save('user', res.data);

        if (!!avatar.current.files[0]) {
            let form = new FormData();
            form.append('avatar', avatar.current.files[0]);
            let res = await userService.updateAvatar(form);

            cookie.save('user', res.data);
        }

        dispatch({
            type: 'UPDATE',
            payload: res.data,
        });

        setDisable((disable) => !disable);

        let param = query.get('next');
        if (!!param) nav(param);
    };

    return (
        <>
            <h1 className="text-center">Thông tin cá nhân</h1>
            <div className={cx('wrapper')}>
                <div className={cx('avatar-wrapper')}>
                    <img className={cx('avatar')} src={userDTO?.avatar || images['no-image']} alt="avatar" />
                    <input className="form-control" type="file" accept="image/*" ref={avatar} />
                </div>
                <div className={cx('inform-wrapper')}>
                    <form>
                        <div className="row gap-2 mb-4">
                            <div className="col">
                                <label className="form-label">Họ</label>
                                <input
                                    className="form-control"
                                    type="text"
                                    value={userDTO.lastName}
                                    onChange={(e) => handleChange(e, 'lastName')}
                                    required
                                />
                            </div>
                            <div className="col">
                                <label className="form-label">Tên</label>
                                <input
                                    className="form-control"
                                    type="text"
                                    value={userDTO.firstName}
                                    onChange={(e) => handleChange(e, 'firstName')}
                                    required
                                />
                            </div>
                        </div>
                        <div className="row gap-2 mb-4">
                            <div className="col">
                                <label className="form-label">Email</label>
                                <input
                                    className="form-control"
                                    type="email"
                                    value={userDTO.email}
                                    onChange={(e) => handleChange(e, 'email')}
                                    required
                                />
                            </div>
                            <div className="col">
                                <label className="form-label">Số điện thoại</label>
                                <input
                                    className="form-control"
                                    type="text"
                                    value={userDTO.phoneNumber}
                                    onChange={(e) => handleChange(e, 'phoneNumber')}
                                    maxLength={15}
                                    required
                                />
                            </div>
                        </div>
                        <div className="mb-4">
                            <label className="form-label">Địa chỉ</label>
                            <input
                                className="form-control"
                                type="text"
                                value={userDTO.address}
                                onChange={(e) => handleChange(e, 'address')}
                                required
                            />
                        </div>
                        <div className="d-flex justify-content-end mt-4">
                            {!disable ? (
                                <button className={cx('update-btn')} type="button" onClick={handleUpdate}>
                                    Cập nhật
                                </button>
                            ) : (
                                <Spinner animation="border" variant="primary" />
                            )}
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
};

export default Profile;
