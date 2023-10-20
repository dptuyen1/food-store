import classNames from 'classnames/bind';
// import Footer from '../components/Footer';
import Header from '../components/Header';
import styles from './SubLayout.module.scss';

const cx = classNames.bind(styles);

const SubLayout = ({ children }) => {
    return (
        <div className={cx('wrapper')}>
            <Header />
            <div className={cx('container')}>
                <div className={cx('content')}>{children}</div>
            </div>
            {/* <Footer /> */}
        </div>
    );
};

export default SubLayout;
