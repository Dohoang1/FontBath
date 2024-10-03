<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Person Management Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Person Management</h1>
    <div class="text-center mb-4">
        <a href="persons?action=persons" class="btn btn-primary">List All Persons</a>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h2 class="text-center">Edit Person</h2>
                </div>
                <div class="card-body">
                    <form method="post">
                        <c:if test="${person != null}">
                            <input type="hidden" name="id" value="<c:out value='${person.id}' />"/>
                        </c:if>
                        <div class="mb-3">
                            <label for="name" class="form-label">Tên:</label>
                            <input type="text" class="form-control" id="name" name="name" value="<c:out value='${person.name}' />"/>
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Giới tính:</label>
                            <input type="text" class="form-control" id="gender" name="gender" value="<c:out value='${person.gender}' />"/>
                        </div>
                        <div class="mb-3">
                            <label for="age" class="form-label">Tuổi:</label>
                            <input type="number" class="form-control" id="age" name="age" value="<c:out value='${person.age}' />"/>
                        </div>
                        <div class="mb-3">
                            <label for="inscription" class="form-label">Ghi Chú:</label>
                            <input type="text" class="form-control" id="inscription" name="inscription" value="<c:out value='${person.inscription}' />"/>
                        </div>
                        <div class="mb-3">
                            <label for="image" class="form-label">Ảnh URL:</label>
                            <input type="text" class="form-control" id="image" name="image" value="<c:out value='${person.image}' />"/>
                        </div>
                        <div class="d-grid">
                            <input type="submit" class="btn btn-primary" value="Save"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>