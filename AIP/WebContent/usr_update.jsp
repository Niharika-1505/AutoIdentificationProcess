<%@page import="java.sql.*,model.*,jbean.*"%>
<%ResultSet rs =null;
String url="";
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="formValidation1.js"></script>
</head>
<%
if(session.getAttribute("ID")!=null)
{
	try
	{
	String userid=(String)session.getAttribute("ID");
	 AIPJB ajb=new AIPJB();
	 ajb.setA_id(userid);
	 String id=ajb.getA_id().substring(0,2);
		if(id.equals("BU")){
		rs=DBTransactions.selectUsers(ajb,config);
		}
		else{
			rs=DBTransactions.selectAdmin(ajb,config);
		}
	 
%>	
<form action="/AIP/Usr_UpdateServlet" method="post" onsubmit="return formValidation1()">

<center>
<table>
<%
while(rs.next())
{
 %>
 <tr><td>User Name: </td><td><input type="text" name="u_id"  value="<%=rs.getString(1) %>" disabled="disabled"></td></tr>
<tr><td>User Name: </td><td><input type="text" name="u_name"  value="<%=rs.getString(2) %>" disabled="disabled"></td></tr>
<tr><td>Password: </td><td><input type="password" name="u_pwd"  value="<%=rs.getString(3) %>"></td></tr>
<tr><td>Confirm Password: </td><td><input type="password" name="u_pwd1"  value="<%=rs.getString(3) %>"></td></tr>
<tr><td>Date of Birth: </td><td><input type="text" name="u_dob"  placeholder="dd/mm/yyyy" value="<%=rs.getString(4) %>" disabled="disabled"></td></tr>
<tr><td>Email Address: </td><td><input type="text" name="u_email"  value="<%=rs.getString(5) %>" ></td></tr>
<tr><td>Contact Number: </td><td><input type="text" name="u_contact"  value="<%=rs.getString(6) %>"></td></tr>
<tr><td>Address: </td><td><textarea name="u_address"  cols="16" rows="3"><%=rs.getString(7)%></textarea></td></tr>

<tr>
	<td><input type="submit" value="UPDATE"></td>
	<td><input type="reset" value="REFRESH"></td>
	</tr>

</table>
</center>
</form>
<%}
}
catch(Exception e)
{
e.printStackTrace();
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