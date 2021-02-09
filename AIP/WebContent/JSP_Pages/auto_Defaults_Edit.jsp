<%@page import="java.sql.*,model.*,jbean.*"%>
<%ResultSet rs =null;
String url="";%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
if(session.getAttribute("ID")!=null)
{
	try
	{
	String email=request.getParameter("email");
	 AIPJB ajb=new AIPJB();
	 ajb.setA_email(email);
		rs=DBTransactions.selectDefault_Details(ajb,config);
	 
%>	
<form action="/AIP/Update_Auto_defaults" method="post" onsubmit="return formValidation1()">

<center>
<table>
<%
while(rs.next())
{
	session.setAttribute("borrower_mail",rs.getString(2));
 %>
 <tr><td>Default Status: </td><td><input type="text" name="default_Status"  value="<%=rs.getString(1) %>"></td></tr>
<tr><td>Borrower Name: </td><td><input type="text" name="borrower_name"  value="<%=rs.getString(3) %>" disabled="disabled"></td></tr>
<tr><td>Borrower Rating:</td><td><input type="text" name="borrower_rating"  value="<%=rs.getString(4) %>" disabled="disabled"></td></tr>
<tr><td>Accrual Status: </td><td><input type="text" name="accrual_status"  placeholder="dd/mm/yyyy" value="<%=rs.getString(5) %>" disabled="disabled"></td></tr>
<tr><td>Bank Number: </td><td><input type="text" name="bank_no"  value="<%=rs.getString(6) %>" disabled="disabled"></td></tr>
<tr><td>Account Number: </td><td><input type="text" name="account_no"  value="<%=rs.getString(7) %>" disabled="disabled"></td></tr>
<tr><td>Days Due Past: </td><td><input type="text" name="days_past_due"  value="<%=rs.getString(8) %>" disabled="disabled"></td></tr>
<tr><td>Comments: </td><td><textarea name="comments"  cols="16" rows="3"><%=rs.getString(9)%></textarea></td></tr>

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