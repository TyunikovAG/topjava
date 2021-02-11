<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">

<head>
    <title>Edit meals</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Add meal</h2>
<form action="meals" method="post">
    <input type="hidden" name="action" value="add">
    <label for="dt-input">DateTime: </label>
    <input type="datetime-local" id="dt-input" name="datetime" value="${time}">
    <br><br>
    <label for="description-input">Description: </label>
    <input type="text" id="description-input" size="50" name="description">
    <br><br>
    <label for="calories-input">Calories: </label>
    <input type="number" min="0" id="calories-input" size="50" name="calories">
    <br><br>
    <p>______________________________
        <input type="submit" value="Save">
        <input type="button" formaction="/parent.html" value="Cancel">
    </p>
</form>

</body>
</html>