<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Система скорочення посилань</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding-top: 50px; }
    </style>
</head>
<body>
<div class="container">
    <h1 class="mb-4">Система скорочення посилань</h1>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>

    <div class="card mb-4">
        <div class="card-header">Створити коротке посилання</div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/create" method="post">
                <div class="mb-3">
                    <label for="originalUrl" class="form-label">Оригінальний URL:</label>
                    <input type="url" class="form-control" id="originalUrl" name="originalUrl"
                           placeholder="https://example.com/your-long-url" required>
                </div>
                <button type="submit" class="btn btn-primary">Створити</button>
            </form>
        </div>
    </div>

    <c:if test="${not empty shortUrl}">
        <div class="card mb-4">
            <div class="card-header">Результат</div>
            <div class="card-body">
                <p>Оригінальний URL: <a href="${urlMapping.originalUrl}" target="_blank">${urlMapping.originalUrl}</a></p>
                <p>Короткий URL: <a href="${shortUrl}" target="_blank">${shortUrl}</a></p>
                <button class="btn btn-sm btn-secondary" onclick="copyToClipboard('${shortUrl}')">Копіювати</button>
            </div>
        </div>
    </c:if>

    <div class="card">
        <div class="card-header">Останні скорочені посилання</div>
        <div class="card-body">
            <jsp:include page="/list" />
        </div>
    </div>
</div>

<script>
    function copyToClipboard(text) {
        navigator.clipboard.writeText(text).then(function() {
            alert('URL скопійовано в буфер обміну!');
        });
    }
</script>
</body>
</html>