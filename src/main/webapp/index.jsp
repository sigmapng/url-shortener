<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Статистика - Система скорочення посилань</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        body { padding-top: 70px; background-color: #f8f9fa; }
        .card { margin-bottom: 20px; box-shadow: 0 0.125rem 0.25rem rgba(0,0,0,0.075); }
        .navbar-brand { font-weight: bold; }
        .stat-card { transition: transform 0.3s; }
        .stat-card:hover { transform: translateY(-5px); }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            <i class="bi bi-link-45deg"></i> URL Shortener
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Головна</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/stats">Статистика</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2 class="mb-4"><i class="bi bi-bar-chart-line"></i> Статистика</h2>

    <div class="row mb-4">
        <div class="col-md-3">
            <div class="card stat-card bg-primary text-white">
                <div class="card-body text-center">
                    <h3>${totalUrls}</h3>
                    <p class="mb-0">Всього посилань</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card stat-card bg-success text-white">
                <div class="card-body text-center">
                    <h3>${totalClicks}</h3>
                    <p class="mb-0">Всього переходів</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card stat-card bg-info text-white">
                <div class="card-body text-center">
                    <h3><fmt:formatNumber value="${avgClicksPerUrl}" pattern="#.##"/></h3>
                    <p class="mb-0">Середнє переходів</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card stat-card bg-warning text-dark">
                <div class="card-body text-center">
                    <h3>${mostClicked.shortCode}</h3>
                    <p class="mb-0">Найпопулярніше</p>
                </div>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-header bg-primary text-white">
            <i class="bi bi-table"></i> Всі посилання
        </div>
        <div class="card-body p-0">
            <table class="table table-striped table-hover mb-0">
                <thead class="table-light">
                <tr>
                    <th>Короткий код</th>
                    <th>Оригінальний URL</th>
                    <th>Дата створення</th>
                    <th>Переходи</th>
                    <th>Дії</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${urls}" var="url">
                    <tr>
                        <td><span class="badge bg-primary">${url.shortCode}</span></td>
                        <td>
                            <div class="text-truncate" style="max-width: 250px;">
                                <a href="${url.originalUrl}" target="_blank">${url.originalUrl}</a>
                            </div>
                        </td>
                        <td>${url.createdAt}</td>
                        <td><span class="badge bg-secondary">${url.visitsCount}</span></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary"
                                    onclick="copyToClipboard('${pageContext.request.contextPath}/${url.shortCode}')">
                                <i class="bi bi-clipboard"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function copyToClipboard(text) {
        navigator.clipboard.writeText(text).then(function() {
            alert('URL скопійовано в буфер обміну!');
        });
    }
</script>
</body>
</html>