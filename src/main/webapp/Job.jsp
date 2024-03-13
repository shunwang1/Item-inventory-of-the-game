<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Character Job Information</title>
</head>
<body>
	<h1>Character Job Information</h1>
        <table border="1">
            <tr>
            	<th>Category</th>
            	<th>Experience Earned</th>
            </tr>
            <td>${characterJobs.getJob_id()}</td>
            <td>${characterJobs.getCurrent_level()}</td>

       </table>
</body>
</html>
