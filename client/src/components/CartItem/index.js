import classNames from 'classnames/bind';
import styles from './CartItem.module.scss';

const cx = classNames.bind(styles);

const CartItem = ({ data }) => {
    return (
        <>
            <div className={cx('item')}>
                <img className={cx('item-image')} src={data.image} alt="img" />

                <div className={cx('inform')}>
                    <h4>{data.name}</h4>
                    <p>
                        {data.quantity} x{' '}
                        {data.price.toLocaleString('vi-VN', {
                            style: 'currency',
                            currency: 'VND',
                        })}
                    </p>
                </div>
            </div>
        </>
    );
};

export default CartItem;
