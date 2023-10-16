import moment from 'moment/moment';
import { useEffect, useState } from 'react';
import { Button, Modal, Table } from 'react-bootstrap';

import { faCircleInfo } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { detailsService } from '~/services/details';
import { invoiceService } from '~/services/invoice';

import classNames from 'classnames/bind';
import styles from './Tracking.module.scss';

const cx = classNames.bind(styles);

const Tracking = () => {
    const [invoices, setInvoices] = useState([]);
    const [details, setDetails] = useState([]);

    const [show, setShow] = useState(false);

    useEffect(() => {
        const loadInvoices = async () => {
            let res = await invoiceService.getInvoices();

            setInvoices(res.data);
        };

        loadInvoices();
    }, []);

    const handleModal = () => {
        setShow((show) => !show);
    };

    const handleViewDetails = async (invoiceId) => {
        handleModal();

        let res = await detailsService.getDetails(invoiceId);

        setDetails(res.data);
    };

    return (
        <>
            <h1 className={cx('heading')}>Lịch sử mua hàng</h1>

            {invoices.length > 0 ? (
                <Table hover className="text-center my-5">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Ngày đặt</th>
                            <th>Số lượng sản phẩm</th>
                            <th>Giảm giá</th>
                            <th>Tổng tiền</th>
                            <th>Chi tiết</th>
                        </tr>
                    </thead>
                    <tbody>
                        {invoices.map((invoice) => {
                            return (
                                <tr key={invoice.id}>
                                    <td>{invoice.id}</td>
                                    <td>{moment(invoice.createdDate).format('L LTS')}</td>
                                    <td>{invoice.totalQuantity}</td>
                                    <td>
                                        {invoice.discountPrice.toLocaleString('vi-VN', {
                                            style: 'currency',
                                            currency: 'VND',
                                        })}
                                    </td>
                                    <td>
                                        {invoice.totalPrice.toLocaleString('vi-VN', {
                                            style: 'currency',
                                            currency: 'VND',
                                        })}
                                    </td>

                                    <td>
                                        <button
                                            className={cx('btn btn-warning', 'button')}
                                            onClick={() => handleViewDetails(invoice.id)}
                                        >
                                            <FontAwesomeIcon icon={faCircleInfo} />
                                        </button>
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </Table>
            ) : (
                <p style={{ fontSize: '2.4rem' }}>Không có hóa đơn!</p>
            )}

            <Modal className={cx('modal')} show={show} onHide={handleModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Chi tiết hóa đơn</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Table hover className="text-center">
                        <thead>
                            <tr>
                                <th>Tên</th>
                                <th>Giá</th>
                                <th>Số lượng</th>
                                <th>Tổng tiền</th>
                            </tr>
                        </thead>
                        <tbody>
                            {details.map((detail) => {
                                return (
                                    <tr key={detail.id}>
                                        <td>{detail.productId.name}</td>
                                        <td>
                                            {detail.unitPrice.toLocaleString('vi-VN', {
                                                style: 'currency',
                                                currency: 'VND',
                                            })}
                                        </td>
                                        <td>{detail.quantity}</td>
                                        <td>
                                            {(detail.unitPrice * detail.quantity).toLocaleString('vi-VN', {
                                                style: 'currency',
                                                currency: 'VND',
                                            })}
                                        </td>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </Table>
                </Modal.Body>
                <Modal.Footer>
                    <Button className={cx('button')} variant="secondary" onClick={handleModal}>
                        Đóng
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
};

export default Tracking;
