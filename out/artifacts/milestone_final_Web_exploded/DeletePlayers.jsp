<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Delete a Player</title>
</head>
<body>
    <h1>Delete Players</h1>
    <form action="playerdelete" method="post">
        <p>
        	<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
            	<label for="username">UserName</label>
            	<input id="username" name="username" value="${fn:escapeXml(param.username)}">
            </div>
        </p>
        <p>
        	<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
            	<input type="submit">
            </span>
        </p>
        <a  href="findplayers" class="button">back to home page</a>
    </form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>