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
<a href="meals?action=create">Add meal</a>
<table>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <c:forEach var="mealTo" items="${mealTos}">
        <tr class="${mealTo.isExcess() ? "isExcess" : "noExcess"}">
            <td><c:out value="${mealTo.getDateTime().toString().replace(\"T\", \" \")}"/></td>
            <td>${mealTo.getDescription()}</td>
            <td>${mealTo.getCalories()}</td>
            <td><a href="meals?action=edit&id=${mealTo.getId()}">EDIT</a></td>
            <td><a href="meals?action=delete&id=${mealTo.getId()}">DELETE</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>