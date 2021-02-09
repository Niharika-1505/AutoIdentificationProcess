<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action ="/AIP/LoginServlet" method="post">
<table>
<tr><td>UserID :</td><td><input type="text" name="u_id"></td></tr>
<tr><td>Password :</td><td><input type="password" name="u_pwd"></td></tr>
<tr><td>Captcha:</td><td><input type="text" name="captcha"></td></tr>
<tr><td></td><td align="right"><img src="/AIP/CaptchaServlet"></td></tr>
<tr><td></td><td><input type="submit" value="login"></td></tr>
<tr><td colspan=2 align="center"><a href="/AIP/JSP_Pages/forgotPassword.jsp"><tt>Forgot password....?</tt></a></td></tr>
</table>
${msg}
</form>
</body>
</html>