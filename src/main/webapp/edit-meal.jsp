<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">

<head>
    <title>Edit meals</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form action="edit-meal" method="get">
    <input type="hidden" value="<c:out value = "${param.id}"/>" name="id" >
    <label for="dt-input">DateTime: </label>
    <input type="datetime-local" id="dt-input" value="<c:out value = "${param.datetime}"/>" name="datetime">
    <br><br>
    <label for="description-input">Description: </label>
    <input type="text" id="description-input" size="50" value="<c:out value = "${param.description}"/>" name="description">
    <br><br>
    <label for="calories-input">Calories: </label>
    <input type="text" id="calories-input" size="50" value="<c:out value = "${param.calories}"/>" name="calories">
    <br><br>
    <p>______________________________
        <input type="submit" value="Save">
        <input type="button" formaction="/parent.html" value="Cancel">
    </p>
</form>

</body>
</html>