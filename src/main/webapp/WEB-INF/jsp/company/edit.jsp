<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Company" %>
<%
    Company company = (Company) request.getAttribute("company");
%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Редагування компанії</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5" style="max-width: 500px;">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4 class="mb-0">Редагування компанії</h4>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/companies/edit" method="post">
                <input type="hidden" name="id" value="<%= company.getId() %>">

                <div class="mb-3">
                    <label class="form-label">Назва компанії:</label>
                    <input type="text" name="name" class="form-control" value="<%= company.getName() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">URL (Ссылка):</label>
                    <input type="url" name="url" class="form-control" value="<%= company.getUrl() != null ? company.getUrl() : "" %>">
                </div>

                <div class="d-flex justify-content-between mt-4">
                    <a href="${pageContext.request.contextPath}/companies" class="btn btn-outline-secondary">Отмена</a>
                    <button type="submit" class="btn btn-warning">Зберегти зміни</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>