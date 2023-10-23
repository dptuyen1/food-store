<%--
    Document   : test
    Created on : Oct 15, 2023, 6:28:25 PM
    Author     : dptuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<sec:authorize access="hasRole('ROLE_STAFF')">
    <h5 class="text-center my-4">Trang quản lý bán hàng</h5>

    <table class="table table-hover text-center">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Ngày đặt</th>
                <th scope="col">Số lượng sản phẩm</th>
                <th scope="col">Giảm giá</th>
                <th scope="col">Tổng tiền</th>
                <th scope="col">Phục vụ</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Chi tiết</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${invoices}" var="invoice">
                <tr>
                    <th scope="row">${invoice.id}</th>
                    <td>${invoice.createdDate}</td>
                    <td>${invoice.totalQuantity}</td>
                    <td>${invoice.discountPrice} VND</td>
                    <td>${invoice.totalPrice} VND</td>
                    <c:choose>
                        <c:when test="${invoice.shoppingId.name == 'SHOPPING_INSTORE'}">
                            <td>Tại quán</td>
                        </c:when>
                        <c:otherwise>
                            <td>Trực tuyến</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${invoice.statusId.name == 'STATUS_PENDING'}">
                            <td>Chờ xác nhận...</td>
                        </c:when>
                        <c:otherwise>
                            <td>Hoàn tất</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:url value="/api/invoices/${invoice.id}" var="api"/>
                        <c:if test="${invoice.statusId.id == 1}">
                            <button type="button" class="btn btn-primary btn-sm" onclick="handleOrder(2, '${api}', '${user.id}')">
                                Xác nhận đơn
                            </button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="handleOrder(3, '${api}', '${user.id}')">
                                Hủy đơn
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</sec:authorize>

<script>

    window.onload = () => {
        refresh();
    };

    const handleOrder = (statusId, path, userId) => {
        fetch(path, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                statusId: statusId,
                userId: userId
            })
        }).then(res => {
            if (res.status === 200) {
                location.reload();
            } else {
                alert("Có lỗi xảy ra!");
            }
        });
    };

    const refresh = () => {
        setTimeout(() => {
            location.reload();
        }, 10000);
    };
</script>