<%@page import="java.sql.*,model.*,jbean.*"%>
<%ResultSet rs =null;
ResultSetMetaData meta=null;
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
  <script type="text/javascript" src="AIP/JS/test.js"></script>
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
<form action="/AIP/Insert_Default_Details" method="post">
 <%
 AIPJB ajb=new AIPJB();
 String status=(String)session.getAttribute("status");
 rs=DBTransactions.selectTemp(status,config);
meta=rs.getMetaData();
if(rs.next()){%>
  <table border="1">
  <thead><tr>
  <th><%=meta.getColumnName(1)%></th>
  <th><%=meta.getColumnName(3)%></th>
  <th><%=meta.getColumnName(4)%></th>
  <th><%=meta.getColumnName(5)%></th>
  <th><%=meta.getColumnName(6)%></th>
  <th><%=meta.getColumnName(7)%></th>
  <th><%=meta.getColumnName(8)%></th>
  <th><%=meta.getColumnName(9)%></th>
  </tr>
  </thead>
 <%
 rs.previous();
  while(rs.next()){%>
	  <tr>
	  <td><%=rs.getString(1)%></td>
	    <td><%=rs.getString(3)%></td>
	     <td><%=rs.getInt(4)%></td>
	     <td><%=rs.getInt(5)%></td>
	     <td><%=rs.getLong(6)%></td>
	     <td><%=rs.getLong(7)%></td>
	     <td><%=rs.getInt(8)%></td>
	  	<td><TextArea><%=rs.getString(9)%></TextArea></td>
	  	<td><a href="/AIP/JSP_Pages/auto_Defaults_Edit.jsp?email=<%=rs.getString(2)%>">Edit</a></td>
	  </tr>
<%}%>
</table>
${updation}
<%
 }
else
	out.print("No data Available Currently Submit the changes made");%>
<%//getServletContext().getRequestDispatcher("/auto_defaults.jsp").include(request,response);
 }
 else
 {
 String message="Please Login";
 request.setAttribute("msg", message);
 getServletContext().getRequestDispatcher("/login.jsp").include(request,response);
 }
  %>
  <input type="submit" value="Default_Details">
  </form>
</body>
</html>