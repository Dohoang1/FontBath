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
                    <h2 class="text-center">Thêm cá thể Font Bath Mới</h2>
                </div>
                <div class="card-body">
                    <form method="post">
                        <div class="mb-3">
                            <label for="name" class="form-label">Tên:</label>
                            <input type="text" class="form-control" name="name" id="name">
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Giới tính:</label>
                            <input type="text" class="form-control" name="gender" id="gender">
                        </div>
                        <div class="mb-3">
                            <label for="age" class="form-label">Tuổi:</label>
                            <input type="number" class="form-control" name="age" id="age">
                        </div>
                        <div class="mb-3">
                            <label for="inscription" class="form-label">Ghi Chú:</label>
                            <input type="text" class="form-control" name="inscription" id="inscription">
                        </div>
                        <div class="mb-3">
                            <label for="image" class="form-label">Ảnh URL:</label>
                            <input type="text" class="form-control" name="image" id="image">
                        </div>
                        <div class="d-grid">
                            <input type="submit" class="btn btn-primary" value="Save">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>