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

    return (
        <>
            <Row>
                <Col md={6}>
                    {products.length > 0 &&
                        products.map((product) => {
                            return <PostItem key={product.id} data={product} />;
                        })}
                </Col>
                <Col>
                    <Monitor />
                </Col>
            </Row>
        </>
    );
};

export default InStore;
