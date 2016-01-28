<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <title><c:out value="${repositoryEntry.displayName}"/> - LMS course manager</title>
    <link href='<c:url value="/static/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet">
    <link href='<c:url value="/static/jquery-ui/themes/smoothness/jquery-ui.min.css"/>' rel="stylesheet">
    <script src='<c:url value="/static/jquery-2.1.4.min.js"/>'></script>
    <script src='<c:url value="/static/jquery-ui/jquery-ui.min.js"/>'></script>
    <script src='<c:url value="/static/bootstrap/js/bootstrap.min.js"/>'></script>
    <script src='<c:url value="/static/angular.min.js"/>'></script>
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

    <div class="row" ng-app="courseManager" ng-controller="courseProgressController" style="margin-top: 20px;">
        <div class="col-md-12">

            <div>
                <form class="form-inline" role="form">
                    <div class="form-group">
                        <label for="dateFrom">Период с </label>
                        <input type="text" ng-model="dateFrom" id="dateFrom"/>
                    </div>

                    <div class="form-group">
                        <label for="dateTo">по</label>
                        <input type="text" ng-model="dateTo" id="dateTo"/>
                    </div>
                </form>
            </div>

            <div>
                Всего участников: {{(userProperties | dateBetween: 5: dateFrom: dateTo).length}}
            </div>

            <div>
                <table class="table table-condensed table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th ng-repeat="t in tests">{{t.displayName}}</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr ng-repeat="up in userProperties | dateBetween: 5: dateFrom: dateTo | orderBy: '-up[5]'">
                        <td nowrap>
                            <div><b>{{up[2]}} {{up[3]}}</b></div>
                            <div><b><small>{{up[1]}}</small></b></div>
                            <div><small>Назначен {{up[5] | date: 'dd.MM.yyyy'}}</small></div>
                        </td>
                        <td nowrap ng-repeat="t in tests">
                            <div ng-repeat="q in qtiResultSets | isAfter: 'creationDate': dateFrom | filter:{identity: {id: up[0]}, repositoryEntry: {id: t.id}}" class="{{q.isPassed? 'text-success': 'text-danger'}}">
                                <span style="font-size: x-small; font-weight: bold;">
                                    {{q.creationDate | date: 'dd.MM.yyyy HH:mm'}}
                                </span>
                                {{q.score}}
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>

<script language="JavaScript">
    $("document").ready(function(){
        $("#dateFrom").datepicker({dateFormat: "dd.mm.yy"});
        $("#dateTo").datepicker({dateFormat: "dd.mm.yy"});
    });

    angular.module('courseManager', [])
            .controller('courseProgressController', function($scope, $http, $filter) {
                var now = new Date();
                $scope.dateTo = $filter('date')(now, 'dd.MM.yyyy');
                $scope.dateFrom = $filter('date')(now.setMonth(now.getMonth() - 1), 'dd.MM.yyyy');

                $http.get('<c:url value="/repositoryentry/${repositoryEntry.id}/userproperties"/>').then(function(response) {$scope.userProperties = response.data;});
                $http.get('<c:url value="/repositoryentry/${repositoryEntry.id}/tests"/>').then(function(response) {$scope.tests = response.data;});
                $http.get('<c:url value="/repositoryentry/${repositoryEntry.id}/qtiresultsets"/>').then(function(response) {$scope.qtiResultSets = response.data;});
            })
            .filter('isAfter', function() {return function(items, itemIndex, dtFrom) {
                var d = dtFrom.split('.');
                var dtf = new Date(Date.UTC(d[2], d[1] - 1, d[0]));
                return items.filter(function(item, i, arr) {
                    return item[itemIndex] >= dtf.getTime();
                });
            }})
            .filter('dateBetween', function() {return function(items, itemIndex, dtFrom, dtTo) {
                var df = dtFrom.split('.');
                var dt = dtTo.split('.');
                var dtf = new Date(Date.UTC(df[2], df[1] - 1, df[0]));
                var dtt = new Date(Date.UTC(dt[2], dt[1] - 1, dt[0]));
                return items.filter(function(item, i, arr) {
                    return item[itemIndex] >= dtf.getTime() && item[itemIndex] < dtt.getTime();
                });
            }});

</script>

</body>

</html>