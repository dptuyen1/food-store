<%--
    Document   : index
    Created on : Sep 27, 2023, 1:23:19 AM
    Author     : dptuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<div class="d-flex flex-column align-items-center">
    <h5 class="text-center">Đăng nhập</h5>

    <c:if test="${param.error != null}">
        <h5 class="text-center text-danger">
            Sai tài khoản hoặc mật khẩu!
        </h5>
    </c:if>

    <c:if test="${param.accessDenied != null}">
        <h5 class="text-center text-danger">
            Bạn không có quyền truy cập vào trang này!
        </h5>
    </c:if>

    <c:if test="${param.logout != null}">
        <h5 class="text-center text-danger">
            Bạn đã đăng xuất khỏi hệ thống!
        </h5>
    </c:if>

    <form class="w-50" method="post" action="">
        <div class="mb-3">
            <label for="username" class="form-label">Tài khoản:</label>
            <input type="text" class="form-control" id="username" placeholder="Nhập tài khoản..." name="username" />
        </div>
        <div class="mb-3">
            <label for="pwd" class="form-label">Mật khẩu:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Nhập mật khẩu..." name="password" />
        </div>
        <button type="submit" class="btn btn-primary">Đăng nhập</button>
    </form>
</div>
