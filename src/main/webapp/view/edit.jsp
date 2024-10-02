<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Person Management Application</title>
</head>
<body>
<h1>Person Management</h1>
<h2>
    <a href="persons?action=persons">List All Persons</a>
</h2>

<div>
    <form method="post">
        <table>
            <caption>
                <h2>Edit Person</h2>
            </caption>
            <c:if test="${person != null}">
                <input type="hidden" name="id" value="<c:out value='${person.id}' />"/>
            </c:if>
            <tr>
                <th>Tên: </th>
                <td>
                    <input type="text" name="name" value="<c:out value='${person.name}' />"/>
                </td>
            </tr>
            <tr>
                <th>Giới tính: </th>
                <td>
                    <input type="text" name="gender" value="<c:out value='${person.gender}' />"/>
                </td>
            </tr>
            <tr>
                <th>Tuổi: </th>
                <td>
                    <input type="number" name="age" value="<c:out value='${person.age}' />"/>
                </td>
            </tr>
            <tr>
                <th>Ghi Chú: </th>
                <td>
                    <input type="text" name="inscription" value="<c:out value='${person.inscription}' />"/>
                </td>
            </tr>
            <tr>
                <th>Ảnh URL: </th>
                <td>
                    <input type="text" name="image" value="<c:out value='${person.image}' />"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>