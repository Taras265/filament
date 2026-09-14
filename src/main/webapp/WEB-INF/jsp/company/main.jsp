<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Company" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Список компаній</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<!-- Навигационная панель -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Абоба</a>
        <div class="navbar-nav">
            <a class="nav-link" href="${pageContext.request.contextPath}/">Филаменты</a>
            <a class="nav-link active" href="${pageContext.request.contextPath}/companies">Компании</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Список компаній</h2>
        <a href="${pageContext.request.contextPath}/companies/add" class="btn btn-primary">Додати компанію</a>
    </div>

    <div class="card shadow-sm">
        <div class="card-body p-0">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Назва</th>
                    <th>Сайт</th>
                    <th class="text-end">Дії</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Company> companies = (List<Company>) request.getAttribute("companies");
                    if (companies != null && !companies.isEmpty()) {
                        for (Company c : companies) {
                %>
                <tr>
                    <td><code><%= c.getId() %></code></td>
                    <td class="fw-bold"><%= c.getName() %></td>
                    <td>
                        <% if (c.getUrl() != null && !c.getUrl().isBlank()) { %>
                        <a href="<%= c.getUrl() %>" target="_blank" class="text-decoration-none"><%= c.getUrl() %></a>
                        <% } else { %>
                        <span class="text-muted">-</span>
                        <% } %>
                    </td>
                    <td class="text-end">
                        <a href="${pageContext.request.contextPath}/companies/view?id=<%= c.getId() %>" class="btn btn-sm btn-outline-info">Дивитись</a>
                        <a href="${pageContext.request.contextPath}/companies/edit?id=<%= c.getId() %>" class="btn btn-sm btn-outline-warning">Змінити</a>
                        <form action="${pageContext.request.contextPath}/companies/delete" method="post" class="d-inline">
                            <input type="hidden" name="id" value="<%= c.getId() %>">
                            <button type="submit" class="btn btn-sm btn-outline-danger" onclick="return confirm('Удалить компанию?')">Видалити</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="4" class="text-center py-4 text-muted">Компанії не найдені</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>