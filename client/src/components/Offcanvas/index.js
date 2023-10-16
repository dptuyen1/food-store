import cookie from 'react-cookies';
import { Link } from 'react-router-dom';

import CartItem from '../CartItem';

import classNames from 'classnames/bind';
import styles from './Offcanvas.module.scss';

const cx = classNames.bind(styles);

const Offcanvas = ({ id }) => {
    const data = cookie.load('cart');

    return (
        <>
            <div className="offcanvas offcanvas-end px-3" tabIndex="-1" id={id}>
                <div className="offcanvas-header my-3">
                    <h2>Giỏ hàng</h2>
                    <button type="button" className="btn-close" data-bs-dismiss="offcanvas" />
                </div>
                <div className="offcanvas-body">
                    {!!data ? (
                        Object.values(data).map((item) => {
                            return <CartItem key={item.id} data={item} />;
                        })
                    ) : (
                        <p style={{ fontSize: '2rem' }}>Giỏ hàng trống!</p>
                    )}

                    {!!data && (
                        <Link className={cx('btn-cart')} to="/cart">
                            Thanh toán
                        </Link>
                    )}
                </div>
            </div>
        </>
    );
};

export default Offcanvas;
