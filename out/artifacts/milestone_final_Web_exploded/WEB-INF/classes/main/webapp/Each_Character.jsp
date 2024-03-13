<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Character Information</title>
</head>
<body>
	<h1>Character Information</h1>
        <table border="1">
            <tr>
            	<th>Currencies</th>
                <th>Job</th>
                <th>Attribute</th>
            </tr>
            <c:forEach items="${playercharacter}" var="character" >
                <tr>
                    <td><a href="currencies?characterId=${character.getCharacterId()}">${character.getUserName()}</a></td>
                	<td><a href="job?characterId=${character.getCharacterId()}">${character.getJobId()}</a></td>
                	<td><a href="attributes?characterId=${character.getCharacterId()}">${character.getAttributeId()}</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
