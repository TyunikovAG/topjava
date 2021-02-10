<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">
<head>
    <title>Meals</title>
    <style type="text/css">
        table {
            width: 60%;
            border-collapse: collapse;
        }

        tr.isExcess {
            color: red;
        }

        tr.noExcess {
            color: green;
        }

        th {
            padding: 10px 0px 10px 0px;
            text-align: center;
            font-weight: bold;
            border: transparent;
        }

        td {
            padding: 10px 10px 10px 10px;
            border: solid thin black;
        }
    </style>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <c:forEach var="mealTo" items="${mealTos}">
        <tr class="${mealTo.isExcess() ? "isExcess" : "noExcess"}">
            <td><c:out value="${mealTo.getDateTime().toString().replace(\"T\", \" \")}"/></td>
            <td><c:out value="${mealTo.getDescription()}"/></td>
            <td><c:out value="${mealTo.getCalories()}"/></td>
            <td><a href="edit-meal?id=${mealTo.getId()}">EDIT</a></td>
            <td><a href="delete-meal?id=${mealTo.getId()}">DELETE</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>