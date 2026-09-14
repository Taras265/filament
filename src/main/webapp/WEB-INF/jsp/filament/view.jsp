<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Filament" %>
<%
    Filament filament = (Filament) request.getAttribute("filament");
%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Інформація о філаменті</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5" style="max-width: 600px;">
    <% if (filament != null) { %>
    <div class="card shadow">
        <div class="card-header bg-info text-white d-flex justify-content-between align-items-center">
            <h4 class="mb-0">Деталі філаменту</h4>
            <span class="badge bg-light text-dark"><%= filament.getFilamentType() %></span>
        </div>
        <div class="card-body">
            <ul class="list-group list-group-flush mb-3">
                <li class="list-group-item"><strong>ID:</strong> <code><%= filament.getId() %></code></li>
                <li class="list-group-item"><strong>Колір:</strong> <%= filament.getColor() %></li>
                <li class="list-group-item"><strong>Компанія:</strong> <%= filament.getCompany() != null ? filament.getCompany().getName() : "Не указана" %></li>
                <li class="list-group-item"><strong>Остаток ваги:</strong> <%= filament.getWeight() %> из <%= filament.getMaxWeight() %> г</li>
                <li class="list-group-item"><strong>Остаток довжини:</strong> <%= filament.getLength() %> из <%= filament.getMaxLength() %> м</li>
                <li class="list-group-item"><strong>Посилання:</strong>
                    <% if (filament.getUrl() != null && !filament.getUrl().isBlank()) { %>
                    <a href="<%= filament.getUrl() %>" target="_blank" class="text-decoration-none"><%= filament.getUrl() %></a>
                    <% } else { %>
                    <span class="text-muted">Не вказана</span>
                    <% } %>
                </li>
            </ul>

            <div class="d-flex justify-content-between">
                <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">Назад до списку</a>
                <a href="${pageContext.request.contextPath}/edit?id=<%= filament.getId() %>" class="btn btn-warning">Редагувати</a>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="alert alert-danger" role="alert">
        Филамент не найден. <a href="${pageContext.request.contextPath}/" class="alert-link">Вернуться на главную</a>
    </div>
    <% } %>
</div>
</body>
</html>