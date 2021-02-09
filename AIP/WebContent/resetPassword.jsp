<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="resetPassword.js"></script>

</head>
<body>
<form action="/AIP/ResetServlet" method="post" name="resetpassword" onsubmit="return resetPassword()">
<table>
<tr><td>UserID</td><td><input type="text" name="userid"></td></tr>
<tr><td>Temporary Password:</td><td><input type="text" name="password"></td></tr>
<tr><td>New password:</td><td><input type="password" name="password1"></td></tr>
<tr><td>confirm password:</td><td><input type="password" name="password2"></td></tr>
<tr><td><input type="submit" value="Reset"></td><td><input type="reset" value="Refresh"></td></tr>
</table>
<h1>${msg}</h1>
</form>
</body>
</html>