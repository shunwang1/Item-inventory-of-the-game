<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Character Currencies</title>
</head>
<body>
<h1>Character Currencies</h1>
<table border="1">
    <tr>
        <th>Currency Name</th>
        <th>Amount Owned</th>
        <th>Weekly Maximum</th>
    </tr>
    <tr>
        <td>${characterCurrency.getCurrency_id()}</td>
        <td>${characterCurrency.getAmount()}</td>
        <td>${characterCurrency.getAmount_weekly()}</td>
    </tr>
</table>
</body>
</html>