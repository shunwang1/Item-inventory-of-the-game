<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Character Attributes</title>
</head>
<body>
	<h1>Character Attributes</h1>
        <table border="1">
            <tr>
            <th>Strength</th>
            <th>Intelligence</th>
            <th>Mind</th>
            <th>Dexterity</th>
        </tr>
        <tr>
            <td>${characterAttribute.getStrength()}</td>
            <td>${characterAttribute.getIntelligence()}</td>
            <td>${characterAttribute.getMind()}</td>
            <td>${characterAttribute.getDexterity()}</td>
        </tr>
    </table>
</body>
</html>
