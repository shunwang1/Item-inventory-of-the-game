<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update a Player</title>
</head>
<body>
    <h1>Update Players</h1>
    <form action="playerupdate" method="post">
        <p>
            <label for="username">UserName</label>
            <input id="username" name="username" value="${fn:escapeXml(param.username)}">
        </p>
        <p>
            <label for="playername">new playerName</label>
            <input id="playername" name="playername" value="${fn:escapeXml(param.playername)}">
        </p>
        <p>
            <label for="playeremail">New email</label>
            <input id="playeremail" name="playeremail" value="${fn:escapeXml(param.playeremail)}">
        </p>
        <p>
            <label for="playeradress">New address</label>
            <input id="playeradress" name="playeradress" value="${fn:escapeXml(param.playeradress)}">
        </p>
        <p>
            <label for="playerphone">New phone</label>
            <input id="playerphone" name="playerphone" value="${fn:escapeXml(param.playerphone)}">
        </p>
        <p>
            <label for="playerstatus">Changing status</label>
            <input type="checkbox" id="playerstatus" name="playerstatus" value="true"${param.playerstatus == 'true' ? 'checked' : ''}>
            <span>Active</span>
        </p>
        <p>
            <input type="submit">
        </p>
        <a  href="findplayers" class="button">back to home page</a>
    </form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>