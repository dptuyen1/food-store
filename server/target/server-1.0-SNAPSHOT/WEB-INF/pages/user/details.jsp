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
    <c:choose>
        <c:when test="${user.id == null}">
            <h5 class="text-center">Đăng ký tài khoản</h5>
            <c:if test="${not empty msg}">
                <h6 class="text-center text-danger">${msg}</h6>
            </c:if>

            <form:form cssClass="w-50" modelAttribute="user" method="POST" action="" enctype="multipart/form-data">
                <div class="mb-3 mt-3">
                    <form:errors path="lastName" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="lastName" type="text" class="form-control" placeholder="Họ..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="firstName" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="firstName" type="text" class="form-control" placeholder="Tên..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="email" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="email" type="email" class="form-control" placeholder="Nhập email..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="address" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="address" type="text" class="form-control" placeholder="Nhập địa chỉ..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="phoneNumber" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="phoneNumber" type="text" maxlength="20" class="form-control" placeholder="Nhập số điện thoại..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="username" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="username" type="text" class="form-control" placeholder="Nhập tài khoản..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="password" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="password" type="password" class="form-control" placeholder="Nhập mật khẩu..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="confirm" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="confirm" type="password" class="form-control" placeholder="Xác nhận mật khẩu..."/>
                </div>
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </form:form>
        </c:when>

        <c:otherwise>
            <h5 class="text-center">Tùy chỉnh tài khoản</h5>
            <c:if test="${not empty msg}">
                <h6 class="text-center text-danger">${msg}</h6>
            </c:if>

            <form:form cssClass="w-50" modelAttribute="user" method="POST" action="" enctype="multipart/form-data">
                <form:hidden path="createdDate"/>
                <form:hidden path="avatar"/>
                <form:hidden path="password"/>
                <div class="mb-3 mt-3">
                    <form:errors path="lastName" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="lastName" type="text" class="form-control" readonly="true"/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="firstName" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="firstName" type="text" class="form-control" readonly="true"/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="email" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="email" type="email" class="form-control" readonly="true"/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="phoneNumber" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="phoneNumber" type="tel" maxlength="11" class="form-control" readonly="true"/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="address" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="address" type="text" class="form-control" readonly="true"/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="username" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="username" type="text" class="form-control" readonly="true"/>
                </div>
                <form:select path="roleId" class="form-select mb-3 mt-3" aria-label="Default select example">
                    <c:forEach items="${roles}" var="role">
                        <c:choose>
                            <c:when test="${user.roleId.id == role.id}">
                                <option value="${role.id}" selected>${role.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${role.id}">${role.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </form:form>
        </c:otherwise>
    </c:choose>
</div>
