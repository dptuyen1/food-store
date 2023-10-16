import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import classNames from 'classnames/bind';
import styles from './Search.module.scss';

const cx = classNames.bind(styles);

const Search = () => {
    const [keyword, setKeyword] = useState('');

    const nav = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        nav(`/?kw=${keyword}`);

        setKeyword('');
    };

    return (
        <form className="d-flex gap-2" onSubmit={handleSubmit}>
            <input
                className={cx('search-input')}
                placeholder="Nhập từ khóa..."
                name="kw"
                value={keyword}
                onChange={(e) => setKeyword(e.target.value)}
                required
            />
            <button type="submit" className={cx('search-btn')}>
                <FontAwesomeIcon icon={faMagnifyingGlass} />
            </button>
        </form>
    );
};

export default Search;
