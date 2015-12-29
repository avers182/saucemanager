<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>LMS course manager</title>
    <link href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-11">
            <h3>Список курсов:</h3>
        </div>
        <div class="col-md-1">
            <a href="<c:url value="/logout"/>">Выйти</a>
        </div>
    </div>

    <div class="row">
        <div>
            <ul>
                <c:forEach var="repositoryEntry" items="${repositoryEntries}">
                    <li>
                        <a href="<c:url value="view/${repositoryEntry.id}"/> ">
                            <c:out value="${repositoryEntry.displayName}"/>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>

</html>