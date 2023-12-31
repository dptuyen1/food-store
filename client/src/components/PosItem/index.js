import { Col, Image, Row } from 'react-bootstrap';
import cookie from 'react-cookies';
import { useCartContext } from '~/hooks';

import classNames from 'classnames/bind';
import styles from './PostItem.module.scss';

const cx = classNames.bind(styles);

const PostItem = ({ data, cart }) => {
    const [, dispatch] = useCartContext();

    const handleAddToCart = (product) => {
        dispatch({
            type: 'INCREASE',
            payload: 1,
        });

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
        <>
            <div className={cx('wrapper')} onClick={() => handleAddToCart(data)}>
                <Row>
                    <Col>
                        <Image src={data.image} alt="product-img" width={100} />
                    </Col>
                    <Col>
                        <h3>{data.name}</h3>
                        <h3 className={cx('price')}>
                            {data.price.toLocaleString('vi-VN', {
                                style: 'currency',
                                currency: 'VND',
                            })}
                        </h3>
                    </Col>
                </Row>
            </div>
        </>
    );
};

export default PostItem;
