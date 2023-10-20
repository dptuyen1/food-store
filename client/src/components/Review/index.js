import { Col, Image, Row } from 'react-bootstrap';

import moment from 'moment';
import 'moment/locale/vi';

import classNames from 'classnames/bind';
import images from '~/assets/Images';
import styles from './Review.module.scss';

const cx = classNames.bind(styles);

const Review = ({ data }) => {
    return (
        <div className={cx('wrapper', 'p-4', 'my-5')}>
            <Row>
                <Col>
                    <Image src={data.userId.avatar || images['no-image']} alt="avatar" width={40} roundedCircle />
                </Col>
                <Col md={11}>
                    <h4>
                        {data.userId.firstName} {data.userId.lastName}
                    </h4>
                    <p>{data.content}</p>
                    {!!data.updatedDate ? (
                        <p>{moment(data.updatedDate).fromNow()}</p>
                    ) : (
                        <p>{moment(data.createdDate).fromNow()}</p>
                    )}
                </Col>
            </Row>
        </div>
    );
};

export default Review;
