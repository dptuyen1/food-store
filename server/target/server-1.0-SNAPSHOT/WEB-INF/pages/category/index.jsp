<%--
    Document   : index
    Created on : Sep 27, 2023, 1:23:19 AM
    Author     : dptuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<div class="d-flex align-items-center justify-content-between my-3">
    <h5 class="text-center">Quản lý danh mục</h5>
    <a href="/categories/add-or-update" class="btn btn-success">
        <i class="fa-regular fa-square-plus"></i>
        Thêm
    </a>
</div>
<table class="table table-hover text-center">
    <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Tên</th>
            <th scope="col">Tùy chỉnh</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${categories}" var="category">
            <tr>
                <th scope="row">${category.id}</th>
                <td>${category.name}</td>
                <td>
                    <a href="/categories/${category.id}" class="btn btn-warning btn-sm text-white">
                        <i class="fa-solid fa-pen"></i>
                    </a>
                    <c:url value="/api/categories/${category.id}" var="api"/>
            <!--                    <button type="button" class="btn btn-danger btn-sm" onclick="deleteProduct('${api}')">
                    <i class="fa-solid fa-trash"></i>
                </button>-->
                    <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title fs-6" id="exampleModalLabel">
                    <i class="fa-solid fa-circle-exclamation"></i>
                    Hệ thống
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Xóa dữ liệu này đồng nghĩa với việc xóa luôn cả những thông tin liên quan như: sản phẩm, hóa đơn! <br/> Bạn có chắc muốn xóa không?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-danger" onclick="deleteData('${api}')">Xóa</button>
            </div>
        </div>
    </div>
</div>