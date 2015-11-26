<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>Всего участников: ${userProperties.size()}</div>

<div>
    <table class="table table-condensed table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>&nbsp;</th>
            <c:forEach items="${tests}" var="t">
                <th><c:out value="${t.displayName}"/></th>
            </c:forEach>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${userProperties}" var="i">
            <tr>
                <td nowrap>
                    <div><b><c:if test="${not empty i[2] or not empty i[3]}"><c:out value="${i[2]} ${i[3]}"/></c:if></b></div>
                    <div><b><small><c:out value="${i[1]}"/></small></b></div>
                        <%--<div><a href="mailto:${i[4]}"><c:out value="${i[4]}"/></a></div>--%>
                    <div><small>Назначен <fmt:formatDate value="${i[5]}" pattern="dd.MM.yyyy"/></small></div>
                </td>
                <c:forEach items="${tests}" var="t">
                    <td nowrap><c:forEach items="${qtiResultSets}" var="qti"><c:if test="${qti.identity.id eq i[0] and qti.repositoryEntry.id eq t.id}"><div class="${qti.isPassed? 'text-success': 'text-danger'}"><span style="font-size: x-small; font-weight: bold;"><fmt:formatDate value="${qti.creationDate}" pattern="dd.MM.yyyy HH:mm"/></span><c:out value=" ${qti.score}"/></div></c:if></c:forEach></td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>