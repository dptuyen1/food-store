import { useEffect, useState } from 'react';
import { Col, Nav, Row } from 'react-bootstrap';
import { useSearchParams } from 'react-router-dom';

import Category from '~/components/Category';
import Product from '~/components/Product';
import { categoryService } from '~/services/category';
import { productService } from '~/services/product';

import classNames from 'classnames/bind';
import styles from './Home.module.scss';

const cx = classNames.bind(styles);

const Home = () => {
    const [categories, setCategories] = useState([]);
    const [products, setProducts] = useState([]);

    const [query, setQuery] = useSearchParams();

    useEffect(() => {
        const loadCategories = async () => {
            let res = await categoryService.getCategories();

            setCategories(res.data);
        };

        const loadProducts = async () => {
            let kw = query.get('kw');
            let categoryId = query.get('categoryId');

            let res = await productService.getProducts(kw, categoryId);

            setProducts(res.data);
        };

        loadCategories();
        loadProducts();
    }, [query]);

    const handleClick = (e) => {
        e.preventDefault();

        setQuery('');
    };

    return (
        <>
            <h1 className={cx('heading')}>Sản phẩm của chúng tôi</h1>

            <Nav className={cx('category-list')}>
                <Nav.Link className={cx('category')} href="/" onClick={handleClick}>
                    Tất cả
                </Nav.Link>
                {categories.map((category) => {
                    return <Category key={category.id} data={category} />;
                })}
            </Nav>

            <Row>
                {products.length > 0 ? (
                    products.map((product) => {
                        return (
                            <Col xs={4} md={2} key={product.id}>
                                <Product data={product} />
                            </Col>
                        );
                    })
                ) : (
                    <p style={{ fontSize: '2.4rem' }}>Không có sản phẩm!</p>
                )}
            </Row>
        </>
    );
};

export default Home;
