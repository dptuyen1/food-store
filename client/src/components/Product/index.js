import { Button, Card } from 'react-bootstrap';
import cookie from 'react-cookies';
import { Link, useNavigate } from 'react-router-dom';

import images from '~/assets/Images';
import { useCartContext, useUserContext } from '~/hooks';

import classNames from 'classnames/bind';
import styles from './Product.module.scss';

const cx = classNames.bind(styles);

const Product = ({ data }) => {
    const [user] = useUserContext();
    const [, dispatch] = useCartContext();

    const nav = useNavigate();

    const handleOrder = (product) => {
        if (!user) {
            nav('/login');
            return;
        }

        dispatch({
            type: 'INCREASE',
            payload: 1,
        });

        let cart = cookie.load('cart') || null;

        if (!cart) cart = {};

        if (product.id in cart) {
            cart[product.id]['quantity'] += 1;
        } else {
            cart[product.id] = {
                id: product.id,
                name: product.name,
                image: product.image,
                price: product.price,
                quantity: 1,
            };
        }

        cookie.save('cart', cart);
    };

    return (
        <Card className={cx('wrapper', 'my-3')} style={{ width: 'fitContent' }}>
            <Card.Img variant="top" src={data?.image || images['no-image']} />
            <hr />
            <Card.Body>
                <Card.Title className={cx('product-name')}>{data.name}</Card.Title>
                <Card.Text className={cx('product-price')}>
                    {data.price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}
                </Card.Text>
                <div className="d-flex justify-content-between">
                    {!!data.active ? (
                        <Button className={cx('button')} variant="primary" onClick={() => handleOrder(data)}>
                            Đặt hàng
                        </Button>
                    ) : (
                        <Button className={cx('button')} variant="primary" disabled>
                            Hết hàng
                        </Button>
                    )}
                    <Link to={`/products/${data.id}`} className={cx('btn btn-success', 'button')}>
                        Chi tiết
                    </Link>
                </div>
            </Card.Body>
        </Card>
    );
};

export default Product;
