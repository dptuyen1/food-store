import { Table } from 'react-bootstrap';
import cookie from 'react-cookies';

import { useCartContext } from '~/hooks';
import { detailsService } from '~/services/details';
import { invoiceService } from '~/services/invoice';
import Button from '../Button';

import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import classNames from 'classnames/bind';
import styles from './Monitor.module.scss';

const cx = classNames.bind(styles);

const Monitor = ({ cart, handleClearCart, handleUpdateCart }) => {
    const [, dispatch] = useCartContext();

    const quantity = Object.values(cart).reduce((init, item) => init + item['quantity'], 0);
    const sum = Object.values(cart).reduce((init, item) => init + item['price'] * item['quantity'], 0);

    const handleCheckOut = async (cart) => {
        let res = await invoiceService.add(sum, quantity, 0, 1, 1, 1, 1);

        let id = res.data.id;

        let data = await detailsService.add(id, cart);

        if (data.status === 201) {
            cookie.remove('cart');

            dispatch({
                type: 'UPDATE',
                payload: 0,
            });

            handleClearCart();
        }
    };

    const handleDeleteItem = (item) => {
        dispatch({
            type: 'DECREASE',
            payload: item.quantity,
        });

        handleUpdateCart(item);
    };

    const handleClear = () => {
        dispatch({
            type: 'UPDATE',
            payload: 0,
        });

        cookie.remove('cart');

        handleClearCart();
    };

    return (
        <>
            <Table hover className="text-center my-5">
                <thead>
                    <tr>
                        <th>Tên</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Tổng tiền</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {Object.values(cart).length > 0 &&
                        Object.values(cart).map((item) => {
                            return (
                                <tr key={item.id}>
                                    <td>{item.name}</td>
                                    <td>
                                        {item.price.toLocaleString('vi-VN', {
                                            style: 'currency',
                                            currency: 'VND',
                                        })}
                                    </td>
                                    <td>{item.quantity}</td>
                                    <td>
                                        {(item.price * item.quantity).toLocaleString('vi-VN', {
                                            style: 'currency',
                                            currency: 'VND',
                                        })}
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
                            <h4>
                                Số lượng: {''}
                                {quantity}
                            </h4>
                        </td>
                        <td colSpan={3}>
                            <h4>
                                Tổng tiền:{' '}
                                {sum.toLocaleString('vi-VN', {
                                    style: 'currency',
                                    currency: 'VND',
                                })}
                            </h4>
                        </td>
                    </tr>
                </tbody>
            </Table>
            {Object.values(cart).length > 0 && (
                <div className="d-flex justify-content-between">
                    <Button secondary onClick={() => handleCheckOut(cart)}>
                        Hoàn tất
                    </Button>
                    <Button danger onClick={handleClear}>
                        Xóa tất cả
                    </Button>
                </div>
            )}
        </>
    );
};

export default Monitor;
