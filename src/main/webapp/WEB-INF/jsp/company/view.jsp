<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Company" %>
<%
    Company company = (Company) request.getAttribute("company");
%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Інформація про компанію</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5" style="max-width: 500px;">
    <% if (company != null) { %>
    <div class="card shadow">
        <div class="card-header bg-info text-white">
            <h4 class="mb-0">Деталі компанії</h4>
        </div>
        <div class="card-body">
            <ul class="list-group list-group-flush mb-3">
                <li class="list-group-item"><strong>ID:</strong> <code><%= company.getId() %></code></li>
                <li class="list-group-item"><strong>Назва:</strong> <%= company.getName() %></li>
                <li class="list-group-item"><strong>Посилання:</strong>
                    <% if (company.getUrl() != null && !company.getUrl().isBlank()) { %>
                    <a href="<%= company.getUrl() %>" target="_blank" class="text-decoration-none"><%= company.getUrl() %></a>
                    <% } else { %>
                    <span class="text-muted">Не вказано</span>
                    <% } %>
                </li>
            </ul>

            <div class="d-flex justify-content-between">
                <a href="${pageContext.request.contextPath}/companies" class="btn btn-outline-secondary">Назад до списку</a>
                <a href="${pageContext.request.contextPath}/companies/edit?id=<%= company.getId() %>" class="btn btn-warning">Редагування</a>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="alert alert-danger" role="alert">
        Компанія не знайдена. <a href="${pageContext.request.contextPath}/companies" class="alert-link">Повернутися до списку компаній</a>
    </div>
    <% } %>
</div>
</body>
</html>