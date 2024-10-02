<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Font Bath Destroyer</title>
</head>
<body>
<h1>Font Bath Destroyer</h1>
<h2>
    <a href="/persons?action=create">Thêm cá thể Font Bath Mới</a>
</h2>
<form action="/persons" method="get">
    <label>Tìm kiếm theo</label>
    <select name="searchtype">
        <option name="id" id="id">ID</option>
        <option name="name" id="name">Name</option>
    </select>
    <input type="text" name="keyword">
    <button type="submit">Search</button>
</form>
<div>
    <table>
        <caption><h2>Danh sách cá thể Font Bath</h2></caption>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Giới Tính</th>
            <th>Tuổi</th>
            <th>Ghi Chú</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="person" items="${listPerson}">
            <tr>
                <td><c:out value="${person.id}"/></td>
                <td><c:out value="${person.name}"/></td>
                <td><c:out value="${person.gender}"/></td>
                <td><c:out value="${person.age}"/></td>
                <td><c:out value="${person.inscription}"/></td>
                <td>
                    <a href="/persons?action=edit&id=${person.id}">Edit</a>
                    <c:url var="deleteUrl" value="/persons">
                        <c:param name="action" value="delete"/>
                        <c:param name="id" value="${person.id}"/>
                    </c:url>
                    <a href="${deleteUrl}" onclick="return confirm('Are you sure you want to delete person with ID ${person.id}?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br> <br>
    <form action="/persons" method="get">
        <label>Sort Persons By</label>
        <select name="sortby">
            <option value="ID">ID</option>
            <option value="Name">Name</option>
        </select>
        <select name="order">
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
        </select>
        <button type="submit">Sort</button>
    </form>
</div>
</body>
</html>