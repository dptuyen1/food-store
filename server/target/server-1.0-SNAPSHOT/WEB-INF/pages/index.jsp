<%--
    Document   : index
    Created on : Sep 27, 2023, 1:23:19 AM
    Author     : dptuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <h5 class="text-center my-4">Trang quản trị viên</h5>

    <div class="row">
        <h5 class="my-4">Thống kê số lượng sản phẩm bán ra</h5>

        <div class="col">
            <form>
                <div class="form-group mb-3">
                    <label class="form-label">Năm</label>
                    <select class="form-select" name="year">
                        <option value="" selected>Tất cả</option>
                        <c:forEach items="${years}" var="year">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group mb-3">
                    <label class="form-label">Tháng</label>
                    <select class="form-select" name="month">
                        <option value="" selected>Tất cả</option>
                        <c:forEach begin="1" end="12" var="order">
                            <option value="${order}">${order}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group mb-3">
                    <label class="form-label">Quý</label>
                    <select class="form-select" name="quarter">
                        <option value="" selected>Tất cả</option>
                        <c:forEach begin="1" end="4" var="order">
                            <option value="${order}">${order}</option>
                        </c:forEach>
                    </select>
                </div>


                <button type="submit" class="btn btn-primary">Thống kê</button>
            </form>

            <div style="max-height: 300px; overflow-y: scroll">
                <table class="table table-hover my-4 text-center">
                    <thead>
                        <tr>
                            <th scope="col">Tên</th>
                            <th scope="col">Số lượng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <th scope="row">${product[0]}</th>
                                <td>${product[1]}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col">
            <canvas id="product-chart"></canvas>
        </div>
    </div>

    <div class="row">
        <h5 class="my-4">Thống kê hóa đơn theo từng năm</h5>

        <div class="col">
            <form>
                <div class="form-group mb-3">
                    <label class="form-label">Tháng</label>
                    <select class="form-select" name="month">
                        <option value="" selected>Tất cả</option>
                        <c:forEach begin="1" end="12" var="order">
                            <option value="${order}">${order}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group mb-3">
                    <label class="form-label">Quý</label>
                    <select class="form-select" name="quarter">
                        <option value="" selected>Tất cả</option>
                        <c:forEach begin="1" end="4" var="order">
                            <option value="${order}">${order}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Thống kê</button>
            </form>

            <table class="table table-hover my-4 text-center">
                <thead>
                    <tr>
                        <th scope="col">Năm</th>
                        <th scope="col">Tổng hóa đơn</th>
                        <th scope="col">Tổng sản phẩm bán ra</th>
                        <th scope="col">Doanh thu</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="totalInvoice" value="0" />
                    <c:set var="totalQuantity" value="0" />
                    <c:set var="totalPrice" value="0" />
                    <c:forEach items="${invoices}" var="invoice">
                        <c:set var="totalInvoice" value="${totalInvoice + invoice[1]}" />
                        <c:set var="totalQuantity" value="${totalQuantity + invoice[2]}" />
                        <c:set var="totalPrice" value="${totalPrice + invoice[3]}" />
                        <tr>
                            <th scope="row">${invoice[0]}</th>
                            <td>${invoice[1]}</td>
                            <td>${invoice[2]}</td>
                            <f:formatNumber value="${invoice[3]}" var="price" type="currency" currencyCode="VND"/>
                            <td>${price}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th scope="row">Tổng cộng</th>
                        <td>${totalInvoice}</td>
                        <td>${totalQuantity}</td>
                        <f:formatNumber value="${totalPrice}" var="price" type="currency" currencyCode="VND"/>
                        <td>${price}</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="col">
            <canvas id="invoice-chart"></canvas>
        </div>
    </div>
</sec:authorize>

<sec:authorize access="not hasRole('ROLE_ADMIN')">
    <h5 class="text-danger">Chỉ có quản trị viên mới được xem trang này!</h5>
</sec:authorize>


<script>
    const productCtx = document.getElementById('product-chart');
    const invoiceCtx = document.getElementById('invoice-chart');

    let bgColors = [];
    let productLabels = [], productInfo = [], productLabel = 'Thống kê sản phẩm';
    let invoiceLabels = [], invoiceInfo = [], invoiceLabel = 'Thống kê hóa đơn';

    <c:forEach items="${products}" var="product">
    productLabels.push("${product[0]}");
    productInfo.push("${product[1]}");

    rgb = `rgb(${Math.round(Math.random() * 255)}, ${Math.round(Math.random() * 255)}, ${Math.round(Math.random() * 255)})`;
    bgColors.push(rgb);
    </c:forEach>

    <c:forEach items="${invoices}" var="invoice">
    invoiceLabels.push("${invoice[0]}");
    invoiceInfo.push("${invoice[2]}");

    rgb = `rgb(${Math.round(Math.random() * 255)}, ${Math.round(Math.random() * 255)}, ${Math.round(Math.random() * 255)})`;
    bgColors.push(rgb);
    </c:forEach>

    const setData = (labels, label, info) => {
        return {
            labels: labels,
            datasets: [{
                    label: label,
                    data: info,
                    backgroundColor: bgColors,
                    hoverOffset: 4
                }]
        };
    };
    const setConfig = (type, data) => {
        return {
            type: type,
            data: data,
            options: {
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        labels: {
                            font: {
                                size: 16
                            }
                        },
                        position: 'bottom'
                    }
                }
            }
        };
    };

    let data = {}, config = {};

    data = setData(productLabels, productLabel, productInfo);
    config = setConfig("bar", data);
    new Chart(productCtx, config);

    data = setData(invoiceLabels, invoiceLabel, invoiceInfo);
    config = setConfig("bar", data);
    new Chart(invoiceCtx, config);
</script>