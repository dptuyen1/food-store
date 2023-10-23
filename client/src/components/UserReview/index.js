import { useState } from 'react';
import { Button, Col, Form, Image, Row } from 'react-bootstrap';

import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useUserContext } from '~/hooks';
import { reviewService } from '~/services/review';

import classNames from 'classnames/bind';
import images from '~/assets/Images';
import styles from './UserReview.module.scss';

const cx = classNames.bind(styles);

const UserReview = ({ productId, setReviews }) => {
    const [user] = useUserContext();

    const [content, setContent] = useState('');

    const handleAddReview = async (e, content) => {
        e.preventDefault();

        let res = await reviewService.add(content, productId);

        setReviews((prev) => [res.data, ...prev]);

        setContent('');
    };

    return (
        <div className="p-3 my-5">
            <Row>
                <Col>
                    <Image src={user.avatar || images['no-image']} alt="avatar" width={40} roundedCircle />
                </Col>
                <Col md={11}>
                    <Form onSubmit={(e) => handleAddReview(e, content)}>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                            <Form.Control
                                as="textarea"
                                style={{ fontSize: '1.6rem' }}
                                placeholder="Góp ý cho chúng tôi thông qua trải nghiệm của bạn về sản phẩm..."
                                value={content}
                                onChange={(e) => setContent(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Button variant="success" type="submit" className="" style={{ fontSize: '1.6rem' }}>
                            <FontAwesomeIcon icon={faPaperPlane} />
                        </Button>
                    </Form>
                </Col>
            </Row>
        </div>
    );
};

export default UserReview;
