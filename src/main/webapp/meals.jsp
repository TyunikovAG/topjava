<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    ArrayList<MealTo> mealToList = (ArrayList<MealTo>) request.getAttribute("mealTos");
%>

<html lang="ru">
<head>
    <title>Meals</title>
    <style type="text/css">
        table{
            width: 60%;
            border-collapse: collapse;
        }
        th {
            padding: 10px 0px 10px 0px;
            text-align: center;
            font-weight: bold;
            border: solid thin black;
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
<ol>
    <%
        for (MealTo mealTo : mealToList) {
            out.println("<li" +
                    (mealTo.isExcess() ? " style=\"background-color: lightpink;\"" : "") + ">" +
                    mealTo.getDateTime() + " - " +
                    mealTo.getDescription() + " - " +
                    mealTo.getCalories() +
                    "</li>");
        }
    %>
</ol>
<table border="1">
    <caption>Список еды</caption>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <%
        for (MealTo mealTo : mealToList) {
            out.println("<tr" +
                    (mealTo.isExcess() ? " style=\"background-color: lightpink;\"" : "") + ">" +
                    mealTo.getDateTime() + " - " +
                    mealTo.getDescription() + " - " +
                    mealTo.getCalories() +
                    "</li>");
        }
    %>
</table>
</body>
</html>