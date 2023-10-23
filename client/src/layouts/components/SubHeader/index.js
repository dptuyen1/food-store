import { Link } from 'react-router-dom';

import images from '~/assets/Images';

import classNames from 'classnames/bind';
import styles from './SubHeader.module.scss';

const cx = classNames.bind(styles);

const SubHeader = () => {
    return (
        <header className={cx('header')}>
            <div className={cx('wrapper')}>
                <div className="container">
                    <div className="d-flex justify-content-between">
                        <Link to="/">
                            <img className={cx('logo')} src={images.logo} alt="logo" />
                        </Link>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default SubHeader;
