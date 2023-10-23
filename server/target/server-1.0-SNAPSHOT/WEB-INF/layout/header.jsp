<%--
    Document   : base
    Created on : Sep 27, 2023, 1:11:50 AM
    Author     : dptuy
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header class="p-3 bg-light text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="<c:url value="/" />" class="nav-link px-2 text-dark">Food Store Management</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <button class="btn btn-outline-secondary border border-0" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling"><i class="fa-solid fa-bars"></i></button>

                    <div class="offcanvas offcanvas-start bg-light" data-bs-scroll="true" data-bs-backdrop="true" tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
                        <div class="offcanvas-header">
                            <h6 class="offcanvas-title" id="offcanvasScrollingLabel">Quản lý</h6>
                            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                        </div>

                        <hr class="m-0 p-0"/>

                        <div class="offcanvas-body">
                            <a href="<c:url value="/categories" />" class="nav-link px-2 text-dark link-primary">Danh mục</a>
                            <a href="<c:url value="/products" />" class="nav-link px-2 text-dark link-primary">Sản phẩm</a>
                            <a href="<c:url value="/coupons" />" class="nav-link px-2 text-dark link-primary">Ưu đãi</a>
                            <!--<a href="<c:url value="/shopping" />" class="nav-link px-2 text-dark link-primary">Phương thức phục vụ</a>-->
                            <a href="<c:url value="/users" />" class="nav-link px-2 text-dark link-primary">Người dùng</a>
                            <a href="<c:url value="/roles" />" class="nav-link px-2 text-dark link-primary">Quyền người dùng</a>
                        </div>
                    </div>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_STAFF')">
                    <a href="<c:url value="/pos" />" class="nav-link px-2 text-dark link-primary">Bán hàng</a>
                    <a href="<c:url value="/invoices" />" class="nav-link px-2 text-dark link-primary">Doanh thu ngày</a>
                </sec:authorize>
            </ul>

            <div class="text-end">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <a href="#" class="btn btn-outline-primary me-2">
                            ${pageContext.request.userPrincipal.name}
                        </a>
                        <a href="<c:url value="/logout" />" class="btn btn-outline-secondary">
                            <i class="fa-solid fa-arrow-right-from-bracket"></i>
                            Đăng xuất
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value="/login" />" class="btn btn-info me-2">Đăng nhập</a>
                        <a href="<c:url value="/signup" />" class="btn btn-warning"><i class="fa-solid fa-user-plus"></i> Đăng ký</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
</header>
