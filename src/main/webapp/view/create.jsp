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
                <h2>Thêm cá thể Font Bath Mới</h2>
            </caption>
            <tr>
                <th>Tên: </th>
                <td>
                    <input type="text" name="name" id="name">
                </td>
            </tr>
            <tr>
                <th>Giới tính: </th>
                <td>
                    <input type="text" name="gender" id="gender">
                </td>
            </tr>
            <tr>
                <th>Tuổi: </th>
                <td>
                    <input type="number" name="age" id="age">
                </td>
            </tr>
            <tr>
                <th>Ghi Chú: </th>
                <td>
                    <input type="text" name="inscription" id="inscription">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>