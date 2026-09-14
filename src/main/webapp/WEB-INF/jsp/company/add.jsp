<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Додати компанію</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5" style="max-width: 500px;">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Додати нову компанію</h4>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/companies/add" method="post">

                <div class="mb-3">
                    <label class="form-label">Назва компанії:</label>
                    <input type="text" name="name" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">URL (Посилання):</label>
                    <input type="url" name="url" class="form-control" placeholder="https://example.com">
                </div>

                <div class="d-flex justify-content-between mt-4">
                    <a href="${pageContext.request.contextPath}/companies" class="btn btn-outline-secondary">Скасувати</a>
                    <button type="submit" class="btn btn-success">Зберегти</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>