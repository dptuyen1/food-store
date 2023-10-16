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
        <c:when test="${product.id == null}">
            <h5 class="text-center">Thêm sản phẩm mới</h5>
            <c:if test="${not empty msg}">
                <h6 class="text-center text-danger">${msg}</h6>
            </c:if>

            <form:form cssClass="w-50" modelAttribute="product" method="POST" action="" enctype="multipart/form-data">
                <div class="mb-3 mt-3">
                    <form:errors path="name" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="name" type="text" class="form-control" placeholder="Tên sản phẩm..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="price" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="price" type="number" class="form-control" placeholder="Giá sản phẩm..."/>
                </div>
                <!--                <div class="mb-3 mt-3">
                <form:errors path="active" element="div" cssClass="text-danger mb-2"/>
                <form:select path="active" class="form-select" aria-label="Default select example">
                    <c:choose>
                        <c:when test="${product.active}">
                            <option value="${product.active}" selected>Hoạt động</option>
                            <option value="${!product.active}">Ngừng hoạt động</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${product.active}">Hoạt động</option>
                            <option value="${!product.active}" selected>Ngừng hoạt động</option>
                        </c:otherwise>
                    </c:choose>
                </form:select>
            </div>-->
                <div class="mb-3 mt-3">
                    <form:errors path="categoryId" element="div" cssClass="text-danger mb-2"/>
                    <form:select path="categoryId" class="form-select" aria-label="Default select example">
                        <c:forEach items="${categories}" var="category">
                            <c:choose>
                                <c:when test="${product.categoryId.id == category.id}">
                                    <option value="${category.id}" selected>${category.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${category.id}">${category.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="mb-3 mt-3">
                    <label class="form-label">Hình ảnh:</label>
                    <form:input path="file" type="file" class="form-control" aria-label="Default select example" accept="image/*" required="required"/>
                </div>
                <button type="submit" class="btn btn-primary">Thêm</button>
            </form:form>
        </c:when>

        <c:otherwise>
            <h5 class="text-center">Tùy chỉnh sản phẩm</h5>
            <c:if test="${not empty msg}">
                <h6 class="text-center text-danger">${msg}</h6>
            </c:if>

            <c:url value="/products/add-or-update" var="action" />
            <form:form cssClass="w-50" modelAttribute="product" method="POST" action="${action}" enctype="multipart/form-data">
                <form:hidden path="id"/>
                <form:hidden path="image"/>
                <div class="mb-3 mt-3">
                    <form:errors path="name" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="name" type="text" class="form-control" placeholder="Tên sản phẩm..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="price" element="h6" cssClass="text-danger mb-2 mb-2"/>
                    <form:input path="price" type="text" class="form-control" placeholder="Giá sản phẩm..."/>
                </div>
                <div class="mb-3 mt-3">
                    <form:errors path="active" element="div" cssClass="text-danger mb-2"/>
                    <form:select path="active" class="form-select" aria-label="Default select example">
                        <c:choose>
                            <c:when test="${product.active}">
                                <option value="true" selected>Hoạt động</option>
                                <option value="false">Ngừng hoạt động</option>
                            </c:when>
                            <c:otherwise>
                                <option value="true">Hoạt động</option>
                                <option value="false" selected>Ngừng hoạt động</option>
                            </c:otherwise>
                        </c:choose>
                    </form:select>
                </div>
                <div class="mb-3">
                    <form:errors path="categoryId" element="div" cssClass="text-danger mb-2"/>
                    <form:select path="categoryId" class="form-select" aria-label="Default select example">
                        <c:forEach items="${categories}" var="category">
                            <c:choose>
                                <c:when test="${product.categoryId.id == category.id}">
                                    <option value="${category.id}" selected>${category.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${category.id}">${category.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="mb-3 mt-3">
                    <label class="form-label">Hình ảnh:</label>
                    <img src="${product.image}" alt="product-image" width="100px"/>
                </div>
                <div class="mb-3 mt-3">
                    <label class="form-label">Hình ảnh:</label>
                    <form:input path="file" type="file" class="form-control" aria-label="Default select example" accept="image/*"/>
                </div>
                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </form:form>
        </c:otherwise>
    </c:choose>
</div>
