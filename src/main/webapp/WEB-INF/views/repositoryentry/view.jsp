<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <title><c:out value="${repositoryEntry.displayName}"/> - Sauce manager</title>
    <link href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet">
    <link href='<c:url value="/resources/jquery-ui/jquery-ui.min.css"/>' rel="stylesheet">
    <script src='<c:url value="/resources/jquery-2.1.4.min.js"/>'></script>
    <script src='<c:url value="/resources/jquery-ui/jquery-ui.min.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>'></script>
</head>

<body>

<div class="container">

    <div class="row">
        <div class="col-md-2">
            <a href="<c:url value="/repositoryentry/listcourses"/>"><span class="glyphicon glyphicon-menu-left"></span>К списку курсов</a>
        </div>
        <div class="col-md-9">
            <h1>
                <c:out value="${repositoryEntry.displayName}"/>
            </h1>
        </div>
        <div class="col-md-1">
            <a href="<c:url value="/logout"/>">Выйти</a>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">

            <div>
                <c:url value="/repositoryentry/view/${repositoryEntry.id}/datepick" var="datePickUrl"/>
                <form:form method="post" action="${datePickUrl}" modelAttribute="datePickForm" class="form-inline" role="form">
                    <div class="form-group">
                        <label>Период с </label>
                        <form:input path="dateFrom"/>
                    </div>

                    <div class="form-group">
                        <label>по</label>
                        <form:input path="dateTo"/>
                    </div>

                    <input type="submit" value="Выбрать"/>
                </form:form>
            </div>

            <div id="progress" class="progress">
                <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">Загружается</div>
            </div>

            <div id="mainTable"/>

        </div>
    </div>
</div>

<script language="JavaScript">
    $("document").ready(function(){
        $("#dateFrom").datepicker({dateFormat: "dd.mm.yy"});
        $("#dateTo").datepicker({dateFormat: "dd.mm.yy"});
        $("#mainTable").load('<c:url value="/repositoryentry/view/${repositoryEntry.id}/maintable"/>', function() {$("#progress").hide()});
    });

    $("#datePickForm").submit(function() {
        $("#progress").show();
        $("#mainTable").html("");
        $.post($(this).attr("action"), $(this).serialize(), function(data) {
            $("#mainTable").html(data);
            $("#progress").hide()
        });
        return false;
    });
</script>

</body>

</html>