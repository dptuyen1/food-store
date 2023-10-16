import { forwardRef } from 'react';
import { Link } from 'react-router-dom';

import classNames from 'classnames/bind';
import styles from './Button.module.scss';

const cx = classNames.bind(styles);

const Button = forwardRef(
    (
        {
            to,
            href,
            children,
            primary = true,
            secondary = false,
            danger = false,
            relative = false,
            onClick,
            ...passProps
        },
        ref,
    ) => {
        let Comp = 'button';
        const props = { onClick, ...passProps };

        if (to) {
            props.to = to;
            Comp = Link;
        } else if (href) {
            props.href = href;
            Comp = 'a';
        }

        const classes = cx('wrapper', {
            primary,
            secondary,
            danger,
            relative,
        });

        return (
            <Comp className={classes} {...props} ref={ref}>
                <span>{children}</span>
            </Comp>
        );
    },
);

export default Button;
