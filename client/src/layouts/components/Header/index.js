import { faMagnifyingGlass, faShoppingCart, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import Tippy from '@tippyjs/react/headless';
import { Link, useNavigate } from 'react-router-dom';

import images from '~/assets/Images';
import Button from '~/components/Button';
import Offcanvas from '~/components/Offcanvas';
import Search from '../Search';

import { useCartContext, useUserContext } from '~/hooks';

import classNames from 'classnames/bind';
import styles from './Header.module.scss';

const cx = classNames.bind(styles);

const Header = () => {
    const [user, dispatch] = useUserContext();
    const [cartQuantity] = useCartContext();

    const nav = useNavigate();

    const handleLogout = (e) => {
        e.preventDefault();

        dispatch({
            type: 'LOGOUT',
        });

        nav('/login');
    };

    const ANONYMOUS_OPTIONS = [
        {
            name: 'Đăng nhập',
            to: '/login',
        },
        {
            name: 'Đăng ký',
            to: '/register',
        },
    ];

    const AUTH_OPTIONS = [
        {
            name: 'Trang cá nhân',
            to: `/${user?.username}`,
        },
        {
            name: 'Lịch sử mua hàng',
            to: '/tracking',
        },
        {
            name: 'Đăng xuất',
            to: '/logout',
            onClick: handleLogout,
        },
    ];

    return (
        <header className={cx('header')}>
            <div className={cx('wrapper')}>
                <div className="container">
                    <div className="d-flex justify-content-between">
                        <Link to="/">
                            <img className={cx('logo')} src={images.logo} alt="logo" />
                        </Link>

                        <div className={cx('actions d-flex gap-3')}>
                            <Tippy
                                delay={[100, 500]}
                                interactive
                                placement="bottom-end"
                                render={(attrs) => (
                                    <div className={cx('search-box')} tabIndex="-1" {...attrs}>
                                        <Search />
                                    </div>
                                )}
                            >
                                <Button primary>
                                    <FontAwesomeIcon icon={faMagnifyingGlass} />
                                </Button>
                            </Tippy>

                            <Tippy
                                delay={[100, 500]}
                                interactive
                                placement="bottom-end"
                                render={(attrs) => (
                                    <div className={cx('user-options')} tabIndex="-1" {...attrs}>
                                        {user
                                            ? AUTH_OPTIONS.map((option, index) => {
                                                  return (
                                                      <Link
                                                          className={cx('options-btn')}
                                                          to={option.to}
                                                          key={index}
                                                          onClick={option.onClick}
                                                      >
                                                          {option.name}
                                                      </Link>
                                                  );
                                              })
                                            : ANONYMOUS_OPTIONS.map((option, index) => {
                                                  return (
                                                      <Link className={cx('options-btn')} to={option.to} key={index}>
                                                          {option.name}
                                                      </Link>
                                                  );
                                              })}
                                    </div>
                                )}
                            >
                                <Button primary>
                                    {user?.avatar ? (
                                        <img className={cx('avatar')} src={user.avatar} alt="avatar" />
                                    ) : (
                                        <FontAwesomeIcon icon={faUser} />
                                    )}
                                </Button>
                            </Tippy>
                            {user && (
                                <Button primary relative data-bs-toggle="offcanvas" data-bs-target="#cart-canvas">
                                    <FontAwesomeIcon icon={faShoppingCart} />
                                    {!!cartQuantity && (
                                        <span className="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                            {cartQuantity}
                                        </span>
                                    )}
                                </Button>
                            )}
                        </div>

                        <Offcanvas id="cart-canvas" />
                    </div>
                </div>
            </div>
        </header>
    );
};

export default Header;
