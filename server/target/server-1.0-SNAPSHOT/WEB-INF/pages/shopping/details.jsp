<%--
    Document   : shopping
    Created on : Sep 19, 2023, 12:13:08 PM
    Author     : dptuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<div class="d-flex flex-column align-items-center">
    <c:choose>
        <c:when test="${shopping.id == null}">
            <h5 class="text-center">Thêm phương thức phục vụ mới</h5>
        </c:when>

        <c:otherwise>
            <h5 class="text-center">Tùy chỉnh phương thức phục vụ</h5>
        </c:otherwise>
    </c:choose>

    <c:if test="${not empty msg}">
        <h6 class="text-center text-danger">${msg}</h6>
    </c:if>

    <c:url value="/shopping/add-or-update" var="action"/>
    <form:form cssClass="w-50" modelAttribute="shopping" method="POST" action="${action}" enctype="multipart/form-data">
        <form:hidden path="id"/>
        <div class="mb-3 mt-3">
            <form:errors path="name" element="h6" cssClass="text-danger mb-2 mb-2"/>
            <form:input path="name" type="text" class="form-control" placeholder="Tên phương thức phục vụ..."/>
        </div>
        <c:choose>
            <c:when test="${shopping.id == null}">
                <button type="submit" class="btn btn-primary">Thêm</button>
            </c:when>
            <c:otherwise>
                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </c:otherwise>
        </c:choose>
    </form:form>
</div>
