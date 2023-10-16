import { Nav } from 'react-bootstrap';

import classNames from 'classnames/bind';
import { useNavigate } from 'react-router-dom';
import styles from './Category.module.scss';

const cx = classNames.bind(styles);

const Category = ({ data }) => {
    const nav = useNavigate();

    const handleClick = (e) => {
        e.preventDefault();

        nav(`/?categoryId=${data.id}`);
    };

    return (
        <Nav.Link className={cx('category')} href="/" onClick={handleClick}>
            {data.name}
        </Nav.Link>
    );
};

export default Category;
