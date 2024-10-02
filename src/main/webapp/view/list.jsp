<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Font Bath Destroyer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .person-image {
            max-width: 100px;
            max-height: 100px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/persons">Font Bath Destroyer</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/persons?action=create">Thêm cá thể Font Bath Mới</a>
                </li>
            </ul>
            <form class="d-flex me-3" action="${pageContext.request.contextPath}/persons" method="get">
                <div class="input-group">
                    <span class="input-group-text">Tìm kiếm</span>
                    <select class="form-select" name="searchtype" style="max-width: 120px;">
                        <option value="id">ID</option>
                        <option value="name">Tên</option>
                    </select>
                    <input class="form-control" type="search" placeholder="Nhập từ khóa" name="keyword">
                    <button class="btn btn-outline-success" type="submit">Tìm</button>
                </div>
            </form>
            <form class="d-flex" action="${pageContext.request.contextPath}/persons" method="get">
                <div class="input-group">
                    <span class="input-group-text">Sắp xếp</span>
                    <select class="form-select" name="sortby" style="max-width: 120px;">
                        <option value="ID">ID</option>
                        <option value="Name">Tên</option>
                    </select>
                    <select class="form-select" name="order" style="max-width: 120px;">
                        <option value="asc">Tăng dần</option>
                        <option value="desc">Giảm dần</option>
                    </select>
                    <button class="btn btn-outline-primary" type="submit">Sắp xếp</button>
                </div>
            </form>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h2>Danh sách cá thể Font Bath</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Giới Tính</th>
            <th>Tuổi</th>
            <th>Ghi Chú</th>
            <th>Ảnh</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="person" items="${listPerson}">
            <tr>
                <td><c:out value="${person.id}"/></td>
                <td><c:out value="${person.name}"/></td>
                <td><c:out value="${person.gender}"/></td>
                <td><c:out value="${person.age}"/></td>
                <td><c:out value="${person.inscription}"/></td>
                <td>
                    <img src="<c:out value="${person.image}"/>" alt="Person Image" class="person-image">
                </td>
                <td>
                    <a href="/persons?action=edit&id=${person.id}" class="btn btn-sm btn-primary">Edit</a>
                    <c:url var="deleteUrl" value="/persons">
                        <c:param name="action" value="delete"/>
                        <c:param name="id" value="${person.id}"/>
                    </c:url>
                    <a href="${deleteUrl}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete person with ID ${person.id}?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="/persons?page=${currentPage - 1}&searchtype=${searchtype}&keyword=${keyword}&sortby=${sortby}&order=${order}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active">
                            <span class="page-link">${i}</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="/persons?page=${i}&searchtype=${searchtype}&keyword=${keyword}&sortby=${sortby}&order=${order}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt totalPages}">
                <li class="page-item">
                    <a class="page-link" href="/persons?page=${currentPage + 1}&searchtype=${searchtype}&keyword=${keyword}&sortby=${sortby}&order=${order}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>

    <p class="text-center">
        Showing ${(currentPage - 1) * recordsPerPage + 1} to ${currentPage * recordsPerPage > totalRecords ? totalRecords : currentPage * recordsPerPage} of ${totalRecords} entries
    </p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>