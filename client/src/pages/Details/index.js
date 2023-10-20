import { useEffect, useState } from 'react';
import { Col, Image, Row } from 'react-bootstrap';
import { useParams } from 'react-router-dom';

import Review from '~/components/Review';
import UserReview from '~/components/UserReview';
import { useUserContext } from '~/hooks';
import { productService } from '~/services/product';
import { reviewService } from '~/services/review';

import classNames from 'classnames/bind';
import styles from './Details.module.scss';

const cx = classNames.bind(styles);

const Details = () => {
    const { id } = useParams();

    const [product, setProduct] = useState(null);
    const [reviews, setReviews] = useState([]);

    const [user] = useUserContext();

    useEffect(() => {
        const loadProduct = async () => {
            let res = await productService.getById(id);

            setProduct(res.data);
        };

        const loadReviews = async () => {
            let res = await reviewService.getReviews(id);

            setReviews(res.data);
        };

        loadProduct();
        loadReviews();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <>
            <h1 className={cx('heading')}>Chi tiết sản phẩm</h1>

            {!!product && (
                <>
                    <div className="d-flex justify-content-center my-5">
                        <div className={cx('wrapper')}>
                            <Row>
                                <Col>
                                    <Image src={product.image} alt="product-img" width={250} />
                                </Col>
                                <Col>
                                    <h2>{product.name}</h2>
                                    <h3 className={cx('price')}>
                                        {product.price.toLocaleString('vi-VN', {
                                            style: 'currency',
                                            currency: 'VND',
                                        })}
                                    </h3>
                                </Col>
                            </Row>
                        </div>
                    </div>

                    <div className={cx('comment-list')}>
                        <h1 className={cx('sub-heading')}>Đánh giá sản phẩm ({reviews.length})</h1>
                        {!!user && <UserReview productId={id} setReviews={setReviews} />}
                        {reviews.length > 0 &&
                            reviews.map((review) => {
                                return <Review key={review.id} data={review} />;
                            })}
                    </div>
                </>
            )}
        </>
    );
};

export default Details;
