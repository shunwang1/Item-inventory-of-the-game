<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findplayers" method="post">
		<h1>Search for a Player by PlayerName</h1>
		<p>
			<label for="playername">PlayerName</label>
			<input id="playername" name="playername" value="${fn:escapeXml(param.playername)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="playerCreate"><a href="UserCreate">Create Player</a></div>
	<br/>
	<h1>Matching Player</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>PlayerName</th>
                <th>email</th>
                <th>address</th>
                <th>phone</th>
                <th>isActive</th>
                <th>Character</th>
                <th>Delete Player</th>
                <th>Update Player</th>
            </tr>
            <c:forEach items="${players}" var="player" >
                <tr>
                    <td><c:out value="${player.getUserName()}" /></td>
                    <td><c:out value="${player.getPlayerName()}" /></td>
                    <td><c:out value="${player.getEmail()}" /></td>
                    <td><c:out value="${player.getAddress()}" /></td>
                    <td><c:out value="${player.getPhone()}" /></td>
                    <td><c:out value="${player.isActive()}" /></td>
                    <td><a href="playercharacter?username=<c:out value="${player.getUserName()}"/>">Character</a></td>
                    <td><a href="playerdelete?username=<c:out value="${player.getUserName()}"/>">Delete</a></td>
                    <td><a href="playerupdate?username=<c:out value="${player.getUserName()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>