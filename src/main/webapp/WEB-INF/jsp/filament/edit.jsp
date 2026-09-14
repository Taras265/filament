<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Filament" %>
<%@ page import="model.Company" %>
<%@ page import="model.FilamentType" %>
<%@ page import="java.util.List" %>
<%
    Filament filament = (Filament) request.getAttribute("filament");
%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Редагування філаменту</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5" style="max-width: 600px;">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4 class="mb-0">Редагування філаменту</h4>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/edit" method="post">
                <input type="hidden" name="id" value="<%= filament.getId() %>">

                <div class="mb-3">
                    <label class="form-label">Цвет:</label>
                    <input type="text" name="color" class="form-control" value="<%= filament.getColor() %>" required>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Тип філаменту:</label>
                        <select name="filamentType" class="form-select" required>
                            <% for (FilamentType type : FilamentType.values()) {
                                boolean selected = filament.getFilamentType() == type;
                            %>
                            <option value="<%= type.name() %>" <%= selected ? "selected" : "" %>><%= type.name() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Компанія:</label>
                        <select name="companyId" class="form-select" required>
                            <%
                                List<Company> companies = (List<Company>) request.getAttribute("companies");
                                if (companies != null) {
                                    for (Company c : companies) {
                                        boolean selected = filament.getCompany() != null && c.getId().equals(filament.getCompany().getId());
                            %>
                            <option value="<%= c.getId() %>" <%= selected ? "selected" : "" %>><%= c.getName() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Поточна вага (г):</label>
                        <input type="number" name="weight" class="form-control" value="<%= filament.getWeight() %>" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Макс. вага (г):</label>
                        <input type="number" name="maxWeight" class="form-control" value="<%= filament.getMaxWeight() %>" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Поточна довжина (м):</label>
                        <input type="number" name="length" class="form-control" value="<%= filament.getLength() %>" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Макс. довжина (м):</label>
                        <input type="number" name="maxLength" class="form-control" value="<%= filament.getMaxLength() %>" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">URL (Посилання):</label>
                    <input type="url" name="url" class="form-control" value="<%= filament.getUrl() != null ? filament.getUrl() : "" %>">
                </div>

                <div class="d-flex justify-content-between mt-4">
                    <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">Скасувати</a>
                    <button type="submit" class="btn btn-warning">Зберегти</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>