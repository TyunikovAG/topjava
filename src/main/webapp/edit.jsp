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
<form action="edit-meal">
    <label for="dt-input">DateTime: </label>
    <input type="datetime-local" id="dt-input">
    <br><br>
    <label for="description-input">Description: </label>
    <input type="text" id="description-input" size="50">
    <br><br>
    <label for="calory-input">Calories: </label>
    <input type="text" id="calory-input" size="50">
    <br><br>
    <p>______________________________
        <input type="submit" value="Save">
        <input type="button" formaction="/parent.html" value="Cancel">
    </p>
</form>

</body>
</html>