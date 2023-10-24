import { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';
import cookie from 'react-cookies';

import { productService } from '~/services/product';

import Monitor from '~/components/Monitor';
import PostItem from '~/components/PosItem';

import classNames from 'classnames/bind';
import styles from './InStore.module.scss';

const cx = classNames.bind(styles);

const InStore = () => {
    const [products, setProducts] = useState([]);

    const [cart, setCart] = useState(cookie.load('cart') || {});

    useEffect(() => {
        const loadProducts = async () => {
            let res = await productService.getProducts();

            setProducts(res.data);
        };

        loadProducts();
    }, []);

    const handleClearCart = () => {
        setCart({});
    };

    const handleUpdateCart = (item) => {
        if (item.id in cart) {
            setCart((current) => {
                delete current[item.id];
                cookie.save('cart', current);

                if (!Object.keys(current).length) cookie.remove('cart');

                return current;
            });
        }
    };

    return (
        <>
            <h1 className={cx('heading')}>Hân hạnh được phục vụ quý khách, xin mời chọn món!</h1>

            <Row className="my-5 gap-5">
                <Col>
                    <Monitor cart={cart} handleClearCart={handleClearCart} handleUpdateCart={handleUpdateCart} />
                </Col>
                <Col>
                    <Row>
                        {products.length > 0 &&
                            products.map((product) => {
                                return (
                                    <Col md={6} key={product.id}>
                                        <PostItem key={product.id} cart={cart} data={product} />
                                    </Col>
                                );
                            })}
                    </Row>
                </Col>
                {/* <Col md={6}>
                    {products.length > 0 &&
                        products.map((product) => {
                            return <PostItem key={product.id} cart={cart} data={product} />;
                        })}
                </Col> */}
            </Row>
        </>
    );
};

export default InStore;
