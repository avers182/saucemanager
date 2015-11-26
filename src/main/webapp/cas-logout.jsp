<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

    <title>Saucemanager logout</title>

    <style type="text/css">
        .infobox {
            width: 40%;
            margin: auto;
            margin-top: 100px;
            padding: 30px;
            /*background-color: #ABCDEF;*/
            background: linear-gradient(#ABCDEF, #D1E3F5, #D1E3F5, #ABCDEF);
            text-align: center;
            border-radius: 7px;
            box-shadow: 5px 5px 10px grey;
        }

        .infotitle {
            padding-top: 15px;
            font-weight: bold;
            color: #453838;
        }

        .infomessage {
            padding: 10px;
            padding-top: 15px;
            padding-left: 50px;
            color: #453B3D;
            text-align: left;
        }
    </style>

</head>

<body>
<div>
    <div class="infobox">
        <div class="infotitle">
            СЕАНС ЗАВЕРШЁН
        </div>
        <div class="infomessage">
            <p>Рекомендуем пройти по <a href="<c:url value="/j_spring_cas_security_logout"/>">ссылке</a>, чтобы завершить сеанс в единой службе авторизации.</p>
            <p>Вернуться <a href="<c:url value="/"/>">на главную</a>.</p>
        </div>
    </div>
</div>
</body>

</html>
