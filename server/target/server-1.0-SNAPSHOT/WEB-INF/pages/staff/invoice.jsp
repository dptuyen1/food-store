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
<jsp:useBean id="now" class="java.util.Date"/>
<h5 class="text-center my-4">Doanh thu ngày <f:formatDate value="${now}" pattern="dd-MM-yyyy"/>
</h5>

<table class="table table-hover text-center">
    <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Ngày đặt</th>
            <th scope="col">Số lượng sản phẩm</th>
            <th scope="col">Giảm giá</th>
            <th scope="col">Tổng tiền</th>
            <th scope="col">Phục vụ</th>
        </tr>
    </thead>
    <tbody>
        <c:set var="totalPrice" value="0" />
        <c:forEach items="${invoices}" var="invoice">
            <c:set var="totalPrice" value="${totalPrice + invoice.totalPrice}" />
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
            </tr>
        </c:forEach>
    </tbody>
</table>

<h5 class="text-end text-danger my-4">Tổng doanh thu: ${totalPrice} VND</h5>
