<%@page import="java.sql.*,model.*,jbean.*"%>
<%ResultSet rs =null;
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
type="text/javascript" language="javascript">    
javascript:window.history.forward(1);
</script>

<%  
response.setHeader("Pragma","no-cache");  
response.setHeader("Cache-Control","no-store");  
response.setHeader("Cache-Control","cache");  
response.setDateHeader("Expires",0);  
%>
</head>
<%
if(session.getAttribute("ID")!=null)
{
 %>
<body>
<h3 align="right"><a href="/AIP/JSP_Pages/usr_update.jsp">Update Profile</a></h3>
<h3 align="right"><a href="/AIP/JSP_Pages/logout.jsp">Logout</a></h3>
Hello ${userid}
<%
 AIPJB ajb=new AIPJB();
String email=(String)session.getAttribute("Email");
 ajb.setA_email(email);
 rs=DBTransactions.selectDefault_Details(ajb,config);
 if(rs.next())  
	{
	out.print("!!! "+rs.getString("comments"));
	}
 }
 else
 {
 String message="Please Login";
 request.setAttribute("msg", message);
 getServletContext().getRequestDispatcher("/login.jsp").include(request,response);
 }
  %>
</body>
</html>