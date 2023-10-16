import { useEffect, useState } from 'react';
import { Spinner, Table } from 'react-bootstrap';
import cookie from 'react-cookies';
import { useNavigate } from 'react-router-dom';

import { faMinus, faPlus, faSackDollar, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import Button from '~/components/Button';
import { useCartContext, useUserContext } from '~/hooks';
import { couponService } from '~/services/coupon';
import { detailsService } from '~/services/details';
import { invoiceService } from '~/services/invoice';

import classNames from 'classnames/bind';
import styles from './Cart.module.scss';

const cx = classNames.bind(styles);

const Cart = () => {
    const [user] = useUserContext();
    const [, dispatch] = useCartContext();

    const [cart, setCart] = useState(cookie.load('cart') || {});
    const [disable, setDisable] = useState(false);
    const [coupons, setCoupons] = useState([]);
    const [discount, setDiscount] = useState(0);

    const quantity = Object.values(cart).reduce((init, item) => init + item['quantity'], 0);
    const sum = Object.values(cart).reduce((init, item) => init + item['price'] * item['quantity'], 0);
    const discountPrice = sum * (discount / 100);
    const totalPrice = sum - discountPrice;

    useEffect(() => {
        const loadCoupons = async () => {
            let res = await couponService.getCoupons();

            setCoupons(res.data);
        };

        loadCoupons();
    }, []);

    const nav = useNavigate();

    const handleOnChange = (e, item) => {
        setCart({
            ...cart,
            [item.id]: {
                ...cart[item.id],
                quantity: e.target.value > 0 ? parseInt(e.target.value) : 1,
            },
        });
    };

    const handleItemQuantity = (item, type) => {
        if (item.quantity === 1 && type === 'DECREASE') return;

        dispatch({
            type: type,
            payload: 1,
        });

        setCart((prevCart) => {
            const updatedCart = {
                ...prevCart,
                [item.id]: {
                    ...prevCart[item.id],
                    quantity:
                        type === 'INCREASE'
                            ? parseInt(prevCart[item.id]['quantity']) + 1
                            : parseInt(prevCart[item.id]['quantity']) - 1,
                },
            };

            cookie.save('cart', updatedCart);
            return updatedCart;
        });
    };

    const handleDeleteItem = (item) => {
        dispatch({
            type: 'DECREASE',
            payload: item.quantity,
        });

        if (item.id in cart) {
            setCart((current) => {
                delete current[item.id];
                cookie.save('cart', current);

                if (!Object.keys(current).length) cookie.remove('cart');

                return current;
            });
        }
    };

    const handleUpdateItem = () => {
        let payload = Object.values(cart).reduce((init, current) => init + current['quantity'], 0);

        dispatch({
            type: 'UPDATE',
            payload: payload,
        });

        cookie.save('cart', cart);
    };

    const handleCheckOut = async (carts) => {
        if (!user.email || !user.address || !user.phoneNumber) {
            nav('/profile/?next=/cart');
            return;
        }

        setDisable((disable) => !disable);

        let res = await invoiceService.add(totalPrice, quantity, discountPrice);

        let id = res.data.id;

        let data = await detailsService.add(id, carts);

        if (data.status === 201) {
            cookie.remove('cart');

            dispatch({
                type: 'UPDATE',
                payload: 0,
            });

            setCart({});

            nav('/tracking');
        }

        setDisable((disable) => !disable);
    };

    return (
        <>
            <h1 className={cx('heading')}>Giỏ hàng</h1>
            {!Object.keys(cart).length ? (
                <p style={{ fontSize: '2.2rem' }}>Giỏ hàng trống!</p>
            ) : (
                <>
                    <Table hover className="text-center my-5">
                        <thead>
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {Object.values(cart).map((item) => {
                                return (
                                    <tr key={item.id}>
                                        <td>
                                            <img style={{ width: '50px' }} src={item.image} alt="item-img" />
                                        </td>
                                        <td>
                                            <h3>{item.name}</h3>
                                        </td>
                                        <td>
                                            {item.price.toLocaleString('vi-VN', {
                                                style: 'currency',
                                                currency: 'VND',
                                            })}
                                        </td>
                                        <td>
                                            <div className="d-flex justify-content-center">
                                                <Button secondary onClick={() => handleItemQuantity(item, 'DECREASE')}>
                                                    <FontAwesomeIcon icon={faMinus} />
                                                </Button>
                                                <input
                                                    className={cx('quantity-input')}
                                                    type="number"
                                                    value={item.quantity}
                                                    min={1}
                                                    onChange={(e) => handleOnChange(e, item)}
                                                    onBlur={handleUpdateItem}
                                                />
                                                <Button secondary onClick={() => handleItemQuantity(item, 'INCREASE')}>
                                                    <FontAwesomeIcon icon={faPlus} />
                                                </Button>
                                            </div>
                                        </td>
                                        <td>
                                            <h3>
                                                {(item.price * item.quantity).toLocaleString('vi-VN', {
                                                    style: 'currency',
                                                    currency: 'VND',
                                                })}
                                            </h3>
                                        </td>
                                        <td>
                                            <Button danger onClick={() => handleDeleteItem(item)}>
                                                <FontAwesomeIcon icon={faTrash} />
                                            </Button>
                                        </td>
                                    </tr>
                                );
                            })}

                            <tr>
                                <td colSpan={2}>
                                    <div className="d-flex ms-5 gap-3">
                                        {!!coupons &&
                                            coupons.map((coupon) => {
                                                return (
                                                    <Button
                                                        key={coupon.id}
                                                        secondary
                                                        onClick={() => setDiscount(coupon.value)}
                                                    >
                                                        {coupon.code}
                                                    </Button>
                                                );
                                            })}
                                    </div>
                                </td>
                                <td>
                                    <h4>
                                        Số lượng: {''}
                                        {quantity}
                                    </h4>
                                </td>
                                <td>
                                    <h4>
                                        Tạm tính:{' '}
                                        {sum.toLocaleString('vi-VN', {
                                            style: 'currency',
                                            currency: 'VND',
                                        })}
                                    </h4>
                                </td>
                                <td>
                                    <h4>
                                        Giảm giá:{' '}
                                        {discountPrice.toLocaleString('vi-VN', {
                                            style: 'currency',
                                            currency: 'VND',
                                        })}
                                    </h4>
                                </td>
                                <td>
                                    <h4>
                                        Tổng tiền:{' '}
                                        {totalPrice.toLocaleString('vi-VN', {
                                            style: 'currency',
                                            currency: 'VND',
                                        })}
                                    </h4>
                                </td>
                            </tr>
                        </tbody>
                    </Table>

                    <div className="d-flex justify-content-end">
                        {!disable ? (
                            <Button secondary onClick={() => handleCheckOut(cart)}>
                                <FontAwesomeIcon icon={faSackDollar} className="me-3" />
                                Thanh toán
                            </Button>
                        ) : (
                            <Spinner animation="border" variant="primary" />
                        )}
                    </div>
                </>
            )}
        </>
    );
};

export default Cart;
