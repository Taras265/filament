<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="model.Filament" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Список філаментів</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<!-- Навигационная панель -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Абоба</a>
        <div class="navbar-nav">
            <a class="nav-link active" href="${pageContext.request.contextPath}/">Філаменти</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/companies">Компанії</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Список філаментів</h2>
        <a href="${pageContext.request.contextPath}/add" class="btn btn-primary">Додати філамент</a>
    </div>

    <div class="card shadow-sm mb-4">
        <div class="card-header bg-white fw-bold">
            Фільтрація
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/" method="get">
                <div class="row g-3">
                    <div class="col-md-3">
                        <label class="form-label small text-muted">Колір</label>
                        <input type="text" name="color" class="form-control form-control-sm"
                               value="${param.color}">
                    </div>

                    <div class="col-md-3">
                        <label class="form-label small text-muted">Тип філаменту</label>
                        <select name="filamentType" class="form-select form-select-sm">
                            <option value="">Усі типи</option>
                            <c:forEach var="type" items="${filamentTypes}">
                                <option value="${type}" ${param.filamentType == type ? 'selected' : ''}>${type}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-3">
                        <label class="form-label small text-muted">Компанія</label>
                        <select name="companyId" class="form-select form-select-sm">
                            <option value="">Усі компанії</option>
                            <%-- Список компаній передається з сервлета --%>
                            <c:forEach var="comp" items="${companies}">
                                <option value="${comp.id}" ${param.companyId == comp.id ? 'selected' : ''}>${comp.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Вага (Від - До) -->
                    <div class="col-md-3">
                        <label class="form-label small text-muted">Залишок ваги (г)</label>
                        <div class="input-group input-group-sm">
                            <input type="number" name="minWeight" class="form-control" placeholder="Від" value="${param.minWeight}">
                            <input type="number" name="maxWeight" class="form-control" placeholder="До" value="${param.maxWeight}">
                        </div>
                    </div>

                    <!-- Довжина (Від - До) -->
                    <div class="col-md-3">
                        <label class="form-label small text-muted">Залишок довжини (м)</label>
                        <div class="input-group input-group-sm">
                            <input type="number" name="minLength" class="form-control" placeholder="Від" value="${param.minLength}">
                            <input type="number" name="maxLength" class="form-control" placeholder="До" value="${param.maxLength}">
                        </div>
                    </div>

                    <!-- Кнопки керування -->
                    <div class="col-md-9 d-flex align-items-end justify-content-end gap-2">
                        <a href="${pageContext.request.contextPath}/" class="btn btn-sm btn-outline-secondary">Скинути</a>
                        <button type="submit" class="btn btn-sm btn-primary">Шукати</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-body p-0">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Колір</th>
                    <th>Тип</th>
                    <th>Компанія</th>
                    <th>Остаток ваги (г)</th>
                    <th>Остаток довжини (м)</th>
                    <th class="text-end">Дії</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Filament> filaments = (List<Filament>) request.getAttribute("filaments");
                    if (filaments != null && !filaments.isEmpty()) {
                        for (Filament f : filaments) {
                %>
                <tr>
                    <td><code><%= f.getId() %></code></td>
                    <td><span class="badge bg-secondary"><%= f.getColor() %></span></td>
                    <td><%= f.getFilamentType() %></td>
                    <td><%= f.getCompany() != null ? f.getCompany().getName() : "-" %></td>
                    <td><%= f.getWeight() %> / <%= f.getMaxWeight() %></td>
                    <td><%= f.getLength() %> / <%= f.getMaxLength() %></td>
                    <td class="text-end">
                        <a href="${pageContext.request.contextPath}/view?id=<%= f.getId() %>" class="btn btn-sm btn-outline-info">Дивитись</a>
                        <a href="${pageContext.request.contextPath}/edit?id=<%= f.getId() %>" class="btn btn-sm btn-outline-warning">Змінити</a>
                        <form action="${pageContext.request.contextPath}/delete" method="post" class="d-inline">
                            <input type="hidden" name="id" value="<%= f.getId() %>">
                            <button type="submit" class="btn btn-sm btn-outline-danger" onclick="return confirm('Видалити?')">Видалити</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="7" class="text-center py-4 text-muted">Філаменти не знайдені</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>