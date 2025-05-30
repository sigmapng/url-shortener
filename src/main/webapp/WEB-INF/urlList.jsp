<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th>Короткий код</th>
        <th>Оригінальний URL</th>
        <th>Дата створення</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${urls}" var="url">
        <tr>
            <td>${url.shortCode}</td>
            <td><a href="${url.originalUrl}" target="_blank">${url.originalUrl}</a></td>
            <td>${url.createdAt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

