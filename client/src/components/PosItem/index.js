import { Col, Image, Row } from 'react-bootstrap';
import cookie from 'react-cookies';

import classNames from 'classnames/bind';
import styles from './PostItem.module.scss';

import { useCartContext } from '~/hooks';

const cx = classNames.bind(styles);

const PostItem = ({ data }) => {
    const [, dispatch] = useCartContext();

    // const [cart, setCart] = useState(cookie.load('cart') || {});

    const handleAddToCart = (product) => {
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
        <>
            <div className={cx('wrapper')} onClick={() => handleAddToCart(data)}>
                <Row>
                    <Col>
                        <Image src={data.image} alt="product-img" width={100} />
                    </Col>
                    <Col md={9}>
                        <h2>{data.name}</h2>
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
