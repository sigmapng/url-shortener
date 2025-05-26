<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table">
    <thead>
        <tr>
            <th>Короткий код</th>
            <th>Оригінальний URL</th>
            <th>Дата створення</th>
            <th>Переходи</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${urlMappings}" var="url">
            <tr>
                <td>${url.shortCode}</td>
                <td><a href="${url.originalUrl}" target="_blank">${url.originalUrl}</a></td>
                <td>${url.createdAt}</td>
                <td>${url.visitsCount}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>